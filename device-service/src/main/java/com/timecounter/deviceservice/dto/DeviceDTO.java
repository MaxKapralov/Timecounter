package com.timecounter.deviceservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
public class DeviceDTO {
    private String id;
    private Long userId;
    private Integer numberOfSides;
    private boolean isBatteryLow;
    private Set<DeviceSideDTO> sides;
}
