package ru.weather.api.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.weather.api.dto.SensorDTO;
import ru.weather.api.dto.WeatherDataDTO;
import ru.weather.api.model.Sensor;
import ru.weather.api.repository.SensorRepository;

import java.util.Optional;

@Component
public class WeatherDataDTOValidator implements Validator {
    private final SensorRepository sensorRepository;

    @Autowired
    public WeatherDataDTOValidator(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return WeatherDataDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        WeatherDataDTO weatherDataDTO = (WeatherDataDTO) target;
        SensorDTO sensorDTO = weatherDataDTO.getSensor();
        if (sensorDTO == null)
            return;

        String sensorName = weatherDataDTO.getSensor().getName();
        if (sensorName == null) {
            errors.rejectValue("sensor", "", "имя сенсора не должно быть пустым");
            return;
        }

        Optional<Sensor> optional = sensorRepository.findBySensorName(sensorName);

        if (optional.isEmpty())
            errors.rejectValue("sensor", "", "указанный сенсор отсутствует в базе");
    }
}
