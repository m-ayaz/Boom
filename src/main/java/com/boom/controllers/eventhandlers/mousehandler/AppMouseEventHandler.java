package com.boom.controllers.eventhandlers.mousehandler;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;



public abstract class AppMouseEventHandler implements EventHandler<MouseEvent> {

     double moveX =-1, moveY =-1, dragX =-1, dragY =-1, pressX =-1, pressY =-1, releaseX =-1, releaseY =-1, clickX =-1, clickY =-1, x =-1, y ;

    @Override
    public void handle(MouseEvent mouseEvent) {
        x=mouseEvent.getX();
        y=mouseEvent.getY();
        if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
            moveX=mouseEvent.getX();
            moveY=mouseEvent.getY();
        } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            pressX=mouseEvent.getX();
            pressY=mouseEvent.getY();
        } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
            dragX=mouseEvent.getX();
            dragY=mouseEvent.getY();
        } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            clickX=mouseEvent.getX();
            clickY=mouseEvent.getY();
        } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            releaseX=mouseEvent.getX();
            releaseY=mouseEvent.getY();
        }

        handleLast(mouseEvent);
    }


    protected abstract void handleLast(MouseEvent mouseEvent);


}