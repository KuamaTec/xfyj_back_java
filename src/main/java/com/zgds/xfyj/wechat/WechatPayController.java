package com.zgds.xfyj.wechat;

import com.github.wxpay.sdk.WXPayUtil;
import com.zgds.xfyj.config.WxPayConfig;
import com.zgds.xfyj.util.HttpClient;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
@Slf4j
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
            map.put("total_fee",String.valueOf(1));
            map.put("spbill_create_ip","127.0.0.1");
            map.put("notify_url",wxPayConfig.getNotifyurl());
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

    //TODO 微信异步回调
    //TODO 微信异步回调
    //TODO 微信异步回调
    //TODO 微信异步回调
    //TODO 微信异步回调
    //TODO 微信异步回调
    //TODO 微信异步回调
    //TODO 微信异步回调
    //TODO 微信异步回调
    //TODO 微信异步回调
    //TODO 微信异步回调
    //TODO 微信异步回调
    //TODO 微信异步回调
    @RequestMapping(value = "/pay/notify",method = RequestMethod.POST)
//    @ResponseBody
    public String notify(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("异步通知：");
        System.out.println("异步通知：");
        System.out.println("异步通知：");
        System.out.println("异步通知：");
        System.out.println("异步通知：");
        System.out.println("异步通知：");
        log.info("================================================开始处理微信小程序发送的异步通知");

        //1 获取微信支付异步回调结果
        String xmlResult = getPostStr(request);

        Map<String, String> resultMap = null;
        try {
            //将结果转成map
            resultMap = WXPayUtil.xmlToMap(xmlResult);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //订单号
        String orderNo = resultMap.get("out_trade_no");
        log.info("订单号：------------------"+orderNo+"结束----------");
        String result_code = resultMap.get("result_code");
        //回调返回的加密签名 保存下来 下面会进行对比
        String sign = resultMap.get("sign");
        //去掉sign和利用微信回调回来的信息重新加密
        resultMap.remove("sign");
        String sign1="";
        try {
            //重新加密 获取加密的签名
            sign1=WXPayUtil.generateSignature(resultMap, wxPayConfig.getKey()); //签名
        }catch (Exception e){

        }


        String resultCode;
        String resultMsg;
        //对比微信回调的加密与重新加密是否一致  一致即为通过 不一致说明呗改动过 加密不通过
        log.info("==============================================开始对比加密++++++++++++++++++++++++++++++++++++++");
        if (sign.equals(sign1)) { //验签通过
            log.info("==============================================验签通过++++++++++++++++++++++++++++++++++++++");

            if (true) {//业务结果为SUCCESS
//            if (WXPayEnum.isPaymentSuccess(result_code)) {//业务结果为SUCCESS
                /**
                 * 这里写你要处理的逻辑
                 */
                resultCode = "SUCCESS";
                resultMsg = "成功";
            } else { // 业务结果为FALL
                resultCode = "FAIL";
                resultMsg = "业务结果为FAIL";
            }

        } else {
            resultCode = "FAIL";
            resultMsg = "验签未通过";
        }

        Map<String, String> returnMap = new HashMap<>();
        returnMap.put("return_code", resultCode);
        returnMap.put("return_msg", resultMsg);
        try {
            String s = WXPayUtil.mapToXml(returnMap);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getPostStr(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        try {
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "";

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String xml = sb.toString(); //次即为接收到微信端发送过来的xml数据
        return xml;

    }

}
