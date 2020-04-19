package com.zgds.xfyj.controller;

import com.zgds.xfyj.service.InformService;
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
@RequestMapping("/inform")
public class InformController {

    @Autowired
    private InformService service;

    /**
     * 查询最新通知
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    @ApiOperation(value = "获取最新通知", notes = "获取最新通知", httpMethod = "GET")
    public ServerResponse getInform() {
        ServerResponse serverResponse = service.getInform();
        return serverResponse;
    }
}
