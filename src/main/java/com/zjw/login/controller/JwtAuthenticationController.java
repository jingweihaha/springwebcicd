package com.zjw.login.controller;

import com.zjw.login.domain.LoginUser;
import com.zjw.login.service.LoginUserServiceImpl;
import com.zjw.login.service.MyUserDetailServiceImpl;
import com.zjw.login.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author jingw
 * @created 10/21/2021 10:43 PM
 * @project login
 */
@RestController
@RequestMapping
@Slf4j
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailServiceImpl myUserDetailService;

    @Autowired
    private LoginUserServiceImpl loginUserService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/authentication")
    public String login(@RequestBody LoginUser user) throws Exception {
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        try {
            authenticationManager.authenticate(auth);
        }catch (BadCredentialsException exception){
            throw new Exception("INVALID CREDENTIALS", exception);
        }catch (DisabledException exception){
            throw new Exception("USER_DISABLED", exception);
        }

        UserDetails userDetails = myUserDetailService.loadUserByUsername(user.getUsername());

        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @PostMapping("/register")
    public String register(@RequestBody LoginUser user) throws Exception {
        String password = user.getPassword();
        String encode = new BCryptPasswordEncoder().encode(password);
        user.setPassword(encode);
        loginUserService.registerNewUser(user);
        log.info("register successfully");
        //return "redirect:/login";
        return "register successfully";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable Long id){
        loginUserService.removeUserById(id);
        return "removed user with id: " + id;
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<LoginUser>  searchById(@PathVariable Long id){
        LoginUser user =  loginUserService.searchUserById(id);
        ResponseEntity<com.zjw.login.domain.LoginUser> entity = ResponseEntity.ok(user);

        return entity;
    }

    @RequestMapping("/search2/{id}")
    public LoginUser searchById2(@PathVariable Long id){
        LoginUser user =  loginUserService.searchUserById(id);
        return user;
    }

}
