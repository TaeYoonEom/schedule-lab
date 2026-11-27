package com.etyproject.schedulelab.common.response;

import com.etyproject.schedulelab.common.error.ApiErrorResponse;
import com.etyproject.schedulelab.common.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ApiResponse {

    private ApiResponse() {
    }

    public static <T> ResponseEntity<T> success(HttpStatus status, T body) {
        return ResponseEntity.status(status).body(body);
    }

    public static ResponseEntity<Void> success(HttpStatus status) {
        return ResponseEntity.status(status).build();
    }

    public static ResponseEntity<ApiErrorResponse> error(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus())
                .body(new ApiErrorResponse(
                        errorCode.name(),
                        errorCode.getType(),
                        errorCode.getMessage()
                ));
    }
}