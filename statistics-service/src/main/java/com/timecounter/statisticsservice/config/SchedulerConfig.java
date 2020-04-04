package com.timecounter.statisticsservice.config;

import com.timecounter.statisticsservice.domain.Statistic;
import com.timecounter.statisticsservice.repository.StatisticRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    private final StatisticRepository statisticRepository;

    public SchedulerConfig(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void resetTimeCounter() {
        LocalDateTime midnight = LocalDate.now().atStartOfDay();
        List<Statistic> records = statisticRepository.findAllByStopTimeIsNull().stream()
                .map(st -> {
                    Statistic newRecord = Statistic.builder()
                            .deviceId(st.getDeviceId())
                            .projectId(st.getProjectId())
                            .stopTime(null)
                            .startTime(midnight)
                            .build();
                    st.setStopTime(midnight);
                    return Arrays.asList(st, newRecord);
                }).flatMap(List::stream).collect(Collectors.toList());
        statisticRepository.saveAll(records);
    }
}
