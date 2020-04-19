package com.zgds.xfyj.controller;

import com.zgds.xfyj.service.OrderService;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZL
 * @descition
 * @date 2020/1/3
 */
@Api(description = "订单（小程序）API")
@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 购物车结算
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    @ApiOperation(value = "购物车结算", notes = "购物车结算", httpMethod = "POST")
    public ServerResponse addOrder(@RequestParam(value = "order_name") String order_name,
                                   @RequestParam(value = "order_price") Double order_price,
                                   @RequestParam(value = "cart_ids") String cart_ids,
                                   @RequestParam(value = "user_id") String user_id) {

        return orderService.addOrder(order_name, order_price, cart_ids, user_id);
    }

    /**
     * 根据订单状态获取用户订单
     */
    @ResponseBody
    @RequestMapping("/list/status/{user_id}/{order_status}/{currPage}/{pageSize}")
    @ApiOperation(value = "根据订单状态获取用户订单", notes = "购物车结算", httpMethod = "POST")
    public ServerResponse getOrdersByUserIdStatus(@PathVariable(value = "user_id") String user_id,
                                                  @PathVariable(value = "order_status") Integer order_status,
                                                  @PathVariable(value = "currPage") Integer currPage,
                                                  @PathVariable(value = "pageSize") Integer pageSize) {

        return orderService.getOrderByStatus(user_id, order_status, currPage, pageSize);
    }

    /**
     * 结算界面获取订单信息
     */
    @ResponseBody
    @RequestMapping("/info/{user_id}/{order_id}")
    @ApiOperation(value = "结算界面获取订单信息", notes = "结算界面获取订单信息", httpMethod = "GET")
    public ServerResponse getOrdersByUserIdStatus(@PathVariable(value = "user_id") String user_id,
                                                  @PathVariable(value = "order_id") String order_id) {

        return orderService.getOrderInfo(order_id, user_id);
    }

    /**
     * 更新订单状态
     */
    @ResponseBody
    @RequestMapping("/update/status/{user_id}/{order_id}/{order_status}")
    @ApiOperation(value = "更新订单状态", notes = "更新订单状态", httpMethod = "GET")
    public ServerResponse getOrdersByUserIdStatus(@PathVariable(value = "user_id") String user_id,
                                                  @PathVariable(value = "order_id") String order_id,
                                                  @PathVariable(value = "order_status") Integer order_status) {

        return orderService.updateOrderStatus(order_id, user_id, order_status);
    }

    /**
     * 删除订单
     */
    @ResponseBody
    @RequestMapping("/del/{user_id}/{order_id}")
    @ApiOperation(value = "删除订单", notes = "删除订单", httpMethod = "GET")
    public ServerResponse delOrder(@PathVariable(value = "user_id") String user_id,
                                   @PathVariable(value = "order_id") String order_id) {

        return orderService.del(user_id, order_id);
    }


}
