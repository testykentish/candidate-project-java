package com.zoomcare.candidatechallenge.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeePropertyDto {
    private String key;
    private String value;
}
