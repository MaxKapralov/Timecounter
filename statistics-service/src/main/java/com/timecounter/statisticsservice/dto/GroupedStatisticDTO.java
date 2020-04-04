package com.timecounter.statisticsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupedStatisticDTO {
    private Long projectId;
    private String deviceId;
    private LocalDate key;
    private Long value;
}
