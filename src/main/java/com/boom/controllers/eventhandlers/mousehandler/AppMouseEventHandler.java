package com.boom.controllers.eventhandlers.mousehandler;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.uuid;

public  class AppMouseEventHandler implements EventHandler<MouseEvent> {

    DoubleProperty moveX = new SimpleDoubleProperty();
    DoubleProperty moveY = new SimpleDoubleProperty();
    DoubleProperty dragX = new SimpleDoubleProperty();
    DoubleProperty dragY = new SimpleDoubleProperty();
    DoubleProperty pressX = new SimpleDoubleProperty();
    DoubleProperty pressY = new SimpleDoubleProperty();
    DoubleProperty releaseX = new SimpleDoubleProperty();
    DoubleProperty releaseY = new SimpleDoubleProperty();
    DoubleProperty clickX = new SimpleDoubleProperty();
    DoubleProperty clickY = new SimpleDoubleProperty();
    DoubleProperty x = new SimpleDoubleProperty();
    DoubleProperty y = new SimpleDoubleProperty();

    int n;

    EventType<MouseEvent> eventType;

    public AppMouseEventHandler(EventType<MouseEvent> eventType){
        this.eventType=eventType;
//        this.n=n;
    }

    public AppMouseEventHandler(int n){
//        this.eventType=eventType;
        this.n=n;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
//        print(uuid(n));
        print(mouseEvent);
//        MouseEvent.
    }



}
