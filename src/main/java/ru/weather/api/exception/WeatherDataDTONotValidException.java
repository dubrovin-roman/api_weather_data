package ru.weather.api.exception;

public class WeatherDataDTONotValidException extends RuntimeException {
    public WeatherDataDTONotValidException(String message) {
        super(message);
    }
}
