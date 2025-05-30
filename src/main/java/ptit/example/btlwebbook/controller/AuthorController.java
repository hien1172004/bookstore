package ptit.example.btlwebbook.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ptit.example.btlwebbook.dto.request.AuthorDTO;
import ptit.example.btlwebbook.dto.response.AuthorResponse;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.ResponeError;
import ptit.example.btlwebbook.dto.response.ResponseData;
import ptit.example.btlwebbook.model.Author;
import ptit.example.btlwebbook.service.AuthorService;

@RestController
@RequestMapping("/authors")
@Slf4j
@RequiredArgsConstructor
@Validated
public class AuthorController {
    private  final AuthorService authorService;
    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseData<Long> addAuthor(@Valid @RequestBody AuthorDTO authorDTO){
        log.info("addAuthor, {}", authorDTO.getName());
        try {
            long authorId = authorService.saveAuthor(authorDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "author add sucess", authorId);
        }catch (Exception e){
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), "Add user fail");
        }
    }

    @PutMapping("/{authorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseData<Void> updateAuthor(@Valid @RequestBody AuthorDTO authorDTO, @PathVariable long authorId){
        log.info("updateAuthor, {}", authorDTO.getName());
        try{
            authorService.updateAuthor(authorId, authorDTO);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "author update sucess");
        }catch (Exception e){
            return new ResponeError(HttpStatus.BAD_REQUEST.value(),"update failed");
        }
    }

    @DeleteMapping("/{authorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseData<Void> deleteAuthor(@PathVariable long authorId){
        log.info("deleteAuthor, {}", authorId);
        try{
            authorService.deleteAuthor(authorId);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "author delete success");
        }catch (Exception e){
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), "delete failed");
        }
    }

    @GetMapping("{authorId}")
    public  ResponseData<AuthorResponse> getAuthor(@PathVariable long authorId){
        log.info("getAuthor, {}", authorId);
        try{
            AuthorResponse authorResponse = authorService.findAuthorById(authorId);
            return new ResponseData<>(HttpStatus.OK.value(), "author get success", authorResponse);
        }catch (Exception e){
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), "get author failed");
        }
    }
    @GetMapping("/")
    public ResponseData<PageResponse<?>> getAllAuthor(@RequestParam(defaultValue = "1", required = false) int pageNo,
                                                      @RequestParam(defaultValue = "10", required = false) int pageSize,
                                                      @RequestParam(defaultValue = "name:asc", required = false) String[] sortBy){
        log.info("getAllAuthor");
        return new ResponseData<>(HttpStatus.OK.value(), "success", authorService.getAllAuthor(pageNo, pageSize, sortBy));
    }
}
