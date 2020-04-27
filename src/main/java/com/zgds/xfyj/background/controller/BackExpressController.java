package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.domain.pojo.Express;
import com.zgds.xfyj.service.ExpressService;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZL
 * @descition 物流信息
 * @date 2020/4/26
 */
@Api(description = "后台管理物流信息API")
@Controller
@RequestMapping("/back/express")
public class BackExpressController {

    @Autowired
    private ExpressService service;

    /**
     * 查看所有物流信息
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAll")
    @ApiOperation(value = "查看所有物流信息", notes = "查看所有物流信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currPage", value = "当前页码", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", paramType = "query", required = true, dataType = "Integer")
    })
    public ServerResponse getAll(Integer currPage,Integer pageSize) {
        ServerResponse serverResponse = service.getAll(currPage,pageSize);
        return serverResponse;
    }

    /**
     * 添加物流信息
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    @ApiOperation(value = "添加物流信息", notes = "添加物流信息", httpMethod = "POST")
    @ApiImplicitParams({
            /*@ApiImplicitParam(name = "id", value = "物流id", paramType = "query", required = true, dataType = "String"),*/
            @ApiImplicitParam(name = "express_company", value = "快递公司", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "express_no", value = "快递单号", paramType = "query", required = true, dataType = "String"),
            /*@ApiImplicitParam(name = "update_time", value = "更新时间", paramType = "query", required = true, dataType = "Date"),*/
            @ApiImplicitParam(name = "order_ids", value = "订单id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "user_id", value = "用户id", paramType = "query", required = true, dataType = "String")

    })
    public ServerResponse insert(Express express) {
        ServerResponse serverResponse = service.insert(express);
        return serverResponse;
    }

}
