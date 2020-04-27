package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.ExpressMapper;
import com.zgds.xfyj.domain.pojo.Express;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ExpressService {
    private final Logger logger = LoggerFactory.getLogger(ExpressService.class);

    @Autowired
    private ExpressMapper mapper;

    private String Mapper = "ExpressMapper";

    /**
     * 管理员查看物流信息
     * @return
     */
    public ServerResponse getAll(Integer start, Integer pageSize){
        List<Express>  expressList=mapper.getAll(start,pageSize);
        log.info("用户查看所有订单:"+Mapper+".getExpress()");
        return ServerResponse.createBySuccess("查询成功！",expressList);
    }

    /**
     *管理员添加物流信息
     * @return
     */
    public ServerResponse insert(Express express) {
        express.setId(UUIDUtils.generateId());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        express.setUpdate_time(sdf.format(new Date()));
        log.info("管理员开始添加物流信息！",Mapper+".insert"+express);
        Integer i= mapper.insert(express);
        if (i>0){
            log.info("用户添加物流信息成功！",Mapper+".insert"+express);
            return ServerResponse.createBySuccess("管理员添加物流信息成功！");
        }else {
            log.info("用户添加物流信息失败！",Mapper+".insert"+express);
            return ServerResponse.createByErrorMessage("管理员添加物流信息失败！");
        }
    }

}