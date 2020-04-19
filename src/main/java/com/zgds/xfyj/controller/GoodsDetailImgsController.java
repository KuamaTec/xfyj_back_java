package com.zgds.xfyj.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZL
 * @descition 查询商品（酒）系列以及系列下的商品详情图
 * @date 2020/1/5
 */
@Api(description = "商品详情API")
@Controller
@RequestMapping("/goodsDetailImgs")
public class GoodsDetailImgsController {

    @Autowired
    private GoodsDetailImgsService service;

    /**
     * 根据产品id查询产品详情图片
     *
     * @param goods_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAll")
    @ApiOperation(value = "根据产品id查询产品详情图片", notes = "根据产品id查询产品详情图片", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goods_id", value = "goods_id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse getAll(@Param("goods_id") String goods_id) {
        ServerResponse serverResponse = service.getAll(goods_id);
        return serverResponse;
    }

}
