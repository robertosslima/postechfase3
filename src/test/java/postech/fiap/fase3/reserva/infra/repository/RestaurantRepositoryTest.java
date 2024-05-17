package postech.fiap.fase3.reserva.infra.repository;

import postech.fiap.fase3.reserva.domain.RestaurantEntity;
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

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantRepositoryTest {

    @Mock
    private RestaurantRepository restaurantRepository;

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
    void shouldFindByNameContaining() {
        // Arrange
        String name = "Ema";
        Pageable pageable = PageRequest.of(0, 10);
        RestaurantEntity restaurant = RestaurantHelper.buildRestaurant();
        Page<RestaurantEntity> page = new PageImpl<>(Collections.singletonList(restaurant), pageable, 10);
        when(restaurantRepository.findByNameContaining(name, PageRequest.of(0, 10))).thenReturn(page);

        // Act
        Page<RestaurantEntity> restaurants = restaurantRepository.findByNameContaining(name, pageable);

        // Assert
        assertThat(restaurants.getContent())
                .hasSize(1)
                .containsExactly(restaurant);
        verify(restaurantRepository, times(1)).findByNameContaining(any(String.class), any(PageRequest.class));
    }

    @Test
    void shouldFindByAddressContaining() {
        // Arrange
        String address = "R. Bela Cintra, 1551 - Consolação, São Paulo - SP, 01415-001";
        Pageable pageable = PageRequest.of(0, 10);
        RestaurantEntity restaurant = RestaurantHelper.buildRestaurant();
        Page<RestaurantEntity> page = new PageImpl<>(Collections.singletonList(restaurant), pageable, 10);
        when(restaurantRepository.findByAddressContaining(any(String.class), any(PageRequest.class))).thenReturn(page);

        // Act
        Page<RestaurantEntity> restaurants = restaurantRepository.findByAddressContaining(address, pageable);

        // Assert
        assertThat(restaurants.getContent())
                .hasSize(1)
                .containsExactly(restaurant);
        verify(restaurantRepository, times(1)).findByAddressContaining(any(String.class), any(PageRequest.class));
    }

    @Test
    void shouldFindByCuisineTypeContaining() {
        // Arrange
        String cuisineType = "BRAZILIAN";
        Pageable pageable = PageRequest.of(0, 10);
        RestaurantEntity restaurant = RestaurantHelper.buildRestaurant();
        Page<RestaurantEntity> page = new PageImpl<>(Collections.singletonList(restaurant), pageable, 10);
        when(restaurantRepository.findByCuisineTypeContaining(any(String.class), any(PageRequest.class))).thenReturn(page);

        // Act
        Page<RestaurantEntity> restaurants = restaurantRepository.findByCuisineTypeContaining(cuisineType, pageable);

        // Assert
        assertThat(restaurants.getContent())
                .hasSize(1)
                .containsExactly(restaurant);
        verify(restaurantRepository, times(1)).findByCuisineTypeContaining(any(String.class), any(PageRequest.class));
    }
}
