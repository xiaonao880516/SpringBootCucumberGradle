package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.ProvinceOperationCenter;
import com.youxinger.springbootcucumbergradle.entity.verifydata.ProvinceOperationCenterVerifyData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author mengwei
 * 2020/6/2 16:59
 * @version 1.0
 */
@Service
public class ProvinceOperationCenterService {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceOperationCenterService.class);

    public ProvinceOperationCenterVerifyData getVerifyData(ProvinceOperationCenter provinceOperationCenter) {
        logger.debug("getVerifyData provinceOperationCenter={}", provinceOperationCenter);
        ProvinceOperationCenterVerifyData provinceOperationCenterVerifyData = new ProvinceOperationCenterVerifyData();
        return provinceOperationCenterVerifyData;
    }
}
