package com.timecounter.statisticsservice.service;

import com.timecounter.statisticsservice.dto.GroupedStatisticDTO;
import com.timecounter.statisticsservice.dto.StatisticDTO;

import java.util.Set;

public interface StatisticService {
    StatisticDTO getSpendTimeForProject(Long projectId, String deviceId);

    void startDeviceCounterForProject(Long projectId, String deviceId);

    void stopDeviceCounterForProject(Long projectId, String deviceId);

    Set<GroupedStatisticDTO> getStatistics(Long projectId, String deviceId, StatisticGroups group);
}
