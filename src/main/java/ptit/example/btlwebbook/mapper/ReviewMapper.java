package ptit.example.btlwebbook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ptit.example.btlwebbook.dto.request.ReviewDTO;
import ptit.example.btlwebbook.dto.response.ReviewResponse;
import ptit.example.btlwebbook.model.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "bookId", target = "book.id")
    Review toReview(ReviewDTO reviewDTO);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    ReviewResponse toReviewResponse(Review review);

    // Cập nhật Review từ ReviewCreateRequest
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "bookId", target = "book.id")
    void updateEntityFromRequest(ReviewDTO request, @MappingTarget Review review);
}
