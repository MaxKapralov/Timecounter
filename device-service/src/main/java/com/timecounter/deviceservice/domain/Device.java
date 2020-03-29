package com.timecounter.deviceservice.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@Document(collection = "device")
public class Device {

    @Id
    private String id;

    private Long userId;

    private Set<DeviceSide> sides;

    private boolean isBatteryLow;

    private boolean isActive;
}
