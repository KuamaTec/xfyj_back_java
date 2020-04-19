package com.zgds.xfyj.domain.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @author ZL
 * @descition 增加积分规则
 * @date 2020/1/9
 */
@Table(name = "tbl_credits_increase_rule")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditsRule {
    private String id;
    private Integer present_percent;//获取积分百分比（区间范围内）
    private Integer min_order_price;//区间下限
    private Integer max_order_price;//区间上限
}
