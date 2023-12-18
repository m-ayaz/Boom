package com.example.structures.abstracts;

import com.example.apppaints.AppStop;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public abstract class AppGradient extends AppPaint {

    protected ObservableList<AppStop> appStops = FXCollections.observableList(new ArrayList<>());
    public BooleanProperty isProportional = new SimpleBooleanProperty(true);

    protected AppGradient(Paint paint) {
        super(paint);

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

     protected abstract void update();


}
