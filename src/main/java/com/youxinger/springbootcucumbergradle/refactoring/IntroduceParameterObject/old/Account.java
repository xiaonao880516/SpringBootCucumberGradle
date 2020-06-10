package com.youxinger.springbootcucumbergradle.refactoring.IntroduceParameterObject.old;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mengwei
 * 2020/6/9 15:36
 * @version 1.0
 */
public class Account {

    private List<Entry> entries = new ArrayList<>();

    //用来计算两日期间的帐项总和.
    double getFlowBetween(Date start, Date end) {
        double result = 0;
        for (Entry e : entries) {
            if (e.getDate().equals(start)
                    || e.getDate().equals(end)
                    || (e.getDate().after(start) && e.getDate()
                    .before(end))) {
                result += e.getValue();
            }
        }
        return result;
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
