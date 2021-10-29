package com.zjw.login;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.TestUtils;
import com.zjw.login.domain.ApplicationTask;
import com.zjw.login.domain.Department;
import com.zjw.login.domain.LoginUser;
import com.zjw.login.service.LoginUserServiceImpl;
import com.zjw.login.util.BatchUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.*;


@SpringBootTest
@Slf4j
class LoginApplicationTests {

    @MockBean
    private LoginUser userTest;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private LoginUserServiceImpl loginUserService;

//    @Autowired
//    private Job myJob;

//    @Autowired
//    private JobLauncher jobLauncher;

    @Test
    void contextLoads() {
    }

    @Test
    void test1(){
        Object authenticationManagerBean = context.getBean(AuthenticationManager.class);
        log.info("authenticationManagerBean: " + authenticationManagerBean.getClass());
    }

    @Test
    void test2(){
        SingletonClass1 singleton1 = SingletonClass1.getInstance();
        SingletonClass1 singleton2 = SingletonClass1.getInstance();
        Assertions.assertEquals(singleton1, singleton2);
    }

    @Test
    void test3(){
        SingletonClass2 singleton1 = SingletonClass2.getInstance();
        SingletonClass2 singleton2 = SingletonClass2.getInstance();
        Assertions.assertEquals(singleton1, singleton2);
    }

    @Test
    void test4() throws JsonProcessingException {
        RestTemplate template = new RestTemplate();
        LoginUser user = new LoginUser();
        user.setUsername("UUIDD");
        user.setPassword("43243");
        String json = new ObjectMapper().writeValueAsString(user);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> requestEntity = new HttpEntity<String>(json, httpHeaders);

        ResponseEntity<String> responseEntity = template.exchange("http://localhost:8080/register", HttpMethod.POST, requestEntity, String.class);
        if(responseEntity.getBody().equals("register successfully")){
            //second request to aquire jwt token
            ResponseEntity<String> token = template.exchange("http://localhost:8080/authentication", HttpMethod.POST, requestEntity, String.class);
            String jwtToken = token.getBody();

            httpHeaders.add("Authorization", "Bearer "+jwtToken);

            HttpEntity<String> requestEntity2 = new HttpEntity<String>(null, httpHeaders);
            //attach token into header and send again
            ResponseEntity<String> greetings = template.exchange("http://localhost:8080/hello", HttpMethod.GET, requestEntity2, String.class);
            log.info(greetings.getBody());
            Assertions.assertEquals(greetings, "Good Morning");
        }
    }

    @Test
    void test5(){
        Assertions.assertEquals(new ParentC().timeSpent(1), 1);
        Assertions.assertEquals(new ChildC().timeSpent(1), 2);
    }

    @Test
    @SneakyThrows
    void test6(){
        ChildC c = new ChildC();
        String s = new ObjectMapper().writeValueAsString(c);
        log.info(s);
    }

    @Test
    void test7(){
//        Date date = new Date();
//        long time = date.getTime();
//        log.info(""+time);

//        java.sql.Date date2 = new Date(2020, 10, 26);
//        long time = date2.getTime();
//        log.info("time: " + time);

        Date date3 = new Date(61564510800000L);
        Assertions.assertEquals(date3.getHours(), 0);
        Assertions.assertEquals(date3.getMinutes(), 0);
        Assertions.assertEquals(date3.getYear(), 2020);
    }

    @Test
    void test8(){
        LoginUser testUser = mock(LoginUser.class);
        when(testUser.getUsername()).thenReturn("JZ");
        String username = testUser.getUsername();
        log.info(username);
    }


    @Test
    void test9(){
        when(userTest.getUsername()).thenReturn("Haha");
        Assertions.assertEquals("Haha", userTest.getUsername());
    }

    @Test
    void test10(){
        List<String> list = new ArrayList<>();
        list.add("AAA");
        list.add("BBB");

        List<String> spy = spy(list);
        doReturn("JZZ").when(spy).get(0);
        Assertions.assertEquals(spy.get(0), "AAA");
        Assertions.assertEquals(spy.get(1), "BBB");
    }

    @Test
    void test11()  {
        LoginUser user = new LoginUser();
        user.setUsername("Test941");
        user.setPassword("Pass");
        try {
            loginUserService.registerNewUser(user);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    void test12(){
        LoginUserServiceImpl mock = mock(LoginUserServiceImpl.class);
        LoginUser user  =  new LoginUser();
        user.setUsername("Haha");
        when(mock.searchUserById(8L)).thenReturn(user);
        when(mock.searchUserById(9L)).thenThrow(new RuntimeException("Id illegal: "));

        Assertions.assertEquals(mock.searchUserById(8L), user);
        //  Assertions.assertThrows(RuntimeException.class, mock.searchUserById(9L));
        //  Executable executable = () -> {mock.searchUserById(9L);};
        Assertions.assertThrows(RuntimeException.class, () -> {mock.searchUserById(9L);});

    }

//    @Test
//    public void test13() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//        JobParameters parameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
//        jobLauncher.run(myJob, parameters);
//    }

    @Test
    @Scheduled(fixedRate = 5000)
    void test14(){
        log.info("test 14");
    }

    @Test
    void test15() throws IOException {
        LoginUser user = new LoginUser();
        user.setUsername("Test146");
        user.setPassword("146");
        Department dept = new Department();
        dept.setName("HR");
        user.setDepartment(dept);
        //it should save both user and department
        loginUserService.registerNewUser(user);
    }


    @Test
    void test16() throws IOException {
        LoginUser user = new LoginUser();
        user.setUsername("test319");
        ApplicationTask task = new ApplicationTask();
        task.setName("Task One");
        Set<ApplicationTask> taskSet = new HashSet<ApplicationTask>();
        taskSet.add(task);
        user.setTaskSet(taskSet);
        loginUserService.registerNewUser(user);
    }

}

//git push --set-upstream origin master