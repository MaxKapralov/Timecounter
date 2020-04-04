package com.timecounter.statisticsservice.repository;

import com.timecounter.statisticsservice.domain.Statistic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticRepository extends CrudRepository<Statistic, Long> {
    List<Statistic> findByProjectIdAndDeviceId(Long projectId, String deviceId);
    Optional<Statistic> findByProjectIdAndDeviceIdAndStopTimeIsNull(Long projectId, String deviceId);
    Optional<Statistic> findByProjectIdAndDeviceIdAndStopTimeIsNotNull(Long projectId, String deviceId);
    List<Statistic> findAllByStopTimeIsNull();
}
