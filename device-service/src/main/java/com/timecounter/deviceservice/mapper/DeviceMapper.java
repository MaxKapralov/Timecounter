package com.timecounter.deviceservice.mapper;

import com.timecounter.deviceservice.domain.Device;
import com.timecounter.deviceservice.dto.DeviceDTO;
import com.timecounter.deviceservice.dto.DevicePostRequest;
import com.timecounter.deviceservice.dto.DevicePutRequest;

public interface DeviceMapper {
    Device toDevice(DevicePostRequest device);
    Device toDevice(DevicePutRequest device, String id, Long userId);
    DeviceDTO toDeviceDTO(Device device);
}
