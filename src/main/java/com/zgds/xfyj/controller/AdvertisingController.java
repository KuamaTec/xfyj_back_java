package com.zgds.xfyj.controller;

import com.zgds.xfyj.service.AdvertisingService;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZL
 * @descition 轮播controller
 * @date 2020/1/3
 */
@Api(description = "轮播图API")
@Controller
@RequestMapping("/advertising")
public class AdvertisingController {

    @Autowired
    private AdvertisingService service;

    /**
     * 查询轮播图
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    @ApiOperation(value = "获取轮播图列表", notes = "获取轮播图列表", httpMethod = "GET")
    public ServerResponse getALl() {
        ServerResponse serverResponse = service.getAll();
        return serverResponse;
    }
}
