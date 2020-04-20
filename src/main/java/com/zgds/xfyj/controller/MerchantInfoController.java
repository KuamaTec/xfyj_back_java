package com.zgds.xfyj.controller;

import com.zgds.xfyj.service.MerchantInfoService;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZL
 * @descition
 * @date 2020/1/3
 */
@Api(description = "通知API")
@Controller
@RequestMapping("/merchant")
public class MerchantInfoController {

    @Autowired
    private MerchantInfoService service;

    /**
     * 查询最新通知
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/info")
    @ApiOperation(value = "获取商家信息", notes = "获取商家信息", httpMethod = "GET")
    public ServerResponse getMerchantInfo() {

        return service.getMerchantInfo();
    }
}
