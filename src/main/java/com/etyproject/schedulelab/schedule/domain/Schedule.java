package com.etyproject.schedulelab.schedule.domain;

import com.etyproject.schedulelab.schedule.domain.enums.ScheduleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "schedules")
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
        if(endTime != null && startTime == null || !endTime.isAfter(startTime)){
            throw new IllegalArgumentException("종료 시각은 시작 시각보다 늦어야 합니다.");
        }

        if((type == ScheduleType.INTERVIEW || type == ScheduleType.APPLICATION_DEADLINE)
        && (companyName == null || companyName.isBlank())){
            throw new IllegalArgumentException("면접과 지원 마감 일정에는 회사 명이 필요합니다.");
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
