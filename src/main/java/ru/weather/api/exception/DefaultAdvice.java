package ru.weather.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.weather.api.util.Response;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(SensorDTONotValidException.class)
    public ResponseEntity<Response> handelSensorDTONotValidException(SensorDTONotValidException e) {
        Response response = new Response();
        response.setMessage(e.getMessage());
        response.setTimeOfError(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WeatherDataDTONotValidException.class)
    public ResponseEntity<Response> handelWeatherDataDTONotValidException(WeatherDataDTONotValidException e) {
        Response response = new Response();
        response.setMessage(e.getMessage());
        response.setTimeOfError(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
