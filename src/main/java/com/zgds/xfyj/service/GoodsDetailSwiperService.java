package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.GoodsDetailSwiperMapper;
import com.zgds.xfyj.domain.pojo.GoodsDetailSwiper;
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
public class GoodsDetailSwiperService {
    private final Logger logger = LoggerFactory.getLogger(GoodsDetailSwiperService.class);

    @Autowired
    private GoodsDetailSwiperMapper mapper;
    @Autowired
    private AliOSSUtils aliOSSUtils;

    private String Mapper = "GoodsDetailSwiperMapper";

    public ServerResponse getAll(String goods_id) {
        log.info("用户查询产品轮播图列表：" + Mapper + ".getAll（）", goods_id);
        return ServerResponse.createBySuccess("查询成功!", mapper.getAll(goods_id));
    }


    public ServerResponse list() {
        log.info("用户查询产品轮播图列表：" + Mapper + ".list（）");
        return ServerResponse.createBySuccess("查询成功！", mapper.list());
    }

    public ServerResponse insert(GoodsDetailSwiper goodsDetailSwiper, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                goodsDetailSwiper.setImg_url(aliOSSUtils.uploadImg(file, "Goods/GoodsDetailSwiper"));
                log.info("用户添加产品轮播图文件上传成功!");
            } catch (FileNotFoundException e) {
                log.info("用户添加产品轮播图文件上传错误：", e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
        goodsDetailSwiper.setId(UUIDUtils.generateId());
        int i = mapper.insert(goodsDetailSwiper);
        if (i > 0) {
            log.info("用户添加产品轮播图成功:" + Mapper + ".insert()", goodsDetailSwiper);
            return ServerResponse.createBySuccessMessages("添加产品轮播图成功！");
        } else {
            log.info("用户添加产品轮播图失败:" + Mapper + ".insert()", goodsDetailSwiper);
            return ServerResponse.createBySuccessMessages("添加产品轮播图失败！");
        }
    }

    public ServerResponse updateGoodsDetailSwiper(GoodsDetailSwiper goodsDetailSwiper, MultipartFile file) {
        int i = mapper.updateGoodsDetailSwiper(goodsDetailSwiper);
        if (file != null) {
            if (!file.isEmpty()) {
                try {
                    goodsDetailSwiper.setImg_url(aliOSSUtils.uploadImg(file, "Goods/GoodsDetailSwiper"));
                    log.info("用户修改产品轮播图文件上传成功!");
                } catch (FileNotFoundException e) {
                    log.info("用户修改产品轮播图文件上传错误：", e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        }
        if (i > 0) {
            log.info("用户修改产品轮播图成功:" + Mapper + ".updateGoodsDetailSwiper()", goodsDetailSwiper);
            return ServerResponse.createBySuccessMessages("修改产品轮播图成功！");
        } else {
            log.info("用户修改产品轮播图失败:" + Mapper + ".updateGoodsDetailSwiper()", goodsDetailSwiper);
            return ServerResponse.createBySuccessMessages("修改产品轮播图失败！");
        }
    }

    public ServerResponse deleteDetailSwiper(String id) {
        int i = mapper.deleteDetailSwiper(id);
        if (i > 0) {
            log.info("用户根据id删除产品轮播图成功:" + Mapper + ".deleteDetailSwiper()", id);
            return ServerResponse.createBySuccessMessages("根据id删除产品轮播图成功！");
        } else {
            log.info("用户根据id删除产品轮播图失败:" + Mapper + ".deleteDetailSwiper()", id);
            return ServerResponse.createBySuccessMessages("根据id删除产品轮播图失败！");
        }
    }

    public ServerResponse deleteGoodsDetailSwiper(String goods_id) {
        int i = mapper.deleteGoodsDetailSwiper(goods_id);
        if (i > 0) {
            log.info("用户根据goods_id（产品id）删除产品轮播图成功:" + Mapper + ".deleteGoodsDetailSwiper()", goods_id);
            return ServerResponse.createBySuccessMessages("根据goods_id删除产品轮播图成功！");
        } else {
            log.info("用户根据goods_id（产品id）删除产品轮播图失败:" + Mapper + ".deleteGoodsDetailSwiper()", goods_id);
            return ServerResponse.createBySuccessMessages("根据goods_id删除产品轮播图失败！");
        }
    }
}