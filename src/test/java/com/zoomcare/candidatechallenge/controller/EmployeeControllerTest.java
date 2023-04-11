package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest extends TestCase {
    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllTopLevelsEmployees() throws Exception{
        //When
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/employee/top"));

        //Then
        result.andExpect(status().isOk());
    }

    @Test
    public void testGetEmployeeById() throws Exception{
        //When
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", 22L));

        //Then
        result.andExpect(status().isNotFound());
    }

    @Test
    public void testGetEmployeeByIdS() throws Exception{
        Long id = 4434L;
        Optional<EmployeeDto> employeeDto = Optional.of(EmployeeDto.builder()
                .id(id)
                .build());
        doReturn(employeeDto).when(employeeService).getById(id);

        //When
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", id));

        //Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(id));
    }
}
