package postech.fiap.fase3.reserva.domain.use_cases;

import postech.fiap.fase3.reserva.domain.dto.RestaurantDTO;
import postech.fiap.fase3.reserva.domain.RestaurantEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class RestaurantUseCases {

    private RestaurantUseCases() { }

    public static RestaurantEntity registerRestaurant(RestaurantDTO restaurantDTO) {
        validateRestaurantDTO(restaurantDTO);
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(UUID.randomUUID());
        restaurantEntity.setName(restaurantDTO.name());
        restaurantEntity.setAddress(restaurantDTO.address());
        restaurantEntity.setCuisineType(restaurantDTO.cuisineType());
        restaurantEntity.setWorkingHours(restaurantDTO.workingHours());
        restaurantEntity.setTotalCapacity(restaurantDTO.totalCapacity());
        return restaurantEntity;
    }

    private static void validateRestaurantDTO(RestaurantDTO restaurantDTO) throws IllegalArgumentException {
        if (
                restaurantDTO.name() == null ||
                restaurantDTO.name().isEmpty() ||
                restaurantDTO.name().isBlank()
        ) {
            throw new IllegalArgumentException("Invalid name value");
        }

        if (
                restaurantDTO.address() == null ||
                restaurantDTO.address().isEmpty() ||
                restaurantDTO.address().isBlank()
        ) {
            throw new IllegalArgumentException("Invalid address value");
        }

        if (
                restaurantDTO.cuisineType() == null ||
                restaurantDTO.cuisineType().isEmpty() ||
                restaurantDTO.cuisineType().isBlank()
        ) {
            throw new IllegalArgumentException("Invalid cuisineType value");
        }

        final List<String> allDays = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        boolean hasAllDays = restaurantDTO.workingHours().stream()
                .allMatch(w -> allDays.contains(w.getWeekday()));
        if (!hasAllDays) {
            throw new IllegalArgumentException("Invalid workingHours list");
        }

        boolean validOpenCloseHours = restaurantDTO.workingHours().stream()
                .allMatch(w -> w.getCloseTime().isAfter(w.getOpenTime()));
        if (!validOpenCloseHours) {
            throw new IllegalArgumentException("Invalid open close hours");
        }

        if(restaurantDTO.totalCapacity() <= 0) {
            throw new IllegalArgumentException("Invalid totalCapacity value");
        }
    }
}
