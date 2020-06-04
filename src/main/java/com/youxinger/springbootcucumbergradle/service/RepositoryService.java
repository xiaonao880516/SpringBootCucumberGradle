package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.Repository;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProductVerifyData;
import com.youxinger.springbootcucumbergradle.entity.verifydata.RepositoryVerifyData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author mengwei
 * 2020/6/2 17:00
 * @version 1.0
 */
@Service
public class RepositoryService {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryService.class);

    public RepositoryVerifyData getVerifyData(Repository repository) {
        logger.debug("getVerifyData repository={}", repository);
        RepositoryVerifyData repositoryVerifyData = new RepositoryVerifyData();
        ProductVerifyData productVerifyData = new ProductVerifyData("M216C237C0458");
        productVerifyData.setQuantity(0);
        repositoryVerifyData.getProductVerifyDataMap().put("M216C237C0458", productVerifyData);
        return repositoryVerifyData;
    }
}
