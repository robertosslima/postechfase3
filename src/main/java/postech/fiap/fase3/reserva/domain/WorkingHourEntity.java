package postech.fiap.fase3.reserva.domain;


import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Embeddable
@Data
public class WorkingHourEntity implements Serializable {
    private String weekday;
    private LocalTime openTime;
    private LocalTime closeTime;

    public WorkingHourEntity() { }

    public WorkingHourEntity(String weekday, LocalTime openTime, LocalTime closeTime) {
        this.weekday = weekday;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
