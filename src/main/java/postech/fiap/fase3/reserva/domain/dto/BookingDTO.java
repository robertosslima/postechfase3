package postech.fiap.fase3.reserva.domain.dto;

import java.time.LocalDateTime;

public record BookingDTO(
        Integer reservation,
        String clientName,
        LocalDateTime date,
        String restaurantId
) { }
