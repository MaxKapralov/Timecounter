package com.timecounter.deviceservice.service;

import com.timecounter.deviceservice.dto.DeviceDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Override
    public Mono<DeviceDTO> createDevice(DeviceDTO newDevice) {
        return null;
    }
}
