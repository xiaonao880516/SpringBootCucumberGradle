package com.youxinger.springbootcucumbergradle.service;

import com.alibaba.fastjson.JSONObject;
import com.youxinger.springbootcucumbergradle.entity.Repository;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProductVerifyData;
import com.youxinger.springbootcucumbergradle.entity.verifydata.RepositoryVerifyData;
import com.youxinger.springbootcucumbergradle.utils.Constants;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

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
        if(repository == null){
            return null;
        }
        if(repository.getId().equals("GLOBAL")){
            return getGlobalRepositoryVerifyData(repository);
        }else{
            return getStoreRepositoryVerifyData(repository);
        }
    }

    private RepositoryVerifyData getStoreRepositoryVerifyData(Repository repository){
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
        for(String barcode : repository.getProductsBarcode()){
            for(Map<String, String> product : products){
                if(barcode.equalsIgnoreCase(product.get("tiaoma"))){
                    ProductVerifyData productVerifyData = new ProductVerifyData(barcode);
                    productVerifyData.setQuantity(Integer.parseInt(product.get("num")));
                    repositoryVerifyData.getProductVerifyDataMap().put(barcode, productVerifyData);
                    break;
                }
            }
        }
        logger.debug("repository.name={}, RepositoryVerifyData, repositoryVerifyData={}", repository.getName(), repositoryVerifyData);
        return repositoryVerifyData;
    }

    private RepositoryVerifyData getGlobalRepositoryVerifyData(Repository repository){
        Response response = given()
                .request()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("tid", dataManager.getSystemUser().getTid())
                .get(Constants.DOMAIN + "/backStage/baseinfo/goods/search-goods")
                .then()
                .statusCode(200)
                .extract().response();

        List<Map<String, String>> products = response.path("data.all_goods");
        RepositoryVerifyData repositoryVerifyData = new RepositoryVerifyData();
        for(String barcode : repository.getProductsBarcode()){
            for(Map<String, String> product : products){
                if(barcode.equalsIgnoreCase(product.get("tiaoma"))){
                    ProductVerifyData productVerifyData = new ProductVerifyData(barcode);
                    productVerifyData.setQuantity(Integer.parseInt(product.get("kucun")));
                    repositoryVerifyData.getProductVerifyDataMap().put(barcode, productVerifyData);
                    break;
                }
            }
        }
        logger.error("repository.name={}, RepositoryVerifyData, repositoryVerifyData={}", repository.getName(), repositoryVerifyData);
        return repositoryVerifyData;
    }

}
