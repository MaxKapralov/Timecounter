package com.timecounter.statisticsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDTO {
    private String deviceId;
    private Long projectId;
    private Long time;
}
