package com.boom.test;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class RGBAFieldIndicator extends Polygon {

    public RGBAFieldIndicator(){

    }
    public RGBAFieldIndicator(double width,double height,double trigHeight){
        super(-width/2,-height/2,width/2,-height/2,0.,-height/2+trigHeight,
                0.,height/2-trigHeight,
                -width/2,height/2,width/2,height/2,0.,height/2-trigHeight,0.,-height/2+trigHeight);
        setFill(Color.RED);
    }
}
