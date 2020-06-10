package com.youxinger.springbootcucumbergradle.refactoring.IntroduceParameterObject.better;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mengwei
 * 2020/6/9 15:36
 * @version 1.0
 * Introduce Parameter Object(引入参数对象)
 */
public class AccountBetter {

    private List<Entry> entries = new ArrayList<>();
    private DateRange dateRange = new DateRange();

    //用来计算两日期间的帐项总和.
    double getFlowBetween(Date start, Date end) {
        double result = 0;
        for (Entry e : entries) {
            if (dateRange.includes(e.getDate())) {
                result += e.getValue();
            }
        }
        return result;
    }
}

class DateRange {
    private Date start;
    private Date end;

    //判断一个时间点是否在该区间段之间
    boolean includes(Date arg) {
        return (arg.equals(this.start) || arg.equals(this.end) || (arg
                .after(this.start) && arg.before(this.end)));
    }
}

class Entry {
    private Date date;
    private double value;

    public Date getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }
}
