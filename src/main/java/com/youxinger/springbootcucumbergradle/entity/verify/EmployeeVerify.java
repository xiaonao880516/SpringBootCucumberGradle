package com.youxinger.springbootcucumbergradle.entity.verify;

import com.youxinger.springbootcucumbergradle.entity.Employee;
import com.youxinger.springbootcucumbergradle.entity.verifydata.EmployeeVerifyData;
import com.youxinger.springbootcucumbergradle.service.EmployeeService;
import com.youxinger.springbootcucumbergradle.utils.CustomManageObjUtil;
import org.junit.Assert;

/**
 * @author mengwei
 * 2020/6/2 17:08
 * @version 1.0
 */
public class EmployeeVerify extends AbstractVerify<Employee, EmployeeVerifyData> {

    private EmployeeService employeeService = CustomManageObjUtil.getBean(EmployeeService.class);

    @Override
    protected void verifyDataSelf(EmployeeVerifyData expectedData) {
        Assert.assertEquals("验证员工业绩失败", expectedData.getPerformance(), postVerifyData.getPerformance() - preVerifyData.getPerformance(), 2.0);
    }

    @Override
    public void updatePreVerifyData(Employee employee) {
        preVerifyData = employeeService.getVerifyData(employee);
    }

    @Override
    public void updatePostVerifyData(Employee employee) {
        postVerifyData = employeeService.getVerifyData(employee);
    }
}
