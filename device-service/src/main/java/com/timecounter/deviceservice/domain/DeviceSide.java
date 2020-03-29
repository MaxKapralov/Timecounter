package com.timecounter.deviceservice.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class DeviceSide {

    private Integer sideId;

    private Long projectId;

}
