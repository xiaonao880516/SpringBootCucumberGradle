package com.youxinger.springbootcucumbergradle.entity;

/**
 * 后台系统用户
 * @author mengwei
 * @version 1.0
 * @date 2020/5/21 17:04
 */
public class SystemUser {

    private String username;
    private String password;
    private boolean entered = false;//是否登录
    private String tid;

    public SystemUser(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean entered) {
        this.entered = entered;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "SystemUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", entered=" + entered +
                ", tid='" + tid + '\'' +
                '}';
    }
}
