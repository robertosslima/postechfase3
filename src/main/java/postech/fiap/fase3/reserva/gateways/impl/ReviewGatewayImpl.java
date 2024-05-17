package postech.fiap.fase3.reserva.gateways.impl;

import postech.fiap.fase3.reserva.domain.ReviewEntity;
import postech.fiap.fase3.reserva.infra.repository.ReviewRepository;
import postech.fiap.fase3.reserva.gateways.ReviewGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewGatewayImpl implements ReviewGateway {

    private final ReviewRepository repository;

    public ReviewGatewayImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public ReviewEntity reviewRestaurant(ReviewEntity review) {
        return repository.save(review);
    }

    @Override
    public Page<ReviewEntity> findReviewsByRestaurantId(UUID id, Pageable pageable) {
        return repository.findAllByRestaurantId(id, pageable);
    }
}
