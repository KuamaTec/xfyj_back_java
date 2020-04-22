package com.zgds.xfyj.controller;

import com.zgds.xfyj.dao.CartMapper;
import com.zgds.xfyj.dao.GoodsMapper;
import com.zgds.xfyj.dao.UserMapper;
import com.zgds.xfyj.domain.pojo.Cart;
import com.zgds.xfyj.domain.pojo.CartVO;
import com.zgds.xfyj.domain.pojo.Goods;
import com.zgds.xfyj.util.BigDecimalArithUtil;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.ListUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 李向平
 * @descition 购物车controller
 * @date 2020/1/9
 */
@Api(description = "商品API")
@Controller
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;


    /**
     * 添加到购物车
     *
     * @param goods_name
     * @param standard    规格
     * @param sale_price
     * @param is_discount 是否打折
     * @param dis_price   折后价
     * @param count       数量
     * @param goods_id
     * @param user_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    @ApiOperation(value = "将商品添加到购物车", notes = "将商品添加到购物车", httpMethod = "POST")
    public ServerResponse add(@RequestParam("count") Integer count,
                              @RequestParam("goods_id") String goods_id,
                              @RequestParam("user_id") String user_id) {
        ServerResponse serverResponse = null;

        try {
            int i = 0;
            //1、根据用户id、商品id 查询该商品是否已加入购入车
            List<Cart> cartList = cartMapper.getByUserIdGoodsId(user_id, goods_id);
            //如果购物车中已有该商品，则修改商品数量即可
            if (!ListUtils.isEmpty(cartList)) {
                Cart cart = cartList.get(0);
                cart.setCount(cart.getCount() + count);
                cart.setUpdate_time(new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()));

                i = cartMapper.updateByPrimaryKey(cart);
                if (i == 1) {
                    serverResponse = ServerResponse.createBySuccess("已加入购物车", cart);
                    log.info("用户{}添加商品{}至购物车成功", user_id, goods_id);
                } else {
                    serverResponse = ServerResponse.createBySuccess("加入购物车失败", cart);
                    log.info("用户{}添加商品{}至购物车失败", user_id, goods_id);
                }
            }
            //如果无，则新增商品至购物车
            else {
                String id = UUIDUtils.generateId();

                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                Cart cart = Cart.builder()
                        .id(id)
                        .count(count)
                        .insert_time(time)
                        .update_time(time)
                        .goods_id(goods_id)
                        .user_id(user_id)
                        .build();

                i = cartMapper.insert(cart);
                if (i == 1) {
                    serverResponse = ServerResponse.createBySuccess("已加入购物车", cart);
                    log.info("用户{}添加商品{}至购物车成功", user_id, goods_id);
                } else {
                    serverResponse = ServerResponse.createBySuccess("加入购物车失败", cart);
                    log.info("用户{}添加商品{}至购物车失败", user_id, goods_id);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("加入购物车异常！");
            log.info("用户{}添加商品{}至购物车异常", user_id, goods_id);
        } finally {
            return serverResponse;
        }
    }

    /**
     * 订单支付展示
     * @param user_id
     * @param cartItemsIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/order/goods/list")
    @ApiOperation(value = "根据商品ids获取订单下的商品列表", notes = "根据商品ids获取订单下的商品列表", httpMethod = "POST")
    public ServerResponse getListByGoodsIds(@RequestParam("user_id") String user_id,
                                            @RequestParam(value = "goodsIds") String cartItemsIds) {
        ServerResponse serverResponse = null;
        List<Cart> list = null;
        List<CartVO> cartVOList = null;

        try {
            String[] cartIdsArr = cartItemsIds.split(",");
            List<String> idsList = new ArrayList();
            for (String id:
                    cartIdsArr) {
                idsList.add(id);
            }
            //从tbl_shop+_cart中获取
            list = cartMapper.getListByUserIdCartItemIds(user_id,idsList);
            //遍历购物车item 组装cartVO
            if(!ListUtils.isEmpty(list)){
                cartVOList = new ArrayList<>();
                for (Cart c:list) {
                    //获取商品
                    String goods_id = c.getGoods_id();
                    Goods goods = goodsMapper.selectByPrimaryKey(goods_id);
                    //组装CartVo
                    Double total_price = 0.0;
                    if(Integer.valueOf(goods.getIs_discount())==0){
                        total_price = BigDecimalArithUtil.mul(Double.parseDouble(goods.getSale_price()), c.getCount());
                    }else if(Integer.valueOf(goods.getIs_discount())==1){
                        total_price = BigDecimalArithUtil.mul(Double.parseDouble(goods.getDiscount_price()), c.getCount());
                    }

                    CartVO cartVO = new CartVO().builder()
                            .id(c.getId())
                            .goods_id(goods.getId())
                            .goods_name(goods.getGoods_name())
                            .user_id(user_id)
                            .main_img(goods.getMain_img())
                            .standard(goods.getStandard())
                            .capacity(goods.getCapacity())
                            .sale_price(Double.parseDouble(goods.getSale_price()))
                            .is_discount(Integer.parseInt(goods.getIs_discount()))
//                            .dis_price(Double.parseDouble(goods.getDiscount_price()))
                            .count(c.getCount())
                            .total_price(total_price)
                            .build();
                    //判断是否打折设置打折价格
                    if(goods.getIs_discount().equals(0)){
                        cartVO.setDis_price(Double.parseDouble(goods.getSale_price()));
                    }else if(goods.getIs_discount().equals(1)){
                        cartVO.setDis_price(Double.parseDouble(goods.getDiscount_price()));
                    }

                    cartVOList.add(cartVO);
                }
            }
            serverResponse = ServerResponse.createBySuccess("根据商品ids获取订单下的商品列表成功", cartVOList);
            log.info("用户{}根据商品ids获取订单下的商品列表成功",user_id);
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("根据商品ids获取订单下的商品列表异常");
            log.info("根据商品ids获取订单下的商品列表异常，错误信息{}", user_id, e.getLocalizedMessage());
        } finally {
            return serverResponse;
        }
    }

    @ResponseBody
    @RequestMapping("/list")
    @ApiOperation(value = "分页获取用户购物车中的商品列表", notes = "分页获取用户购物车中的商品列表", httpMethod = "POST")
    public ServerResponse getRecommend(@RequestParam("usr_id") String user_id,
                                       @RequestParam(value = "currPage", required = true, defaultValue = "1") Integer currPage,
                                       @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize) {
        ServerResponse serverResponse = null;
        List<CartVO> list = null;

        try {
            int totalPage = 0;
            int totalCount = cartMapper.allNumber(user_id);
            if ((totalCount % pageSize) == 0) {
                totalPage = totalCount / pageSize;
            } else {
                totalPage = (totalCount / pageSize) + 1;
            }
            if (currPage > totalPage) {
                serverResponse = ServerResponse.createBySuccessMessages("页码错误");
                return serverResponse;
            }
            int startIndex = (currPage - 1) * pageSize;
            //1、分页获取用户购物车中的所有商品
            List<Cart> carts = cartMapper.selectByPage(user_id, startIndex, pageSize);
            //2、遍历carts，组装cartVO
            if (!ListUtils.isEmpty(carts)) {
                list = new ArrayList<>();
                for (Cart c :
                        carts) {
                    String goods_id = c.getGoods_id();
                    Goods goods = goodsMapper.selectByPrimaryKey(goods_id);
                    /**组装*/
                    //小计
                    double total_price = 0.0;
                    double dis_price = 0.0;
                    int count = c.getCount();
                    //判断是否打折
                    if (goods.getIs_discount().equals("1")) {
                        total_price = count * Double.parseDouble(goods.getDiscount_price());
                        dis_price = Double.parseDouble(goods.getDiscount_price());
                    } else {
                        total_price = count * Double.parseDouble(goods.getSale_price());
                        dis_price = 0.0;
                    }
                    CartVO cartVO = CartVO.builder()
                            .id(c.getId())//必须有！！！
                            .count(count)
                            .goods_name(goods.getGoods_name())
                            .main_img(goods.getMain_img())
                            .standard(goods.getStandard())
                            .capacity(goods.getCapacity())
                            .sale_price(Double.parseDouble(goods.getSale_price()))
                            .is_discount(Integer.parseInt(goods.getIs_discount()))
                            .dis_price(dis_price)
                            .count(count)
                            .total_price(total_price)
                            .insert_time(c.getInsert_time())
                            .update_time(c.getUpdate_time())
                            .goods_id(goods_id)
                            .user_id(user_id)
                            .build();
                    list.add(cartVO);
                }
            }

            serverResponse = ServerResponse.createBySuccess("分页获取购物车中商品成功", list);
            log.info("用户{}分页获取购物车中的商品成功");
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("分页获取购物车中商品异常");
            log.info("用户{}分页获取购物车中的商品异常，错误信息{}", user_id, e.getLocalizedMessage());
        } finally {
            return serverResponse;
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    @ApiOperation(value = "用户更新购物车中的商品的数量", notes = "用户更新购物车中的商品的数量", httpMethod = "POST")
    public ServerResponse getRecommend(@RequestParam("cart_id") String cart_id,
                                       @RequestParam(value = "user_id") String user_id,
                                       @RequestParam("count") Integer count) {

        ServerResponse serverResponse = null;

        try {
            //更新数量
            cartMapper.updateCountByIdUserId(cart_id, user_id, count);
            serverResponse = ServerResponse.createBySuccess("修改购物车中商品购买数量成功");
            log.info("用户{}修改购物车中商品{}购买数量成功", user_id, cart_id.toString());
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("修改购物车中商品购买数量异常");
            log.info("用户{}修改购物车中商品{}购买数量为{}异常，错误信息：{}", user_id, cart_id, count, e.getLocalizedMessage());
        } finally {
            return serverResponse;
        }
    }

    @ResponseBody
    @RequestMapping("/del")
    @ApiOperation(value = "用户删除购物车中的商品", notes = "用户删除购物车中的商品", httpMethod = "POST")
    public ServerResponse delByCartIdUserId(@RequestParam("cart_ids") String cart_ids,
                                            @RequestParam(value = "user_id") String user_id) {

        ServerResponse serverResponse = null;

        String[] ids = cart_ids.split(",");
        try {
            for (String cart_id :
                    ids) {
                cartMapper.del(user_id, cart_id);
                log.info("用户{}删除购物车中商品{}成功", user_id, cart_id);
            }
            serverResponse = ServerResponse.createBySuccess("用户删除购物车中的商品成功");
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("用户删除购物车中商品异常");
            log.info("用户{}删除购物车中商品{}异常，错误信息：{}", user_id, ids, e.getLocalizedMessage());
        } finally {
            return serverResponse;
        }
    }


}
