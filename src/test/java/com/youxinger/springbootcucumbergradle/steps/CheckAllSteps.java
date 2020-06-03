package com.youxinger.springbootcucumbergradle.steps;


import com.youxinger.springbootcucumbergradle.utils.Constants;
import cucumber.api.PendingException;
import cucumber.api.java.zh_cn.那么;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.lessThan;

/**
 * @author mengwei
 * 2020/5/22 13:35
 * @version 1.0
 */
public class CheckAllSteps {

    private static final Logger logger = LoggerFactory.getLogger(CheckAllSteps.class);

    @那么("^系统服务及网络无异常")
    public void checkAll() {
        get(Constants.DOMAIN + "/health/check-all")
                .then().statusCode(200)
                .time(lessThan(3000L)); // Milliseconds, 验证返回时间
    }
}
