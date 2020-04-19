package com.zgds.xfyj.controller;

import com.zgds.xfyj.dao.AddressMapper;
import com.zgds.xfyj.dao.UserMapper;
import com.zgds.xfyj.domain.pojo.Address;
import com.zgds.xfyj.domain.pojo.User;
import com.zgds.xfyj.service.AddressService;
import com.zgds.xfyj.service.UserService;
import com.zgds.xfyj.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;

/**
 * @author ZL
 * @descition
 * @date 2020/1/3
 */
@Api(description = "通知API")
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @ResponseBody
    @RequestMapping("/info/{user_id}")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "GET")
    public ServerResponse getCollections(@PathVariable(value = "user_id") String user_id) {
        ServerResponse serverResponse = null;

        try {
            User user = userMapper.selectByPrimaryKey(user_id);
            user.setNick(URLDecoder.decode(user.getNick()));
            serverResponse = ServerResponse.createBySuccess("获取用户信息成功", user);
            log.info("获取用户{}信息成功", user_id);
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("获取用户信息失败");
            log.info("获取用户{}信息异常，错误信息{}", user_id, e.getLocalizedMessage());
        } finally {
            return serverResponse;
        }

    }

    /**
     * 添加收藏
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/collection/add/{user_id}/{goods_id}/{goods_name}")
    @ApiOperation(value = "添加收藏", notes = "添加收藏", httpMethod = "GET")
    public ServerResponse getCollections(@PathVariable(value = "user_id") String userId,
                                         @PathVariable(value = "goods_id") String goodsId,
                                         @PathVariable(value = "goods_name") String goodsName) {
        return userService.addCollection(goodsName, goodsId, userId);
    }

    /**
     * 判断是否收藏
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/collection/is_collected/{user_id}/{goods_id}")
    @ApiOperation(value = "判断是否收藏", notes = "判断是否收藏", httpMethod = "GET")
    public ServerResponse getCollections(@PathVariable(value = "user_id") String userId,
                                         @PathVariable(value = "goods_id") String goodsId) {
        return userService.isCollected(goodsId, userId);
    }

    /**
     * 获取用户收藏列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/collection/list")
    @ApiOperation(value = "获取收藏列表", notes = "获取收藏列表", httpMethod = "POST")
    public ServerResponse getCollections(@RequestParam(value = "userId") String userId,
                                         @RequestParam(value = "currPage") Integer currPage,
                                         @RequestParam(value = "pageSize") Integer pageSize) {
        return userService.getCollectionsByUserId(userId, currPage, pageSize);
    }

    /**
     * 用户删除收藏
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/collection/del/{user_id}/{goods_id}")
    @ApiOperation(value = "用户删除收藏", notes = "用户删除收藏", httpMethod = "GET")
    public ServerResponse delCollections(@PathVariable(value = "user_id") String userId,
                                         @PathVariable(value = "goods_id") String id) {

        return userService.delCollection(id, userId);
    }


    /**
     * 获取用户收货地址
     *
     * @return
     */
    @Autowired
    private AddressMapper addressMapper;

    @ResponseBody
    @RequestMapping("/addr/{userId}")
    @ApiOperation(value = "获取指定用户的收货地址信息", notes = "获取指定用户的收货地址信息", httpMethod = "GET")
    public ServerResponse getAddress(@PathVariable(value = "userId") String userId) {

        return addressService.getAddress(userId);
    }

    /**
     * 设置默认收货地址
     *
     * @param userId
     * @param addressId
     * @return
     */
    @Autowired
    private AddressService addressService;

    @ResponseBody
    @RequestMapping("/addr/default/{userId}/{addressId}")
    @ApiOperation(value = "设置默认收货地址", notes = "设置默认收货地址", httpMethod = "GET")
    public ServerResponse setAddress(@PathVariable(value = "userId") String userId,
                                     @PathVariable(value = "addressId") String addressId) {

        return addressService.setDefaultAddress(userId, addressId);
    }

    @ResponseBody
    @RequestMapping("/addr/default/{userId}")
    @ApiOperation(value = "获取用户默认收货地址", notes = "获取用户默认收货地址", httpMethod = "GET")
    public ServerResponse getDefaultAddress(@PathVariable(value = "userId") String userId) {

        ServerResponse serverResponse = null;

        try {
            Address address = addressMapper.getDeafultAddressByUserId(userId);
            serverResponse = ServerResponse.createBySuccess("获取成功", address);
            log.info("用户{}获取默认收货地址{}成功", userId, address);
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("获取异常");
            log.info("用户{}获取默认收货地址异常", userId);
        } finally {
            return serverResponse;
        }
    }

    @ResponseBody
    @RequestMapping("/addr/del/{userId}/{addrId}")
    @ApiOperation(value = "删除收货地址信息", notes = "删除收货地址信息", httpMethod = "GET")
    public ServerResponse delAddress(@PathVariable(value = "userId") String userId,
                                     @PathVariable(value = "addrId") String addrId) {

        return addressService.delAddress(userId, addrId);
    }

    @ResponseBody
    @RequestMapping(value = "/addr/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加收货地址", notes = "添加收货地址", httpMethod = "POST")
    public ServerResponse addOrUpdate(@RequestParam(value = "recieve_name") String recieve_name,
                                      @RequestParam(value = "detail_address") String detail_address,
                                      @RequestParam(value = "mobile1") String mobile1,
                                      @RequestParam(value = "mobile2") String mobile2,
                                      @RequestParam(value = "province") String province,
                                      @RequestParam(value = "city") String city,
                                      @RequestParam(value = "region") String region,
                                      @RequestParam(value = "user_id") String user_id) {

        return addressService.add(recieve_name, detail_address, mobile1, mobile2, province, city, region, user_id);
    }

    @ResponseBody
    @RequestMapping(value = "/addr/update", method = RequestMethod.POST)
    @ApiOperation(value = "添加收货地址", notes = "添加收货地址", httpMethod = "POST")
    public ServerResponse addOrUpdate(@RequestParam(value = "addr_id") String addr_id,
                                      @RequestParam(value = "recieve_name") String recieve_name,
                                      @RequestParam(value = "detail_address") String detail_address,
                                      @RequestParam(value = "mobile1") String mobile1,
                                      @RequestParam(value = "mobile2") String mobile2,
                                      @RequestParam(value = "province") String province,
                                      @RequestParam(value = "city") String city,
                                      @RequestParam(value = "region") String region,
                                      @RequestParam(value = "user_id") String user_id) {

        return addressService.update(addr_id, recieve_name, detail_address, mobile1, mobile2, province, city, region, user_id);
    }


}
