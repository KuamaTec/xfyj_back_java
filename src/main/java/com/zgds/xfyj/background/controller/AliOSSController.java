package com.zgds.xfyj.background.controller;


import com.zgds.xfyj.util.AliOSSUtils;
import com.zgds.xfyj.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Administrator
 * @descition
 * @date 2019/12/10 0010  11:17
 */
@Controller
@RequestMapping("/ali_oss")
public class AliOSSController {


    @Autowired
    private AliOSSUtils aliyunOSSUtil;

    @RequestMapping("/index")
    public String toUpLoadFile() {
        return "oss_image_upload";
    }

    /**
     * 文件上传
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public ServerResponse uploadBlog(@RequestParam("upfile") MultipartFile file) {
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        try {

            if (file != null) {
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    aliyunOSSUtil.uploadImg(newFile, "测试");
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ServerResponse.createByErrorMessage("上传失败");
        }
        return ServerResponse.createBySuccessMessages("上传成功");
    }

}
