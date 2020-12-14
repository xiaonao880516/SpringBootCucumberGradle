package com.youxinger.springbootcucumbergradle.entity;

import com.youxinger.springbootcucumbergradle.entity.verify.IVerify;
import com.youxinger.springbootcucumbergradle.entity.verify.RepositoryVerify;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProductVerifyData;
import com.youxinger.springbootcucumbergradle.entity.verifydata.RepositoryVerifyData;

/**
 * @author mengwei
 * 2020/5/27 18:26
 * @version 1.0
 */
public class Repository extends BaseEntity<RepositoryVerifyData>{

    private String name;//仓库名称
    private String id;

    public Repository(String id, String name) {
        this.id = id;
        this.name = name;
        RepositoryVerify repositoryVerify = new RepositoryVerify(name);
        this.verify = (IVerify)repositoryVerify;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * 设置要验证的条码预期值
     * @param productVerifyData
     */
    public void addProductVerifyData(ProductVerifyData productVerifyData){
        RepositoryVerifyData repositoryVerifyData = this.verify.getExpectedData();
        if(repositoryVerifyData == null){
            repositoryVerifyData = new RepositoryVerifyData();
            this.verify.setExpectedData(repositoryVerifyData);
        }
        repositoryVerifyData.getProductVerifyDataMap().put(productVerifyData.getBarcode(), productVerifyData);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
