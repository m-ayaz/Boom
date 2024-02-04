package com.boom.structures.enums;

public enum LegendAnchor {

    SOUTHWEST(0,0),
    NORTHWEST(0,1),
    SOUTHEAST(1,0),
    NORTHEAST(1,1);


    private final int x;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private final int y;
//    private final int id;


    LegendAnchor(int x,int y) {
        this.x=x;
        this.y=y;
    }
}
