package com.youxinger.springbootcucumbergradle.service;

import com.youxinger.springbootcucumbergradle.entity.Platform;
import com.youxinger.springbootcucumbergradle.entity.verifydata.PlatformVerifyData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author mengwei
 * 2020/6/2 16:58
 * @version 1.0
 */
@Service
public class PlatformService {

    private static final Logger logger = LoggerFactory.getLogger(PlatformService.class);

    public PlatformVerifyData getVerifyData(Platform platform) {
        logger.debug("getVerifyData platform={}", platform);
        PlatformVerifyData platformVerifyData = new PlatformVerifyData();
        return platformVerifyData;
    }
}
