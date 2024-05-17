package postech.fiap.fase3.reserva.controller;

import postech.fiap.fase3.reserva.domain.dto.BookingDTO;
import postech.fiap.fase3.reserva.domain.BookingEntity;
import postech.fiap.fase3.reserva.domain.RestaurantEntity;
import postech.fiap.fase3.reserva.gateways.BookingGateway;
import postech.fiap.fase3.reserva.gateways.RestaurantGateway;
import postech.fiap.fase3.reserva.domain.use_cases.BookingUseCases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingGateway bookingGateway;
    private final RestaurantGateway restaurantGateway;

    BookingController(BookingGateway bookingGateway, RestaurantGateway restaurantGateway) {
        this.bookingGateway = bookingGateway;
        this.restaurantGateway = restaurantGateway;
    }

    @PostMapping
    public ResponseEntity<BookingEntity> bookATable(
            @RequestBody BookingDTO bookingDTO
    ) {
        UUID restaurantId = UUID.fromString(bookingDTO.restaurantId());
        RestaurantEntity restaurant = restaurantGateway.findById(restaurantId);
        List<BookingEntity> bookingsWithTheSameDate = bookingGateway.findAllByDate(restaurantId, bookingDTO.date());
        BookingEntity booking = BookingUseCases.bookATable(bookingDTO, restaurant, bookingsWithTheSameDate);
        booking = bookingGateway.bookATable(booking);
        return ResponseEntity.status(HttpStatus.OK).body(booking);
    }

    @GetMapping("/find-by-restaurant-id/{id}")
    public ResponseEntity<Page<BookingEntity>> findByRestaurantId(
            @PathVariable String id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        UUID restaurantId = UUID.fromString(id);
        RestaurantEntity restaurant = restaurantGateway.findById(restaurantId);
        Pageable pageable = PageRequest.of(page, size);
        Page<BookingEntity> bookings = bookingGateway.findByRestaurantId(restaurant.getId(), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }

    @GetMapping("/find-by-client-name/{clientName}")
    public ResponseEntity<Page<BookingEntity>> findByClientName(
            @PathVariable String clientName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookingEntity> bookings = bookingGateway.findByClientName(clientName, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }
}
