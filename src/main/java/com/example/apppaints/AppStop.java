package com.example.apppaints;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;

import static com.example.tools.Tools.print;

public class AppStop extends SimpleObjectProperty<Stop> {

    public AppColor appColor;
    public SimpleDoubleProperty offset=new SimpleDoubleProperty();

    public AppStop(double offset1,Color color1){

        appColor=new AppColor(color1);
        offset.set(offset1);
        appColor.getPaintProperty().addListener((a, b, c)-> set(new Stop(offset.get(), (Color) appColor.getPaintProperty().get())));
        offset.addListener((a,b,c)-> set(new Stop(offset.get(), (Color) appColor.getPaintProperty().get())));

    }


}
