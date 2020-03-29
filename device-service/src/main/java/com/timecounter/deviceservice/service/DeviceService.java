package com.timecounter.deviceservice.service;

import com.timecounter.deviceservice.dto.DeviceDTO;
import reactor.core.publisher.Mono;

public interface DeviceService {
    Mono<DeviceDTO> createDevice(DeviceDTO newDevice);
}
