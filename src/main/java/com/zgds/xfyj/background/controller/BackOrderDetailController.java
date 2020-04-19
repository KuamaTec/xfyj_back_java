package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.service.OrderDetailService;
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
 * @descition 订单信息
 * @date 2020/1/9
 */
@Api(description = "后台管理订单信息API")
@Controller
@RequestMapping("/back/orderDetail")
public class BackOrderDetailController {

    @Autowired
    private OrderDetailService service;

    /**
     * 查看所有订单详情
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAll")
    @ApiOperation(value = "查看所有订单", notes = "查看所有订单", httpMethod = "GET")
    public ServerResponse getAll() {
        ServerResponse serverResponse = service.getAll();
        return serverResponse;
    }

    /**
     * 根据订单id查询订单详情
     *
     * @param order_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOrderId")
    @ApiOperation(value = "根据订单id查询订单详情", notes = "根据订单id查询订单详情", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order_id", value = "订单id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse getOrderId(String order_id) {
        ServerResponse serverResponse = service.getOrderId(order_id);
        return serverResponse;
    }

}
