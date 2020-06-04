package com.youxinger.springbootcucumbergradle.steps;

import com.youxinger.springbootcucumbergradle.entity.Employee;
import com.youxinger.springbootcucumbergradle.entity.SystemUser;
import com.youxinger.springbootcucumbergradle.service.EmployeeService;
import com.youxinger.springbootcucumbergradle.service.SystemUserService;
import cucumber.api.DataTable;
import cucumber.api.java.zh_cn.假设;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration // 不加此注解，bean会注入不进去
@SpringBootTest // 不加此注解会找不到bean
public class LoginSteps {
    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);

    @Resource(name = "systemUserService")
    private SystemUserService systemUserService;
    private SystemUser systemUser;

    @Resource(name="employeeService")
    private EmployeeService employeeService;

    private List<Employee> employeeList;


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

    @假设("^有员工id是：\"([^\"]*)\" ，姓名是： \"([^\"]*)\" ，密码是： \"([^\"]*)\" ，手机号是： \"([^\"]*)\"$")
    public void initEmployees(int id, String name, String password, String phone) {
        logger.debug("initEmployees, id={}, name={}, password={}, phone={}", id, name, password, phone);
    }

    @假设("^有以下员工列表$")
    public void initEmployeeList(DataTable table) {
        logger.debug("initEmployeeList");
        employeeList = table.asList(Employee.class);
    }

    @当("^所有员工前台登录$")
    public void foregroundLoginByEmployee() {
        logger.debug("foregroundLoginByEmployee");
        for(Employee employee : employeeList){
            employeeService.employeeForegroundLogin(employee);
        }
    }

    @那么("^所有员工登录成功$")
    public void checkEmployeeLoginStatus() {
        for(Employee employee : employeeList){
            assertTrue(employee.isEntered());
            assertNotNull(employee.getTid());
            logger.debug("checkEmployeeLoginStatus, getTid={}", employee.getTid());
        }
    }
}
