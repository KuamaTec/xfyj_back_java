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
    private String appid;
    //商户号
    private String mch_id;
    //生成二维码支付链接接口地址
    private String payUrl;
    private String notifyurl;
    //秘钥
    private String appsercret;
    //商户
    private String key;
    //普通会员
    private Integer ordinarymember;
    //顶级会员
    private Integer seniormember;

    //普通会员升级高级会员
    private Integer upgrademember;


}
