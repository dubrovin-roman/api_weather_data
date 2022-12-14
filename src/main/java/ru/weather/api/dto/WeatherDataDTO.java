package ru.weather.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WeatherDataDTO {
    @NotNull(message = "значение измеренной температуры не должно быть пустым")
    @Min(value = -100, message = "значение температуры не должно быть меньше -100")
    @Max(value = 100, message = "значение температуры не должно быть больше 100")
    private Double value;
    @NotNull(message = "значение не должно быть пустым")
    private Boolean raining;
    @NotNull(message = "значение не должно быть пустым")
    private SensorDTO sensor;

    public WeatherDataDTO() {
    }

    public WeatherDataDTO(Double value, Boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
