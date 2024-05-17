package postech.fiap.fase3.reserva.infra.repository;

import postech.fiap.fase3.reserva.domain.ReviewEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@Transactional
class ReviewRepositoryIT {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    void shouldCreateTable() {
        long totalRecords = reviewRepository.count();
        assertThat(totalRecords)
                .isPositive();
    }

    @Test
    void shouldFindAllByRestaurantId() {
        // Arrange
        UUID restaurantId = UUID.fromString("3e9bbc25-53ca-443c-bce0-11518e45f8f8");
        Pageable pageable = PageRequest.of(0, 10);

        // Act
        Page<ReviewEntity> reviews = reviewRepository.findAllByRestaurantId(restaurantId, pageable);

        // Assert
        assertThat(reviews.getContent())
                .hasSize(3);
    }
}
