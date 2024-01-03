package com.boom.appcharts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public final class Series {
    ObservableList<Data> dataList = FXCollections.observableList(new ArrayList<>());

    public void addData(Data data) {
        dataList.add(data);
    }

    public void addData(int i, Data data) {
        dataList.add(i, data);
    }

    public void removeData(int i) {
        dataList.remove(i);
    }

    public void removeData(Data data) {
        dataList.remove(data);
    }
}
