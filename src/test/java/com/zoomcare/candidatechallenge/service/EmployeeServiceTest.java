package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.EmployeeProperty;
import com.zoomcare.candidatechallenge.model.EmployeePropertyPK;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    public void testGetEmployeeById() {
        Long employee_id = 1L;
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(newEmployee(employee_id)));

        Optional<EmployeeDto> result = employeeService.getById(employee_id);
        assertTrue(result.isPresent());
        assertFalse(result.get().getProperties().isEmpty());
        assertEquals(2, result.get().getProperties().size());
        assertEquals("title", result.get().getProperties().get(0).getKey());
        assertEquals("Regional Director of Marketing", result.get().getProperties().get(0).getValue());
        assertEquals("region", result.get().getProperties().get(1).getKey());
        assertEquals("Europe", result.get().getProperties().get(1).getValue());
        assertFalse(result.get().getEmployees().isEmpty());
    }

    @Test
    public void testGetTopLevel() {
        List<Employee> topLevelEmployees = Arrays.asList(newEmployee(2L), newEmployee(3L), newEmployee(4L));

        when(employeeRepository.findAllBySupervisorIdIsNull()).thenReturn(topLevelEmployees);

        List<EmployeeDto> employees = employeeService.getTopLevel();

        assertFalse(employees.isEmpty());
        assertEquals(3, employees.size());
        assertEquals(2L, employees.get(0).getId().longValue());
        assertEquals(3L, employees.get(1).getId().longValue());
        assertEquals(4L, employees.get(2).getId().longValue());
    }


    private Employee newEmployee(Long id) {
        Employee employee = new Employee();
        employee.setId(id);

        List<EmployeeProperty> properties = Arrays.asList(
                generateEmployeeProperty(id, "title", "Regional Director of Marketing"),
                generateEmployeeProperty(id, "region", "Europe"));

        employee.setProperties(properties);

        employee.setEmployees(Collections.singletonList(new Employee()));

        return employee;
    }

    private EmployeeProperty generateEmployeeProperty(Long id, String key, String value) {
        EmployeeProperty employeeProperty = new EmployeeProperty();
        EmployeePropertyPK pk = new EmployeePropertyPK();
        pk.setKey(key);
        pk.setEmployeeId(id);
        employeeProperty.setId(pk);
        employeeProperty.setValue(value);
        return employeeProperty;
    }
}
