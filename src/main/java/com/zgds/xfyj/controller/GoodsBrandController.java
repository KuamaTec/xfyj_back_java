package com.zgds.xfyj.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZL
 * @descition
 * @date 2020/1/3
 */
@Api(description = "商品分类下系列API")
@Controller
@RequestMapping("/goodsBrand")
public class GoodsBrandController {

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
     * 根据id查询品牌
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBrand")
    @ApiOperation(value = "根据id查询商品系列", notes = "根据id查询商品系列", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "分类id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse getBrand(@Param("id") String id) {
        ServerResponse serverResponse = service.getBrand(id);
        return serverResponse;
    }

    /**
     * 根据分类id查询品牌列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getGoodsBrand")
    @ApiOperation(value = "根据分类id查询商品系列列表", notes = "根据分类id查询商品系列列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classify_id", value = "分类id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse getGoodsBrand(@Param("classify_id") String classify_id) {
        ServerResponse serverResponse = service.getGoodsBrand(classify_id);
        return serverResponse;
    }

    /**
     * 查询首页展示的所有系列
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/list/home")
    @ApiOperation(value = "获取所有首页展示商品系列列表", notes = "获取所有首页展示商品系列列表", httpMethod = "GET")
    public ServerResponse getShowHome() {
        ServerResponse serverResponse = service.getShowHome();
        return serverResponse;
    }

}
