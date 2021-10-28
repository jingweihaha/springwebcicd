package com.zjw.login.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jingw
 * @created 10/23/2021 10:49 AM
 * @project login
 */
@Slf4j
public class StaticUtils {

    static String username = "JZ";

    private static String username2 = "Zhang";

    String username3 = "Jeremy";



    public void test1(){
         log.info(username);
         log.info(username2);
         log.info(username3);

    }

    public static void test2(){
        log.info(username);
        log.info(username2);
        //log.info(username3);
        //test1()
    }



}
