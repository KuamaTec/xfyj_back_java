package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.domain.pojo.CreditsConsume;
import com.zgds.xfyj.service.CreditsConsumeService;
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
 * @descition 消费积分规则表
 * @date 2020/1/9
 */
@Api(description = "后台管理消费积分规则API")
@Controller
@RequestMapping("/back/creditsConsume")
public class CreditsConsumeController {

    @Autowired
    private CreditsConsumeService service;

    /**
     * 消费积分规则列表
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAll")
    @ApiOperation(value = "消费积分规则列表", notes = "消费积分规则列表", httpMethod = "GET")
    public ServerResponse getAll() {
        ServerResponse serverResponse = service.getAll();
        return serverResponse;
    }


    /**
     * 添加消费积分规则
     *
     * @param creditsConsume
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    @ApiOperation(value = "添加消费积分规则", notes = "添加消费积分规则", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "min_order_price", value = "使用积分最低订单金额", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "consume_percent", value = "消费积分规则", paramType = "query", required = true, dataType = "Integer")

    })
    public ServerResponse insert(CreditsConsume creditsConsume) {
        ServerResponse serverResponse = service.insert(creditsConsume);
        return serverResponse;
    }

    /**
     * 删除消费积分规则
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteCreditsConsume")
    @ApiOperation(value = "删除消费积分规则", notes = "删除消费积分规则", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "消费积分规则id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse deleteCreditsConsume(@Param("id") String id) {
        ServerResponse serverResponse = service.deleteCreditsConsume(id);
        return serverResponse;
    }

    /**
     * 修改消费积分规则
     *
     * @param creditsConsume
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateCreditsConsume")
    @ApiOperation(value = "修改消费积分规则", notes = "修改消费积分规则", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "消费积分规则id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "min_order_price", value = "使用积分最低订单金额", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "consume_percent", value = "消费积分规则", paramType = "query", required = true, dataType = "Integer")

    })
    public ServerResponse updateCreditsConsume(CreditsConsume creditsConsume) {
        ServerResponse serverResponse = service.updateCreditsConsume(creditsConsume);
        return serverResponse;
    }
}
