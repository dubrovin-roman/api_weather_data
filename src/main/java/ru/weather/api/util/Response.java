package ru.weather.api.util;

public class Response {
    private String message;
    private Long timeOfError;

    public Response() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeOfError() {
        return timeOfError;
    }

    public void setTimeOfError(Long timeOfError) {
        this.timeOfError = timeOfError;
    }
}
