package com.zgds.xfyj.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Xiang-ping li
 * @descition 收藏
 * @date 2020/1/5 0003  15：23
 */
@Table(name = "tbl_collection")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Collection {

    @Id
    private String id;
    private String goods_name;
    private String collect_time;
    private String goods_id;
    private String user_id;
}
