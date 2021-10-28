package com.zjw.login.util;

import com.zjw.login.domain.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

/**
 * @author jingw
 * @created 10/26/2021 2:36 PM
 * @project login
 */
@Slf4j
public class MyProcessor implements ItemProcessor<LoginUser, LoginUser> {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public LoginUser process(LoginUser loginUser) throws Exception {
        LoginUser user = new LoginUser();
        user.setUsername(loginUser.getUsername().toUpperCase());
        user.setPassword(loginUser.getPassword().toLowerCase());
        log.info("log info:"+ user.toString());
        //jobRepository.
        return user;
    }
}
