package com.timecounter.deviceservice.mapper;

import com.timecounter.deviceservice.domain.DeviceSide;
import com.timecounter.deviceservice.dto.DeviceSideDTO;

public interface DeviceSideMapper {
    DeviceSide toDeviceSide(DeviceSideDTO side);
    DeviceSideDTO toDeviceSideDTO(DeviceSide side);
}
