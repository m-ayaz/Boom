package com.boom.appcharts;

public final class AppData {

    String x = null;
    String y = null;
    boolean isXDataNumerable = false;
    boolean isYDataNumerable = false;

    public AppData(String x, String y) {
        setX(x);
        setY(y);
    }

    public AppData() {
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
        try {
            Double.parseDouble(x);
            isXDataNumerable = true;
        } catch (Exception e) {
            isXDataNumerable = false;
        }
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
        try {
            Double.parseDouble(y);
            isYDataNumerable = true;
        } catch (Exception e) {
            isYDataNumerable = false;
        }
    }

    public boolean isXDataNumerable() {
        return isXDataNumerable;
    }

    public boolean isYDataNumerable() {
        return isYDataNumerable;
    }

    public String toString() {
        return "(%s,%s)".formatted(x, y);
    }

}