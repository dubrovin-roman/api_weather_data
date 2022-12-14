package ru.weather.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.weather.api.dto.SensorDTO;
import ru.weather.api.exception.SensorDTONotValidException;
import ru.weather.api.service.SensorService;
import ru.weather.api.util.SensorDTOValidator;
import ru.weather.api.util.Util;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorDTOValidator sensorDTOValidator;

    @Autowired
    public SensorController(SensorService sensorService, SensorDTOValidator sensorDTOValidator) {
        this.sensorService = sensorService;
        this.sensorDTOValidator = sensorDTOValidator;
    }

    @PostMapping("/registration")
    private String registrationSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                      BindingResult bindingResult) {
        sensorDTOValidator.validate(sensorDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new SensorDTONotValidException(Util.getMessageErrorsFromBindingResult(bindingResult));
        }

        sensorService.saveSensor(sensorDTO.getSensor());

        return "Ok";
    }

}
