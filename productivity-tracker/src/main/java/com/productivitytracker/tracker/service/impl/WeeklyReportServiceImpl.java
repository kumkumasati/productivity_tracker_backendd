package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.WeeklyReportDto;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.entity.WeeklyReport;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.WeeklyReportMapper;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.repository.WeeklyReportRepository;
import com.productivitytracker.tracker.service.WeeklyReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WeeklyReportServiceImpl implements WeeklyReportService {


    private WeeklyReportRepository repository;
    private UserRepository userRepository;

    @Override
    public WeeklyReportDto create(WeeklyReportDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

        WeeklyReport report = WeeklyReportMapper.mapToEntity(dto);
        report.setUser(user);

        WeeklyReport saved = repository.save(report);

        return WeeklyReportMapper.mapToDto(saved);
    }

    @Override
    public WeeklyReportDto getById(Long id) {
        WeeklyReport report = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Weekly report not found: " + id));

        return WeeklyReportMapper.mapToDto(report);
    }

    @Override
    public List<WeeklyReportDto> getAll() {
        return repository.findAll()
                .stream()
                .map(WeeklyReportMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public WeeklyReportDto update(Long id, WeeklyReportDto dto) {

        WeeklyReport report = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Weekly report not found: " + id));

        report.setStrongestDay(dto.getStrongestDay());
        report.setWeakestDay(dto.getWeakestDay());
        report.setTotalTask(dto.getTotalTask());
        report.setCompletedTask(dto.getCompletedTask());
        report.setTotalXp(dto.getTotalXp());
        report.setCompletionRate(dto.getCompletionRate());
        report.setStreak(dto.getStreak());
        report.setSummary(dto.getSummary());
        report.setTips(dto.getTips());

        WeeklyReport updated = repository.save(report);

        return WeeklyReportMapper.mapToDto(updated);
    }

    @Override
    public void delete(Long id) {
        WeeklyReport report = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Weekly report not found: " + id));

        repository.delete(report);
    }
}
