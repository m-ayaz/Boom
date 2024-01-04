package com.boom.test;

import com.boom.appcharts.AppData;

import java.util.Comparator;

public class DataComparator implements Comparator<AppData> {

    @Override
    public int compare(AppData o1, AppData o2) {
//        print(uuid(20));
        return Double.compare(Double.parseDouble(o1.getX()), Double.parseDouble(o2.getX()));
    }

}