package com.etyproject.schedulelab.schedule.service;

import com.etyproject.schedulelab.common.error.BusinessException;
import com.etyproject.schedulelab.schedule.domain.Schedule;
import com.etyproject.schedulelab.schedule.dto.CreateScheduleRequest;
import com.etyproject.schedulelab.schedule.exception.ScheduleErrorCode;
import com.etyproject.schedulelab.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public UUID create(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.type(),
                request.title(),
                request.startDate(),
                request.startTime(),
                request.endTime(),
                request.companyName(),
                request.location(),
                request.memo()
        );

        Schedule saved = scheduleRepository.save(schedule);
        return saved.getId();
    }

    @Transactional
    public void updateCompletion(UUID id, boolean completed) {
        Schedule schedule = scheduleRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new BusinessException(ScheduleErrorCode.SCHEDULE_NOT_FOUND));

        schedule.changeCompletion(completed);
    }

    @Transactional
    public void deleteSchedule(UUID id) {
        Schedule schedule = scheduleRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new BusinessException(ScheduleErrorCode.SCHEDULE_NOT_FOUND));
        schedule.markDeleted(Instant.now());
    }
}
