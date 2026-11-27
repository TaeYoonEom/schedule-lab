package com.etyproject.schedulelab.schedule.dto;

import com.etyproject.schedulelab.schedule.domain.enums.ScheduleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateScheduleRequest(
        @NotNull(message = "일정 유형은 필수입니다.")
        ScheduleType type,

        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @NotNull(message = "시작 날짜는 필수입니다.")
        LocalDate startDate,

        LocalTime startTime,
        LocalTime endTime,
        String companyName,
        String location,
        String memo
) {
}
