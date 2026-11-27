package com.etyproject.schedulelab.schedule.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateScheduleCompletionRequest(
        @NotNull(message = "완료 여부는 필수입니다.")
        Boolean completed
) {
}
