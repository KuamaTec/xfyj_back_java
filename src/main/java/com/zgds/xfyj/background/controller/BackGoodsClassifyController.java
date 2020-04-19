package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.domain.pojo.GoodsClassify;
import com.zgds.xfyj.service.GoodsClassifyService;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZL
 * @descition
 * @date 2020/1/8
 */
@Api(description = "后台商品分类API")
@Controller
@RequestMapping("/back/goodsClassify")
public class BackGoodsClassifyController {

    @Autowired
    private GoodsClassifyService service;

    /**
     * 查询所有分类列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    @ApiOperation(value = "获取分类列表", notes = "获取分类列表", httpMethod = "GET")
    public ServerResponse getAll() {
        ServerResponse serverResponse = service.getAll();
        return serverResponse;
    }

    /**
     * 根据id查询产品分类名称
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getClassifyName")
    @ApiOperation(value = "根据id查询产品分类名称", notes = "根据id查询产品分类名称", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "分类id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse getClassifyName(String id) {
        ServerResponse serverResponse = service.getClassifyName(id);
        return serverResponse;
    }

    /**
     * 添加产品分类
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    @ApiOperation(value = "添加产品分类", notes = "添加产品分类", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classify_name", value = "分类名称", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse insert(GoodsClassify goodsClassify) {
        ServerResponse serverResponse = service.insert(goodsClassify);
        return serverResponse;
    }

    /**
     * 修改产品分类
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateGoodsClassify")
    @ApiOperation(value = "修改产品分类", notes = "修改产品分类", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "分类id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "classify_name", value = "分类名称", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse updateGoodsClassify(GoodsClassify goodsClassify) {
        ServerResponse serverResponse = service.updateGoodsClassify(goodsClassify);
        return serverResponse;
    }

    /**
     * 删除产品分类
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteGoodsClassify")
    @ApiOperation(value = "删除产品分类", notes = "删除产品分类", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "分类id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse deleteGoodsClassify(String id) {
        ServerResponse serverResponse = service.deleteGoodsClassify(id);
        return serverResponse;
    }
}
