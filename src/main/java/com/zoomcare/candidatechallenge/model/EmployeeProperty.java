package com.zoomcare.candidatechallenge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "property")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmployeeProperty implements Serializable {

    @EmbeddedId
    private EmployeePropertyPK id;

    private String value;
}
