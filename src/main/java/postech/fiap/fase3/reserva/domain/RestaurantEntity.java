package postech.fiap.fase3.reserva.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class RestaurantEntity {
    @Id
    private UUID id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @NotEmpty
    private String cuisineType;

    @ElementCollection
    private List<WorkingHourEntity> workingHours;

    @NotNull
    private Integer totalCapacity;
}
