package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.service.UserService;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZL
 * @descition 用户信息
 * @date 2020/4/19
 */
@Api(description = "后台用户信息信息API")
@Controller
@RequestMapping("/back/user")
public class BackUserController {

    @Autowired
    private UserService service;

    /**
     * 查看所有订单
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAll")
    @ApiOperation(value = "查看所有订单", notes="查看所有订单", httpMethod = "GET")
    public ServerResponse getAll(){
        ServerResponse serverResponse = service.getAll();
        return serverResponse;
    }

}
