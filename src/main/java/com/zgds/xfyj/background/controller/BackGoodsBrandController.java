package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.domain.pojo.GoodsBrand;
import com.zgds.xfyj.service.GoodsBrandService;
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
 * @descition
 * @date 2020/1/8
 */
@Api(description = "后台商品分类下系列API")
@Controller
@RequestMapping("/back/goodsBrand")
public class BackGoodsBrandController {

    @Autowired
    private GoodsBrandService service;

    /**
     * 查询所有品牌列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    @ApiOperation(value = "获取所有商品系列列表", notes = "获取所有商品系列列表", httpMethod = "GET")
    public ServerResponse getAll() {
        ServerResponse serverResponse = service.getAll();
        return serverResponse;
    }

    /**
     * 添加产品分类系列
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    @ApiOperation(value = "添加产品分类系列", notes = "添加产品分类系列", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classify_id", value = "分类id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "brand_name", value = "系列名称", paramType = "query", required = true, dataType = "String"),
            /*@ApiImplicitParam(name = "img", value = "分类名称", paramType = "query", required = true, dataType = "String"),*/
            @ApiImplicitParam(name = "show_home", value = "首页是否显示（0-否，1-是）", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rank", value = "首页显示顺序（越小越靠前）", paramType = "query", required = true, dataType = "String")

    })
    public ServerResponse insert(GoodsBrand goodsBrand, @RequestParam("file") MultipartFile file) {
        ServerResponse serverResponse = service.insert(goodsBrand, file);
        return serverResponse;
    }

    /**
     * 修改产品分类系列
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateGoodsClassify")
    @ApiOperation(value = "修改产品分类系列", notes = "修改产品分类系列", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "系列id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "classify_id", value = "分类id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "brand_name", value = "系列名称", paramType = "query", required = true, dataType = "String"),
            /*@ApiImplicitParam(name = "img", value = "分类名称", paramType = "query", required = true, dataType = "String"),*/
            @ApiImplicitParam(name = "show_home", value = "首页是否显示（0-否，1-是）", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rank", value = "首页显示顺序（越小越靠前）", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse updateGoodsClassify(GoodsBrand goodsBrand, @Param("file") MultipartFile file) {
        ServerResponse serverResponse = service.updateGoodsBrand(goodsBrand, file);
        return serverResponse;
    }

    /**
     * 删除产品分类系列
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteGoodsClassify")
    @ApiOperation(value = "删除产品分类系列", notes = "删除产品分类系列", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "系列id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse deleteGoodsClassify(String id) {
        ServerResponse serverResponse = service.deleteGoodsBrand(id);
        return serverResponse;
    }
}
