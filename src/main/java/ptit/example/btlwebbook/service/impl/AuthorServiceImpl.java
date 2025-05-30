package ptit.example.btlwebbook.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptit.example.btlwebbook.dto.request.AuthorDTO;
import ptit.example.btlwebbook.dto.response.AuthorResponse;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.exception.ResourceNotFoundException;
import ptit.example.btlwebbook.mapper.AuthorMapper;
import ptit.example.btlwebbook.model.Author;
import ptit.example.btlwebbook.model.Book;
import ptit.example.btlwebbook.repository.AuthorRepository;
import ptit.example.btlwebbook.repository.BookRepository;
import ptit.example.btlwebbook.service.AuthorService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {
    private  final AuthorRepository authorRespository;
    private final BookRepository bookRepository;
    private  final AuthorMapper authorMapper;
    @Override
    public long saveAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.toAuthor(authorDTO);
        log.info("Author saved");
       return authorRespository.save(author).getId();
    }

    @Override
    public void updateAuthor(long authorId, AuthorDTO author) {
        Author authorEntity = authorRespository.findById(authorId)
               .orElseThrow(() -> new RuntimeException("Author not found"));
        authorMapper.updateAuthor(authorEntity, author);
        authorRespository.save(authorEntity);
        log.info("Author updated successfully with id {}", authorId);
    }

    @Override
    @Transactional
    public void deleteAuthor(long authorId) {
        Author author = authorRespository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        List<Book> books = bookRepository.findBookByAuthors_Id(authorId);
        for(Book book : books){
            book.setAuthors(null);
            bookRepository.save(book);
        }

        authorRespository.deleteById(authorId);
        log.info("Author deleted successfully with id {}", authorId);
    }

    @Override
    @Transactional
    public AuthorResponse findAuthorById(long authorId) {
        Author author = authorRespository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return authorMapper.toAuthorResponse(author);
    }

    @Override
    public PageResponse<?> getAllAuthor(int pageNo, int pageSize, String ...sorts) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String sortBy : sorts) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sortBy);
            if (matcher.find()) {
                if (matcher.group(3).equalsIgnoreCase("asc")) {
                    orders.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                } else if (matcher.group(3).equalsIgnoreCase("desc")) {
                    orders.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                }
            }
        }

        Sort sort = orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);

        if (pageSize <= 0) {
            // Lấy tất cả không phân trang
            List<Author> authors = authorRespository.findAll(sort);
            List<AuthorResponse> authorResponses = authors.stream()
                    .map(authorMapper::toAuthorResponse)
                    .toList();

            return PageResponse.builder()
                    .pageSize(authors.size())
                    .pageNo(1)
                    .totalPages(1)
                    .items(authorResponses)
                    .build();
        } else {
            int page = 0;
            if (pageNo > 0) {
                page = pageNo - 1;
            }
            Pageable pageable = PageRequest.of(page, pageSize, sort);
            Page<Author> pageAuthor = authorRespository.findAll(pageable);
            List<AuthorResponse> authorResponses = pageAuthor.stream()
                    .map(authorMapper::toAuthorResponse)
                    .toList();
            return PageResponse.builder()
                    .pageSize(pageSize)
                    .pageNo(pageNo)
                    .totalPages(pageAuthor.getTotalPages())
                    .items(authorResponses)
                    .build();
        }
    }
}
