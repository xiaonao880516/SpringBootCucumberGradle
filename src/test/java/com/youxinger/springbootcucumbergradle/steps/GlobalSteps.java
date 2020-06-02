package com.youxinger.springbootcucumbergradle.steps;

import com.youxinger.springbootcucumbergradle.utils.Constants;
import com.youxinger.springbootcucumbergradle.entity.*;
import com.youxinger.springbootcucumbergradle.service.DataManager;
import com.youxinger.springbootcucumbergradle.service.EmployeeService;
import com.youxinger.springbootcucumbergradle.service.SystemUserService;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.zh_cn.假设;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import static com.youxinger.springbootcucumbergradle.utils.Constants.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author mengwei
 * 2020/5/27 17:13
 * @version 1.0
 */
public class GlobalSteps {

    private static final Logger logger = LoggerFactory.getLogger(GlobalSteps.class);

    @Resource(name = "systemUserService")
    private SystemUserService systemUserService;

    @Resource(name = "employeeService")
    private EmployeeService employeeService;

    @Resource
    private DataManager dataManager;

    @假设("有测试门店,测试门店有店长")
    public void initData() {
        logger.debug("initData");
        //构造平台
        Platform testPlatform = new Platform(Constants.PLATFORM_ID, Constants.PLATFORM_NAME);
        //构造员工
        Employee employee = new Employee(Constants.EMPLOYEE_ID, Constants.EMPLOYEE_NAME, Constants.EMPLOYEE_PHONE, Constants.EMPLOYEE_PASSWORD);
        //构造门店
        Store testStore = new Store(Constants.STORE_NAME, Constants.STORE_NUMBER);
        testStore.getPlatformList().add(testPlatform);
        testStore.getEmployeeList().add(employee);
        //构造运营中心
        OperationCenter testOperationCenter = new OperationCenter(OPERATION_CENTER_NAME, OPERATION_CENTER_NUMBER);
        testOperationCenter.getStoreList().add(testStore);
        //构造省运营中心
        ProvinceOperationCenter testProvinceOperationCenter = new ProvinceOperationCenter(PROVINCE_OPERATION_CENTER_NAME, PROVINCE_OPERATION_CENTER_NUMBER);
        testProvinceOperationCenter.getOperationCenterList().add(testOperationCenter);
        //构造全局
        Global global = new Global();
        global.getProvinceOperationCenterList().add(testProvinceOperationCenter);

        dataManager.setGlobal(global);
        backgroundLogin();
        foregroundLogin();

        logger.debug("initData, global={}", global);

    }

    /**
     * 后台登陆
     */
    private void backgroundLogin() {
        SystemUser systemUser = new SystemUser(Constants.SYSTEM_USER_USERNAME, Constants.SYSTEM_USER_PASSWORD);
        dataManager.setSystemUser(systemUser);
        systemUserService.backgroundLogin(systemUser);
        assertTrue(systemUser.isEntered());
        assertNotNull(systemUser.getTid());
        logger.debug("backgroundLogin, getTid={}", systemUser.getTid());
    }

    /**
     * 前台登录
     */
    private void foregroundLogin() {
        for (Employee employee : dataManager.getAllEmployees()) {
            employeeService.employeeForegroundLogin(employee);
            assertTrue(employee.isEntered());
            assertNotNull(employee.getTid());
            logger.debug("foregroundLogin, employeeName={}, Tid={}", employee.getName(), employee.getTid());
        }
    }

    /**
     * 每个 scenario 前执行
     */
    @Before
    public void before() {
        logger.debug("run before every scenario");
    }

    /**
     * 每个 scenario 后执行
     */
    @After
    public void after() {
        logger.debug("run after every scenario");
    }

    /**
     * 指定tag的scenario前置性
     */
    @Before("@THooks")
    public void beforeSmokeTest() {
        logger.debug("run before every scenario with @THooks annotation");
    }

    /**
     * 指定tag的scenario后置性
     */
    @After("@THooks")
    public void afterSmokeTest() {
        logger.debug("run after every scenario with @THooks annotation");
    }

}
