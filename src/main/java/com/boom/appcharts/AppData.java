package com.boom.appcharts;

public class AppData {

    public Double x = null;
    public Double y = null;

    public AppData(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    private AppData() {
    }


    public String toString() {
        return "(%s,%s)".formatted(x, y);
    }

    public AppData copy() {
        return new AppData(x, y);
    }
}