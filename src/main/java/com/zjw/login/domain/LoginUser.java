package com.zjw.login.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zjw.login.annotation.PasswordValidator;
import com.zjw.login.annotation.PasswordValidator2;
import com.zjw.login.marker.Create;
import com.zjw.login.marker.Update;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author jingw
 * @created 10/21/2021 10:51 PM
 * @project login
// */
@Entity
@Table(name = "sys_loginuser")
@Getter
@Setter
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "l_id")
    private  Long id;

    @Column(name = "l_username")
    private String username;

    //@JsonIgnore

    @Column(name = "l_password")
    @PasswordValidator(groups = Create.class)
    @PasswordValidator2(groups= Update.class)
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "d_id", name = "d_l_id")
    private Department department;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "u_t_join",
            joinColumns = {@JoinColumn(referencedColumnName = "l_id", name = "loginuser_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "t_id")})
    private Set<ApplicationTask> taskSet;

    @Override
    public String toString(){
        return this.getUsername()+":"+this.getPassword();
    }

}
