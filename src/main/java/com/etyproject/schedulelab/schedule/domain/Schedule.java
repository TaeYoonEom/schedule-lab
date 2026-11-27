package com.etyproject.schedulelab.schedule.domain;

import com.etyproject.schedulelab.common.error.BusinessException;
import com.etyproject.schedulelab.schedule.domain.enums.ScheduleType;
import com.etyproject.schedulelab.schedule.exception.ScheduleErrorCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "schedules")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScheduleType type;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalTime startTime;
    private LocalTime endTime;
    private String companyName;
    private String location;
    private String memo;

    @Column(nullable = false)
    private boolean completed;

    public Schedule(
            ScheduleType type,
            String title,
            LocalDate startDate,
            LocalTime startTime,
            LocalTime endTime,
            String companyName,
            String location,
            String memo
    ){
        if (endTime != null && (startTime == null || !endTime.isAfter(startTime))) {
            throw new BusinessException(ScheduleErrorCode.SCHEDULE_INVALID_TIME_RANGE);
        }

        if((type == ScheduleType.INTERVIEW || type == ScheduleType.APPLICATION_DEADLINE)
        && (companyName == null || companyName.isBlank())){
            throw new BusinessException(ScheduleErrorCode.SCHEDULE_COMPANY_REQUIRED);
        }

        this.type = type;
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.companyName = companyName;
        this.location = location;
        this.memo = memo;
    }

    public void changeCompletion(boolean completed){
        this.completed = completed;
    }
}
