package ru.weather.api.util;

import org.springframework.validation.BindingResult;

public class Util {
    public static String getMessageErrorsFromBindingResult(BindingResult bindingResult) {
        StringBuilder message = new StringBuilder();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            message.append(fieldError.getField())
                    .append(" : ")
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
        });
        return message.toString();
    }
}
