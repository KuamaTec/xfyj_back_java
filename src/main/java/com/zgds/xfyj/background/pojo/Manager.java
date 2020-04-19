package com.zgds.xfyj.background.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Xiang-ping li
 * @descition 商家用户实体
 * @date 2020/1/3 0003  18:12
 */
@Table(name = "tbl_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Manager {
    @Id
    private String id;
    private String username;
    private String password;
}
