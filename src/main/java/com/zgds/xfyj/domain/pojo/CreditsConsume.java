package com.zgds.xfyj.domain.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @author ZL
 * @descition 积分消费规则
 * @date 2020/1/9
 */
@Table(name = "tbl_credits_consume_rule")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditsConsume {
    private String id;//积分消费id
    private Integer min_order_price;//最低使用积分总额
    private Integer consume_percent;//积分使用规则
}
