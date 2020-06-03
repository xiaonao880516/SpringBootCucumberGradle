package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.Global;
import com.youxinger.springbootcucumbergradle.entity.verifydata.GlobalVerifyData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author mengwei
 * 2020/5/28 14:55
 * @version 1.0
 */
@Service("globalService")
public class GlobalService {

    private static final Logger logger = LoggerFactory.getLogger(GlobalService.class);

    public GlobalVerifyData getVerifyData(Global global) {
        logger.debug("getVerifyData global={}", global);
        GlobalVerifyData globalVerifyData = new GlobalVerifyData();
        return globalVerifyData;
    }
}
