package com.etyproject.schedulelab.schedule.domain.enums;

import jakarta.persistence.Enumerated;

public enum ScheduleType {
    INTERVIEW,             // 면접
    APPLICATION_DEADLINE,  // 지원 마감
    APPOINTMENT,           // 약속
    BAND_REHEARSAL,         // 밴드 합주
    PERSONAL_PLAN          // 개인 계획
}
