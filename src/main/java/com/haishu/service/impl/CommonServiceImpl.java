package com.haishu.service.impl;

import com.haishu.service.CommonService;
import com.haishu.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * CommonServiceImpl
 * @author zhb
 * @date 2019/09/05
 */
@Service
public class CommonServiceImpl implements CommonService {
    private @Value("${weixin.app.id}") String appId;
    private @Value("${weixin.app.secret}") String appSecret;
    private @Value("${weixin.service.url}") String serviceUrl;

    /**
     * 获取鉴权链接
     * @param url
     * @return
     */
    @Override
    public ResponseVo getAuthUrl(String url) {
        return null;
    }
}