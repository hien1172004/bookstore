package ptit.example.btlwebbook.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ptit.example.btlwebbook.dto.request.ReviewDTO;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.ReviewResponse;
import ptit.example.btlwebbook.dto.response.UserResponse;
import ptit.example.btlwebbook.exception.ResourceNotFoundException;
import ptit.example.btlwebbook.mapper.ReviewMapper;
import ptit.example.btlwebbook.mapper.UserMapper;
import ptit.example.btlwebbook.model.Book;
import ptit.example.btlwebbook.model.Review;
import ptit.example.btlwebbook.model.User;
import ptit.example.btlwebbook.repository.BookRepository;
import ptit.example.btlwebbook.repository.ReviewRepository;
import ptit.example.btlwebbook.repository.UserRepository;
import ptit.example.btlwebbook.service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl  implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ReviewMapper reviewMapper;
    private final UserMapper userMapper;
    @Override
    public ReviewResponse createReview(ReviewDTO request) {
        if(reviewRepository.existsByBookIdAndUserId(request.getBookId(), request.getUserId())){
            throw new IllegalArgumentException("user had reviewed book");
        }
        // Kiểm tra user và book tồn tại
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Book book = bookRepository.findById(request.getBookId()).orElseThrow(() -> new ResourceNotFoundException("book not found"));

        // Ánh xạ DTO sang entity
        Review review = reviewMapper.toReview(request);
//        review.setUser(user);
//        review.setBook(book);

        review = reviewRepository.save(review);
        return reviewMapper.toReviewResponse(review);
    }

    @Override
    public ReviewResponse getReviewById(Long id) {
        return null;
    }

    @Override
    public PageResponse<List<ReviewResponse>> getReviewsByBookId(int pageNo, int pageSize, Long bookId) {
        int page = 0;
        if(pageNo > 0){
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Review> reviews = reviewRepository.findByBookId(bookId, pageable);

        List<ReviewResponse> reviewResponses = reviews.stream().map(reviewMapper::toReviewResponse).toList();
        return PageResponse.<List<ReviewResponse>>builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(reviews.getTotalPages())
                .items(reviewResponses)
                .build();
    }

    @Override
    public List<ReviewResponse> getReviewsByUserId(Long userId) {
        List<Review> list = reviewRepository.findByUser_Id(userId);
        return list.stream().map(reviewMapper::toReviewResponse).toList();
    }

    @Override
    public ReviewResponse updateReview(Long id, ReviewDTO request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        // Kiểm tra user và book
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        // Cập nhật review từ DTO
        reviewMapper.updateEntityFromRequest(request, review);
        review.setUser(user);
        review.setBook(book);

        review = reviewRepository.save(review);
        return reviewMapper.toReviewResponse(review);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        reviewRepository.delete(review);
    }

    public boolean isReviewOwner(long reviewId, long userId) {
        return reviewRepository.findById(reviewId)
                .map(review -> review.getUser().getId().equals(userId))
                .orElse(false);
    }
}
