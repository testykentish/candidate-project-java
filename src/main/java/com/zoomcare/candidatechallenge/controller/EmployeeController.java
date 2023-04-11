package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.exception.NotFoundException;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/top")
    public List<EmployeeDto> getTopLevel() {
        return employeeService.getTopLevel();
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable("id") Long id) {
        return employeeService.getById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
