package com.etyproject.schedulelab.common.error;

public record ApiErrorResponse(
        String name,
        ErrorType type,
        String message
) {
}
