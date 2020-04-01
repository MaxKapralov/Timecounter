package com.timecounter.deviceservice.mapper;

import com.timecounter.deviceservice.domain.DeviceSide;
import com.timecounter.deviceservice.dto.DeviceSideDTO;
import org.springframework.stereotype.Component;

@Component
public class DeviceSideMapperImpl implements DeviceSideMapper{
    @Override
    public DeviceSide toDeviceSide(DeviceSideDTO side) {
        return DeviceSide.builder()
                .id(side.getId())
                .projectId(side.getProjectId())
                .build();
    }

    @Override
    public DeviceSideDTO toDeviceSideDTO(DeviceSide side) {
        return DeviceSideDTO.builder()
                .id(side.getId())
                .projectId(side.getProjectId())
                .projectName("Project Name") //todo add project name here
                .build();
    }
}
