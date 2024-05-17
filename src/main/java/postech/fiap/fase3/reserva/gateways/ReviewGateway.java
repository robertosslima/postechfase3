package postech.fiap.fase3.reserva.gateways;

import postech.fiap.fase3.reserva.domain.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReviewGateway {
    ReviewEntity reviewRestaurant(ReviewEntity review);
    Page<ReviewEntity> findReviewsByRestaurantId(UUID id, Pageable pageable);
}
