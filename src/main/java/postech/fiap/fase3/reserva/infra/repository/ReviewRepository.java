package postech.fiap.fase3.reserva.infra.repository;

import postech.fiap.fase3.reserva.domain.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {
    Page<ReviewEntity> findAllByRestaurantId(UUID id, Pageable pageable);
}
