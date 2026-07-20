package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.XpLogDto;

import java.util.List;

public interface XpLogService {

    XpLogDto create(XpLogDto dto);

    XpLogDto getById(Long id);

    List<XpLogDto> getAll();

    void delete(Long id);
}
