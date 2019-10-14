package com.haishu.common.exception;

import lombok.Data;

/**
 * BusinessException
 * @author zhb
 * @date 2019/06/02
 */
@Data
public class BusinessException extends RuntimeException {
    private int errCode;

    public BusinessException(int errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
    }

    public BusinessException(String errMsg) {
        super(errMsg);
        this.errCode = 1;
    }
}
