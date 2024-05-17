package postech.fiap.fase3.reserva.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class BookingEntity {
    @Id
    private UUID id;

    @NotNull
    private Integer reservation;

    @NotNull
    private String clientName;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private UUID restaurantId;
}
