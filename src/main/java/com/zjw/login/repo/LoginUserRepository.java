package com.zjw.login.repo;

import com.zjw.login.domain.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author jingw
 * @created 10/24/2021 5:16 PM
 * @project login
 */

public interface LoginUserRepository extends JpaRepository<LoginUser, Long>, JpaSpecificationExecutor<LoginUser> {

    /**
     *
     * @param username
     * @return detailed LoginUser info
     */
    LoginUser findByUsername(String username);

}
