package com.zgds.xfyj.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by on 2019/1/4.
 */
@ConfigurationProperties(prefix = "wx.config")
@Component
@Data
public class WxPayConfig {

    //唯一标识
    //商户号
    private String appsercret;
    private String appid;

    //生成二维码支付链接接口地址
    //回调地址
    //秘钥
    private String mch_id;
    private String payUrl;
    private String notifyurl;
    private String key;

}
