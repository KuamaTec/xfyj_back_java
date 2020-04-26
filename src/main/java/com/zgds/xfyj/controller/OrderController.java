package com.zgds.xfyj.controller;

import com.alibaba.fastjson.JSONObject;
import com.zgds.xfyj.dao.ExpressMapper;
import com.zgds.xfyj.domain.pojo.Express;
import com.zgds.xfyj.service.OrderService;
import com.zgds.xfyj.util.HttpUtils;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 根据订单状态获取用户订单
     */
    @ResponseBody
    @RequestMapping("/list/status/{user_id}/{order_status}/{currPage}/{pageSize}")
    @ApiOperation(value = "根据订单状态获取用户订单", notes = "根据订单状态获取用户订单", httpMethod = "POST")
    public ServerResponse getOrdersByUserIdStatus(@PathVariable(value = "user_id") String user_id,
                                                  @PathVariable(value = "order_status") Integer order_status,
                                                  @PathVariable(value = "currPage") Integer currPage,
                                                  @PathVariable(value = "pageSize") Integer pageSize) {

        return orderService.getOrderByStatus(user_id, order_status, currPage, pageSize);
    }

    /**
     * 通过购物车结算下单入库
     *
     * @param order_price 订单价格
     * @param cart_ids    购物车中的id字符串（逗号分隔）
     * @param user_id     用户id
     * @param addr_id     收货地址id
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    @ApiOperation(value = "下单入库", notes = "下单入库", httpMethod = "POST")
    public ServerResponse addOrder(@RequestParam(value = "order_price") String order_price,
                                   @RequestParam(value = "cart_ids") String cart_ids,
                                   @RequestParam(value = "user_id") String user_id,
                                   @RequestParam(value = "addr_id") String addr_id) {

        return orderService.addOrder(Double.parseDouble(order_price), cart_ids, user_id, addr_id);
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

    @RequestMapping("/express1/{order_no}")
    @ResponseBody
    public ServerResponse test1(@PathVariable(name = "order_no") String orderNo){
        ServerResponse serverResponse = null;

        String host = "https://kdwlcxf.market.alicloudapi.com";
        String path = "/kdwlcx";
        String method = "GET";
        String appcode = "fba5288ec00a444c8c6da7a04d2b7d0e";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("no", "780098068058");
//        querys.put("no", orderNo);
        querys.put("type", "zto");
        //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));

            String s = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSONObject.parseObject(s);


            serverResponse = ServerResponse.createBySuccess(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serverResponse;
    }

    @Autowired
    private ExpressMapper expressMapper;
    /**
     * 根据订单获取物流单号
     */
    @RequestMapping("/express/{user_id}/{order_id}")
    @ResponseBody
    public ServerResponse test1(@PathVariable(name = "user_id") String userId,
                                @PathVariable(name = "order_id") String order_id){
        ServerResponse serverResponse = null;

        try {
            List<Express> express = expressMapper.getExpressByUserIdOrderId(userId, order_id);

            serverResponse = ServerResponse.createBySuccess(express.get(0).getExpress_no());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serverResponse;
    }


}
