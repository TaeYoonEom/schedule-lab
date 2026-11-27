package com.etyproject.schedulelab.schedule.exception;

import com.etyproject.schedulelab.common.error.ErrorCode;
import com.etyproject.schedulelab.common.error.ErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ScheduleErrorCode implements ErrorCode {

    INVALID_SCHEDULE_REQUEST(HttpStatus.BAD_REQUEST, ErrorType.VALIDATION, "일정 요청값을 확인해 주세요."),
    SCHEDULE_TYPE_REQUIRED(HttpStatus.BAD_REQUEST, ErrorType.VALIDATION, "일정 유형은 필수입니다."),
    SCHEDULE_TITLE_REQUIRED(HttpStatus.BAD_REQUEST, ErrorType.VALIDATION, "제목은 필수입니다."),
    SCHEDULE_START_DATE_REQUIRED(HttpStatus.BAD_REQUEST, ErrorType.VALIDATION, "시작 날짜는 필수입니다."),
    SCHEDULE_COMPANY_REQUIRED(HttpStatus.BAD_REQUEST, ErrorType.VALIDATION,"면접과 지원 마감 일정에는 회사명이 필요합니다."),
    SCHEDULE_INVALID_TIME_RANGE(HttpStatus.BAD_REQUEST, ErrorType.VALIDATION,"종료 시각을 입력하려면 시작 시각이 있어야 하고, 종료 시각은 시작 시각보다 늦어야 합니다.");

    private final HttpStatus status;
    private final ErrorType type;
    private final String message;
}
