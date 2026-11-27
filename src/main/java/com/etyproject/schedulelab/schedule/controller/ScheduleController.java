package com.etyproject.schedulelab.schedule.controller;

import com.etyproject.schedulelab.schedule.domain.Schedule;
import com.etyproject.schedulelab.schedule.dto.CreateScheduleRequest;
import com.etyproject.schedulelab.schedule.dto.CreateScheduleResponse;
import com.etyproject.schedulelab.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreateScheduleResponse> create(
            @Valid @RequestBody CreateScheduleRequest request
    ){
        UUID id = scheduleService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateScheduleResponse(id));
    }
}
