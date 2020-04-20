package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.domain.pojo.Inform;
import com.zgds.xfyj.service.InformService;
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
 * @descition
 * @date 2020/1/8
 */
@Api(description = "后台通知API")
@Controller
@RequestMapping("/back/inform")
public class BackInformController {

    @Autowired
    private InformService service;

    /**
     * 查询通知信息列表
     *
     * @param
     * @return
     */
    /*@ResponseBody
    @RequestMapping("/getAll")
    @ApiOperation(value = "查询通知信息列表", notes = "查询通知信息列表", httpMethod = "GET")
    public ServerResponse getAll() {
        ServerResponse serverResponse = service.getAll();
        return serverResponse;
    }*/

    /**
     * 查询通知信息列表
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAll")
    @ApiOperation(value = "查询通知信息列表", notes="查询通知信息列表", httpMethod = "POST")
    public ServerResponse getAll(@RequestParam(value = "currPage", required = false, defaultValue = "1") Integer currPage,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        ServerResponse serverResponse = service.getAll(currPage,pageSize);
        return serverResponse;
    }
    /**
     * 添加通知信息
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    @ApiOperation(value = "添加通知信息", notes = "添加通知信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "通知内容", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse insert(Inform inform) {
        ServerResponse serverResponse = service.insert(inform);
        return serverResponse;
    }

    /**
     * 修改通知信息
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateInform")
    @ApiOperation(value = "修改通知信息", notes = "修改通知信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "通知id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "通知内容", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse updateInform(Inform inform) {
        ServerResponse serverResponse = service.updateInform(inform);
        return serverResponse;
    }

    /**
     * 根据id删除通知信息
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteInform")
    @ApiOperation(value = "根据id删除通知信息", notes = "根据id删除通知信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "通知id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse deleteInform(String id) {
        ServerResponse serverResponse = service.deleteInform(id);
        return serverResponse;
    }

    /**
     * 根据id删除通知信息
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    @ApiOperation(value = "根据id删除通知信息", notes="根据id删除通知信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "通知id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse deleteInform(@RequestParam(value = "id[]") String[] id){
        ServerResponse serverResponse =null;
        for (int i = 0; i < id.length; i++) {
            serverResponse = service.deleteInform(id[i]);
        }
        return serverResponse;
    }
}
