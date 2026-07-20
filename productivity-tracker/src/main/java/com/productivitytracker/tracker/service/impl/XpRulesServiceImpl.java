package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.XpRulesDto;
import com.productivitytracker.tracker.entity.XpRules;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.XpRulesMapper;
import com.productivitytracker.tracker.repository.XpRulesRepository;
import com.productivitytracker.tracker.service.XpRulesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class XpRulesServiceImpl implements XpRulesService {

    private XpRulesRepository repository;

    @Override
    public XpRulesDto create(XpRulesDto dto) {
        XpRules entity = XpRulesMapper.mapToEntity(dto);
        XpRules saved = repository.save(entity);
        return XpRulesMapper.mapToDto(saved);
    }

    @Override
    public XpRulesDto getById(Long id) {
        XpRules entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("XpRule not found with id: " + id));

        return XpRulesMapper.mapToDto(entity);
    }

    @Override
    public List<XpRulesDto> getAll() {
        return repository.findAll()
                .stream()
                .map(XpRulesMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public XpRulesDto update(Long id, XpRulesDto dto) {

        XpRules entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("XpRule not found with id: " + id));

        entity.setAction(dto.getAction());
        entity.setXpValue(dto.getXpValue());

        XpRules updated = repository.save(entity);

        return XpRulesMapper.mapToDto(updated);
    }

    @Override
    public void delete(Long id) {

        XpRules entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("XpRule not found with id: " + id));

        repository.delete(entity);
    }
}
