package com.zgds.xfyj.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiang-ping li
 * @descition
 * @date 2020/4/21 0021  8:47
 */
@Controller
public class WebSocketController {

    @RequestMapping("/socket/index")
    public String websocket(){
        return "websocket";
    }

    @RequestMapping(value="/pushVideoListToWeb",method= RequestMethod.GET)
    @ResponseBody
//    public Map<String,Object> pushVideoListToWeb(@RequestBody Map<String,Object> param) {
    public Map<String,Object> pushVideoListToWeb() {
        Map<String,Object> result =new HashMap<String,Object>();

        try {
            WebSocketServer.sendInfo("有新客户呼入");
            result.put("operationResult", true);
        }catch (IOException e) {
            result.put("operationResult", true);
        }
        return result;
    }

}
