package com.boom.controllers.eventhandlers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import static com.boom.tools.Tools.print;

public class AppMouseEventHandler implements EventHandler<MouseEvent> {

    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    DoubleProperty x=new SimpleDoubleProperty();
    DoubleProperty y=new SimpleDoubleProperty();

    StringProperty tempObjectName;

    int drawingStage;

    public AppMouseEventHandler(StringProperty tempObjectName){
        this.tempObjectName=tempObjectName;
    }



    @Override
    public void handle(MouseEvent mouseEvent) {
        x.set(mouseEvent.getX());
        y.set(mouseEvent.getY());

//        print(x.get()+","+y.get());

//        print(mouseEvent.getEventType().getName());

        if(tempObjectName.get().equals("rect")){
            
        }

//        if(mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
//        }



//        switch (mouseEvent.getEventType().getName()){
//            case MouseEvent.MOUSE_CLICKED.getName() -> {
//                int x = 1;
//            }
//        }

    }


}


