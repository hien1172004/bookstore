package ptit.example.btlwebbook.service;

import ptit.example.btlwebbook.dto.request.ReviewDTO;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.ReviewResponse;

import java.awt.print.Pageable;
import java.util.List;

public interface ReviewService {
    ReviewResponse createReview(ReviewDTO request);
    ReviewResponse getReviewById(Long id);
    PageResponse<List<ReviewResponse>> getReviewsByBookId(int pageNo, int pageSize, Long bookId);
    List<ReviewResponse> getReviewsByUserId(Long userId);
    ReviewResponse updateReview(Long id, ReviewDTO request);
    void deleteReview(Long id);
}
