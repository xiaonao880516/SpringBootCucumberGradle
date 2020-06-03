package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.Store;
import com.youxinger.springbootcucumbergradle.entity.verifydata.StoreVerifyData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author mengwei
 * 2020/6/2 17:04
 * @version 1.0
 */
@Service
public class StoreService {

    private static final Logger logger = LoggerFactory.getLogger(StoreService.class);

    public StoreVerifyData getVerifyData(Store store) {
        logger.debug("getVerifyData store={}", store);
        StoreVerifyData storeVerifyData = new StoreVerifyData();
        return storeVerifyData;
    }
}
