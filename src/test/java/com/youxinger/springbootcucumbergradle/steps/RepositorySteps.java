package com.youxinger.springbootcucumbergradle.steps;

import com.youxinger.springbootcucumbergradle.entity.Employee;
import com.youxinger.springbootcucumbergradle.service.RepositoryService;
import com.youxinger.springbootcucumbergradle.utils.Constants;
import cucumber.api.java.zh_cn.当;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RepositorySteps
 * @Description: TODO
 * @Author: zhengxiaolong
 * @Date: 2020/6/29 下午4:48
 * @Version: 2.2.0
 */
public class RepositorySteps extends BaseSteps {
    private static final Logger logger = LoggerFactory.getLogger(RepositorySteps.class);

    @Resource
    RepositoryService repositoryService;

    /**
     * 申请补仓
     *
     * @param tiaoma
     * @param num
     */
    @当("^门店申请补仓商品([^\"]+),(\\d+)个$")
    public void replenishment(String tiaoma, int num) throws Throwable {
        cachePreVerifyData();
        logger.debug("customerCreate, tiaoma={},num={}", tiaoma, num);
        Employee employee = dataManager.getEmployeeById(Constants.EMPLOYEE_ID);
        Map<String, Object> params = new HashMap<>();
        params.put("goods_list[0][tiaoma]", tiaoma);
        params.put("goods_list[0][num]", num);
        params.put("goods_list[0][goods_name]", "无钢托短文胸");

        repositoryService.replenishment(params, employee);
        //后台查询该仓库当天补仓申请的数据
        String applicationId = repositoryService.searchList();
        //后台审核通过补仓申请
        repositoryService.agree(applicationId);
        //导出未导出的申请
        repositoryService.notExportInbound();
        //修改发货订单号
        repositoryService.addOrderNum(applicationId);
        //前台确认入库
        repositoryService.inRepo(employee);
        cachePostVerifyData();
    }

//    @并且("^后台查询该仓库当天补仓申请的数据$")
//    public void searchList() {
//        repositoryService.searchList();
//    }

//    @并且("^后台审核通过补仓申请$")
//    public void agree() {
//        repositoryService.agree();
//    }

//    @并且("^导出未导出的申请$")
//    public void notExportInbound() {
//        repositoryService.notExportInbound();
//    }

//    @并且("^修改发货订单号$")
//    public void addOrderNum() {
//        repositoryService.addOrderNum();
//    }

//    @并且("^前台确认入库$")
//    public void inRepo() {
//        Employee employee = dataManager.getEmployeeById(Constants.EMPLOYEE_ID);
//        repositoryService.inRepo(employee);
//    }

    /**
     * 门店申请退货
     *
     * @param tiaoma
     * @param num
     */
    @当("^门店申请退货商品([^\"]+),(\\d+)个$")
    public void applyReturn(String tiaoma, int num) throws Throwable {
        cachePreVerifyData();
        Employee employee = dataManager.getEmployeeById(Constants.EMPLOYEE_ID);
        Map<String, Object> params = new HashMap<>();
        params.put("goods_list[0][tiaoma]", tiaoma);
        params.put("goods_list[0][num]", num);
        params.put("goods_list[0][goods_name]", "无钢托短文胸");
        repositoryService.applyReturn(params, employee);
        cachePostVerifyData();
        String returnId = repositoryService.searchReturn();
        //填写退货物流单号
        repositoryService.addTrackingNumber(returnId, employee);
        //导出未导出的退货申请
        repositoryService.notExportReturn();
        //提交退货审核
        repositoryService.submitApplyReturn(returnId);

    }

//    @并且("^查询退货信息$")
//    public void searchReturn() {
//        repositoryService.searchReturn();
//    }

//    @并且("^填写退货物流单号$")
//    public void addTrackingNumber() {
//        Employee employee = dataManager.getEmployeeById(Constants.EMPLOYEE_ID);
//        repositoryService.addTrackingNumber(employee);
//    }

//    @并且("^导出未导出的退货申请$")
//    public void notExportReturn() {
//        repositoryService.notExportReturn();
//    }
//
//    @并且("^提交退货审核$")
//    public void SubmitApplyReturn() {
//        repositoryService.submitApplyReturn();
//    }


}
