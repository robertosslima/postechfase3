package postech.fiap.fase3.reserva.infra.repository;

import postech.fiap.fase3.reserva.domain.BookingEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@Transactional
class BookingRepositoryIT {

    @Autowired
    BookingRepository bookingRepository;

    @Test
    void shouldCreateTable() {
        long totalRecords = bookingRepository.count();
        assertThat(totalRecords)
                .isPositive();
    }

    @Test
    void shouldFindByRestaurantIdAndDate() {
        // Arrange
        UUID restaurantId = UUID.fromString("3e9bbc25-53ca-443c-bce0-11518e45f8f8");
        LocalDateTime date = LocalDateTime.parse("2024-05-01T19:00:00");
        // Act
        List<BookingEntity> bookings = bookingRepository.findByRestaurantIdAndDate(restaurantId, date);

        // Assert
        assertThat(bookings)
                .hasSize(2);
    }

    @Test
    void shouldFindByRestaurantId() {
        // Arrange
        UUID restaurantId = UUID.fromString("3e9bbc25-53ca-443c-bce0-11518e45f8f8");
        Pageable pageable = PageRequest.of(0, 10);

        // Act
        Page<BookingEntity> bookings = bookingRepository.findByRestaurantId(restaurantId, pageable);

        // Assert
        assertThat(bookings)
                .hasSize(3);
    }

    @Test
    void shouldFindByClientName() {
        // Arrange
        String clientName = "Lucas Pereira";
        Pageable pageable = PageRequest.of(0, 10);

        // Act
        Page<BookingEntity> bookings = bookingRepository.findByClientName(clientName, pageable);

        // Assert
        assertThat(bookings)
                .hasSize(1);
    }
}
