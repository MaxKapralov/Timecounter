package com.timecounter.statisticsservice.controller;

import com.timecounter.statisticsservice.dto.GroupedStatisticDTO;
import com.timecounter.statisticsservice.dto.StatisticDTO;
import com.timecounter.statisticsservice.service.StatisticGroups;
import com.timecounter.statisticsservice.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/statistics")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/spend-time/{projectId}/{deviceId}")
    public ResponseEntity<StatisticDTO> getSpendTimeForProject(@PathVariable("projectId") Long projectId, @PathVariable("deviceId") String deviceId) {
        StatisticDTO st = statisticService.getSpendTimeForProject(projectId, deviceId);
        return ResponseEntity.ok(st);
    }

    @PutMapping("/start/{projectId}/{deviceId}")
    public ResponseEntity<Void> startDeviceCounterForProject(@PathVariable("projectId") Long projectId, @PathVariable("deviceId") String deviceId) {
        statisticService.startDeviceCounterForProject(projectId, deviceId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/stop/{projectId}/{deviceId}")
    public ResponseEntity<Void> stopDeviceCounterForProject(@PathVariable("projectId") Long projectId, @PathVariable("deviceId") String deviceId) {
        statisticService.stopDeviceCounterForProject(projectId, deviceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{projectId}/{deviceId}")
    public ResponseEntity<Set<GroupedStatisticDTO>> getGroupedStatistics(@PathVariable("projectId") Long projectId,
                                                                         @PathVariable("deviceId") String deviceId,
                                                                         @RequestParam StatisticGroups query) {
        return ResponseEntity.ok(statisticService.getStatistics(projectId, deviceId, query));
    }
}
