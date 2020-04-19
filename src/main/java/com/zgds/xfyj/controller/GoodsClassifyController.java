package com.zgds.xfyj.controller;

import com.zgds.xfyj.service.GoodsClassifyService;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZL
 * @descition
 * @date 2020/1/3
 */
@Api(description = "商品分类API")
@Controller
@RequestMapping("/goodsClassify")
public class GoodsClassifyController {

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

}
