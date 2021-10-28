package com.zjw.login;

/**
 * @author jingw
 * @created 10/23/2021 11:12 AM
 * @project login
 */
public final class SingletonClass2 {

    private static final class InnerClass2{
        private static SingletonClass2 instance = new SingletonClass2();
    }

    private SingletonClass2(){

    }

    public static SingletonClass2 getInstance(){
        return InnerClass2.instance;
    }

}
