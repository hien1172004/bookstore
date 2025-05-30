package ptit.example.btlwebbook.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ptit.example.btlwebbook.dto.request.PublisherDTO;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.PublisherResponse;
import ptit.example.btlwebbook.dto.response.ResponeError;
import ptit.example.btlwebbook.dto.response.ResponseData;
import ptit.example.btlwebbook.service.PublisherService;

import java.util.List;

@RestController
@Validated
@RequestMapping("/publishers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PublisherController {
    PublisherService publisherService;
    String ERROR_MESSAGE ="errorMessage: {} ";
    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseData<?> createPublisher(@Valid @RequestBody PublisherDTO publisherDTO){
        try {
            PublisherResponse publisherResponse = publisherService.savePublisher(publisherDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "success", publisherResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    ResponseData<?> updatePublisher(@PathVariable Long id, @Valid @RequestBody PublisherDTO publisherDTO){
        try {
            PublisherResponse publisherResponse = publisherService.updatePublisher(id, publisherDTO);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "updated success", publisherResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    ResponseData<?> deletePublisher(@PathVariable Long id){
        try {
            publisherService.deletePublisher(id);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "delete success");
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    ResponseData<?> getDetailPublisher(@PathVariable Long id){
        try {
            PublisherResponse publisherResponse = publisherService.getDetailResponse(id);
            return new ResponseData<>(HttpStatus.OK.value(), "get success", publisherResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
    @GetMapping("/")
    ResponseData<PageResponse<?>> getAllPublisher(@RequestParam(defaultValue = "1", required = false) int pageNo,
                                               @RequestParam(defaultValue = "10", required = false) int pageSize){
        return new ResponseData<>(HttpStatus.OK.value(), "get all success", publisherService.getAllPublisher(pageNo, pageSize));
    }
}
