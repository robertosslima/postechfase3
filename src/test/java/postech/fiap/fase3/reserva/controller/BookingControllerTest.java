package postech.fiap.fase3.reserva.controller;

import postech.fiap.fase3.reserva.domain.dto.BookingDTO;
import postech.fiap.fase3.reserva.domain.BookingEntity;
import postech.fiap.fase3.reserva.domain.RestaurantEntity;
import postech.fiap.fase3.reserva.gateways.BookingGateway;
import postech.fiap.fase3.reserva.gateways.RestaurantGateway;
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

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookingGateway bookingGateway;

    @Mock
    private RestaurantGateway restaurantGateway;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        BookingController bookingController = new BookingController(bookingGateway, restaurantGateway);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController)
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
    void shouldBookATable() throws Exception {
        RestaurantEntity restaurant = RestaurantHelper.buildRestaurant();
        when(restaurantGateway.findById(any(UUID.class))).thenReturn(restaurant);

        BookingDTO bookingDTO = new BookingDTO(
                2,
                "Lucas",
                LocalDateTime.parse("2025-05-08T19:00:00"),
                "3e9bbc25-53ca-443c-bce0-11518e45f8f8"
        );

        mockMvc.perform(
                post("/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bookingDTO))
        ).andExpect(status().isOk());
        verify(restaurantGateway, times(1)).findById(any(UUID.class));
        verify(bookingGateway, times(1)).findAllByDate(any(UUID.class), any(LocalDateTime.class));
        verify(bookingGateway, times(1)).bookATable(any(BookingEntity.class));
    }

    @Test
    void shouldFindByRestaurantId() throws Exception {
        RestaurantEntity restaurant = RestaurantHelper.buildRestaurant();
        when(restaurantGateway.findById(any(UUID.class))).thenReturn(restaurant);

        String id = "3e9bbc25-53ca-443c-bce0-11518e45f8f8";

        mockMvc.perform(
                get("/booking/find-by-restaurant-id/{id}", id).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(restaurantGateway, times(1)).findById(any(UUID.class));
        verify(bookingGateway, times(1)).findByRestaurantId(any(UUID.class), any(Pageable.class));
    }

    @Test
    void shouldFindByClientName() throws Exception {
        String clientName = "Lucas";

        mockMvc.perform(
                get("/booking/find-by-client-name/{clientName}", clientName).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(bookingGateway, times(1)).findByClientName(any(String.class), any(Pageable.class));
    }

    private String asJsonString(final Object object) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(object);
    }
}
