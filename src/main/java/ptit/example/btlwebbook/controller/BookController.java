package ptit.example.btlwebbook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ptit.example.btlwebbook.dto.request.BookDTO;
import ptit.example.btlwebbook.dto.response.BookResponse;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.ResponeError;
import ptit.example.btlwebbook.dto.response.ResponseData;
import ptit.example.btlwebbook.service.BookService;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/books")
@Validated

public class BookController {
    private final BookService bookService;
    String ERROR_MESSAGE ="errorMessage: {} ";
    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseData<?> createBook( @RequestBody @Valid BookDTO bookDTO) throws IOException {
        try{
            BookResponse bookResponse =  bookService.saveBook(bookDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "success", bookResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseData<?> getBookById(@PathVariable long id){
        try {
            BookResponse bookResponse =  bookService.getDetailBook(id);
            return new ResponseData<>(HttpStatus.OK.value(), "success", bookResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseData<?> updateBook(@PathVariable long id, @Valid @RequestBody BookDTO bookDTO){
        try {
            BookResponse bookResponse =  bookService.updateBook(bookDTO, id);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "success", bookResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping()
    public ResponseData<?> getAllBooks(@RequestParam(defaultValue = "1", required = false) int pageNo,
                                       @RequestParam(defaultValue = "8", required = false) int pageSize,
                                       @RequestParam(defaultValue = "createdAt:desc", required = false) String sortBy,
                                       @RequestParam(name = "genres" , required = false) List<Long> genres){
        try {
            PageResponse<?> pageResponse = bookService.getAll(pageNo, pageSize, genres, sortBy);
            return new ResponseData<>(HttpStatus.OK.value(), "success", pageResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

    }

    @GetMapping("/slug/{slug}")
    public ResponseData<BookResponse> getBookBySlug(@PathVariable String slug) {
        BookResponse bookResponse = bookService.getBySlug(slug);
        return new ResponseData<>(HttpStatus.OK.value(), "success", bookResponse);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseData<Void> deleteBook(@PathVariable Long id){
        try {
            bookService.deleteBook(id);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "success");
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
    @GetMapping("/bookId/{code}")
    public ResponseData<?> getBookByCode(@PathVariable String code){
        try {
            BookResponse bookResponse =  bookService.getBookByBookId(code);
            return new ResponseData<>(HttpStatus.OK.value(), "success", bookResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseData<?> searchBooks(@RequestParam(defaultValue = "1", required = false) int pageNo,
                                       @RequestParam(defaultValue = "8", required = false) int pageSize,
                                       @RequestParam(name = "key") String key){
        try {
            PageResponse<?> pageResponse = bookService.searchBook(pageNo, pageSize, key);
            return new ResponseData<>(HttpStatus.OK.value(), "success", pageResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/is-ordered/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseData<Boolean> checkIsOrdered(@PathVariable long id){
        try {
            boolean result = bookService.checkIsOrdered(id);
            return new ResponseData<>(HttpStatus.OK.value(), "success", result);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
