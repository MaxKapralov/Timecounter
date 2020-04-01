package com.timecounter.deviceservice.service;

import com.timecounter.deviceservice.dto.DeviceDTO;
import com.timecounter.deviceservice.dto.DevicePostRequest;
import com.timecounter.deviceservice.dto.DevicePutRequest;
import reactor.core.publisher.Mono;

public interface DeviceService {
    Mono<DeviceDTO> createDevice(DevicePostRequest device);
    Mono<DeviceDTO> updateDevice(DevicePutRequest device, String id);
}
