package com.haishu.common.components;

import com.haishu.common.exception.BusinessException;
import com.haishu.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常拦截
 * @author zhb
 * @date 2018/08/07
 */
@Slf4j
@ControllerAdvice(basePackages = "com.haishu.controller")
public class GlobalExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        // 参数校验失败
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            FieldError fieldError = exception.getBindingResult().getFieldError();
            log.error("参数校验异常,{}", fieldError.getField() + ":" + fieldError.getDefaultMessage(), ex);
            ResponseVo result = new ResponseVo(1, fieldError.getDefaultMessage());
            return new ResponseEntity<>(result, status);
        }

        // 参数缺失异常
        if (ex instanceof MissingServletRequestParameterException) {
            ResponseVo result = new ResponseVo(1, "缺少请求参数");
            return new ResponseEntity<>(result, status);
        }

        return null;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    ResponseEntity<Object> handleRuntimeException(HttpServletRequest request, BusinessException ex) {
        ResponseVo result = new ResponseVo(ex.getErrCode(), ex.getMessage());
        log.error("业务异常",ex);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    ResponseEntity<Object> handleRuntimeException(HttpServletRequest request, Exception ex) {
        logger.error("系统异常", ex);
        ResponseVo result = new ResponseVo(1, "系统异常");
        return new ResponseEntity<>(result, getStatus(request));
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
