package com.timecounter.deviceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DevicePutRequest {
    private String id;
    private boolean isBatteryLow;
    private boolean isActive;
    private Set<DeviceSideDTO> sides;
}
