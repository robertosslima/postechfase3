package postech.fiap.fase3.reserva.infra.repository;

import postech.fiap.fase3.reserva.domain.ReviewEntity;
import postech.fiap.fase3.reserva.utils.ReviewHelper;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReviewRepositoryTest {

    @Mock
    private ReviewRepository reviewRepository;

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
    void shouldFindAllByRestaurantId() {
        // Arrange
        ReviewEntity review = ReviewHelper.buildReview();
        Pageable pageable = PageRequest.of(0, 10);
        Page<ReviewEntity> page = new PageImpl<>(Collections.singletonList(review), pageable, 10);
        when(reviewRepository.findAllByRestaurantId(any(UUID.class), any(PageRequest.class))).thenReturn(page);

        // Act
        Page<ReviewEntity> reviews = reviewRepository.findAllByRestaurantId(review.getId(), pageable);

        // Assert
        assertThat(reviews.getContent())
                .hasSize(1)
                .containsExactly(review);
        verify(reviewRepository, times(1)).findAllByRestaurantId(any(UUID.class), any(PageRequest.class));
    }
}
