package com.youxinger.springbootcucumbergradle.steps;

import com.youxinger.springbootcucumbergradle.entity.Employee;
import com.youxinger.springbootcucumbergradle.entity.Store;
import com.youxinger.springbootcucumbergradle.entity.verifydata.EmployeeVerifyData;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProductVerifyData;
import com.youxinger.springbootcucumbergradle.entity.verifydata.RepositoryVerifyData;
import com.youxinger.springbootcucumbergradle.entity.verifydata.StoreVerifyData;
import cucumber.api.java.zh_cn.那么;

/**
 * @author mengwei
 * 2020/6/4 11:10
 * @version 1.0
 */
public class EmployeeSteps extends BaseSteps {

    @那么("^预期店长业绩增加(\\d+)元")
    public void storeVerifyStep(int salesSum) throws Throwable {
        for (Employee employee : dataManager.getAllEmployees()) {
            EmployeeVerifyData employeeVerifyData = new EmployeeVerifyData();
            employeeVerifyData.setPerformance(salesSum);
            employee.setExpectedData(employeeVerifyData);
        }
    }
}
