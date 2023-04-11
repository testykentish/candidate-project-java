package com.zoomcare.candidatechallenge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class EmployeePropertyPK implements Serializable {
    @Column(name = "key")
    private String key;

    @Column(name = "employee_id")
    private Long employeeId;
}