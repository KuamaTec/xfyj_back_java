package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.OrderItemsMapper;
import com.zgds.xfyj.util.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderDetailService {
    private final Logger logger = LoggerFactory.getLogger(OrderDetailService.class);

    @Autowired
    private OrderItemsMapper mapper;

    private String Mapper = "OrderItemsMapper";

    /**
     * 用户查看所有订单
     *
     * @return
     */
    public ServerResponse getAll() {
        log.info("用户查看所有订单详情:" + Mapper + ".getAll()");
        return ServerResponse.createBySuccess("查询成功！", mapper.getAll());
    }

    /**
     * 用户根据订单状态查看订单详情
     *
     * @return
     */
    public ServerResponse getOrderId(String order_id) {
        log.info("用户查看所有订单:" + Mapper + ".getShowGoods()", order_id);
        return ServerResponse.createBySuccess("查询成功！", mapper.getOrderId(order_id));
    }

}