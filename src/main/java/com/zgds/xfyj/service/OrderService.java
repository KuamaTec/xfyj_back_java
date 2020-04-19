package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.CartMapper;
import com.zgds.xfyj.dao.GoodsMapper;
import com.zgds.xfyj.dao.OrderItemsMapper;
import com.zgds.xfyj.dao.OrderMapper;
import com.zgds.xfyj.domain.pojo.Cart;
import com.zgds.xfyj.domain.pojo.Goods;
import com.zgds.xfyj.domain.pojo.Order;
import com.zgds.xfyj.domain.pojo.OrderItems;
import com.zgds.xfyj.domain.vo.OrderVO;
import com.zgds.xfyj.util.BigDecimalArithUtil;
import com.zgds.xfyj.util.PlatformOrderNoUtils;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.thymeleaf.util.ListUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderMapper mapper;
    @Autowired
    private OrderItemsMapper orderItemsMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    private String Mapper = "OrderMapper";

    /**
     * 购物车结算
     */
    @Transactional
    public ServerResponse addOrder(String order_name,
                                   Double order_price,
                                   String cart_ids,
                                   String user_id) {

        ServerResponse serverResponse = null;

        try {
            //1、保存order
            String orderId = UUIDUtils.generateId();
            Order order = Order.builder()
                    .id(orderId)
                    .order_name(order_name)
                    .order_no(PlatformOrderNoUtils.generateOrderNo())
                    .order_price(order_price)
                    .place_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                    .update_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                    .cart_ids(cart_ids)
                    .order_status(1)//已下单——待付款
                    .user_id(user_id)
                    .build();

            int i = mapper.insert(order);
            //2、保存订单项
            String[] cartIds = cart_ids.split(",");
            for (String cart_id :
                    cartIds) {
                Cart cart = cartMapper.selectByPrimaryKey(cart_id);
                Goods goods = goodsMapper.selectByPrimaryKey(cart.getGoods_id());

                Double total_price = 0.0;
                Double dis_price = 0.0;
                if (goods.getIs_discount().equals("1")) {
                    total_price = BigDecimalArithUtil.mul(cart.getCount(), Double.parseDouble(goods.getDiscount_price()));
                } else {
                    total_price = BigDecimalArithUtil.mul(cart.getCount(), Double.parseDouble(goods.getSale_price()));
                    dis_price = Double.parseDouble(goods.getSale_price());
                }


                OrderItems orderItem = OrderItems.builder()
                        .id(UUIDUtils.generateId())
                        .goods_name(goods.getGoods_name())
                        .standard(goods.getStandard())
                        .sale_price(Double.parseDouble(goods.getSale_price()))
                        .is_discount(Integer.parseInt(goods.getIs_discount()))
                        .dis_price(dis_price)
                        .count(cart.getCount())
                        .goods_id(cart.getGoods_id())
                        .cart_id(cart_id)
                        .order_id(orderId)
                        .user_id(user_id)
                        .total_price(total_price)
                        .main_img(goods.getMain_img())
                        .build();
                orderItemsMapper.insert(orderItem);
                cartMapper.deleteByPrimaryKey(cart_id);
            }

            if (i == 1) {
                serverResponse = ServerResponse.createBySuccess("下单成功", order);
                log.info("用户{}下单成功", user_id);
            } else {
                serverResponse = ServerResponse.createBySuccessMessages("下单失败");
                log.info("用户{}下单失败", user_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("下单异常");
            log.info("用户{}下单异常，错误信息{}", user_id, e.getLocalizedMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return serverResponse;
        }
    }

    /**
     * 根据订单id获取订单信息
     */
    public ServerResponse getOrderInfo(String order_id,
                                       String user_id) {

        ServerResponse serverResponse = null;
        OrderVO orderVO = null;

        try {
            //1、根据订单id获取订单
            Order order = mapper.selectByPrimaryKey(order_id);
            //2、根据orderId获取订单项（详情）
            Example example = new Example(OrderItems.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("order_id", order_id);
            criteria.andEqualTo("user_id", user_id);
            List<OrderItems> orderItems = orderItemsMapper.selectByExample(example);

            orderVO = OrderVO.builder()
                    .order(order)
                    .list(orderItems)
                    .build();
            serverResponse = ServerResponse.createBySuccess("获取订单详情成功", orderVO);
            log.info("用户{}获取订单{}成功", user_id, order_id);
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("获取订单详情异常");
            log.info("用户{}获取订单{}详情异常，错误信息{}", user_id, order_id, e.getLocalizedMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return serverResponse;
        }
    }


    /**
     * 用户查看所有订单
     *
     * @return
     */
    public ServerResponse getAll() {
        List<Order> goodsList = mapper.getAll();
        log.info("用户查看所有订单:" + Mapper + ".getShowGoods()");
        return ServerResponse.createBySuccess("查询成功！", goodsList);
    }

    /**
     * 用户根据订单状态获取订单列表
     *
     * @param userId
     * @param order_status 订单状态
     * @return
     */
    public ServerResponse getOrderByStatus(String userId, Integer order_status, Integer currPage, Integer pageSize) {
        ServerResponse serverResponse = null;
        List<OrderVO> vo = null;

        try {
            int totalPage = 0;
            int totalCount = mapper.allCount(userId);
            if ((totalCount % pageSize) == 0) {
                totalPage = totalCount / pageSize;
            } else {
                totalPage = (totalCount / pageSize) + 1;
            }
            if (currPage > totalPage) {
                return ServerResponse.createByErrorMessage("页码错误");
            }
            int startIndex = (currPage - 1) * pageSize;

            List<Order> goodsList = mapper.getOrderByStatus(order_status, userId, startIndex, pageSize);
            //循环组装OrderVO显示
            if (!ListUtils.isEmpty(goodsList)) {
                vo = new ArrayList<>();
                for (Order order :
                        goodsList) {
                    String[] cartids = order.getCart_ids().split(",");
                    List<OrderItems> orderItemsList = null;
                    if (cartids != null && cartids.length > 0) {
                        orderItemsList = new ArrayList<>();
                        for (String cart_id :
                                cartids) {
                            Example example = new Example(OrderItems.class);
                            Example.Criteria criteria = example.createCriteria();
                            criteria.andEqualTo("cart_id", cart_id);
                            criteria.andEqualTo("order_id", order.getId());
                            OrderItems orderItem = orderItemsMapper.selectByExample(example).get(0);
                            orderItemsList.add(orderItem);
                        }
                        OrderVO orderVO = OrderVO.builder()
                                .list(orderItemsList)
                                .order(order)
                                .build();
                        vo.add(orderVO);
                    }
                }
            }


            serverResponse = ServerResponse.createBySuccess("获取订单列表成功！", vo);
            log.info("用户{} 获取订单列表{}成功:", userId, goodsList);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("用户{} 获取订单列表异常:", userId);
            serverResponse = ServerResponse.createByErrorMessage("获取订单列表失败！");
        } finally {
            return serverResponse;
        }

    }

    /**
     * 根据id删除订单信息
     *
     * @param id
     * @return
     */
    public ServerResponse deleteOrder(String id) {
        Integer i = mapper.deleteOrder(id);
        if (i > 0) {
            log.info("根据id删除订单信息成功:" + Mapper + ".deleteGoods()", id);
            return ServerResponse.createBySuccessMessages("删除成功！");
        } else {
            log.info("根据id删除订单信息失败:" + Mapper + ".deleteGoods()", id);
            return ServerResponse.createByErrorMessage("删除失败！");
        }
    }

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param userId
     * @param orderStatus
     * @return
     */
    public ServerResponse updateOrderStatus(String orderId, String userId, Integer orderStatus) {
        ServerResponse serverResponse = null;
        try {
            Integer i = mapper.updateOrderStatus(orderStatus, userId, orderId);
            if (i > 0) {
                log.info("更新订单{}状态{}成功:", orderId, orderStatus);
                serverResponse = ServerResponse.createBySuccessMessages("更新订单成功！");
            } else {
                log.info("更新订单{}状态{}失败！", orderId, orderStatus);
                serverResponse = ServerResponse.createByErrorMessage("更新订单状态失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("更新订单{}状态{}异常！", orderId, orderStatus);
            serverResponse = ServerResponse.createByErrorMessage("更新订单异常！");
        } finally {
            return serverResponse;
        }
    }

    /**
     * 修改订单信息信息
     *
     * @param order
     * @return
     */
    public ServerResponse updateOrder(Order order) {
        Integer i = mapper.updateOrder(order);
        if (i > 0) {
            log.info("用户修改订单信息信息成功:" + Mapper + ".updateGoods()", order);
            return ServerResponse.createBySuccessMessages("修改成功！");
        } else {
            log.info("用户修改订单信息信息失败:" + Mapper + ".updateGoods()", order);
            return ServerResponse.createByErrorMessage("修改失败！");
        }
    }

    /**
     * 删除订单
     *
     * @param order
     * @return
     */
    public ServerResponse del(String userId, String orderId) {
        Integer i = mapper.deleteOrderByIdUserId(orderId, userId);
        if (i > 0) {
            log.info("删除订单{}成功", orderId);
            return ServerResponse.createBySuccessMessages("删除成功！");
        } else {
            log.info("删除订单{}失败:", orderId);
            return ServerResponse.createByErrorMessage("删除失败！");
        }
    }
}