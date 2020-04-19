package com.zgds.xfyj.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zgds.xfyj.config.AliyunOSSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lxp
 * @descition
 * @date 2019/10/28 0028  16:33
 */
@Component
public class AliOSSUtils {

    @Autowired
    private AliyunOSSConfig aliyunOSSConfig;

    /**
     * 阿里云OSS上传单张图片
     *
     * @param file
     * @param imgClass 图片分类
     * @throws FileNotFoundException
     */
    public void uploadImg(File file, String imgClass) throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = aliyunOSSConfig.getEndpint();
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = aliyunOSSConfig.getAccesskeyid();
        String accessKeySecret = aliyunOSSConfig.getAccesskeysecret();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //创建一个date格式化对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String objectName = sdf.format(new Date()) + "/" + imgClass + "/" + file.getName();

        InputStream inputStream = new FileInputStream(file.getPath());
        ossClient.putObject(aliyunOSSConfig.getBucket(), objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 阿里云OSS上传单张图片
     *
     * @param file
     * @param imgClass 图片分类
     * @throws FileNotFoundException
     */
    public String uploadImg(MultipartFile file, String imgClass) throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = aliyunOSSConfig.getEndpint();
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = aliyunOSSConfig.getAccesskeyid();
        String accessKeySecret = aliyunOSSConfig.getAccesskeysecret();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //创建一个date格式化对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String objectName = "xunfuyanjiju/" + imgClass + "/" + sdf.format(new Date()) + "/" + file.getOriginalFilename();

        try {
            ossClient.putObject(aliyunOSSConfig.getBucket(), objectName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        return "http://budd.lzzgjt.com/" + objectName;
    }
}