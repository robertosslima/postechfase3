package postech.fiap.fase3.reserva.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class ReviewEntity {
    @Id
    private UUID id;

    @Max(5)
    @Min(0)
    private Integer rating;

    private String comment;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private String clientName;

    @NotNull
    private UUID restaurantId;
}
