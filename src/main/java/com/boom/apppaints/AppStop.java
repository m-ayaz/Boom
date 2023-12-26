package com.boom.apppaints;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;

public final class AppStop extends SimpleObjectProperty<Stop> {

    public AppColor appColor;
    public SimpleDoubleProperty offset=new SimpleDoubleProperty();

    public AppStop(Stop stop){

        set(stop);
        appColor=new AppColor(stop.getColor());
        offset.set(stop.getOffset());
        appColor.addListener((a, b, c)-> update());
        offset.addListener((a,b,c)-> update());

    }

    void update(){
        set(new Stop(offset.get(), (Color) appColor.get()));
    }


    public String toSVG(int tabIndent){
        return "\n"+"\t".repeat(tabIndent)+"<stop offset=\"%f\" stop-color=\"%s\" />".formatted(get().getOffset(),get().getColor().toString().replace("0x", "#"));
    }

}
