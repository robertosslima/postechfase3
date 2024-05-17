package postech.fiap.fase3.reserva.utils;

import postech.fiap.fase3.reserva.domain.RestaurantEntity;
import postech.fiap.fase3.reserva.domain.WorkingHourEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class RestaurantHelper {

    public static RestaurantEntity buildRestaurant() {
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setId(UUID.randomUUID());
        restaurant.setName("Ema");
        restaurant.setAddress("R. Bela Cintra, 1551 - Consolação, São Paulo - SP, 01415-001");
        restaurant.setCuisineType("BRAZILIAN");
        restaurant.setWorkingHours(buildWorkingHourList());
        restaurant.setTotalCapacity(100);
        return restaurant;
    }

    public static List<WorkingHourEntity> buildWorkingHourList() {
        return Arrays.asList(
                new WorkingHourEntity("Monday", LocalTime.parse("11:00:00"), LocalTime.parse("16:00:00")),
                new WorkingHourEntity("Tuesday", LocalTime.parse("11:00:00"), LocalTime.parse("16:00:00")),
                new WorkingHourEntity("Wednesday", LocalTime.parse("11:00:00"), LocalTime.parse("16:00:00")),
                new WorkingHourEntity("Thursday", LocalTime.parse("11:00:00"), LocalTime.parse("16:00:00")),
                new WorkingHourEntity("Friday", LocalTime.parse("11:00:00"), LocalTime.parse("16:00:00")),
                new WorkingHourEntity("Saturday", LocalTime.parse("11:00:00"), LocalTime.parse("22:00:00")),
                new WorkingHourEntity("Sunday", LocalTime.parse("12:00:00"), LocalTime.parse("16:00:00"))
        );
    }

    public static String asJsonString(final Object object) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(object);
    }
}
