package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.utils.Constants;
import com.youxinger.springbootcucumbergradle.entity.Employee;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.fail;

@Service("employeeService")
public class EmployeeService {

    /**
     * 员工登录
     */
    public void employeeForegroundLogin(Employee employee) {
        if (employee != null) {
            Response loginResponse = given()
                    .formParam("username", employee.getPhone())
                    .formParam("password", employee.getPassword())
                    .request()
                    .header("Accept", "application/json, text/plain, */*")
                    .post(Constants.DOMAIN + "/frontStage/login/login");

            String tid = loginResponse.getHeader("tid");
            employee.setEntered(true);
            employee.setTid(tid);
        }else{
            fail("没有有效的员工");
        }
    }
}
