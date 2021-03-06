package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.InformMapper;
import com.zgds.xfyj.domain.pojo.Inform;
import com.zgds.xfyj.domain.pojo.User;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class InformService {
    private final Logger logger = LoggerFactory.getLogger(InformService.class);

    @Autowired
    private InformMapper mapper;

    public ServerResponse getInform() {
        return ServerResponse.createBySuccess("查询成功！", mapper.getInform());
    }


    public ServerResponse getAll(Integer currPage,Integer pageSize){
        log.info("商家查询所有通知信息：InformMapper.getAll（）");
        List<Inform> informs = mapper.getAll(currPage,pageSize);
        Map map = new HashMap();
        map.put("list",informs);
        map.put("number",mapper.number());
        return ServerResponse.createBySuccess("查询成功！",map);
    }

    public ServerResponse insert(Inform inform) {
        inform.setId(UUIDUtils.generateId());
        inform.setTime(new Date());
        int i = mapper.insert(inform);
        if (i > 0) {
            log.info("商家添加通知信息成功：InformMapper.insert（）");
            return ServerResponse.createBySuccessMessages("添加成功！");
        } else {
            log.info("商家添加通知信息失败：InformMapper.insert（）");
            return ServerResponse.createBySuccessMessages("添加失败！");
        }
    }

    public ServerResponse deleteInform(String id) {
        int i = mapper.deleteInform(id);
        if (i > 0) {
            log.info("商家删除通知信息成功：InformMapper.deleteInform（）");
            return ServerResponse.createBySuccessMessages("删除成功！");
        } else {
            log.info("商家删除通知信息失败：InformMapper.deleteInform（）");
            return ServerResponse.createBySuccessMessages("删除失败！");
        }
    }

    public ServerResponse updateInform(Inform inform) {
        inform.setTime(new Date());
        int i = mapper.updateInform(inform);
        if (i > 0) {
            log.info("商家修改通知信息成功：InformMapper.updateInform（）");
            return ServerResponse.createBySuccessMessages("修改成功！");
        } else {
            log.info("商家修改通知信息失败：InformMapper.updateInform（）");
            return ServerResponse.createBySuccessMessages("修改失败！");
        }
    }
}
