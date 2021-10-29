package com.zjw.login.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author jingw
 * @created 10/27/2021 2:07 PM
 * @project login
 */
@Entity
@Table(name = "sys_atsk_1027")
@Getter
@Setter
public class ApplicationTask {

    @Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_name")
    private String name;

    @ManyToMany(mappedBy = "taskSet", cascade = CascadeType.ALL)
    private Set<LoginUser> loginUserSet;

}
