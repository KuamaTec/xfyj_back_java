package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.domain.pojo.GoodsDetailImgs;
import com.zgds.xfyj.service.GoodsDetailImgsService;
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
 * @descition 查询商品（酒）系列以及系列下的商品详情图
 * @date 2020/1/8
 */
@Api(description = "后台商品详情API")
@Controller
@RequestMapping("/back/goodsDetailImgs")
public class BackGoodsDetailImgsController {

    @Autowired
    private GoodsDetailImgsService service;

    /**
     * 查看所有详情列表
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    @ApiOperation(value = "查看所有详情列表", notes = "查看所有详情列表", httpMethod = "GET")
    public ServerResponse list() {
        ServerResponse serverResponse = service.list();
        return serverResponse;
    }

    /**
     * 添加产品详情图片
     *
     * @param goodsDetailImgs
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    @ApiOperation(value = "添加产品详情图片", notes = "添加产品详情图片", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "img_order", value = "图片顺序", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "goods_id", value = "产品id", paramType = "query", required = true, dataType = "String")

    })
    public ServerResponse insert(GoodsDetailImgs goodsDetailImgs, @RequestParam("file") MultipartFile file) {
        ServerResponse serverResponse = service.insert(goodsDetailImgs, file);
        return serverResponse;
    }

    /**
     * 修改产品详情图片
     *
     * @param goodsDetailImgs
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateGoodsDetailImgs")
    @ApiOperation(value = "修改产品详情图片", notes = "修改产品详情图片", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品轮播图id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "img_order", value = "图片顺序", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "goods_id", value = "产品id", paramType = "query", required = true, dataType = "String")

    })
    public ServerResponse updateGoodsDetailImgs(GoodsDetailImgs goodsDetailImgs, @Param("file") MultipartFile file) {
        ServerResponse serverResponse = service.updateGoodsDetailImgs(goodsDetailImgs, file);
        return serverResponse;
    }

    /**
     * 根据id删除产品详情图片
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteDetailImgs")
    @ApiOperation(value = "根据id删除产品详情图片", notes = "根据id删除产品详情图片", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品轮播图id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse deleteDetailImgs(String id) {
        ServerResponse serverResponse = service.deleteDetailImgs(id);
        return serverResponse;
    }

    /**
     * 根据goods_id删除产品详情图片
     *
     * @param goods_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteGoodsDetailImgs")
    @ApiOperation(value = "根据goods_id删除产品详情图片", notes = "根据goods_id删除产品详情图片", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goods_id", value = "产品id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse deleteGoodsDetailImgs(String goods_id) {
        ServerResponse serverResponse = service.deleteGoodsDetailImgs(goods_id);
        return serverResponse;
    }
}
