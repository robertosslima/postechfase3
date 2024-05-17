package postech.fiap.fase3.reserva.gateways.impl;

import postech.fiap.fase3.reserva.domain.BookingEntity;
import postech.fiap.fase3.reserva.infra.repository.BookingRepository;
import postech.fiap.fase3.reserva.gateways.BookingGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingGatewayImpl implements BookingGateway {

    private final BookingRepository repository;

    BookingGatewayImpl(BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public BookingEntity bookATable(BookingEntity booking) {
        return repository.save(booking);
    }

    @Override
    public Page<BookingEntity> findByRestaurantId(UUID restaurantId, Pageable pageable) {
        return repository.findByRestaurantId(restaurantId, pageable);
    }

    @Override
    public Page<BookingEntity> findByClientName(String clientName, Pageable pageable) {
        return repository.findByClientName(clientName, pageable);
    }

    @Override
    public List<BookingEntity> findAllByDate(UUID restaurantId, LocalDateTime date) {
        return repository.findByRestaurantIdAndDate(restaurantId, date);
    }
}
