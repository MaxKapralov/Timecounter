package com.timecounter.statisticsservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String deviceId;

    private Long projectId;

    private LocalDateTime startTime;

    private LocalDateTime stopTime;

    public Statistic(String deviceId, Long projectId, LocalDateTime startTime, LocalDateTime stopTime) {
        this.deviceId = deviceId;
        this.projectId = projectId;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }
}
