package postech.fiap.fase3.reserva.controller;

import postech.fiap.fase3.reserva.controller.RestaurantController;
import postech.fiap.fase3.reserva.domain.dto.RestaurantDTO;
import postech.fiap.fase3.reserva.domain.RestaurantEntity;
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

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RestaurantGateway restaurantGateway;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        RestaurantController restaurantController = new RestaurantController(restaurantGateway);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController)
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
    void shouldRegister() throws Exception {
        RestaurantDTO restaurantDTO = new RestaurantDTO(
                "Ema",
                "R. Bela Cintra, 1551 - Consolação, São Paulo - SP, 01415-001",
                "BRAZILIAN",
                RestaurantHelper.buildWorkingHourList(),
                100
        );

        mockMvc.perform(
                post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(restaurantDTO))
        ).andExpect(status().isOk());
        verify(restaurantGateway, times(1)).registerRestaurant(any(RestaurantEntity.class));
    }

    @Test
    void shouldFindById() throws Exception {
        String id = "3e9bbc25-53ca-443c-bce0-11518e45f8f8";

        mockMvc.perform(
                get("/restaurant/{id}", id).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(restaurantGateway, times(1)).findById(any(UUID.class));
    }

    @Test
    void shouldFindByName() throws Exception {
        String name = "Ema";

        mockMvc.perform(
                get("/restaurant/find-by-name/{name}", name).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(restaurantGateway, times(1)).findByNameContaining(any(String.class), any(Pageable.class));
    }

    @Test
    void shouldFindByAddressContaining() throws Exception {
        String address = "R. Bela Cintra, 1551 - Consolação, São Paulo - SP, 01415-001";

        mockMvc.perform(
                get("/restaurant/find-by-address/{address}", address).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(restaurantGateway, times(1)).findByAddressContaining(any(String.class), any(Pageable.class));
    }

    @Test
    void shouldFindByCuisineTypeContaining() throws Exception {
        String cuisineType = "BRAZILIAN";

        mockMvc.perform(
                get("/restaurant/find-by-cuisine/{cuisine}", cuisineType).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(restaurantGateway, times(1)).findByCuisineTypeContaining(any(String.class), any(Pageable.class));
    }

    private String asJsonString(final Object object) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(object);
    }
}
