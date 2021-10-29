package com.zjw.login.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.util.Set;

/**
 * @author jingw
 * @created 10/27/2021 11:01 AM
 * @project login
 */
@Entity
@Table(name = "sys_dept_1027")
@Getter
@Setter
public class Department {

    @Id
    @Column(name = "d_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "d_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<LoginUser> userSet;

}
