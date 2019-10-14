package com.haishu.vo;

import com.haishu.common.mybatis.Pageable;
import lombok.Data;

/**
 * ResponseVo
 * @author zhb
 * @date 2019/07/04
 */
@Data
public class ResponseVo {
    private Integer errCode;
    private String errMsg;
    private Integer page;
    private Integer size;
    private Integer totalElements;
    private Integer totalPage;
    private Object data;

    public ResponseVo(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public ResponseVo(String errMsg) {
        this.errCode = 0;
        this.errMsg = errMsg;
    }

    public ResponseVo(Integer errCode, String errMsg, Object data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public ResponseVo(String errMsg, Object data) {
        this.errCode = 0;
        this.errMsg = errMsg;
        this.data = data;
    }

    public ResponseVo(Integer errCode, String errMsg, Pageable pageable) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.page = pageable.getPage();
        this.size = pageable.getSize();
        this.totalElements = pageable.getTotalElements();
        this.totalPage = (pageable.getTotalElements() + pageable.getSize() - 1) / pageable.getSize();
    }

    public ResponseVo(String errMsg, Pageable pageable) {
        this.errCode = 0;
        this.errMsg = errMsg;
        this.page = pageable.getPage();
        this.size = pageable.getSize();
        this.totalElements = pageable.getTotalElements();
        this.totalPage = (pageable.getTotalElements() + pageable.getSize() - 1) / pageable.getSize();
    }

    public ResponseVo(Integer errCode, String errMsg, Pageable pageable, Object data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.page = pageable.getPage();
        this.size = pageable.getSize();
        this.totalElements = pageable.getTotalElements();
        this.totalPage = (pageable.getTotalElements() + pageable.getSize() - 1) / pageable.getSize();
        this.data = data;
    }

    public ResponseVo(String errMsg, Pageable pageable, Object data) {
        this.errCode = 0;
        this.errMsg = errMsg;
        this.page = pageable.getPage();
        this.size = pageable.getSize();
        this.totalElements = pageable.getTotalElements();
        this.totalPage = (pageable.getTotalElements() + pageable.getSize() - 1) / pageable.getSize();
        this.data = data;
    }
}