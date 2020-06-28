package com.youxinger.springbootcucumbergradle.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author mengwei
 * 2020/6/23 16:32
 * @version 1.0
 */
public class TimeUtil {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取前月的第一天
     *
     * @return
     */
    public static String getMonthStartTime() {
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(cale.getTime());
    }

    /**
     * 获取前月的最后一天
     *
     * @return
     */
    public static String getMonthEndTime() {

        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return format.format(cale.getTime());
    }
}
