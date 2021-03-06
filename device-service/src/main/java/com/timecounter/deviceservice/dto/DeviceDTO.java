package com.timecounter.deviceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceDTO {
    private String id;
    private boolean isBatteryLow;
    private boolean isActive;
    private Set<DeviceSideDTO> sides;
}
