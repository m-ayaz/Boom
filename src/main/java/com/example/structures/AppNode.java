package com.example.structures;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import org.json.JSONObject;

import java.util.List;

public abstract class AppNode {

    protected AppNode(Node node){
        this.node = node;
        this.type= node.getClass().getName();
        bindProperties();
    }

    public Affine affineTransform = new Affine();
    public Rectangle border = new Rectangle();
    public Node node;
    public String type;

    public abstract AppNode copy() ;

    public abstract void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY);

    public abstract JSONObject toJSON();

    public abstract String toTeX();

    public void hide() {
        node.setVisible(false);
    }

    public abstract void setBackgroundColor(Paint paint);


    public abstract void setBorderColor(Paint paint);


    public abstract void setBorderDashArray(List<Double> borderDashArray);

    public abstract void setBorderWidth(double borderWidth);

    public void setAffineTransform(List<Double> list){
        affineTransform.setMxx(Double.parseDouble(String.valueOf(list.get(0))));
        affineTransform.setMxy(Double.parseDouble(String.valueOf(list.get(1))));
        affineTransform.setTx(Double.parseDouble(String.valueOf(list.get(2))));
        affineTransform.setMyx(Double.parseDouble(String.valueOf(list.get(3))));
        affineTransform.setMyy(Double.parseDouble(String.valueOf(list.get(4))));
        affineTransform.setTy(Double.parseDouble(String.valueOf(list.get(5))));
    }

    protected void bindProperties() {
        border.setFill(new Color(0, 0, 0, 0));
        border.setStroke(new Color(0, 0, 0, 1));
        border.setMouseTransparent(true);
        border.setStrokeWidth(1);
        border.getStrokeDashArray().addAll(5.0, 6.0, 6.0, 6.0);
        border.setVisible(false);
        node.boundsInParentProperty().addListener((_1, _2, newVal) -> {
            border.setX(newVal.getMinX());
            border.setY(newVal.getMinY());
            border.setWidth(newVal.getWidth());
            border.setHeight(newVal.getHeight());
        });
        node.getTransforms().add(affineTransform);
    }



}
