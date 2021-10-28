package com.zjw.login;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jingw
 * @created 10/25/2021 9:59 AM
 * @project login
 */
@Slf4j
public class ParentC {
    public int speed = 1;

    public double timeSpent(double distance){
        log.info("parentC speed: " + speed);
        return speed;
    }
}
