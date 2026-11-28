package com.etyproject.schedulelab.schedule.repository;

import com.etyproject.schedulelab.schedule.domain.Schedule;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Schedule> findByIdAndDeletedAtIsNull(UUID id);
}
