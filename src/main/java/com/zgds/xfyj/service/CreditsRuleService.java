package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.CreditsRuleMapper;
import com.zgds.xfyj.domain.pojo.CreditsRule;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreditsRuleService {
    private final Logger logger = LoggerFactory.getLogger(CreditsRuleService.class);

    @Autowired
    private CreditsRuleMapper mapper;

    private String Mapper = "CreditsRuleMapper";

    /**
     * 查看所有获取积分规则列表
     *
     * @return
     */
    public ServerResponse getAll() {
        log.info("用户查询获取积分规则成功：" + Mapper + ".getAll()");
        return ServerResponse.createBySuccess("查询成功！", mapper.getAll());
    }

    /**
     * 添加获取积分规则
     *
     * @param creditsRule
     * @return
     */
    public ServerResponse insert(CreditsRule creditsRule) {
        creditsRule.setId(UUIDUtils.generateId());
        Integer i = mapper.insert(creditsRule);
        if (i > 0) {
            log.info("添加获取积分规则成功:" + Mapper + ".insert()");
            return ServerResponse.createBySuccessMessages("添加成功！");
        } else {
            log.info("添加获取积分规则失败:" + Mapper + ".insert()");
            return ServerResponse.createByErrorMessage("添加失败！");
        }
    }

    /**
     * 根据id删除获取积分规则
     *
     * @param id
     * @return
     */
    public ServerResponse deleteCreditsRule(String id) {
        Integer i = mapper.deleteCreditsRule(id);
        if (i > 0) {
            log.info("删除获取积分规则成功:" + Mapper + ".deleteCreditsRule()");
            return ServerResponse.createBySuccessMessages("删除成功！");
        } else {
            log.info("删除获取积分规则失败:" + Mapper + ".deleteCreditsRule()");
            return ServerResponse.createByErrorMessage("删除失败！");
        }
    }

    /**
     * 修改获取积分规则
     *
     * @param creditsRule
     * @return
     */
    public ServerResponse updateCreditsRule(CreditsRule creditsRule) {
        Integer i = mapper.updateCreditsRule(creditsRule);
        if (i > 0) {
            log.info("修改获取积分规则成功:" + Mapper + ".updateCreditsRule()");
            return ServerResponse.createBySuccessMessages("修改成功！");
        } else {
            log.info("修改获取积分规则失败:" + Mapper + ".updateCreditsRule()");
            return ServerResponse.createByErrorMessage("修改失败！");
        }
    }
}