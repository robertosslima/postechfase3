package postech.fiap.fase3.reserva.gateways;

import postech.fiap.fase3.reserva.domain.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface RestaurantGateway {
    RestaurantEntity registerRestaurant(RestaurantEntity restaurant);
    RestaurantEntity findById(UUID id) throws RuntimeException;
    Page<RestaurantEntity> findByNameContaining(String name, Pageable pageable);
    Page<RestaurantEntity> findByAddressContaining(String address, Pageable pageable);
    Page<RestaurantEntity> findByCuisineTypeContaining(String cuisine, Pageable pageable);
}
