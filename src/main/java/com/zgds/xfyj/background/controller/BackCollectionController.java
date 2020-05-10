package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.service.CollectionService;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZL
 * @descition 管理员查看用户收藏列表
 * @date 2020/4/21
 */
@Api(description = "后台管理用户收藏信息API")
@Controller
@RequestMapping("/back/collection")
public class BackCollectionController {

    @Autowired
    private CollectionService service;

    /**
     * 管理员查看用户收藏列表
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserIdCollection")
    @ApiOperation(value = "管理员查看用户收藏列表", notes = "管理员查看用户收藏列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页码", paramType = "query", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "页大小", paramType = "query", required = true, dataType = "Integer")
    })
    public ServerResponse getUserIdCollection(String user_id,Integer page,Integer limit) {
        page-=1;
        ServerResponse serverResponse = service.getUserIdCollection(user_id,page,limit);
        return serverResponse;
    }


}
