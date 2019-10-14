package com.haishu.controller;

import com.haishu.service.CommonService;
import com.haishu.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * ManageUserController
 * @author zhb
 * @date 2019/07/08
 */
@RestController
@RequestMapping("/api/common")
@Api(value = "通用部分")
public class CommonController {
    private @Autowired CommonService commonService;

    @GetMapping(path = "getAuthUrl")
    @ApiOperation(value = "获取鉴权链接", notes = "获取鉴权链接")
    public ResponseVo getAuthUrl(@RequestParam @ApiParam(value = "内容", required = true) String url) {
        return commonService.getAuthUrl(url);
    }
}