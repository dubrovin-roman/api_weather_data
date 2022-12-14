package ru.weather.api.exception;

public class SensorDTONotValidException extends RuntimeException {
    public SensorDTONotValidException(String message) {
        super(message);
    }
}
