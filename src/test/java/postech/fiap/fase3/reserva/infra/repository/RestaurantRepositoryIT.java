package postech.fiap.fase3.reserva.infra.repository;

import postech.fiap.fase3.reserva.domain.RestaurantEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@Transactional
class RestaurantRepositoryIT {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    void shouldCreateTable() {
        long totalRecords = restaurantRepository.count();
        assertThat(totalRecords)
                .isPositive();
    }

    @Test
    void shouldFindByNameContaining() {
        // Arrange
        String name = "Ema";
        Pageable pageable = PageRequest.of(0, 10);

        // Act
        Page<RestaurantEntity> restaurants = restaurantRepository.findByNameContaining(name, pageable);

        // Assert
        assertThat(restaurants)
                .hasSize(1);
    }

    @Test
    void shouldFindByAddressContaining() {
        // Arrange
        String address = "R. Bela Cintra, 1551 - Consolação, São Paulo - SP, 01415-001";
        Pageable pageable = PageRequest.of(0, 10);

        // Act
        Page<RestaurantEntity> restaurants = restaurantRepository.findByAddressContaining(address, pageable);

        // Assert
        assertThat(restaurants)
                .hasSize(1);
    }

    @Test
    void shouldFindByCuisineTypeContaining() {
        // Arrange
        String cuisineType = "BRAZILIAN";
        Pageable pageable = PageRequest.of(0, 10);

        // Act
        Page<RestaurantEntity> restaurants = restaurantRepository.findByCuisineTypeContaining(cuisineType, pageable);

        // Assert
        assertThat(restaurants)
                .hasSize(1);
    }
}
