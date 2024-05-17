package postech.fiap.fase3.reserva.controller;

import postech.fiap.fase3.reserva.domain.dto.ReviewDTO;
import postech.fiap.fase3.reserva.domain.RestaurantEntity;
import postech.fiap.fase3.reserva.domain.ReviewEntity;
import postech.fiap.fase3.reserva.gateways.RestaurantGateway;
import postech.fiap.fase3.reserva.domain.use_cases.ReviewUseCases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postech.fiap.fase3.reserva.gateways.ReviewGateway;

import java.util.UUID;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewGateway reviewGateway;
    private final RestaurantGateway restaurantGateway;

    public ReviewController(ReviewGateway reviewGateway, RestaurantGateway restaurantGateway) {
        this.reviewGateway = reviewGateway;
        this.restaurantGateway = restaurantGateway;
    }

    @PostMapping()
    public ResponseEntity<Void> review(@RequestBody ReviewDTO reviewDTO) {
        UUID restaurantId = UUID.fromString(reviewDTO.restaurantId());
        RestaurantEntity restaurant = restaurantGateway.findById(restaurantId);
        ReviewEntity review = ReviewUseCases.reviewRestaurant(reviewDTO, restaurant);
        reviewGateway.reviewRestaurant(review);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<ReviewEntity>> findReviewsByRestaurantId(
            @PathVariable String id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        UUID restaurantId = UUID.fromString(id);
        RestaurantEntity restaurant = restaurantGateway.findById(restaurantId);
        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewEntity> reviews = reviewGateway.findReviewsByRestaurantId(restaurant.getId(), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }
}
