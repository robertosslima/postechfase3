package postech.fiap.fase3.reserva.utils;

import postech.fiap.fase3.reserva.domain.ReviewEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class ReviewHelper {

    public static ReviewEntity buildReview() {
        ReviewEntity review = new ReviewEntity();
        review.setId(UUID.randomUUID());
        review.setRating(3);
        review.setComment("Ótima comida");
        review.setDate(LocalDateTime.now());
        review.setClientName("João da Silva Sauro");
        review.setRestaurantId(UUID.randomUUID());
        return review;
    }
}
