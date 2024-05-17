package postech.fiap.fase3.reserva.infra.repository;

import postech.fiap.fase3.reserva.domain.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, UUID> {
    Page<RestaurantEntity> findByNameContaining(String name, Pageable pageable);
    Page<RestaurantEntity> findByAddressContaining(String address, Pageable pageable);
    Page<RestaurantEntity> findByCuisineTypeContaining(String cuisineType, Pageable pageable);
}
