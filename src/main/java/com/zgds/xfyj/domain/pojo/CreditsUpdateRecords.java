package com.zgds.xfyj.domain.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author ZL
 * @descition 用户积分查询
 * @date 2020/1/12
 */
@Table(name = "tbl_credits_update_records")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditsUpdateRecords {
    private String id;
    private Integer old_credits;//历史总积分
    private Integer recrease_credits;//增加积分
    private Integer current_credits;//当前积分
    private Date update_time;//跟新时间
    private String order_id;//订单id
    private String user_id;//用户id
    private Integer update_type;//标识（0-消费积分，1-增加积分）
}