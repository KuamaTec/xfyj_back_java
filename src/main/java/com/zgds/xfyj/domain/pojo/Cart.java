package com.zgds.xfyj.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Xiang-ping li
 * @descition 购物车
 * @date 2020/1/9 0003  9:33
 */
@Table(name = "tbl_shop_cart")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {

    @Id
    private String id;
    private Integer count;//操作数量
    private String insert_time;//添加到购物车的时间
    private String update_time;//更新时间
    private String goods_id;
    private String user_id;
}
