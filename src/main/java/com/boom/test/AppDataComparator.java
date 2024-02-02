package com.boom.test;

import java.util.Comparator;

public class AppDataComparator implements Comparator<double[]> {

    @Override
    public int compare(double[] o1, double[] o2) {
        return Double.compare(o1[0], o2[0]);
    }


}