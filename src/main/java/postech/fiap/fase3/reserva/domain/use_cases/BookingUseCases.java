package postech.fiap.fase3.reserva.domain.use_cases;

import postech.fiap.fase3.reserva.domain.dto.BookingDTO;
import postech.fiap.fase3.reserva.domain.BookingEntity;
import postech.fiap.fase3.reserva.domain.RestaurantEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BookingUseCases {

    private BookingUseCases() { }

    public static BookingEntity bookATable(BookingDTO bookingDTO, RestaurantEntity restaurant, List<BookingEntity> bookingsWithTheSameDate) {

        validateBookingDTO(bookingDTO);

        Integer capacityAtDate = 0;

        for (BookingEntity booking :  bookingsWithTheSameDate) {
            capacityAtDate += booking.getReservation();
        }

        if (capacityAtDate + bookingDTO.reservation() <= restaurant.getTotalCapacity()) {
            BookingEntity booking = new BookingEntity();
            booking.setId(UUID.randomUUID());
            booking.setReservation(bookingDTO.reservation());
            booking.setClientName(bookingDTO.clientName());
            booking.setDate(bookingDTO.date());
            booking.setRestaurantId(restaurant.getId());
            return booking;
        } else {
            throw new RuntimeException("Restaurant in full capacity");
        }
    }

    private static void validateBookingDTO(BookingDTO bookingDTO) throws IllegalArgumentException {
        if (bookingDTO.reservation() <= 0) {
            throw new IllegalArgumentException("Invalid reservation value");
        }
        if (
                bookingDTO.clientName() == null ||
                bookingDTO.clientName().isEmpty() ||
                bookingDTO.clientName().isBlank()
        ) {
            throw new IllegalArgumentException("Invalid clientName value");
        }
        if (bookingDTO.date().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Invalid date value");
        }
    }
}
