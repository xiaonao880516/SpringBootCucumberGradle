package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.utils.Constants;
import com.youxinger.springbootcucumbergradle.entity.SystemUser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;

import static org.junit.Assert.fail;

/**
 * @author mengwei
 * @version 1.0
 * 2020/5/21 17:07
 */
@Service("systemUserService")
public class SystemUserService {

    /**
     * 后台系统登录
     */
    public void backgroundLogin(SystemUser systemUser) {
        if (systemUser != null) {
            Response loginResponse = RestAssured.given()
                    .formParam("username", systemUser.getUsername())
                    .formParam("password", systemUser.getPassword())
                    .request()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post(Constants.DOMAIN + "/backStage/login/login");

            String tid = loginResponse.getHeader("tid");
            systemUser.setEntered(true);
            systemUser.setTid(tid);
        } else {
            fail("没有有效的后台系统用户");
        }

    }
}
