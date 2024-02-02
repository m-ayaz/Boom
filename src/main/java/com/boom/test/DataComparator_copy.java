package com.boom.test;

import com.boom.appcharts.AppData;

import java.util.Comparator;

public class DataComparator_copy implements Comparator<AppData> {
    @Override
    public int compare(AppData o1, AppData o2) {
        return 0;
    }

//    AppAxisSortingPolicy appAxisSortingPolicy = AppAxisSortingPolicy.SortByDefault;
//
//    public void setAppAxisSortingPolicy(AppAxisSortingPolicy appAxisSortingPolicy) {
//        this.appAxisSortingPolicy = appAxisSortingPolicy;
//    }
//
//
//    @Override
//    public int compare(AppData o1, AppData o2) {
//        switch (appAxisSortingPolicy) {
//            case SortByX -> {
//                if (o1.isXDataNumerable() && o2.isXDataNumerable()) {
//                    return Double.valueOf(o1.getX()).compareTo(Double.valueOf(o2.getX()));
//                } else {
//                    return o1.getX().compareTo(o2.getX());
//                }
//            }
//            case SortByY -> {
//                if (o1.isXDataNumerable() && o2.isXDataNumerable()) {
//                    return Double.valueOf(o1.getX()).compareTo(Double.valueOf(o2.getX()));
//                } else {
//                    return o1.getX().compareTo(o2.getX());
//                }
//            }
////            case SortByLiteralX -> {return o1.getX().compareTo(o2.getX());}
////            case SortByLiteralY -> {return o1.getY().compareTo(o2.getY());}
////            case SortByNumericX -> {return Double.valueOf(o1.getX()).compareTo(Double.valueOf(o2.getX()));}
////            case SortByNumericY -> {return Double.valueOf(o1.getY()).compareTo(Double.valueOf(o2.getY()));}
////            case SortByLiteralX -> {return o1.getX().compareTo(o2.getX());}
////            case SortByLiteralY -> {return o1.getY().compareTo(o2.getY());}
//            case SortByDefault -> {
//                return 0;
//            }
//            default -> throw new AppException(AppExceptionEnum.UnexpectedError);
//        }
//
//    }

}