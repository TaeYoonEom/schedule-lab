package com.etyproject.schedulelab.common.error;

import com.etyproject.schedulelab.common.response.ApiResponse;
import com.etyproject.schedulelab.schedule.exception.ScheduleErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.etyproject.schedulelab.schedule")
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException exception
    ) {
        FieldError fieldError = exception.getBindingResult().getFieldError();
        String field = fieldError == null ? "" : fieldError.getField();

        ErrorCode errorCode = switch (field) {
            case "type" -> ScheduleErrorCode.SCHEDULE_TYPE_REQUIRED;
            case "title" -> ScheduleErrorCode.SCHEDULE_TITLE_REQUIRED;
            case "startDate" -> ScheduleErrorCode.SCHEDULE_START_DATE_REQUIRED;
            default -> ScheduleErrorCode.INVALID_SCHEDULE_REQUEST;
        };

        return ApiResponse.error(errorCode);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessException(
            BusinessException exception
    ) {
        return ApiResponse.error(exception.getErrorCode());
    }
}