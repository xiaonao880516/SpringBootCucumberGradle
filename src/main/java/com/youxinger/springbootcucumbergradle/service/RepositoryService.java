package com.youxinger.springbootcucumbergradle.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.youxinger.springbootcucumbergradle.entity.Employee;
import com.youxinger.springbootcucumbergradle.entity.Repository;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProductVerifyData;
import com.youxinger.springbootcucumbergradle.entity.verifydata.RepositoryVerifyData;
import com.youxinger.springbootcucumbergradle.utils.Constants;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author mengwei
 * 2020/6/2 17:00
 * @version 1.0
 */
@Service
public class RepositoryService {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryService.class);

    @Resource
    protected DataManager dataManager;

    public RepositoryVerifyData getVerifyData(Repository repository) {
        logger.debug("getVerifyData repository={}", repository);
        if (repository == null) {
            return null;
        }
        if (repository.getId().equals("GLOBAL")) {
            return getGlobalRepositoryVerifyData(repository);
        } else if (repository.getId().equals("GLOBAL_POINT_MALL")) {
            return getGlobalPointMallRepositoryVerifyData(repository);
        } else {
            return getStoreRepositoryVerifyData(repository);
        }
    }


    private RepositoryVerifyData getStoreRepositoryVerifyData(Repository repository) {
        Map<String, String> params = new HashMap<>();
        params.put("repository_id", repository.getId());
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .get(Constants.DOMAIN + "/backStage/repository/inventory/search-inventory")
                .then()
                .statusCode(200)
                .extract().response();

        List<Map<String, String>> products = response.path("data.goods_list");
        RepositoryVerifyData repositoryVerifyData = new RepositoryVerifyData();
        for (Map<String, String> product : products) {
            String barcode = product.get("tiaoma");
            ProductVerifyData productVerifyData = new ProductVerifyData(barcode);
            productVerifyData.setQuantity(Integer.parseInt(product.get("num")));
            repositoryVerifyData.getProductVerifyDataMap().put(barcode, productVerifyData);
        }
//        logger.debug("repository.name={}, RepositoryVerifyData, repositoryVerifyData={}", repository.getName(), repositoryVerifyData);
        return repositoryVerifyData;
    }

    private RepositoryVerifyData getGlobalRepositoryVerifyData(Repository repository) {
        Response response = given()
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .get(Constants.DOMAIN + "/backStage/baseinfo/goods/search-goods")
                .then()
                .statusCode(200)
                .extract().response();

        return getGlobalRepositoryVerifyData(repository, response);
    }

    private RepositoryVerifyData getGlobalPointMallRepositoryVerifyData(Repository repository) {
        Response response = given()
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .get(Constants.DOMAIN + "/backStage/pointsmall/baseinfo/goods/search-goods")
                .then()
                .statusCode(200)
                .extract().response();

        return getGlobalRepositoryVerifyData(repository, response);
    }
    
    private RepositoryVerifyData getGlobalRepositoryVerifyData(Repository repository, Response response) {
        List<Map<String, String>> products = response.path("data.all_goods");
        RepositoryVerifyData repositoryVerifyData = new RepositoryVerifyData();
        for (Map<String, String> product : products) {
            String barcode = product.get("tiaoma");
            ProductVerifyData productVerifyData = new ProductVerifyData(barcode);
            productVerifyData.setQuantity(Integer.parseInt(product.get("kucun")));
            repositoryVerifyData.getProductVerifyDataMap().put(barcode, productVerifyData);
        }
//        logger.debug("repository.name={}, RepositoryVerifyData, repositoryVerifyData={}", repository.getName(), repositoryVerifyData);
        return repositoryVerifyData;
    }

    /**
     * 补仓申请
     *
     * @param employee
     */
    public void replenishment(Map params, Employee employee) {
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", employee.getTid())
                .post(Constants.DOMAIN + "/frontStage/repository/repo/add-apply")
                .then()
                .statusCode(200)
                .body("msg", equalTo("补仓申请提交成功"))
                .extract().response();

    }

    /**
     * 查询补仓申请的列表
     */
    public String searchList() {
        HashMap<String, String> params = new HashMap<>();
        params.put("repo", Constants.STORE_STANDARD_REPO_ID);
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .get(Constants.DOMAIN + "/backStage/repository/addGoods/get-list")
                .then()
                .statusCode(200)
                .body("msg", equalTo("查询成功"))
                .extract().response();
        return response.path("data.rows[0].application_id");
    }

    /**
     * 审核通过
     */
    public void agree(String applicationId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("application_id", applicationId);
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .post(Constants.DOMAIN + "/backStage/repository/addGoods/agree")
                .then()
                .statusCode(200)
                .body("msg", equalTo("已通过补仓申请"))
                .extract().response();
    }

    /**
     * 导出未导出订单
     */
    public void notExportInbound() {
        Response response = given()
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .post(Constants.DOMAIN + "/backStage/repository/export/not-export-inbound")
                .then()
                .statusCode(200)
                .body("msg", equalTo("导出成功"))
                .extract().response();
    }

    /**
     * 修改物流信息
     */
    public void addOrderNum(String applicationId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("application_id", applicationId);
        params.put("order_num", "123456");
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .post(Constants.DOMAIN + "/backStage/repository/addGoods/add-order-num")
                .then()
                .statusCode(200)
                .body("msg", equalTo("修改发货订单号成功"))
                .extract().response();
    }

    /**
     * 确认入库
     *
     * @param employee
     */
    public void inRepo(Employee employee) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", 1);
        params.put("application_id", this.searchList());
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", employee.getTid())
                .post(Constants.DOMAIN + "/frontStage/repository/in-repo")
                .then()
                .statusCode(200)
                .body("msg", equalTo("补仓商品已入库"))
                .extract().response();
    }

    /**
     * 退货申请
     *
     * @param params
     * @param employee
     */
    public void applyReturn(Map<String, Object> params, Employee employee) {
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", employee.getTid())
                .post(Constants.DOMAIN + "/frontStage/repository/return_application/apply-return")
                .then()
                .statusCode(200)
                .body("msg", equalTo("退货申请提交成功"))
                .extract().response();
    }

    /**
     * 查询退货信息
     */
    public String searchReturn() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("repo", Constants.STORE_STANDARD_REPO_ID);
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .get(Constants.DOMAIN + "/backStage/repository/return_application/return-list")
                .then()
                .statusCode(200)
                .body("msg", equalTo("查询成功"))
                .extract().response();
        return response.path("data.rows[0].return_id");
    }

    /**
     * 填写退货物流单号
     *
     * @param employee
     */
    public void addTrackingNumber(String applicationId, Employee employee) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("tracking_number", "456789");
        params.put("application_id", applicationId);
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", employee.getTid())
                .post(Constants.DOMAIN + "/frontStage/repository/return_application/add-tracking-number")
                .then()
                .statusCode(200)
                .body("msg", equalTo("该退货申请已发货"))
                .extract().response();
    }

    /**
     * 导出未导出的退货申请
     */
    public void notExportReturn() {
        Response response = given()
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .post(Constants.DOMAIN + "/backStage/repository/export/not-export-return")
                .then()
                .statusCode(200)
                .body("msg", equalTo("导出成功"))
                .extract().response();
    }

    /**
     * 提交退货审核
     */
    public void submitApplyReturn(String applicationId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("application_id", applicationId);
        //查询退货信息
        Response response = given()
                .params(params)
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .get(Constants.DOMAIN + "/backStage/repository/return_application/return-detail")
                .then()
                .statusCode(200)
                .body("msg", equalTo("查询成功"))
                .extract().response();
        Map<String, Object> searchData = response.path("data[0]");
        //审核没有驳回的情况下  公司接受的数量和申请的数量应该是一致的
        searchData.put("received_num", searchData.get("return_num"));
        logger.debug("searchData={}", JSON.toJSONString(searchData));
        submitApplyReturnNext(searchData);
    }

    private void submitApplyReturnNext(Map<String, Object> searchData) {
        HashMap<String, Object> params = new HashMap<>();
        HashMap<String, Object> update = new HashMap<>();
        ArrayList<Object> DataArr = new ArrayList<>();
        DataArr.add(searchData);
        update.put(Constants.STORE_STANDARD_REPO_NAME, DataArr);
        params.put("update_data", update);
        params.put("refused_reason", "");
        logger.debug("params={}", JSON.toJSONString(params));

        Response response = given()
                .contentType("application/json")
                .header("tid", dataManager.getSystemUser().getTid())
                .body(JSONObject.toJSONString(params))
                .post(Constants.DOMAIN + "/backStage/repository/return_application/apply-return")
                .then()
                .statusCode(200)
                .body("msg", equalTo("审核成功"))
                .extract()
                .response();
        logger.debug("check-data={}", (Object) response.path("data"));
    }


}
