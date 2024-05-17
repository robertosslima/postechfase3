package postech.fiap.fase3.reserva.infra.repository;

import postech.fiap.fase3.reserva.domain.BookingEntity;
import postech.fiap.fase3.reserva.domain.RestaurantEntity;
import postech.fiap.fase3.reserva.utils.BookingHelper;
import postech.fiap.fase3.reserva.utils.RestaurantHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BookingRepositoryTest {

    @Mock
    private BookingRepository bookingRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldFindByRestaurantIdAndDate() {
        // Arrange
        RestaurantEntity restaurant = RestaurantHelper.buildRestaurant();
        LocalDateTime date = LocalDateTime.of(2025, Month.MAY, 23, 19, 0, 0);
        BookingEntity booking = BookingHelper.buildBooking();
        List<BookingEntity> bookingEntityList = Collections.singletonList(booking);
        when(bookingRepository.findByRestaurantIdAndDate(restaurant.getId(), date)).thenReturn(bookingEntityList);

        // Act
        List<BookingEntity> bookings = bookingRepository.findByRestaurantIdAndDate(restaurant.getId(), date);

        // Assert
        assertThat(bookings)
                .hasSize(1)
                .containsExactly(booking);
        verify(bookingRepository, times(1)).findByRestaurantIdAndDate(restaurant.getId(), date);
    }

    @Test
    void shouldFindByRestaurantId() {
        // Arrange
        RestaurantEntity restaurant = RestaurantHelper.buildRestaurant();
        Pageable pageable = PageRequest.of(0, 10);
        BookingEntity booking = BookingHelper.buildBooking();
        Page<BookingEntity> page = new PageImpl<>(Collections.singletonList(booking), pageable, 10);
        when(bookingRepository.findByRestaurantId(restaurant.getId(), pageable)).thenReturn(page);

        // Act
        Page<BookingEntity> bookings = bookingRepository.findByRestaurantId(restaurant.getId(), pageable);

        // Assert
        assertThat(bookings.getContent())
                .hasSize(1)
                .containsExactly(booking);
        verify(bookingRepository, times(1)).findByRestaurantId(restaurant.getId(), pageable);
    }

    @Test
    void shouldFindByClientName() {
        // Arrange
        String clientName = "João da Silva Sauro";
        Pageable pageable = PageRequest.of(0, 10);
        BookingEntity booking = BookingHelper.buildBooking();
        Page<BookingEntity> page = new PageImpl<>(Collections.singletonList(booking), pageable, 10);
        when(bookingRepository.findByClientName(clientName, pageable)).thenReturn(page);

        // Act
        Page<BookingEntity> bookings = bookingRepository.findByClientName(clientName, pageable);

        // Assert
        assertThat(bookings.getContent())
                .hasSize(1)
                .containsExactly(booking);
        verify(bookingRepository, times(1)).findByClientName(clientName, pageable);
    }
}
