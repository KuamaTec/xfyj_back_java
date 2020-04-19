package com.zgds.xfyj.domain.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ZL
 * @descition 首页通知
 * @date 2020/1/3
 */
@Table(name = "tbl_inform")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Inform {

    @Id
    private String id;//id主键
    private String content;//首页通知内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;//通知时间


}
