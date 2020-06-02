package com.youxinger.springbootcucumbergradle.steps;

import com.youxinger.springbootcucumbergradle.entity.SystemUser;
import com.youxinger.springbootcucumbergradle.service.SystemUserService;
import cucumber.api.java.zh_cn.假设;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@ContextConfiguration // 不加此注解，bean会注入不进去
@SpringBootTest // 不加此注解会找不到bean
public class BackgroundSystemUserLoginSteps {
    private static final Logger logger = LoggerFactory.getLogger(BackgroundSystemUserLoginSteps.class);

    @Resource(name = "systemUserService")
    private SystemUserService systemUserService;
    private SystemUser systemUser;


    @假设("^有系统用户，用户名是：([^\"]*) ，密码是：([^\"]*)$")
    public void initSystemUser(String username, String password) {
        logger.debug("initSystemUser, username={}, password={}" , username, password);
        systemUser = new SystemUser(username, password);
    }

    @当("^系统用户后台登录$")
    public void backgroundLoginBySystemUser() {
        logger.debug("backgroundLoginBySystemUser");
        systemUserService.backgroundLogin(systemUser);
    }

    @那么("^系统用户后台登录成功$")
    public void checkSystemUserLoginStatus() {
        logger.debug("checkSystemUserLoginStatus");
        if (systemUser != null) {
            assertTrue(systemUser.isEntered());
            assertNotNull(systemUser.getTid());
            logger.debug("checkSystemUserLoginStatus, getTid={}", systemUser.getTid());
        }else{
            fail("没有有效的后台系统用户");
        }
    }
}
