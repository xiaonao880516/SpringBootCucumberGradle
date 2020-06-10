package com.youxinger.springbootcucumbergradle.refactoring.preserveWholeObject;

/**
 * @author mengwei
 * 2020/6/9 15:54
 * @version 1.0
 * Preserve Whole Object(保持对象完整)
 */
public class PreserveWholeObject {

    public static void main(String[] args) {
        TempRange daysTempRange = new TempRange();
        int low = daysTempRange.getLow();
        int high = daysTempRange.getHigh();


        boolean withinPlan = PreserveWholeObject.withinRange(low, high);

        //避免将来调用函数需要新的数据项,并提高代码可读性.
        boolean withinPlanNew = PreserveWholeObject.withinRange(daysTempRange);
    }



    public static boolean withinRange(int low, int high){
        return false;
    }

    public static boolean withinRange(TempRange d){
        return false;
    }
}

class TempRange {
    int low;
    int high;

    public int getLow() {
        return low;
    }


    public int getHigh() {
        return high;
    }
}
