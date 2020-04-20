package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.GoodsClassifyMapper;
import com.zgds.xfyj.domain.pojo.GoodsClassify;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GoodsClassifyService {
    private final Logger logger = LoggerFactory.getLogger(GoodsClassifyService.class);

    @Autowired
    private GoodsClassifyMapper mapper;

    public ServerResponse getAll() {
        log.info("用户查询酒类分类列表：GoodsClassifyMapper.getAll（）");
        return ServerResponse.createBySuccess("查询成功！", mapper.getAll());
    }


    public ServerResponse getClassifyName(String id) {
        log.info("用户根据id查询酒类分类：GoodsClassifyMapper.getClassifyName（）", id);
        return ServerResponse.createBySuccess("查询成功！", mapper.getClassifyName(id));
    }

    public ServerResponse insert(GoodsClassify goodsClassify) {
        goodsClassify.setId(UUIDUtils.generateId());
        int i = mapper.insert(goodsClassify);
        if (i > 0) {
            log.info("用户添加酒类分类成功：GoodsClassifyMapper.insert（）", goodsClassify);
            return ServerResponse.createBySuccessMessages("添加成功！");
        } else {
            log.info("用户添加酒类分类失败：GoodsClassifyMapper.insert（）", goodsClassify);
            return ServerResponse.createBySuccessMessages("添加失败！");
        }

    }

    public ServerResponse updateGoodsClassify(GoodsClassify goodsClassify) {
        int i = mapper.updateGoodsClassify(goodsClassify);
        if (i > 0) {
            log.info("用户添加酒类分类成功：GoodsClassifyMapper.updateGoodsClassify（）", goodsClassify);
            return ServerResponse.createBySuccessMessages("修改成功！");
        } else {
            log.info("用户添加酒类分类成功：GoodsClassifyMapper.updateGoodsClassify（）", goodsClassify);
            return ServerResponse.createBySuccessMessages("修改失败！");
        }
    }

    public ServerResponse deleteGoodsClassify(String id) {
        int i = mapper.deleteGoodsClassify(id);
        if (i > 0) {
            log.info("用户删除酒类分类成功：GoodsClassifyMapper.deleteGoodsClassify（）", id);
            return ServerResponse.createBySuccessMessages("删除成功！");
        } else {
            log.info("用户删除酒类分类失败：GoodsClassifyMapper.deleteGoodsClassify（）", id);
            return ServerResponse.createBySuccessMessages("删除失败！");
        }
    }
}
