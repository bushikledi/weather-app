package com.project.weather.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    @Id
    private Integer weatherId;
    private Instant date;
    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Double windDirection;
    private Double windSpeed;
}
