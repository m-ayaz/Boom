package com.boom.appcharts;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public final class Series {
    ObservableList<AppData> appDataList = FXCollections.observableList(new ArrayList<>());

    SimpleIntegerProperty numberOfUnNumerableXData = new SimpleIntegerProperty(0);
    SimpleIntegerProperty numberOfUnNumerableYData = new SimpleIntegerProperty(0);

    SimpleBooleanProperty isXNumerable = new SimpleBooleanProperty();
    SimpleBooleanProperty isYNumerable = new SimpleBooleanProperty();

    SimpleBooleanProperty isXNumeric = new SimpleBooleanProperty();
    SimpleBooleanProperty isYNumeric = new SimpleBooleanProperty();

//    List<Serializable> xData=new ArrayList<>();
//    List<Serializable> yData=new ArrayList<>();

    public Series() {
        isXNumerable.bind(numberOfUnNumerableXData.isEqualTo(0));
        isYNumerable.bind(numberOfUnNumerableYData.isEqualTo(0));

        appDataList.addListener((ListChangeListener<AppData>) change -> {

//            change.getList().forEach(data -> xData.add(Double.parseDouble(data.getX())));
//            change.getList().forEach(data -> yData.add(Double.parseDouble(data.getY())));


        });
    }

    public void addData(AppData appData) {
        addData(appDataList.size() - 1, appData);
    }

    public void addData(int i, AppData appData) {
        numberOfUnNumerableXData.set(numberOfUnNumerableXData.get() + (appData.isXDataNumerable() ? 0 : 1));
        numberOfUnNumerableYData.set(numberOfUnNumerableYData.get() + (appData.isYDataNumerable() ? 0 : 1));
        appDataList.add(i, appData);
//        dataList.sort(new DataComparator());
//        dataList.sort(data1->(Double.parseDouble(data1.getX())));
    }

    public void removeData(int i) {
        removeData(appDataList.get(i));
    }

    public void removeData(AppData appData) {
        numberOfUnNumerableXData.set(numberOfUnNumerableXData.get() - (appData.isXDataNumerable() ? 0 : 1));
        numberOfUnNumerableYData.set(numberOfUnNumerableYData.get() - (appData.isYDataNumerable() ? 0 : 1));
        appDataList.remove(appData);

    }



//    public void setXNumeric(boolean isXNumeric) {
//        if (isXNumerable.get() && isXNumeric) {
//            xData=dataList.stream().map(data -> Double.parseDouble(data.getX())).collect(Collectors.toList());
//            yData=dataList.stream().map(data -> Double.parseDouble(data.getY())).collect(Collectors.toList());
//        }
//    }
//
//    public void setYNumeric(boolean isYNumeric) {
//
//    }
}


//class DataComparator implements Comparator<Data>{
//
//    @Override
//    public int compare(Data o1, Data o2) {
//        return 0;
////        return o1.getX()-o2.getX();
//    }
//}