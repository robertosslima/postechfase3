package postech.fiap.fase3.reserva.gateways.impl;

import postech.fiap.fase3.reserva.domain.RestaurantEntity;
import postech.fiap.fase3.reserva.infra.repository.RestaurantRepository;
import postech.fiap.fase3.reserva.gateways.RestaurantGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class RestaurantGatewayImpl implements RestaurantGateway {

    private final RestaurantRepository repository;

    RestaurantGatewayImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public RestaurantEntity registerRestaurant(RestaurantEntity restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public RestaurantEntity findById(UUID id) throws RuntimeException {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    @Override
    public Page<RestaurantEntity> findByNameContaining(String name, Pageable pageable) {
        return repository.findByNameContaining(name, pageable);
    }

    @Override
    public Page<RestaurantEntity> findByAddressContaining(String address, Pageable pageable) {
        return repository.findByAddressContaining(address, pageable);
    }

    @Override
    public Page<RestaurantEntity> findByCuisineTypeContaining(String cuisine, Pageable pageable) {
        return repository.findByCuisineTypeContaining(cuisine, pageable);
    }
}
