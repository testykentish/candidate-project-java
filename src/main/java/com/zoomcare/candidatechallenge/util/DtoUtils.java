package com.zoomcare.candidatechallenge.util;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.dto.EmployeePropertyDto;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.EmployeeProperty;
import com.zoomcare.candidatechallenge.model.EmployeePropertyPK;

import java.util.stream.Collectors;

public class DtoUtils {
    public static EmployeeDto toEmployeeDto(Employee employee) {
        EmployeeDto.EmployeeDtoBuilder builder =  EmployeeDto.builder()
                .id(employee.getId())
                .supervisorId(employee.getSupervisorId());

        if (employee.getProperties() != null) {
            builder.properties(employee.getProperties().stream().map(DtoUtils::toEmployeePropertyDto).collect(Collectors.toList()));
        }

        if (employee.getEmployees() != null) {
            builder.employees(employee.getEmployees().stream().map(DtoUtils::toEmployeeDto).collect(Collectors.toList()));
        }
        return builder.build();
    }

    public static EmployeePropertyDto toEmployeePropertyDto(EmployeeProperty employeeProperty) {
        EmployeePropertyPK id = employeeProperty.getId();
        return EmployeePropertyDto.builder()
                .key(id.getKey())
                .value(employeeProperty.getValue())
                .build();
    }
}
