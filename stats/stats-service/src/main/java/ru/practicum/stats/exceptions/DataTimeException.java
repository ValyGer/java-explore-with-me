package ru.practicum.stats.exceptions;

import lombok.Getter;

@Getter
public class DataTimeException extends RuntimeException {
    public DataTimeException(String message) {
        super(message);
    }
}
