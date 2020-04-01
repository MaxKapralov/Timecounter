package com.timecounter.deviceservice.service;

import com.timecounter.deviceservice.domain.Device;
import com.timecounter.deviceservice.dto.DeviceDTO;
import com.timecounter.deviceservice.dto.DevicePostRequest;
import com.timecounter.deviceservice.dto.DevicePutRequest;
import com.timecounter.deviceservice.mapper.DeviceMapperImpl;
import com.timecounter.deviceservice.repository.DeviceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapperImpl deviceMapper;

    public DeviceServiceImpl(DeviceRepository deviceRepository, DeviceMapperImpl deviceMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceMapper = deviceMapper;
    }

    @Override
    public Mono<DeviceDTO> createDevice(DevicePostRequest device) {
        return deviceRepository.save(deviceMapper.toDevice(device))
                .map(deviceMapper::toDeviceDTO);
    }

    @Override
    public Mono<DeviceDTO> updateDevice(DevicePutRequest device, String id) {
        return deviceRepository.findById(id).flatMap(entity -> {
            Device updatedDevice = deviceMapper.toDevice(device, id, entity.getUserId());
            return deviceRepository.save(updatedDevice)
                    .map(deviceMapper::toDeviceDTO);
        });
    }
}
