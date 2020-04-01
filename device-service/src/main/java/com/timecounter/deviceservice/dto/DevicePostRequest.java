package com.timecounter.deviceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DevicePostRequest {
    private Long userId;
    private Integer numberOfSides;
    private boolean isBatteryLow;
    private boolean isActive;
}
