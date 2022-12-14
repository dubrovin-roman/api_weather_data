package ru.weather.api.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import ru.weather.api.model.Sensor;

public class SensorDTO {
    @NotNull(message = "значение поля не должно быть пустым")
    @Length(min = 3, max = 30, message = "имя должно быть не менее 3 и не более 30 символов")
    private String name;

    public SensorDTO() {
    }

    public SensorDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sensor getSensor() {
        Sensor sensor = new Sensor();
        sensor.setSensorName(this.name);
        return sensor;
    }
}
