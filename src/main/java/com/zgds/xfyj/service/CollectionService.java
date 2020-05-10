package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.CollectionMapper;
import com.zgds.xfyj.dao.GoodsMapper;
import com.zgds.xfyj.domain.pojo.Collection;
import com.zgds.xfyj.domain.pojo.Goods;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
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
public class CollectionService {
    private final Logger logger = LoggerFactory.getLogger(CollectionService.class);

    @Autowired
    private CollectionMapper mapper;
    @Autowired
    private GoodsMapper goodsMapper;

    private String Mapper = "OrderMapper";


    /**
     * 管理员查询用户收藏列表
     * @return
     */
    public ServerResponse getUserIdCollection(String user_id, Integer page, Integer pageSize){
        log.info("管理员查询用户收藏列表:"+Mapper+".getUserIdCollection()");
        List<Collection>  goodsList=mapper.getUserIdCollection(user_id,page,pageSize);
        List list = new ArrayList();
        for (Collection li:goodsList) {
            Map map = new HashMap();
            Goods goods = goodsMapper.getGoodsId(li.getGoods_id());
            map.put("id",li.getId());
            map.put("goods_name",li.getGoods_name());
            map.put("sale_price",goods.getSale_price());
            map.put("main_img",goods.getMain_img());
            map.put("collect_time",li.getCollect_time());
            list.add(map);
        }
        Map map = new HashMap();
        map.put("list",list);
        map.put("number",mapper.number());
        return ServerResponse.createBySuccess("查询成功！",map);
    }




}