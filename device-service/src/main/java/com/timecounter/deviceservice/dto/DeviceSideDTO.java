package com.timecounter.deviceservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class DeviceSideDTO {
    private String id;
    private Long projectId;
    private String name;
}
