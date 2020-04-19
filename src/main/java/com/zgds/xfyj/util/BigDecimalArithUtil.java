package com.zgds.xfyj.util;

import java.math.BigDecimal;

public class BigDecimalArithUtil {

    private static final int DIV_SCALE = 10;//除法精度（除不尽时保留10为小数）

    /**
     * 小数精确加法
     */
    public static double add(double d1, double d2) {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        return bd1.add(bd2).doubleValue();
    }

    /**
     * 小数精确减法
     */
    public static double sub(double d1, double d2) {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        return bd1.subtract(bd2).doubleValue();
    }

    /**
     * 小数精确乘法
     */
    public static double mul(double d1, double d2) {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        return bd1.multiply(bd2).doubleValue();
    }

    /**
     * 小数精确除法
     */
    public static double div(double d1, double d2) {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        /*
         * 当除不尽时，以四舍五入的方式（关于除不尽后的值的处理方式有很多种）保留小数点后10位小数
         */
        return bd1.divide(bd2, DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        //测试加法
        System.out.println("0.05+0.01=" + BigDecimalArithUtil.add(0.05, 0.01));
        System.out.println("0.05+0.01=" + (0.05 + 0.01));
        //测试减法
        System.out.println("1.0-0.42=" + BigDecimalArithUtil.sub(1.0, 0.42));
        System.out.println("1.0-0.42=" + (1.0 - 0.42));
        //测试乘法
        System.out.println("4.015*100=" + BigDecimalArithUtil.mul(4.015, 100));
        System.out.println("4.015*100=" + (4.015 * 100));
        //测试除法
        System.out.println("123.3/100=" + BigDecimalArithUtil.div(123.3, 100));
        System.out.println("123.3/100=" + (123.3 / 100));
    }

}