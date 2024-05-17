package postech.fiap.fase3.reserva.utils;

import postech.fiap.fase3.reserva.domain.BookingEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BookingHelper {

    public static BookingEntity buildBooking() {
        BookingEntity booking = new BookingEntity();
        booking.setId(UUID.randomUUID());
        booking.setReservation(2);
        booking.setClientName("João da Silva Sauro");
        booking.setDate(LocalDateTime.now());
        booking.setRestaurantId(UUID.randomUUID());
        return booking;
    }

    public static BookingEntity buildBooking(String name) {
        BookingEntity booking = new BookingEntity();
        booking.setId(UUID.randomUUID());
        booking.setReservation(2);
        booking.setClientName(name);
        booking.setDate(LocalDateTime.now());
        booking.setRestaurantId(UUID.randomUUID());
        return booking;
    }
}
