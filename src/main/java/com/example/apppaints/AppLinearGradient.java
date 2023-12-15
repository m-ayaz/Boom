package com.example.apppaints;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.example.tools.Tools.print;

public class AppLinearGradient extends AppPaint {


    ObservableList<AppStop> appStops = FXCollections.observableList(new ArrayList<>());
    public DoubleProperty startX = new SimpleDoubleProperty(0);
    public DoubleProperty startY = new SimpleDoubleProperty(0);
    public DoubleProperty endX = new SimpleDoubleProperty(0);
    public DoubleProperty endY = new SimpleDoubleProperty(0);
    public BooleanProperty isProportional = new SimpleBooleanProperty(true);

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

    public AppLinearGradient(LinearGradient linearGradient) {
        super(linearGradient);
        linearGradient.getStops().forEach(stop -> addAppStop(new AppStop(stop.getOffset(), stop.getColor())));
        startX.set(linearGradient.getStartX());
        startY.set(linearGradient.getStartY());
        endX.set(linearGradient.getEndX());
        endY.set(linearGradient.getEndY());
        isProportional.set(linearGradient.isProportional());
        appStops.addListener((ListChangeListener<AppStop>) change -> update());
        startX.addListener((a,b,c) -> update());
        startY.addListener((a,b,c) -> update());
        endX.addListener((a,b,c) -> update());
        endY.addListener((a,b,c) -> update());
        isProportional.addListener((a,b,c) -> update());

    }

    void update() {
//        print(appStops);
//        print(uuid(50));
//        appStops.forEach(appStop -> print(appStop.color.get()+" , "+appStop.offset.get()));
        paintProperty.set(new LinearGradient(startX.get(), startY.get(), endX.get(), endY.get(), isProportional.get(), CycleMethod.NO_CYCLE, appStops.stream().map(appStop -> new Stop(appStop.offset.get(),appStop.color.get())).collect(Collectors.toList())));
//        print(get());
    }

}








