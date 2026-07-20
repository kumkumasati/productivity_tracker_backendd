package com.productivitytracker.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XpLogDto {
    private Long id;
    private Long userId;
    private String source;
    private Integer amount;
    private String createdAt;
}