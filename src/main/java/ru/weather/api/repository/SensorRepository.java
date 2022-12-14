package ru.weather.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.weather.api.model.Sensor;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Optional<Sensor> findBySensorName(String sensorName);
}
