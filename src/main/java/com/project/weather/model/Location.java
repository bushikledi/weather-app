package com.project.weather.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    private Integer locationId;
    private Double latitude;
    private Double longitude;
    @NotBlank
    private String cityName;
    @NotBlank
    private String countyName;
    @OneToMany
    @JoinColumn(name = "location_id")
    private List<Weather> weathers;
}
