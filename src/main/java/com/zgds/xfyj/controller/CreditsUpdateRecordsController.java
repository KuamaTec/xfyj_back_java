package com.zgds.xfyj.controller;

import com.zgds.xfyj.service.CreditsUpdateRecordsService;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZL
 * @descition 用户积分表
 * @date 2020/1/12
 */
@Api(description = "用户积分API")
@Controller
@RequestMapping("/credits")
public class CreditsUpdateRecordsController {

    @Autowired
    private CreditsUpdateRecordsService service;

    /**
     * 获取用户积分记录列表
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAll")
    @ApiOperation(value = "获取用户积分记录列表", notes = "获取用户积分记录列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse getAll(@RequestParam("user_id") String user_id,
                                 @RequestParam(value = "currPage", required = false, defaultValue = "1") Integer currPage,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ServerResponse serverResponse = service.getAll(user_id, currPage, pageSize);
        return serverResponse;
    }

    /**
     * 获取用户当前积分
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCredits")
    @ApiOperation(value = "获取用户当前积分", notes = "获取用户当前积分", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse getCredits(@RequestParam("user_id") String user_id) {
        ServerResponse serverResponse = service.getCredits(user_id);
        return serverResponse;
    }
}
