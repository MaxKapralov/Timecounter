package com.timecounter.deviceservice.mapper;

import com.timecounter.deviceservice.domain.Device;
import com.timecounter.deviceservice.domain.DeviceSide;
import com.timecounter.deviceservice.dto.DeviceDTO;
import com.timecounter.deviceservice.dto.DevicePostRequest;
import com.timecounter.deviceservice.dto.DevicePutRequest;
import com.timecounter.deviceservice.dto.DeviceSideDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DeviceMapperImpl implements DeviceMapper {

    private final DeviceSideMapper deviceSideMapper;

    public DeviceMapperImpl(DeviceSideMapper deviceSideMapper) {
        this.deviceSideMapper = deviceSideMapper;
    }

    @Override
    public Device toDevice(DevicePostRequest device) {
        Set<DeviceSide> sides = IntStream.range(0, device.getNumberOfSides())
                .mapToObj(num -> new DeviceSide(num, null))
                .collect(Collectors.toSet());

        return Device.builder()
                .userId(device.getUserId())
                .isBatteryLow(device.isBatteryLow())
                .isActive(device.isActive())
                .sides(sides)
                .build();
    }

    @Override
    public Device toDevice(DevicePutRequest device, String id, Long userId) {
        Set<DeviceSide> sides = device.getSides().stream()
                .map(deviceSideMapper::toDeviceSide)
                .collect(Collectors.toSet());

        return Device.builder()
                .id(id)
                .userId(userId)
                .isBatteryLow(device.isBatteryLow())
                .isActive(device.isActive())
                .sides(sides)
                .build();
    }

    @Override
    public DeviceDTO toDeviceDTO(Device device) {
        Set<DeviceSideDTO> sides = device.getSides().stream()
                .map(deviceSideMapper::toDeviceSideDTO)
                .collect(Collectors.toSet());
        return DeviceDTO.builder()
                .id(device.getId())
                .isBatteryLow(device.isBatteryLow())
                .isActive(device.isActive())
                .sides(sides)
                .build();
    }
}
