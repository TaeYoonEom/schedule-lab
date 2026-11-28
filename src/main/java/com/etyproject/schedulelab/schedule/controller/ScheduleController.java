package com.etyproject.schedulelab.schedule.controller;

import com.etyproject.schedulelab.common.response.ApiResponse;
import com.etyproject.schedulelab.schedule.domain.Schedule;
import com.etyproject.schedulelab.schedule.dto.CreateScheduleRequest;
import com.etyproject.schedulelab.schedule.dto.CreateScheduleResponse;
import com.etyproject.schedulelab.schedule.dto.UpdateScheduleCompletionRequest;
import com.etyproject.schedulelab.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return ApiResponse.success(
                HttpStatus.CREATED,
                new CreateScheduleResponse(id)
        );
    }

    @PatchMapping("/{id}/completion")
    public ResponseEntity<Void> updateCompletion(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateScheduleCompletionRequest request
            ) {
        scheduleService.updateCompletion(id, request.completed());
        return ApiResponse.success(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable("id") UUID id
    ) {
        scheduleService.deleteSchedule(id);
        return ApiResponse.success(HttpStatus.NO_CONTENT);
    }
}
