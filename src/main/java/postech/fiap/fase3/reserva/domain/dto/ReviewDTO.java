package postech.fiap.fase3.reserva.domain.dto;

public record ReviewDTO(
        Integer rating,
        String comment,
        String clientName,
        String restaurantId
) { }
