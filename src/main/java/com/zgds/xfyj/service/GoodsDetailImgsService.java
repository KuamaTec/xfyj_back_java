package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.GoodsDetailImgsMapper;
import com.zgds.xfyj.domain.pojo.GoodsDetailImgs;
import com.zgds.xfyj.util.AliOSSUtils;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@Service
@Slf4j
public class GoodsDetailImgsService {
    private final Logger logger = LoggerFactory.getLogger(GoodsDetailImgsService.class);

    @Autowired
    private GoodsDetailImgsMapper mapper;
    @Autowired
    private AliOSSUtils aliOSSUtils;

    private String Mapper = "GoodsDetailImgsMapper";

    public ServerResponse getAll(String goods_id) {
        log.info("用户根据goods_id（产品id）查询产品详情图图:" + Mapper + "getAll()", goods_id);
        return ServerResponse.createBySuccess("查询成功！", mapper.getAll(goods_id));
    }


    public ServerResponse list() {
        log.info("用户查询产品详情图列表：" + Mapper + "list()");
        return ServerResponse.createBySuccess("查询成功！", mapper.list());
    }

    public ServerResponse insert(GoodsDetailImgs goodsDetailImgs, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                goodsDetailImgs.setImg_url(aliOSSUtils.uploadImg(file, "Goods/GoodsDetailImgs"));
                log.info("用户添加产品详情图成功");
            } catch (FileNotFoundException e) {
                log.info("用户添加产品详情图错误", e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
        goodsDetailImgs.setId(UUIDUtils.generateId());
        int i = mapper.insert(goodsDetailImgs);
        if (i > 0) {
            log.info("用户添加产品详情图成功:" + Mapper + "insert()", goodsDetailImgs);
            return ServerResponse.createBySuccessMessages("添加产品详情图成功！");
        } else {
            log.info("用户添加产品详情图失败:" + Mapper + "insert()", goodsDetailImgs);
            return ServerResponse.createBySuccessMessages("添加产品详情图失败！");
        }
    }

    public ServerResponse updateGoodsDetailImgs(GoodsDetailImgs goodsDetailImgs, MultipartFile file) {
        if (file != null) {
            if (!file.isEmpty()) {
                try {
                    goodsDetailImgs.setImg_url(aliOSSUtils.uploadImg(file, "Goods/GoodsDetailImgs"));
                    log.info("用户修改产品详情图成功");
                } catch (FileNotFoundException e) {
                    log.info("用户修改产品详情图错误", e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        }
        int i = mapper.updateGoodsDetailImgs(goodsDetailImgs);
        if (i > 0) {
            log.info("用户修改产品详情图成功:" + Mapper + "updateGoodsDetailImgs()", goodsDetailImgs);
            return ServerResponse.createBySuccessMessages("修改产品详情图成功！");
        } else {
            log.info("用户修改产品详情图失败:" + Mapper + "updateGoodsDetailImgs()", goodsDetailImgs);
            return ServerResponse.createBySuccessMessages("修改产品详情图失败！");
        }
    }

    public ServerResponse deleteDetailImgs(String id) {
        int i = mapper.deleteDetailImgs(id);
        if (i > 0) {
            log.info("用户根据id删除产品详情图成功:" + Mapper + "deleteDetailImgs()", id);
            return ServerResponse.createBySuccessMessages("根据id删除产品详情图成功！");
        } else {
            log.info("用户根据id删除产品详情图失败:" + Mapper + "deleteDetailImgs()", id);
            return ServerResponse.createBySuccessMessages("根据id删除产品详情图失败！");
        }
    }

    public ServerResponse deleteGoodsDetailImgs(String goods_id) {
        int i = mapper.deleteGoodsDetailImgs(goods_id);
        if (i > 0) {
            log.info("用户根据goods_id（产品id）删除产品详情图成功:" + Mapper + "deleteGoodsDetailImgs()", goods_id);
            return ServerResponse.createBySuccessMessages("根据goods_id删除产品详情图成功！");
        } else {
            log.info("用户根据goods_id（产品id）删除产品详情图失败:" + Mapper + "deleteGoodsDetailImgs()", goods_id);
            return ServerResponse.createBySuccessMessages("根据goods_id删除产品详情图失败！");
        }
    }
}