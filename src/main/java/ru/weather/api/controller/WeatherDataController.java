package ru.weather.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.weather.api.dto.WeatherDataDTO;
import ru.weather.api.exception.WeatherDataDTONotValidException;
import ru.weather.api.service.WeatherDataService;
import ru.weather.api.util.Util;
import ru.weather.api.util.WeatherDataDTOValidator;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class WeatherDataController {
    private final WeatherDataService weatherDataService;
    private final WeatherDataDTOValidator weatherDataDTOValidator;

    @Autowired
    public WeatherDataController(WeatherDataService weatherDataService, WeatherDataDTOValidator weatherDataDTOValidator) {
        this.weatherDataService = weatherDataService;
        this.weatherDataDTOValidator = weatherDataDTOValidator;
    }

    @PostMapping("/add")
    public String addMeasurements(@RequestBody @Valid WeatherDataDTO weatherDataDTO,
                                  BindingResult bindingResult) {
        weatherDataDTOValidator.validate(weatherDataDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new WeatherDataDTONotValidException(Util.getMessageErrorsFromBindingResult(bindingResult));
        }

        weatherDataService.saveWeatherData(weatherDataDTO);

        return "Ok";
    }

    @GetMapping()
    public List<WeatherDataDTO> getAllMeasurementsBySensorName(@RequestParam("sensorName") String sensorName) {
        return weatherDataService.getAllBySensorName(sensorName);
    }

    @GetMapping("/rainyDaysCount")
    public String getRainyDaysCountBySensorName(@RequestParam("sensorName") String sensorName) {
        return "Количество дождливых дней у сенсора "
                + sensorName
                + " равно "
                + weatherDataService.getRainyDaysCountBySensorName(sensorName);
    }
}
