package ptit.example.btlwebbook.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ptit.example.btlwebbook.dto.request.GenreDTO;
import ptit.example.btlwebbook.dto.response.GenreResponse;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.exception.ResourceNotFoundException;
import ptit.example.btlwebbook.mapper.GenreMapper;
import ptit.example.btlwebbook.model.Book;
import ptit.example.btlwebbook.model.Genre;
import ptit.example.btlwebbook.repository.BookRepository;
import ptit.example.btlwebbook.repository.GenreRespository;
import ptit.example.btlwebbook.service.GenreService;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreResponseImpl implements GenreService {
    GenreRespository genreRespository;

    GenreMapper genreMapper;

    BookRepository bookRepository;

    private String generateSlug(String name) {
        if (name == null) return "";

        // Chuyển thành chữ thường
        String slug = name.trim().toLowerCase(Locale.ROOT);

        // Loại bỏ dấu tiếng Việt
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
        slug = Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(slug).replaceAll("");

        // Thay khoảng trắng và ký tự đặc biệt bằng dấu gạch ngang
        slug = slug.replaceAll("[^a-z0-9]+", "-");

        // Xóa các dấu gạch ngang dư thừa ở đầu và cuối
        slug = slug.replaceAll("^-+|-+$", "");

        return slug;
    }
    @Override
    public GenreResponse saveGenre(GenreDTO genreDTO) {
        Genre genre = genreMapper.ToGenre(genreDTO);
        String slug = generateSlug(genreDTO.getName());
        genre.setSlug(slug);
        genreRespository.save(genre);
        log.info("genre saved");
        return genreMapper.toGenreResponse(genre);
    }

    @Override
    public GenreResponse updateGenre(Long Id, GenreDTO genreDTO) {
        Genre genre = genreRespository.findById(Id).orElseThrow(() ->new ResourceNotFoundException("not found genre"));
        String slug = generateSlug(genreDTO.getName());
        genreMapper.updateGenre(genre, genreDTO);
        genre.setSlug(slug);
        Genre updated = genreRespository.save(genre);
        log.info("genre updated");
        return genreMapper.toGenreResponse(updated);
    }

    @Override
    public GenreResponse getDetailGenre(Long Id) {
        Genre genre = genreRespository.findById(Id).orElseThrow(()-> new ResourceNotFoundException("not found genre"));
        log.info("genre get detail");
        return genreMapper.toGenreResponse(genre);
    }

    @Override
    public PageResponse<?> getAllGenre(int pageNo, int pageSize) {
        int page = pageNo > 0 ? pageNo - 1 : 0;
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Genre> list = genreRespository.findAll(pageRequest);
        List<GenreResponse> genreResponseList= list.stream().map(genreMapper::toGenreResponse).toList();
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(list.getTotalPages())
                .items(genreResponseList)
                .build();
    }

    @Override
    public GenreResponse getBySLug(String slug) {
        Genre genre = genreRespository.findBySlug(slug);

        return genreMapper.toGenreResponse(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        Genre genre = genreRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found genre"));
        List<Book> list = bookRepository.findByGenres_Id(id);
        for(Book book : list){
            book.setGenres(null);
            bookRepository.save(book);
        }
        genreRespository.deleteById(id);
    }
}
