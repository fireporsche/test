package com.haishu.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class ExternalRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String spanId = MDC.get("PspanId");
            if (StringUtils.isBlank(spanId)) {
                MDC.put("PspanId", UUID.randomUUID().toString().replaceAll("-", ""));
            }
            chain.doFilter(request, response);
        } catch (Throwable e) {
            throw e;
        } finally {
            //请求处理完毕删除线程变量
            MDC.remove("PspanId");
        }

    }

    @Override
    public void destroy() {

    }
}
