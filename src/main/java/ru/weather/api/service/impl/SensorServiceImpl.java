package ru.weather.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weather.api.model.Sensor;
import ru.weather.api.repository.SensorRepository;
import ru.weather.api.service.SensorService;

@Service
public class SensorServiceImpl implements SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public void saveSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
