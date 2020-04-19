package com.zgds.xfyj.domain.vo;

import com.zgds.xfyj.domain.pojo.Order;
import com.zgds.xfyj.domain.pojo.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xiang-ping li
 * @descition 订单表现类
 * @date 2020/1/12 0012  14:46
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
    private List<OrderItems> list;
    private Order order;
}
