package postech.fiap.fase3.reserva.domain.dto;

import postech.fiap.fase3.reserva.domain.WorkingHourEntity;

import java.util.List;

public record RestaurantDTO(
        String name,
        String address,
        String cuisineType,
        List<WorkingHourEntity> workingHours,
        Integer totalCapacity
) { }
