package postech.fiap.fase3.reserva.domain.use_cases;

import postech.fiap.fase3.reserva.domain.dto.ReviewDTO;
import postech.fiap.fase3.reserva.domain.RestaurantEntity;
import postech.fiap.fase3.reserva.domain.ReviewEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReviewUseCases {

    private ReviewUseCases() { }

    public static ReviewEntity reviewRestaurant(ReviewDTO reviewDTO, RestaurantEntity restaurant) {
        validateReviewDTO(reviewDTO);
        ReviewEntity review =  new ReviewEntity();
        review.setId(UUID.randomUUID());
        review.setRating(reviewDTO.rating());
        review.setComment(reviewDTO.comment());
        review.setDate(LocalDateTime.now());
        review.setClientName(reviewDTO.clientName());
        review.setRestaurantId(restaurant.getId());
        return review;
    }

    private static void validateReviewDTO(ReviewDTO reviewDTO) throws IllegalArgumentException {
        if (reviewDTO.rating() < 1 || reviewDTO.rating() > 5) {
            throw new IllegalArgumentException("Invalid rating value");
        }

        if (
                reviewDTO.clientName() == null ||
                reviewDTO.clientName().isEmpty() ||
                reviewDTO.clientName().isBlank()

        ) {
            throw new IllegalArgumentException("Invalid clientName value");
        }
    }
}
