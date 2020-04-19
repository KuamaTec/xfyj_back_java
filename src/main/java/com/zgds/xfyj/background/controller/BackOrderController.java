package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.domain.pojo.Order;
import com.zgds.xfyj.service.OrderService;
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
 * @descition 订单信息
 * @date 2020/1/9
 */
@Api(description = "后台管理订单信息API")
@Controller
@RequestMapping("/back/order")
public class BackOrderController {

    @Autowired
    private OrderService service;

    /**
     * 查看所有订单
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
     * 根据订单状态查询订单
     *
     * @param order_status
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOrderStatus")
    @ApiOperation(value = "根据订单状态查询订单", notes = "根据订单状态查询订单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户id", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "order_status", value = "订单状态", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "currPage", value = "当前页码", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", paramType = "query", required = true, dataType = "Integer")
    })
    public ServerResponse getOrderStatus(Integer order_status, String user_id, Integer currPage, Integer pageSize) {
        return service.getOrderByStatus(user_id, order_status, currPage, pageSize);
    }

    /**
     * 删除订单
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteOrder")
    @ApiOperation(value = "根据订单id删除订单", notes = "根据订单id删除订单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse deleteOrder(@Param("id") String id) {
        ServerResponse serverResponse = service.deleteOrder(id);
        return serverResponse;
    }

    /**
     * 修改订单信息
     *
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateOrder")
    @ApiOperation(value = "修改订单信息", notes = "修改订单信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "order_name", value = "订单名称", paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "order_status", value = "订单状态", paramType = "query", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "order_price", value = "订单总金额", paramType = "query", required = false, dataType = "Double")
    })
    public ServerResponse updateOrder(Order order) {
        ServerResponse serverResponse = service.updateOrder(order);
        return serverResponse;
    }
}
