package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.AdvertisingMapper;
import com.zgds.xfyj.util.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertisingService {
    private final Logger logger = LoggerFactory.getLogger(AdvertisingService.class);

    @Autowired
    private AdvertisingMapper mapper;

    public ServerResponse getAll() {
        return ServerResponse.createBySuccess("查询成功！", mapper.getAll());
    }


}
