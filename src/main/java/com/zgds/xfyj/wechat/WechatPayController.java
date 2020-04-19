package com.zgds.xfyj.wechat;

import com.github.wxpay.sdk.WXPayUtil;
import com.zgds.xfyj.config.WxPayConfig;
import com.zgds.xfyj.util.HttpClient;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiang-ping li
 * @descition
 * @date 2020/4/15 0015  9:31
 */
@Controller
@RequestMapping("/weChat")
@EnableConfigurationProperties(WxPayConfig.class)
public class WechatPayController {

    @Autowired
    private WxPayConfig wxPayConfig;

    /**
     * 微信支付预下单（1次签名）
     * @param openId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/placeAnOrder/{openid}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Map> preOrder(@PathVariable(name = "openid") String openId) throws Exception{
        try {
            String out_trade_no = UUIDUtils.generateId();
            HashMap<String, String> map = new HashMap<>();
            map.put("appid",wxPayConfig.getAppid());
            map.put("mch_id",wxPayConfig.getMch_id());
            map.put("openid",openId);
            map.put("nonce_str", WXPayUtil.generateNonceStr());
            map.put("body","the wind swordsman");
            map.put("out_trade_no",out_trade_no);
            map.put("total_fee",String.valueOf(wxPayConfig.getOrdinarymember()));
            map.put("spbill_create_ip","127.0.0.1");
            map.put("notify_url","www.baidu.com");
            map.put("trade_type","JSAPI");
            //统一下单 key传入的是 api密钥
            map.put("sign", WXPayUtil.generateSignature(map,wxPayConfig.getKey()));
            //校验签名是否正确
            boolean signatureValid = WXPayUtil.isSignatureValid(map, wxPayConfig.getKey());
            System.out.println(signatureValid);
            //转换参数为xml格式的字符串 key传入的是 api密钥
            String s = WXPayUtil.generateSignedXml(map, wxPayConfig.getKey());
            //创建httpClient 对象，调用微信支付接口，传递参数
            HttpClient httpClient = new HttpClient(wxPayConfig.getPayUrl());
            //设置请求方式
            httpClient.setHttps(true);
            //设置参数
            httpClient.setXmlParam(s);
            //发送请求
            httpClient.post();
            //获取返回结果
            String result = httpClient.getContent();
            //把返回值结果转换为map
            Map<String, String> urlMap = WXPayUtil.xmlToMap(result);
            long time = new Date().getSeconds();
            urlMap.put("timeStemp",time+"");
            //获取支付链接地址
            System.out.println(urlMap);

            return ServerResponse.createBySuccess(urlMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("下单失败");
        }
    }

    @RequestMapping(value = "/second_sign/{openid}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Map> pay(@PathVariable(name = "openid") String openId) throws Exception {
        ServerResponse<Map> order = preOrder(openId);
        Map data = order.getData();
        //签名
        HashMap<String, String> map = new HashMap<>();
        String appid = (String)data.get("appid");
        String timeStamp = (String)data.get("timeStemp");
        String nonce_str = (String)data.get("nonce_str");
        String prepay_id = (String)data.get("prepay_id");

        map.put("appId",appid);
        map.put("timeStamp",timeStamp);
        map.put("nonceStr",nonce_str);
        map.put("package","prepay_id="+prepay_id);
        map.put("signType","MD5");

        //支付使用的
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("timeStamp",timeStamp);
        map1.put("nonceStr",nonce_str);
        map1.put("package","prepay_id="+prepay_id);
        map1.put("paySign",WXPayUtil.generateSignature(map,wxPayConfig.getKey()));
        map1.put("signType","MD5");

        return ServerResponse.createBySuccess(map1);
    }

}
