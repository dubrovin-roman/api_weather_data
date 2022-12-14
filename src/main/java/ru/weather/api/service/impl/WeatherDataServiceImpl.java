package ru.weather.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weather.api.dto.SensorDTO;
import ru.weather.api.dto.WeatherDataDTO;
import ru.weather.api.model.Sensor;
import ru.weather.api.model.WeatherData;
import ru.weather.api.repository.SensorRepository;
import ru.weather.api.repository.WeatherDataRepository;
import ru.weather.api.service.WeatherDataService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private final WeatherDataRepository weatherDataRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public WeatherDataServiceImpl(WeatherDataRepository weatherDataRepository, SensorRepository sensorRepository) {
        this.weatherDataRepository = weatherDataRepository;
        this.sensorRepository = sensorRepository;
    }

    @Override
    public void saveWeatherData(WeatherDataDTO weatherDataDTO) {
        Sensor sensor = sensorRepository.findBySensorName(weatherDataDTO.getSensor().getName()).get();
        WeatherData weatherData = new WeatherData();
        weatherData.setValue(weatherDataDTO.getValue());
        weatherData.setRaining(weatherDataDTO.getRaining());
        weatherData.setMeasurementTime(LocalDateTime.now());
        weatherData.setSensor(sensor);
        weatherDataRepository.save(weatherData);
    }

    @Override
    public List<WeatherDataDTO> getAllBySensorName(String sensorName) {
        List<WeatherDataDTO> dataDTOList = new ArrayList<>();
        weatherDataRepository.findAllBySensor_SensorName(sensorName).forEach(weatherData -> {
            WeatherDataDTO weatherDataDTO = new WeatherDataDTO(
                    weatherData.getValue(),
                    weatherData.getRaining(),
                    new SensorDTO(sensorName)
            );
            dataDTOList.add(weatherDataDTO);
        });
        return dataDTOList;
    }

    @Override
    public Long getRainyDaysCountBySensorName(String sensorName) {
        Long count = 0L;

        List<WeatherData> weatherDataList = weatherDataRepository.findAllBySensor_SensorName(sensorName);

        HashSet<LocalDate> localDates = new HashSet<>();
        weatherDataList.forEach(weatherData -> localDates.add(weatherData.getMeasurementTime().toLocalDate()));

        HashMap<LocalDate, List<WeatherData>> weatherDataByDate = new HashMap<>();
        for (LocalDate date : localDates) {
            List<WeatherData> weatherDataTemp = weatherDataList.stream()
                    .filter(weatherData -> weatherData.getMeasurementTime().toLocalDate().equals(date)).toList();
            weatherDataByDate.put(date, weatherDataTemp);
        }

        for (Map.Entry<LocalDate, List<WeatherData>> entry : weatherDataByDate.entrySet()) {
            for (WeatherData data : entry.getValue()) {
                if (data.getRaining()) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }
}
