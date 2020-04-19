package com.zgds.xfyj.controller;

import com.zgds.xfyj.dao.GoodsDetailImgsMapper;
import com.zgds.xfyj.dao.GoodsDetailSwiperMapper;
import com.zgds.xfyj.domain.pojo.Goods;
import com.zgds.xfyj.domain.pojo.GoodsDetailImgs;
import com.zgds.xfyj.domain.pojo.GoodsDetailSwiper;
import com.zgds.xfyj.domain.vo.GoodsDetailVO;
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

import java.util.List;

/**
 * @author ZL
 * @descition 查询商品（酒）系列以及系列下的所有商品
 * @date 2020/1/3
 */
@Api(description = "商品API")
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService service;
    @Autowired
    private GoodsDetailSwiperMapper detailSwiperMapper;
    @Autowired
    private GoodsDetailImgsMapper detailImgsMapper;

    /**
     * 根据分类id获取系列
     *
     * @param brand_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getShowGoods")
    @ApiOperation(value = "根据分类id获取系列（首页）", notes = "根据分类id获取系列（首页）", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brand_id", value = "brand_id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse getShowGoods(@RequestParam("brand_id") String brand_id) {
        ServerResponse serverResponse = service.getShowGoods(brand_id);
        return serverResponse;
    }

    /**
     * 根据系列id获取商品列表
     *
     * @param brand_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getGoods")
    @ApiOperation(value = "根据系列id获取商品列表（分类）", notes = "根据系列id获取商品列表（分类）", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brand_id", value = "brand_id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse getGoods(@Param("brand_id") String brand_id) {
        ServerResponse serverResponse = service.getGoods(brand_id);
        return serverResponse;
    }

    /**
     * 根据商品id查询商品信息
     *
     * @param id 商品id
     * @return
     */
    @ResponseBody
    @RequestMapping("/detail")
    @ApiOperation(value = "根据商品id获取商品信息", notes = "根据商品id获取商品信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse getGoodsId(@RequestParam("id") String id) {
        ServerResponse serverResponse = null;
        Goods goods = (Goods) service.getGoodsId(id).getData();
        List<GoodsDetailImgs> list1 = detailImgsMapper.getAll(id);
        List<GoodsDetailSwiper> list2 = detailSwiperMapper.getAll(id);

        GoodsDetailVO detailVO = GoodsDetailVO.builder()
                .goods(goods)
                .goodsDetailImgs(list1)
                .goodsDetailSwiper(list2)
                .build();

        serverResponse = ServerResponse.createBySuccess("获取商品详情成功", detailVO);

        return serverResponse;
    }

    /**
     * 推荐商品信息
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRecommend")
    @ApiOperation(value = "推荐商品信息", notes = "推荐商品信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classify_id", value = "产品分类id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse getRecommend(@RequestParam("classify_id") String classify_id,
                                       @RequestParam(value = "currPage", required = false, defaultValue = "1") Integer currPage,
                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ServerResponse serverResponse = service.getRecommend(classify_id, currPage, pageSize);
        return serverResponse;
    }

    /**
     * 优惠商品信息
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getIsDiscount")
    @ApiOperation(value = "优惠商品信息", notes = "优惠商品信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classify_id", value = "产品分类id", paramType = "query", required = true, dataType = "String")
    })
    public ServerResponse getIsDiscount(@RequestParam("classify_id") String classify_id,
                                        @RequestParam(value = "currPage", required = false, defaultValue = "1") Integer currPage,
                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ServerResponse serverResponse = service.getIsDiscount(classify_id, currPage, pageSize);
        return serverResponse;
    }

}
