package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.domain.pojo.CreditsRule;
import com.zgds.xfyj.service.CreditsRuleService;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZL
 * @descition 获取积分规则表
 * @date 2020/1/9
 */
@Api(description = "后台管理获取积分规则API")
@Controller
@RequestMapping("/back/creditsRule")
public class CreditsRuleController {

    @Autowired
    private CreditsRuleService service;


    /**
     * 获取积分规则列表
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAll")
    @ApiOperation(value = "获取积分规则列表", notes = "获取积分规则列表", httpMethod = "GET")
    public ServerResponse getAll() {
        ServerResponse serverResponse = service.getAll();
        return serverResponse;
    }


    /**
     * 添加获取积分规则
     *
     * @param creditsRule
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    @ApiOperation(value = "添加获取积分规则", notes = "添加获取积分规则", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "present_percent", value = "获取积分规则（订单金额百分比）", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "min_order_price", value = "规则金额区间下限", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "max_order_price", value = "规则金额区间上限", paramType = "query", required = true, dataType = "Integer")
    })
    public ServerResponse insert(CreditsRule creditsRule) {
        ServerResponse serverResponse = service.insert(creditsRule);
        return serverResponse;
    }

    /**
     * 删除获取积分规则
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteCreditsRule")
    @ApiOperation(value = "删除获取积分规则", notes = "删除获取积分规则", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "获取积分规则id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse deleteCreditsRule(@Param("id") String id) {
        ServerResponse serverResponse = service.deleteCreditsRule(id);
        return serverResponse;
    }

    /**
     * 修改获取积分规则
     *
     * @param creditsRule
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateCreditsRule")
    @ApiOperation(value = "修改获取积分规则", notes = "修改获取积分规则", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "获取积分规则id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "present_percent", value = "获取积分规则（订单金额百分比）", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "min_order_price", value = "规则金额区间下限", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "max_order_price", value = "规则金额区间上限", paramType = "query", required = true, dataType = "Integer"),
    })
    public ServerResponse updateCreditsRule(CreditsRule creditsRule) {
        ServerResponse serverResponse = service.updateCreditsRule(creditsRule);
        return serverResponse;
    }
}
