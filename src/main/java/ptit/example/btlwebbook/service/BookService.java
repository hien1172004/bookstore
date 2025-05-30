package ptit.example.btlwebbook.service;

import org.springframework.web.multipart.MultipartFile;
import ptit.example.btlwebbook.dto.request.BookDTO;
import ptit.example.btlwebbook.dto.response.BookResponse;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.model.Genre;

import java.io.IOException;
import java.util.List;

public interface BookService {
    BookResponse saveBook(BookDTO bookDTO) throws IOException;

    BookResponse updateBook(BookDTO bookDTO, Long id) throws IOException;

    void deleteBook(Long id) throws IOException;

    BookResponse getDetailBook(Long id);

    BookResponse getBySlug(String slug);

    PageResponse<?> getAll(int pageNo, int pageSize, List<Long> genres, String sortBy);

    PageResponse<?> searchBook(int pageNo, int pageSize, String key);

    BookResponse getBookByBookId(String code);

    boolean checkIsOrdered(Long bookId);
}
