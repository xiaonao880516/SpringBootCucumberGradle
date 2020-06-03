package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.OperationCenter;
import com.youxinger.springbootcucumbergradle.entity.verifydata.OperationCenterVerifyData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author mengwei
 * 2020/6/2 16:56
 * @version 1.0
 */
@Service
public class OperationCenterService {


    private static final Logger logger = LoggerFactory.getLogger(OperationCenterService.class);

    public OperationCenterVerifyData getVerifyData(OperationCenter operationCenter) {
        logger.debug("getVerifyData operationCenter={}", operationCenter);
        OperationCenterVerifyData operationCenterVerifyData = new OperationCenterVerifyData();
        return operationCenterVerifyData;
    }
}
