package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.CreditsConsumeMapper;
import com.zgds.xfyj.dao.CreditsRuleMapper;
import com.zgds.xfyj.domain.pojo.CreditsConsume;
import com.zgds.xfyj.domain.pojo.CreditsRule;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CreditsRuleService {
    private final Logger logger = LoggerFactory.getLogger(CreditsRuleService.class);

    @Autowired
    private CreditsRuleMapper mapper;

    @Autowired
    private CreditsConsumeMapper consumeMapper;

    private String Mapper = "CreditsRuleMapper";

    /**
     * 查看所有获取积分规则列表
     *
     * @return
     */
    /*public ServerResponse getAll() {
        log.info("用户查询获取积分规则成功：" + Mapper + ".getAll()");
        return ServerResponse.createBySuccess("查询成功！", mapper.getAll());
    }*/

    /**
     * 查看所有获取积分规则列表
     * @return
     */
    public ServerResponse getAll(){
        log.info("用户查询获取积分规则成功："+Mapper+".getAll()");
        List list = new ArrayList();
        List<CreditsRule> creditsRuleList=mapper.getAll();
        List<CreditsConsume> consumeList=consumeMapper.getAll();
        for (CreditsRule rList:creditsRuleList) {
            Map<String,Object> map = new HashMap<>();
            for (CreditsConsume cList:consumeList) {
                map.put("id",rList.getId());
                map.put("present_percent",rList.getPresent_percent());
                map.put("min_order_price",rList.getMin_order_price());
                map.put("max_order_price",rList.getMax_order_price());
                map.put("consume_id",cList.getId());
                map.put("consume_min_order_price",cList.getMin_order_price());
                map.put("consume_percent",cList.getConsume_percent());
                list.add(map);
                break;
            }
        }
        return ServerResponse.createBySuccess("查询成功！",list);
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
     * @param creditsRule
     * @return
     */
    public ServerResponse updateCreditsRule(CreditsRule creditsRule){
        Integer i=mapper.updateCreditsRule(creditsRule);
        if (i>0){
            log.info("修改获取积分规则成功:"+Mapper+".updateCreditsRule()");
            return ServerResponse.createBySuccessMessages("修改成功！");
        }else {
            log.info("修改获取积分规则失败:"+Mapper+".updateCreditsRule()");
            return ServerResponse.createByErrorMessage("修改失败！");
        }
    }

    /**
     * 添加获取积分规则
     * @param creditsRule
     * @return
     */
    public ServerResponse insertCredits(CreditsRule creditsRule,Integer c_min_order_price,Integer consume_percent){
        int o=3,p=3;
        if (c_min_order_price!=null||consume_percent!=null){
            CreditsConsume creditsConsume = new CreditsConsume();
            creditsConsume.setId(UUIDUtils.generateId());
            creditsConsume.setMin_order_price(c_min_order_price);
            creditsConsume.setConsume_percent(consume_percent);
            int i = consumeMapper.insert(creditsConsume);
            if (i>0){
                log.info("添加消费积分规则成功:consumeMapper.insert()");
                o=1;
            }else {
                log.info("添加消费积分规则失败:consumeMapper.insert()");
                o=0;
            }
        }
        if (creditsRule!=null){
            creditsRule.setId(UUIDUtils.generateId());
            Integer j=mapper.insert(creditsRule);
            if (j>0){
                log.info("添加获取积分规则成功:"+Mapper+".insert()");
                p=1;
            }else {
                log.info("添加获取积分规则失败:"+Mapper+".insert()");
                p=0;
            }
        }
        if (o!=3&&p!=3){
            if (o==1&&p==1){
                return ServerResponse.createBySuccessMessages("添加成功！");
            }else if (o!=1&&p!=1){
                return ServerResponse.createByErrorMessage("添加失败！");
            }else if (o!=1){
                return ServerResponse.createByErrorMessage("消费积分规则添加失败！");
            }else if (p!=1){
                return ServerResponse.createByErrorMessage("获取积分规则添加失败！");
            }
        }else if(o!=3){
            if (o==1){
                return ServerResponse.createBySuccessMessages("消费积分规则添加成功！");
            }else {
                return ServerResponse.createByErrorMessage("消费积分规则添加失败！");
            }
        }else if (p!=3){
            if (p==1){
                return ServerResponse.createBySuccessMessages("获取积分规则添加成功！");
            }else {
                return ServerResponse.createByErrorMessage("获取积分规则添加失败！");
            }
        }

        return ServerResponse.createByErrorMessage("添加异常");
    }




}