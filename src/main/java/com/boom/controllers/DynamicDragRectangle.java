package com.boom.controllers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DynamicDragRectangle extends Rectangle {

    public DynamicDragRectangle() {
        setFill(new Color(1, 1, 1, 0));
        setStroke(new Color(0, 0, 0, 1));
        setStrokeWidth(1);
        getStrokeDashArray().addAll(2.0, 5.0, 2.0, 5.0);
    }

    public void draw(double dragStartX, double dragStartY, double unreleasedDragX, double unreleasedDragY) {
        setX(Math.min(dragStartX, unreleasedDragX));
        setY(Math.min(dragStartY, unreleasedDragY));
        setWidth(Math.abs(unreleasedDragX - dragStartX));
        setHeight(Math.abs(unreleasedDragY - dragStartY));
    }

    public void reset() {
        setX(0);
        setY(0);
        setWidth(0);
        setHeight(0);
    }

//    public boolean contains(AppNode obj) {
//        return getX() <= obj.node.getBoundsInParent().getMinX() &&
//                getY() <= obj.node.getBoundsInParent().getMinY() &&
//                getX() + getWidth() >= obj.node.getBoundsInParent().getMaxX() &&
//                getY() + getHeight() >= obj.node.getBoundsInParent().getMaxY();
//    }

}