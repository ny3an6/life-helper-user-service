package com.lifehelper.userservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseStatus
public class InvalidDataException extends RuntimeException {

    private static final long serialVersionUID = 100L;
    private Map<String, Object> errorsMap = new HashMap<>();

    public Map<String, Object> getErrorsMap() {
        return errorsMap;
    }

    /**
     * Конструктор для последующего заполнения ошибками.
     *
     * @param message текст ошибки.
     */
    public InvalidDataException(String message) {
        super(message);
        this.errorsMap = new HashMap<>();
        errorsMap.put("error", message);
    }

    public InvalidDataException(String message, Map<String, Object> errorsMap) {
        super(message);
        this.errorsMap = errorsMap;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("<br>");
        errorsMap.forEach((key, value) -> sb.append(value).append("<br>"));
        return sb.toString();
    }
}
