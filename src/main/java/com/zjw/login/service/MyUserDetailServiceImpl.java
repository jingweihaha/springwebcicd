package com.zjw.login.service;

import com.zjw.login.domain.LoginUser;
import com.zjw.login.repo.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author jingw
 * @created 10/21/2021 11:18 PM
 * @project login
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private LoginUserRepository loginUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = loginUserRepository.findByUsername(username);
        if(loginUser != null){
            return new User(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<GrantedAuthority>());
        }
        else{
            throw new UsernameNotFoundException("User name: "+username+" not found");
        }
    }
}
