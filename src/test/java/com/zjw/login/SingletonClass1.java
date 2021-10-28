package com.zjw.login;

/**
 * @author jingw
 * @created 10/23/2021 10:59 AM
 * @project login
 */
public final class SingletonClass1 {

    private static final SingletonClass1 instance;

//    private static SingletonClass1 init(){
//        return new SingletonClass1();
//    }

    static{
        instance = new SingletonClass1();

    }

    private SingletonClass1(){

    }

    public static SingletonClass1 getInstance(){
        return instance;
    }

}
