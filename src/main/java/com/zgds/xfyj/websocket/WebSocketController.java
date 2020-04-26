package com.zgds.xfyj.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

/**
 * @author Xiang-ping li
 * @descition
 * @date 2020/4/21 0021  8:47
 */
@Controller
public class WebSocketController {

    @Autowired
    private WebSocketServer webSocketServer;

    @RequestMapping("/socket/index")
    public String websocket(){
        return "websocket";
    }



    @RequestMapping(value="/push2chrome/{user_id}/{order_id}",method= RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> pushVideoListToWeb(@PathVariable(name = "user_id")String userId,
                                                 @PathVariable(name = "order_id")String orderId) {
        try {
            //1、存库
            //2、推送
//            sendMessage("你有新的订单请及时处理");
            webSocketServer.sendInfo("你有新的订单请及时处理,用户id："+userId+"，订单id："+orderId);
        }catch (IOException e) {
        }
        return null;
    }

}
