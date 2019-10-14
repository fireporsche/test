package com.haishu.service;

import com.haishu.vo.ResponseVo;

/**
 * CommonService
 * @author zhb
 * @date 2019/09/05
 */
public interface CommonService {
    /**
     * 获取鉴权链接
     * @param url
     * @return
     */
    ResponseVo getAuthUrl(String url);
}