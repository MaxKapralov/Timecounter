package com.timecounter.deviceservice.repository;

import com.timecounter.deviceservice.domain.Device;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends ReactiveCrudRepository<Device, String> {
}
