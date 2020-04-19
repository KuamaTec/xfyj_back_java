package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.domain.pojo.Goods;
import com.zgds.xfyj.service.GoodsService;
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
 * @descition 查询商品（酒）系列以及系列下的所有商品
 * @date 2020/1/8
 */
@Api(description = "后台商品API")
@Controller
@RequestMapping("/back/goods")
public class BackGoodsController {

    @Autowired
    private GoodsService service;

    /**
     * 添加商品信息
     *
     * @param goods
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    @ApiOperation(value = "添加商品信息", notes = "添加商品信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goods_name", value = "商品名称", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sale_price", value = "价格", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "standard", value = "规格", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "produce_addr", value = "产地", paramType = "query", required = true, dataType = "String"),
            /*@ApiImplicitParam(name = "file", value = "图片路径", paramType = "query", required = true, dataType = "MultipartFile"),*/
            @ApiImplicitParam(name = "recommend", value = "是否推荐（0-否，1-是）", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "classify_id", value = "分类id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "brand_id", value = "系列id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "grade", value = "酒精度", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "is_discount", value = "是否打折（0-不打折，1-打折）", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "discount", value = "所打折扣", paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "discount_price", value = "折后价", paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "capacity", value = "容量", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse insert(Goods goods, @RequestParam("file") MultipartFile file) {
        ServerResponse serverResponse = service.insert(goods, file);
        return serverResponse;
    }

    /**
     * 删除商品信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteGoods")
    @ApiOperation(value = "根据商品id删除商品信息", notes = "根据商品id删除商品信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse deleteGoods(@Param("id") String id) {
        ServerResponse serverResponse = service.deleteGoods(id);
        return serverResponse;
    }

    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateGoods")
    @ApiOperation(value = "修改商品信息", notes = "修改商品信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goods_name", value = "商品名称", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sale_price", value = "价格", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "standard", value = "规格", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "produce_addr", value = "产地", paramType = "query", required = true, dataType = "String"),
            /*@ApiImplicitParam(name = "file", value = "图片路径", paramType = "query", required = true, dataType = "MultipartFile"),*/
            @ApiImplicitParam(name = "recommend", value = "是否推荐（0-否，1-是）", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "classify_id", value = "分类id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "brand_id", value = "系列id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "grade", value = "酒精度", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "is_discount", value = "是否打折（0-不打折，1-打折）", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "discount", value = "所打折扣", paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "discount_price", value = "折后价", paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "capacity", value = "容量", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse updateGoods(Goods goods, @Param("file") MultipartFile file) {
        ServerResponse serverResponse = service.updateGoods(goods, file);
        return serverResponse;
    }
}
