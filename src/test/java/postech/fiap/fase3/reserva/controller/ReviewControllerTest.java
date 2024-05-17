package postech.fiap.fase3.reserva.controller;

import postech.fiap.fase3.reserva.domain.dto.ReviewDTO;
import postech.fiap.fase3.reserva.domain.RestaurantEntity;
import postech.fiap.fase3.reserva.domain.ReviewEntity;
import postech.fiap.fase3.reserva.gateways.RestaurantGateway;
import postech.fiap.fase3.reserva.gateways.ReviewGateway;
import postech.fiap.fase3.reserva.utils.RestaurantHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReviewControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReviewGateway reviewGateway;

    @Mock
    private RestaurantGateway restaurantGateway;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        ReviewController reviewController = new ReviewController(reviewGateway, restaurantGateway);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldReview() throws Exception {
        RestaurantEntity restaurant = RestaurantHelper.buildRestaurant();
        when(restaurantGateway.findById(any(UUID.class))).thenReturn(restaurant);

        ReviewDTO reviewDTO = new ReviewDTO(
                4,
                "",
                "Isabela Garcia",
                "3e9bbc25-53ca-443c-bce0-11518e45f8f8"
        );

        mockMvc.perform(
            post("/review")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(reviewDTO))
        ).andExpect(status().isOk());
        verify(restaurantGateway, times(1)).findById(any(UUID.class));
        verify(reviewGateway, times(1)).reviewRestaurant(any(ReviewEntity.class));
    }

    @Test
    void shouldFindReviewsByRestaurantId() throws Exception {
        RestaurantEntity restaurant = RestaurantHelper.buildRestaurant();
        when(restaurantGateway.findById(any(UUID.class))).thenReturn(restaurant);

        String id = "3e9bbc25-53ca-443c-bce0-11518e45f8f8";

        mockMvc.perform(
            get("/review/{id}", id).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(restaurantGateway, times(1)).findById(any(UUID.class));
        verify(reviewGateway, times(1)).findReviewsByRestaurantId(any(UUID.class), any(Pageable.class));
    }

    private String asJsonString(final Object object) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(object);
    }
}
