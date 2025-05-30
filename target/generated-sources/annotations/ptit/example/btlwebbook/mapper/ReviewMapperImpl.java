package ptit.example.btlwebbook.mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ptit.example.btlwebbook.dto.request.ReviewDTO;
import ptit.example.btlwebbook.dto.response.ReviewResponse;
import ptit.example.btlwebbook.model.Book;
import ptit.example.btlwebbook.model.Review;
import ptit.example.btlwebbook.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T00:54:34+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public Review toReview(ReviewDTO reviewDTO) {
        if ( reviewDTO == null ) {
            return null;
        }

        Review.ReviewBuilder<?, ?> review = Review.builder();

        review.user( reviewDTOToUser( reviewDTO ) );
        review.book( reviewDTOToBook( reviewDTO ) );
        review.content( reviewDTO.getContent() );
        review.rating( reviewDTO.getRating() );

        return review.build();
    }

    @Override
    public ReviewResponse toReviewResponse(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewResponse reviewResponse = new ReviewResponse();

        reviewResponse.setUserId( reviewUserId( review ) );
        reviewResponse.setBookId( reviewBookId( review ) );
        reviewResponse.setContent( review.getContent() );
        if ( review.getCreatedAt() != null ) {
            reviewResponse.setCreatedAt( LocalDateTime.ofInstant( review.getCreatedAt().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        reviewResponse.setId( review.getId() );
        reviewResponse.setRating( review.getRating() );
        if ( review.getUpdatedAt() != null ) {
            reviewResponse.setUpdatedAt( LocalDateTime.ofInstant( review.getUpdatedAt().toInstant(), ZoneId.of( "UTC" ) ) );
        }

        return reviewResponse;
    }

    @Override
    public void updateEntityFromRequest(ReviewDTO request, Review review) {
        if ( request == null ) {
            return;
        }

        if ( review.getUser() == null ) {
            review.setUser( User.builder().build() );
        }
        reviewDTOToUser1( request, review.getUser() );
        if ( review.getBook() == null ) {
            review.setBook( Book.builder().build() );
        }
        reviewDTOToBook1( request, review.getBook() );
        review.setContent( request.getContent() );
        review.setRating( request.getRating() );
    }

    protected User reviewDTOToUser(ReviewDTO reviewDTO) {
        if ( reviewDTO == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.id( reviewDTO.getUserId() );

        return user.build();
    }

    protected Book reviewDTOToBook(ReviewDTO reviewDTO) {
        if ( reviewDTO == null ) {
            return null;
        }

        Book.BookBuilder<?, ?> book = Book.builder();

        book.id( reviewDTO.getBookId() );

        return book.build();
    }

    private Long reviewUserId(Review review) {
        if ( review == null ) {
            return null;
        }
        User user = review.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long reviewBookId(Review review) {
        if ( review == null ) {
            return null;
        }
        Book book = review.getBook();
        if ( book == null ) {
            return null;
        }
        Long id = book.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected void reviewDTOToUser1(ReviewDTO reviewDTO, User mappingTarget) {
        if ( reviewDTO == null ) {
            return;
        }

        mappingTarget.setId( reviewDTO.getUserId() );
    }

    protected void reviewDTOToBook1(ReviewDTO reviewDTO, Book mappingTarget) {
        if ( reviewDTO == null ) {
            return;
        }

        mappingTarget.setId( reviewDTO.getBookId() );
    }
}
