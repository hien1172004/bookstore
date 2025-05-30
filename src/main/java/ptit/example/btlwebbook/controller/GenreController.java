package ptit.example.btlwebbook.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ptit.example.btlwebbook.dto.request.GenreDTO;
import ptit.example.btlwebbook.dto.response.GenreResponse;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.ResponeError;
import ptit.example.btlwebbook.dto.response.ResponseData;
import ptit.example.btlwebbook.model.Genre;
import ptit.example.btlwebbook.service.GenreService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/genres")
@Validated
@RequiredArgsConstructor

public class GenreController {
    private final GenreService genreService;
    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    ResponseData<?> createGenre(@Valid @RequestBody GenreDTO genreDTO){
            try{
                GenreResponse genreResponse = genreService.saveGenre(genreDTO);
                return new ResponseData<>(HttpStatus.CREATED.value(), "success",genreResponse);
            }catch (Exception e){
                return  new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            }
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    ResponseData<?> updateGenre(@Valid @RequestBody GenreDTO genreDTO,@PathVariable Long id){
        try{
            GenreResponse genreResponse = genreService.updateGenre(id, genreDTO);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "success", genreResponse);
        }catch (Exception e){
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    ResponseData<?> getDetailGenre(@PathVariable Long id){
        try{
            GenreResponse genreResponse = genreService.getDetailGenre(id);
            return new ResponseData<>(HttpStatus.OK.value(), "success", genreResponse);
        }catch (Exception e){
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
    @GetMapping("/")
    ResponseData<?> getAllGenre(@RequestParam(defaultValue = "1", required = false) int pageNo,
                                @RequestParam(defaultValue = "10", required = false) int pageSize){
        return new ResponseData<>(HttpStatus.OK.value(), "success", genreService.getAllGenre(pageNo, pageSize));
    }
    @GetMapping("/slug/{slug}")
    ResponseData<?> getDetailBySlug(@PathVariable String slug){
        GenreResponse genreResponse = genreService.getBySLug(slug);
        return new ResponseData<>(HttpStatus.OK.value(), "Success", genreResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    ResponseData<?> deleteGenre(@PathVariable Long id){
        try{
            genreService.deleteGenre(id);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "success");
        }catch (Exception e){
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
