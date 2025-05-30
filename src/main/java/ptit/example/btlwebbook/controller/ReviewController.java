package ptit.example.btlwebbook.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ptit.example.btlwebbook.dto.request.ReviewDTO;
import ptit.example.btlwebbook.dto.response.*;
import ptit.example.btlwebbook.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    String ERROR_MESSAGE ="errorMessage: {} ";

    @PostMapping("/")
    @PreAuthorize("isAuthenticated()")
    public ResponseData<?> createReview(@Valid @RequestBody ReviewDTO reviewDTO){
        try {
            ReviewResponse reviewResponse = reviewService.createReview(reviewDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "success", reviewResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    // Lấy danh sách đánh giá của một cuốn sách theo phân trang
    @GetMapping("/book/{bookId}")
    public ResponseData<PageResponse<List<ReviewResponse>>> getReviewsByBookId(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
            @PathVariable Long bookId) {
        try {
            PageResponse<List<ReviewResponse>> response = reviewService.getReviewsByBookId(pageNo, pageSize, bookId);
            return new ResponseData<>(HttpStatus.OK.value(), "success", response);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
    @GetMapping("/user/{userId}")
    @PreAuthorize("#userId == authentication.principal.id or hasAnyRole('STAFF', 'ADMIN')")
    public ResponseData<List<ReviewResponse>> getReviewsByUserId(@PathVariable long userId) {

        try {
            List<ReviewResponse> responses = reviewService.getReviewsByUserId(userId);
            return new ResponseData<>(HttpStatus.OK.value(), "success", responses);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("@reviewServiceImpl.isReviewOwner(#id, authentication.principal.id) or hasRole('ADMIN')")
    public ResponseData<?> updateReview(@PathVariable long id, @RequestBody @Valid ReviewDTO reviewDTO){
        try {
            ReviewResponse responses = reviewService.updateReview(id, reviewDTO);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "success", responses);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("@reviewServiceImpl.isReviewOwner(#id, authentication.principal.id) or hasRole('ADMIN')")
    public ResponseData<?> deleteReview(@PathVariable long id){
        try {
            reviewService.deleteReview(id);
            return new ResponseData<>(HttpStatus.OK.value(), "success");
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
