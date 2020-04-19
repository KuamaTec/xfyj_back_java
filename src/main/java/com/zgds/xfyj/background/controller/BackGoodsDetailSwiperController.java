package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.domain.pojo.GoodsDetailSwiper;
import com.zgds.xfyj.service.GoodsDetailSwiperService;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZL
 * @descition 查询商品（酒）系列以及系列下的商品轮播图
 * @date 2020/1/8
 */
@Api(description = "后台商品轮播图API")
@Controller
@RequestMapping("/back/goodsDetailSwiper")
public class BackGoodsDetailSwiperController {

    @Autowired
    private GoodsDetailSwiperService service;

    /**
     * 查看所有产品轮播图列表
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    @ApiOperation(value = "查看所有产品轮播图列表", notes = "查看所有产品轮播图列表", httpMethod = "GET")
    public ServerResponse list() {
        ServerResponse serverResponse = service.list();
        return serverResponse;
    }

    /**
     * 添加产品轮播图
     *
     * @param goodsDetailSwiper
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    @ApiOperation(value = "添加产品轮播图", notes = "添加产品轮播图", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goods_id", value = "产品id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse insert(GoodsDetailSwiper goodsDetailSwiper, @RequestParam("file") MultipartFile file) {
        ServerResponse serverResponse = service.insert(goodsDetailSwiper, file);
        return serverResponse;
    }

    /**
     * 修改产品轮播图
     *
     * @param goodsDetailSwiper
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateGoodsDetailSwiper")
    @ApiOperation(value = "修改产品轮播图", notes = "修改产品轮播图", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品轮播图id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goods_id", value = "产品id", paramType = "query", required = true, dataType = "String")

    })
    public ServerResponse updateGoodsDetailSwiper(GoodsDetailSwiper goodsDetailSwiper, @Param("file") MultipartFile file) {
        ServerResponse serverResponse = service.updateGoodsDetailSwiper(goodsDetailSwiper, file);
        return serverResponse;
    }

    /**
     * 根据id删除产品轮播图
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteDetailSwiper")
    @ApiOperation(value = "根据id删除产品轮播图", notes = "根据id删除产品轮播图", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品轮播图id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse deleteDetailSwiper(String id) {
        ServerResponse serverResponse = service.deleteDetailSwiper(id);
        return serverResponse;
    }

    /**
     * 根据goods_id删除产品轮播图
     *
     * @param goods_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteGoodsDetailSwiper")
    @ApiOperation(value = "根据goods_id删除产品轮播图", notes = "根据goods_id删除产品轮播图", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goods_id", value = "产品id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse deleteGoodsDetailSwiper(String goods_id) {
        ServerResponse serverResponse = service.deleteGoodsDetailSwiper(goods_id);
        return serverResponse;
    }

}
