package com.zjw.login.controller;

import com.zjw.login.domain.LoginUser;
import com.zjw.login.marker.Create;
import com.zjw.login.service.LoginUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author jingw
 * @created 10/21/2021 10:32 PM
 * @project login
 */
@RestController
@RequestMapping("/hello")
@Validated
public class HelloController {

    @Autowired
    private LoginUserServiceImpl userService;

    @GetMapping
    public String greetings(){
        return "Good Morning";
    }

    @GetMapping("/repeat")
    public void repeat(){
        userService.repeat();
    }

    @PostMapping("/valiBean")
    @Validated(Create.class)
    public String endPointValidation(@RequestBody @Valid LoginUser loginUser, BindingResult result){
        if(result.hasErrors())
            return "request invalid";
        else
            return "request is valid";
    }

}
