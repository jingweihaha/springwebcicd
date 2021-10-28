package com.zjw.login;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jingw
 * @created 10/25/2021 10:00 AM
 * @project login
 */
@Slf4j
public class ChildC extends ParentC{

    public int speed = 2;

    @Override
    public double timeSpent(double distance) {
        log.info("ChildC speed: " + speed);
        return speed;
    }
}
