package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.Employee;
import com.youxinger.springbootcucumbergradle.entity.verifydata.EmployeeVerifyData;
import com.youxinger.springbootcucumbergradle.utils.Constants;
import com.youxinger.springbootcucumbergradle.utils.TimeUtil;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.fail;

@Service("employeeService")
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Resource
    protected DataManager dataManager;

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
        } else {
            fail("没有有效的员工");
        }
    }

    /**
     * 获取员工的数据
     * @param employee
     * @return
     */
    public EmployeeVerifyData getVerifyData(Employee employee) {
        logger.debug("getVerifyData employee={}", employee);

        Map<String, String> params = new HashMap<>();
        params.put("page_size", "15");
        params.put("page_num", "1");
        params.put("store_id", "");
        params.put("center_id", "");
        params.put("employee", employee.getName());
        params.put("area_id", "");
        params.put("start_time", TimeUtil.getMonthStartTime());
        params.put("end_time", TimeUtil.getMonthEndTime());

        Response response = given()
                .params(params)
                .request()
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .get(Constants.DOMAIN + "/backStage/achieve/query-back");

        String salesSum = response.path("data.tot");
        EmployeeVerifyData employeeVerifyData = new EmployeeVerifyData();
        employeeVerifyData.setPerformance(Float.parseFloat(salesSum));
        return employeeVerifyData;
    }
}
