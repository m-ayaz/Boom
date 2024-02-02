package com.boom.test;

import com.boom.appcharts.AppData;
import com.boom.exceptions.AppException;
import com.boom.structures.enums.AppDataSortingPolicy;
import com.boom.structures.enums.AppExceptionEnum;

import java.util.Comparator;

public class AppDataComparator_copy implements Comparator<AppData> {
    @Override
    public int compare(AppData o1, AppData o2) {
        return 0;
    }

//    public void setSortingPolicy(AppDataSortingPolicy appDataSortingPolicy) {
//        this.appDataSortingPolicy = appDataSortingPolicy;
//    }
//
//    public AppDataSortingPolicy getAppDataSortingPolicy() {
//        return appDataSortingPolicy;
//    }
//
//    AppDataSortingPolicy appDataSortingPolicy = AppDataSortingPolicy.NoSort;
//
////    boolean isNumericalComparison=false;
////    public void setNumericalComparison(boolean isNumericalComparison){
////        this.isNumericalComparison=isNumericalComparison;
////    }
////    public void setAppAxisSortingPolicy(AppAxisSortingPolicy appAxisSortingPolicy) {
////        this.appAxisSortingPolicy = appAxisSortingPolicy;
////    }
//
//
//    @Override
//    public int compare(AppData o1, AppData o2) {
//        switch (appDataSortingPolicy){
//            case LiteralXSort -> {
//                return o1.getX().compareTo(o2.getX());
//            }
//            case LiteralYSort -> {
//                return o1.getY().compareTo(o2.getY());
//            }
//            case NumericalXSort -> {
//                return Double.valueOf(o1.getX()).compareTo(Double.valueOf(o2.getX()));
//            }
//            case NumericalYSort -> {
//                return Double.valueOf(o1.getY()).compareTo(Double.valueOf(o2.getY()));
//            }
//            case NoSort -> {
//                return 0;
//            }
//            default -> throw new AppException(AppExceptionEnum.UnexpectedError);
//        }
////        switch (appAxisSortingPolicy) {
////            if(isNumericalComparison){
////                return Double.valueOf(o1).compareTo(Double.valueOf(o2));
////            }else{
////                return o1.compareTo(o2);
////            }
////            case NumericalSortByX -> {
////                return Double.valueOf(o1).compareTo(Double.valueOf(o2));
////            }
////            case NumericalSortByX -> {
////                return Double.valueOf(o1).compareTo(Double.valueOf(o2));
////            }
////            case SortByY -> {
////                if (o1.isXDataNumerable() && o2.isXDataNumerable()) {
////                    return Double.valueOf(o1.getX()).compareTo(Double.valueOf(o2.getX()));
////                } else {
////                    return o1.getX().compareTo(o2.getX());
////                }
////            }
////            case SortByLiteralX -> {return o1.getX().compareTo(o2.getX());}
////            case SortByLiteralY -> {return o1.getY().compareTo(o2.getY());}
////            case SortByNumericX -> {return Double.valueOf(o1.getX()).compareTo(Double.valueOf(o2.getX()));}
////            case SortByNumericY -> {return Double.valueOf(o1.getY()).compareTo(Double.valueOf(o2.getY()));}
////            case SortByLiteralX -> {return o1.getX().compareTo(o2.getX());}
////            case SortByLiteralY -> {return o1.getY().compareTo(o2.getY());}
////            case SortByDefault -> {
////                return 0;
////            }
////            default -> throw new AppException(AppExceptionEnum.UnexpectedError);
////        }
//
//    }

}