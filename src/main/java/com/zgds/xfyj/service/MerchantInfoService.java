package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.MerchantInfoMapper;
import com.zgds.xfyj.domain.pojo.MerchantInfo;
import com.zgds.xfyj.util.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MerchantInfoService {

    @Autowired
    private MerchantInfoMapper mapper;

    /**
     * 获取商家信息
     * @return
     */
    public ServerResponse getMerchantInfo() {
        ServerResponse serverResponse = null;

        try {
            MerchantInfo merchantInfo = mapper.getAll().get(0);
            merchantInfo.setPassword(null);//不显示密码
            serverResponse = ServerResponse.createBySuccess(merchantInfo);
            log.info("获取商家信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取商家信息异常");
            serverResponse = ServerResponse.createByErrorMessage("获取商家信息异常");
        } finally {
            return serverResponse;
        }
    }

}
