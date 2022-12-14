package ru.weather.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.weather.api.dto.SensorDTO;
import ru.weather.api.model.Sensor;
import ru.weather.api.repository.SensorRepository;

import java.util.Optional;

@Component
public class SensorDTOValidator implements Validator {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorDTOValidator(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        Optional<Sensor> optional = sensorRepository.findBySensorName(sensorDTO.getName());
        String message = "Сенсор с именем " + sensorDTO.getName() + " уже существует";

        if (optional.isPresent()) {
            errors.rejectValue("name", "", message);
        }
    }
}
