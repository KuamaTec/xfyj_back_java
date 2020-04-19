package com.zgds.xfyj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lxp
 * @descition 时间工具类
 * @date 2019/10/28 0028  11:37
 */
public class DateTimeUtils {

    /**
     * 获取当前date
     *
     * @return
     */
    public static String nowDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:sss");
        String s = sdf.format(new Date());
        return s;
    }

}
