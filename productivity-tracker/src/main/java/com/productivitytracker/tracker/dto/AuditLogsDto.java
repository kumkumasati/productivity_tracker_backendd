package com.productivitytracker.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogsDto {
    private Long id;
    private Long userId;
    private String action;
    private String metadata;
    private String createdAt;
}
