package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.util.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<EmployeeDto> getTopLevel() {
        List<Employee> employees = employeeRepository.findAllBySupervisorIdIsNull();
        return employees.stream().map(DtoUtils::toEmployeeDto).collect(Collectors.toList());
    }

    public Optional<EmployeeDto> getById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(DtoUtils::toEmployeeDto);
    }

}
