package com.example.apppaints;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;

import static com.example.tools.Tools.print;

public class AppStop extends SimpleObjectProperty<Stop> {

    public SimpleObjectProperty<Color> color=new SimpleObjectProperty<>();
    public SimpleDoubleProperty offset=new SimpleDoubleProperty();

    public AppStop(double offset1,Color color1){

        color.set(color1);
        offset.set(offset1);
        color.addListener((a,b,c)-> set(new Stop(offset.get(), color.get())));
        offset.addListener((a,b,c)-> set(new Stop(offset.get(),color.get())));

    }


}
