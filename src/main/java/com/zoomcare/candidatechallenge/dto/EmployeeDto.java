package com.zoomcare.candidatechallenge.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EmployeeDto {
    private Long id;
    private Long supervisorId;
    private List<EmployeePropertyDto> properties;
    private List<EmployeeDto> employees;
}
