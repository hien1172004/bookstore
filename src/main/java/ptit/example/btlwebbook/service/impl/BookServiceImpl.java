package ptit.example.btlwebbook.service.impl;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ptit.example.btlwebbook.dto.request.AuthorDTO;
import ptit.example.btlwebbook.dto.request.BookDTO;
import ptit.example.btlwebbook.dto.request.GenreDTO;
import ptit.example.btlwebbook.dto.request.PublisherDTO;
import ptit.example.btlwebbook.dto.response.BookResponse;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.exception.ResourceNotFoundException;
import ptit.example.btlwebbook.mapper.BookMapper;
import ptit.example.btlwebbook.model.Author;
import ptit.example.btlwebbook.model.Book;
import ptit.example.btlwebbook.model.Genre;
import ptit.example.btlwebbook.model.Publisher;
import ptit.example.btlwebbook.repository.*;
import ptit.example.btlwebbook.service.BookService;
import ptit.example.btlwebbook.service.UploadImageFile;
import ptit.example.btlwebbook.utils.SlugUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final UploadImageFile uploadImageFile;
    private final GenreRespository genreRespository;
    private final OrderDetailRepository orderDetailRepository;
    private final PublisherRespository publisherRespository;
    private final AuthorRepository authorRepository;
//    private final UploadImageFile cloudinary;

    @Override
    @Transactional
    @CacheEvict(value = {"books", "booksBySlug", "booksByCode", "bookPages", "bookSearch"}, allEntries = true)
    public BookResponse saveBook(BookDTO bookDTO) throws IOException {
        // Chuyển đổi bookDTO thành đối tượng Book
        Book book = bookMapper.toBook(bookDTO);

        // Kiểm tra xem sách đã tồn tại hay chưa
        if (bookRepository.existsByName(book.getName())) {
            throw new IllegalArgumentException("book already existed");
        }

        // Tạo slug từ tên sách
        String slug = SlugUtils.generateSlug(book.getName());
        book.setSlug(slug);

        // Kiểm tra và gán Publisher nếu chưa có
        if (book.getPublisher() != null) {
            Publisher publisher = publisherRespository.findByName(book.getPublisher().getName());
            if (publisher != null) {
                book.setPublisher(publisher);
            } else {
                throw new IllegalArgumentException("Publisher does not exist");
            }
        }
        book.setPublisher(resolvePublisher(bookDTO.getPublisher()));
        book.setAuthors(resolveAuthors(bookDTO.getAuthors()));
        book.setGenres(resolveGenres(bookDTO.getGenres()));


        // Lưu đối tượng Book vào cơ sở dữ liệu
        Book savedBook = bookRepository.save(book);

        // Chuyển đổi đối tượng Book thành BookResponse và trả về
        return bookMapper.toBookResponse(savedBook);
    }

    @Override
    @Transactional
    @CachePut(value = "books", key = "#id")
    @CacheEvict(value = {"booksBySlug", "booksByCode", "bookPages", "bookSearch"}, allEntries = true)
    public BookResponse updateBook(BookDTO bookDTO, Long id) throws IOException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found book"));
        bookMapper.updateBook(book,bookDTO);
    if(!bookDTO.getName().isEmpty()){
        String slug = SlugUtils.generateSlug(bookDTO.getName());
        book.setSlug(slug);
    }
        // Cập nhật publicId nếu có
        if (bookDTO.getPublicId() != null && !bookDTO.getPublicId().isEmpty()) {
            book.setPublicId(bookDTO.getPublicId());
        }

        // Cập nhật imageUrl nếu có
        if (bookDTO.getImageUrl() != null && !bookDTO.getImageUrl().isEmpty()) {
            book.setImageUrl(bookDTO.getImageUrl());
        }

        book.setPublisher(resolvePublisher(bookDTO.getPublisher()));
        book.setAuthors(resolveAuthors(bookDTO.getAuthors()));
        book.setGenres(resolveGenres(bookDTO.getGenres()));
        // Lưu đối tượng Book vào cơ sở dữ liệu
        Book savedBook = bookRepository.save(book);
        return bookMapper.toBookResponse(savedBook);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"books", "booksBySlug", "booksByCode", "bookPages", "bookSearch"}, allEntries = true)
    public void deleteBook(Long id) throws IOException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found book"));
        boolean isOrdered = orderDetailRepository.existsByBookId(id);
        if (isOrdered) {
            throw new IllegalStateException("Không thể xóa sách đã được mua");
        }
        String publicId = book.getPublicId();
        if(publicId != null && !publicId.isEmpty()){
            uploadImageFile.deleteImage(publicId);
        }

        bookRepository.delete(book);
        log.info("Đã xóa sách thành công với id: {}", id);
    }

    @Override
    @Cacheable(value = "books", key = "#id")
    public BookResponse getDetailBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found book"));
        return bookMapper.toBookResponse(book);
    }

    @Override
    @Cacheable(value = "booksBySlug", key = "#slug")
    public BookResponse getBySlug(String slug) {
      Book book = bookRepository.findBySlug(slug);
      return bookMapper.toBookResponse(book);
    }

    @Override
    @Cacheable(value = "bookPages", key = "#pageNo + '-' + #pageSize + '-' + #genresIds + '-' + #sortBy")
    public PageResponse<?> getAll(int pageNo, int pageSize, List<Long> genresIds, String sortBy) {
        int page = 0;
        if (pageNo > 0){
            page = pageNo -1;
        }
        List<Sort.Order> sorts = new ArrayList<Sort.Order>();
        if(StringUtils.hasLength(sortBy)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sortBy);
            if(matcher.find()) {
                if(matcher.group(3).equalsIgnoreCase("asc")){
                    sorts.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                }
                else if(matcher.group(3).equalsIgnoreCase("desc")){
                    sorts.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                }
            }
        }
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sorts));

        Page<Book> bookPage;

        // Lọc theo danh sách genreIds nếu có
        if (genresIds != null && !genresIds.isEmpty()) {
            bookPage = bookRepository.findByGenresIn(genresIds, pageable);
        } else {
            bookPage = bookRepository.findAll(pageable);
        }

        List<BookResponse> bookResponses = bookPage.stream()
                .map(bookMapper::toBookResponse)
                .toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(bookPage.getTotalPages())
                .items(bookResponses)
                .build();
    }

    @Override
    @Cacheable(value = "bookSearch", key = "#pageNo + '-' + #pageSize + '-' + #key")
    public PageResponse<?> searchBook(int pageNo, int pageSize, String key) {
        int page = 0;
        if(pageNo > 0){
            page = pageNo -1;
        }
        Page<Book> bookPage = null;
        Pageable pageable = PageRequest.of(page, pageSize,Sort.by(Sort.Direction.DESC, "createdAt"));
        if(!key.isEmpty()){
             bookPage = bookRepository.searchBooksByAuthorOrName(key, pageable);
        }
        else {
            bookPage = bookRepository.findAll(pageable);
        }
        assert bookPage != null;
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(bookPage.getTotalPages())
                .items(bookPage.stream().map(bookMapper::toBookResponse).toList())
                .build();
    }
    @Override
    @Cacheable(value = "booksByCode", key = "#code")
    public BookResponse getBookByBookId(String code){
        Book book = bookRepository.findByCode(code);
        return bookMapper.toBookResponse(book);
    }

    @Override
    public boolean checkIsOrdered(Long bookId) {
        return orderDetailRepository.existsByBookId(bookId);
    }

    private Set<Author> resolveAuthors(List<AuthorDTO> authorDTOs) {
        Set<Author> authors = new HashSet<>();
        for (AuthorDTO authorDTO : authorDTOs) {
            Author author = authorRepository.findByName(authorDTO.getName());
            if (author == null) {
                throw new IllegalArgumentException("Author " + authorDTO.getName() + " does not exist");
            }
            authors.add(author);
        }
        return authors;
    }

    private Set<Genre> resolveGenres(List<GenreDTO> genreDTOs) {
        Set<Genre> genres = new HashSet<>();
        for (GenreDTO genreDTO : genreDTOs) {
            Genre genre = genreRespository.findByName(genreDTO.getName());
            if (genre == null) {
                throw new IllegalArgumentException("Genre " + genreDTO.getName() + " does not exist");
            }
            genres.add(genre);
        }
        return genres;
    }

    private Publisher resolvePublisher(PublisherDTO publisherDTO) {
        Publisher publisher = publisherRespository.findByName(publisherDTO.getName());
        if (publisher == null) {
            throw new IllegalArgumentException("Publisher does not exist");
        }
        return publisher;
    }

}
