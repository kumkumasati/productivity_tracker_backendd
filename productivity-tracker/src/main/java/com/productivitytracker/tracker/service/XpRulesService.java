package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.XpRulesDto;

import java.util.List;

public interface XpRulesService {

    XpRulesDto create(XpRulesDto dto);

    XpRulesDto getById(Long id);

    List<XpRulesDto> getAll();

    XpRulesDto update(Long id, XpRulesDto dto);

    void delete(Long id);
}