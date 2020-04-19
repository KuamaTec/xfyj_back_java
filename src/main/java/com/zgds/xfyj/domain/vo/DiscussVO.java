package com.zgds.xfyj.domain.vo;

import com.zgds.xfyj.domain.pojo.Discuss;
import com.zgds.xfyj.domain.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xiang-ping li
 * @descition
 * @date 2020/1/5 0005  21:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscussVO {
    private Discuss discuss;
    private User user;
}
