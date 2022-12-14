package ru.weather.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.weather.api.model.WeatherData;

import java.util.List;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    List<WeatherData> findAllBySensor_SensorName(String sensorName);
}
