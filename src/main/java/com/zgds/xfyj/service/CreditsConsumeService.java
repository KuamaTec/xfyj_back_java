package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.CreditsConsumeMapper;
import com.zgds.xfyj.domain.pojo.CreditsConsume;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreditsConsumeService {
    private final Logger logger = LoggerFactory.getLogger(CreditsConsumeService.class);

    @Autowired
    private CreditsConsumeMapper mapper;

    private String Mapper = "CreditsConsumeMapper";

    /**
     * 查看所有消费积分规则列表
     *
     * @return
     */
    public ServerResponse getAll() {
        log.info("用户查询消费积分规则成功：" + Mapper + ".getAll()");
        return ServerResponse.createBySuccess("查询成功！", mapper.getAll());
    }

    /**
     * 添加消费积分规则
     *
     * @param creditsConsume
     * @return
     */
    public ServerResponse insert(CreditsConsume creditsConsume) {
        creditsConsume.setId(UUIDUtils.generateId());
        Integer i = mapper.insert(creditsConsume);
        if (i > 0) {
            log.info("添加消费积分规则成功:" + Mapper + ".insert()");
            return ServerResponse.createBySuccessMessages("添加成功！");
        } else {
            log.info("添加消费积分规则失败:" + Mapper + ".insert()");
            return ServerResponse.createByErrorMessage("添加失败！");
        }
    }

    /**
     * 根据id删除消费积分规则
     *
     * @param id
     * @return
     */
    public ServerResponse deleteCreditsConsume(String id) {
        Integer i = mapper.deleteCreditsConsume(id);
        if (i > 0) {
            log.info("删除消费积分规则成功:" + Mapper + ".deleteCreditsConsume()");
            return ServerResponse.createBySuccessMessages("删除成功！");
        } else {
            log.info("删除消费积分规则失败:" + Mapper + ".deleteCreditsConsume()");
            return ServerResponse.createByErrorMessage("删除失败！");
        }
    }

    /**
     * 修改消费积分规则
     *
     * @param creditsConsume
     * @return
     */
    public ServerResponse updateCreditsConsume(CreditsConsume creditsConsume) {
        Integer i = mapper.updateCreditsConsume(creditsConsume);
        if (i > 0) {
            log.info("修改消费积分规则成功:" + Mapper + ".updateCreditsConsume()");
            return ServerResponse.createBySuccessMessages("修改成功！");
        } else {
            log.info("修改消费积分规则失败:" + Mapper + ".updateCreditsConsume()");
            return ServerResponse.createByErrorMessage("修改失败！");
        }
    }
}