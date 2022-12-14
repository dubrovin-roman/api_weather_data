package ru.weather.api.service;

import ru.weather.api.dto.WeatherDataDTO;

import java.util.List;

public interface WeatherDataService {
    void saveWeatherData(WeatherDataDTO weatherDataDTO);

    List<WeatherDataDTO> getAllBySensorName(String sensorName);

    Long getRainyDaysCountBySensorName(String sensorName);
}
