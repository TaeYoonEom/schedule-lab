package com.etyproject.schedulelab.common.error;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name();
    HttpStatus getStatus();
    ErrorType getType();
    String getMessage();
}
