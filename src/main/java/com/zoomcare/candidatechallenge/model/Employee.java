package com.zoomcare.candidatechallenge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Employee {
    @Id
    private Long id;

    @Column(name = "supervisor_id")
    private Long supervisorId;

    @Fetch(FetchMode.JOIN)
    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<EmployeeProperty> properties;

    @Fetch(FetchMode.JOIN)
    @OneToMany
    @JoinColumn(name = "supervisor_id")
    private List<Employee> employees;
}
