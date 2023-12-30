package com.boom.structures.abstracts;

import com.boom.apppaints.AppStop;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AppGradient extends AppPaint {

    protected final HashMap<Boolean,String> svgIsProportional=new HashMap<>();
    protected final HashMap<String,String> svgCycleMethod=new HashMap<>();

    protected ObservableList<AppStop> appStops = FXCollections.observableList(new ArrayList<>());
    public BooleanProperty isProportional = new SimpleBooleanProperty(true);

    public SimpleObjectProperty<CycleMethod> cycleMethod=new SimpleObjectProperty<>();

    protected AppGradient(Paint paint) {
        super(paint);
        svgIsProportional.put(true,"objectBoundingBox");
        svgIsProportional.put(false,"userSpaceOnUse");
        svgCycleMethod.put(CycleMethod.NO_CYCLE.name(), "pad");
        svgCycleMethod.put(CycleMethod.REPEAT.name(), "repeat");
        svgCycleMethod.put(CycleMethod.REFLECT.name(), "reflect");
    }

    public void addAppStop(int index,AppStop appStop){
        appStops.add(index,appStop);
        appStop.addListener((a,b,c)->update());
    }

    public void addAppStop(AppStop appStop){
        appStops.add(appStop);
        appStop.addListener((a,b,c)->update());
    }

    public int getStopsSize(){
        return appStops.size();
    }

    public AppStop getAppStop(int index){
        return appStops.get(index);
    }

    public void removeAppStop(int index){
        appStops.remove(index);
    }

    public void removeAppStop(AppStop appStop){
        appStops.remove(appStop);
    }



}
