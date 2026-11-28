package com.etyproject.schedulelab.schedule.repository;

import com.etyproject.schedulelab.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    Optional<Schedule> findByIdAndDeletedAtIsNull(UUID id);
}
