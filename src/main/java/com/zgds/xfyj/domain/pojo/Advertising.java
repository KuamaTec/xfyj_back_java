package com.zgds.xfyj.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ZL
 * @descition 首页轮播图
 * @date 2020/1/3
 */
@Table(name = "tbl_advertising")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Advertising {

    @Id
    private String id;//id主键
    private String img_url;//首页轮播图片路径
}
