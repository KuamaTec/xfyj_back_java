package com.zgds.xfyj.util;

import org.apache.commons.lang3.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Xiang-ping li
 * @descition 平台订单号工具类
 * @date 2020/1/12 0012  14:28
 */
public class PlatformOrderNoUtils {


    public static String generateOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd-HH-mm-ss");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(RandomUtils.nextInt(1, 10));
        }

        return sdf.format(new Date()) + "-" + sb.toString();

    }

//    public static void main(String[] args) {
//        StringBuffer sb= new StringBuffer();
//        for(int i=0;i<10;i++){
//            sb.append(RandomUtils.nextInt(1,10));
//        }
//        System.out.print(sb.toString());
//    }
}
