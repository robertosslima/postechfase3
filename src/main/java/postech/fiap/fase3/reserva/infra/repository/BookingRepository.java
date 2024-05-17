package postech.fiap.fase3.reserva.infra.repository;

import postech.fiap.fase3.reserva.domain.BookingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    List<BookingEntity> findByRestaurantIdAndDate(UUID restaurantId, LocalDateTime date);
    Page<BookingEntity> findByRestaurantId(UUID restaurantId, Pageable pageable);
    Page<BookingEntity> findByClientName(String clientName, Pageable pageable);
}
