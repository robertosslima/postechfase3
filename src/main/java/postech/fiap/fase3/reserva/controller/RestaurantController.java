package postech.fiap.fase3.reserva.controller;

import postech.fiap.fase3.reserva.domain.dto.RestaurantDTO;
import postech.fiap.fase3.reserva.domain.RestaurantEntity;
import postech.fiap.fase3.reserva.gateways.RestaurantGateway;
import postech.fiap.fase3.reserva.domain.use_cases.RestaurantUseCases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantGateway gateway;

    public RestaurantController(RestaurantGateway gateway) {
        this.gateway = gateway;
    }

    @PostMapping
    public ResponseEntity<RestaurantEntity> register(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantEntity restaurantEntity = RestaurantUseCases.registerRestaurant(restaurantDTO);
        restaurantEntity = gateway.registerRestaurant(restaurantEntity);
        return ResponseEntity.status(HttpStatus.OK).body(restaurantEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantEntity> findById(@PathVariable String id) {
        UUID restaurantId = UUID.fromString(id);
        RestaurantEntity restaurant = gateway.findById(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<Page<RestaurantEntity>> findByName(
            @PathVariable() String name,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RestaurantEntity> restaurants = gateway.findByNameContaining(name, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    @GetMapping("/find-by-address/{address}")
    public ResponseEntity<Page<RestaurantEntity>> findByAddress(
            @PathVariable() String address,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RestaurantEntity> restaurants = gateway.findByAddressContaining(address, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    @GetMapping("/find-by-cuisine/{cuisine}")
    public ResponseEntity<Page<RestaurantEntity>> findByCuisine(
            @PathVariable() String cuisine,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RestaurantEntity> restaurants = gateway.findByCuisineTypeContaining(cuisine, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }
}
