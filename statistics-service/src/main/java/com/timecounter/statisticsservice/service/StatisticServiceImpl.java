package com.timecounter.statisticsservice.service;

import com.timecounter.statisticsservice.domain.Statistic;
import com.timecounter.statisticsservice.dto.GroupedStatisticDTO;
import com.timecounter.statisticsservice.dto.StatisticDTO;
import com.timecounter.statisticsservice.exception.StatisticException;
import com.timecounter.statisticsservice.repository.StatisticRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;

    private Function<Statistic, LocalDate> groupByDay = st -> st.getStartTime().toLocalDate();

    private Function<Statistic, LocalDate> groupByWeek = st -> st.getStartTime().toLocalDate()
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.of(1)));

    private Function<Statistic, LocalDate> groupByMonth = st -> st.getStartTime().toLocalDate()
            .with(TemporalAdjusters.firstDayOfMonth());

    private Function<Statistic, LocalDate> groupByYear = st -> st.getStartTime().toLocalDate()
            .with(TemporalAdjusters.firstDayOfYear());

    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public StatisticDTO getSpendTimeForProject(Long projectId, String deviceId) {
        Long time = getStatisticsTime(statisticRepository.findByProjectIdAndDeviceId(projectId, deviceId).stream());
        return new StatisticDTO(deviceId, projectId, time);
    }

    @Override
    public Set<GroupedStatisticDTO> getStatistics(Long projectId, String deviceId, StatisticGroups group) {
        switch(group) {
            case DAY: return getStatistics(projectId, deviceId, groupByDay);
            case WEEK: return getStatistics(projectId, deviceId, groupByWeek);
            case MONTH: return getStatistics(projectId, deviceId, groupByMonth);
            case YEAR: return getStatistics(projectId, deviceId, groupByYear);
        }
        throw new StatisticException("Unknown group");
    }

    private Set<GroupedStatisticDTO> getStatistics(Long projectId, String deviceId, Function<Statistic, LocalDate> groupByFunc) {
        return statisticRepository.findByProjectIdAndDeviceId(projectId, deviceId).stream()
                .collect(Collectors.groupingBy(groupByFunc))
                .entrySet().stream()
                .map(entry -> {
                    Long time = getStatisticsTime(entry.getValue().stream());
                    return GroupedStatisticDTO.builder()
                            .projectId(projectId)
                            .deviceId(deviceId)
                            .key(entry.getKey())
                            .value(time)
                            .build();
                }).collect(Collectors.toSet());
    }

    @Override
    public void startDeviceCounterForProject(Long projectId, String deviceId) {
        statisticRepository.findByProjectIdAndDeviceIdAndStopTimeIsNull(projectId, deviceId)
                .ifPresent(st -> {
                    throw new StatisticException("Unable to start. Counting is already started.");
                });
        Statistic st = new Statistic(deviceId, projectId, LocalDateTime.now(), null);
        this.statisticRepository.save(st);
    }

    @Override
    public void stopDeviceCounterForProject(Long projectId, String deviceId) {
        Statistic st = statisticRepository.findByProjectIdAndDeviceIdAndStopTimeIsNull(projectId, deviceId)
                .orElseThrow(() -> new StatisticException("Unable to stop. Counting already stopped"));
        st.setStopTime(LocalDateTime.now());
        statisticRepository.save(st);
    }

    private Long duration(Statistic st) {
        LocalDateTime stopTimeOpt = st.getStopTime();
        LocalDateTime stopTime = stopTimeOpt == null ? LocalDateTime.now() : stopTimeOpt;
        return Duration.between(st.getStartTime(), stopTime).getSeconds();
    }

    private Long getStatisticsTime(Stream<Statistic> stream) {
        return stream.map(this::duration).reduce(0L, (acc, next) -> acc + next);
    }
}
