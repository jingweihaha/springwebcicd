package com.zjw.login.service;

import com.zjw.login.domain.LoginUser;
import com.zjw.login.repo.LoginUserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author jingw
 * @created 10/24/2021 5:27 PM
 * @project login
 */
@Service
@Slf4j
public class LoginUserServiceImpl {

    @Autowired
    private LoginUserRepository loginUserRepository;

    @Transactional
    public LoginUser registerNewUser(LoginUser user) throws IOException {
//        try {
        LoginUser save = loginUserRepository.save(user);
        return save;
        //throw new IOException("Save function failed");
           //throw new RuntimeException("Save Function failed");
//        }catch (Exception exception){
//            exception.printStackTrace();
//        }
        //return null;
    }

    @Transactional
    public Long removeUserById(Long id) {
        loginUserRepository.deleteById(id);
        return id;
    }

    @Transactional
    @SneakyThrows
    public LoginUser searchUserById(Long id) {
        LoginUser user = loginUserRepository.findById(id).orElseThrow(() -> new RuntimeException("id: "+ id +" not found"));
        return user;
    }

    @Scheduled(fixedRate = 3000, timeUnit = TimeUnit.MILLISECONDS)
    public void repeat() {
        log.info("repeat");
    }
}
