package postech.fiap.fase3.reserva.gateways;

import postech.fiap.fase3.reserva.domain.BookingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingGateway {
    BookingEntity bookATable(BookingEntity booking);
    List<BookingEntity> findAllByDate(UUID restaurantId, LocalDateTime date);
    Page<BookingEntity> findByRestaurantId(UUID restaurantId, Pageable pageable);
    Page<BookingEntity> findByClientName(String clientName, Pageable pageable);
}
