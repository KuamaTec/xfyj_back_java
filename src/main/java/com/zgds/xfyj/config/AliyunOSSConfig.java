package com.zgds.xfyj.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @descition
 * @date 2019/10/28 0028  17:09
 */
@ConfigurationProperties(prefix = "aliyunoss")
@Component
public class AliyunOSSConfig {
    private String endpint;// http://oss-cn-beijing.aliyuncs.com
    private String accesskeyid;// LTAIiqlVRrr68NMR
    private String accesskeysecret;// kshf6fIstKusgz4m8j6JfNVTQS6Fcp
    private String bucket;// fojingapp

    public String getEndpint() {
        return endpint;
    }

    public void setEndpint(String endpint) {
        this.endpint = endpint;
    }

    public String getAccesskeyid() {
        return accesskeyid;
    }

    public void setAccesskeyid(String accesskeyid) {
        this.accesskeyid = accesskeyid;
    }

    public String getAccesskeysecret() {
        return accesskeysecret;
    }

    public void setAccesskeysecret(String accesskeysecret) {
        this.accesskeysecret = accesskeysecret;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}