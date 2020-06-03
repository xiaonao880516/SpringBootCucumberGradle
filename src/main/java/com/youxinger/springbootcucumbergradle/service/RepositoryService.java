package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.Repository;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProductVerifyData;
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

    public ProductVerifyData getVerifyData(Repository repository) {
        logger.debug("getVerifyData repository={}", repository);
        ProductVerifyData productVerifyData = new ProductVerifyData(null);
        return productVerifyData;
    }
}
