package com.boom.structures.abstracts;

import com.boom.styles.CSSProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import org.json.JSONObject;

import java.util.List;

public abstract class AppNode {

    public Affine affineTransform = new Affine();
    public Rectangle border = new Rectangle();
    public CSSProperty backgroundStyle;
//    public Region region;
    protected Node styleableNode;
    protected String type;
    public abstract AppNode  parseFromJSON(JSONObject jsonObject);

    public AppNode(Node styleableNode, String fillColorFX, String strokeColorFX, String strokeWidthFX) {
        this.styleableNode = styleableNode;
        styleableNode.getTransforms().add(affineTransform);
//        bindBorder();
        backgroundStyle = new CSSProperty(fillColorFX, strokeColorFX, strokeWidthFX);
        styleableNode.styleProperty().bind(backgroundStyle);
    }


    public abstract AppNode copy();

//    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
//        styleableNode.setVisible(true);
////        print("==================================================");
////        print(Math.min(dragStartX, currentDragPosX)+" , "+ Math.min(dragStartY, currentDragPosY));
////        print(Math.abs(currentDragPosX - dragStartX)+" , "+ Math.abs(currentDragPosY - dragStartY));
//        affineTransform.setToTransform(new Translate(Math.min(dragStartX, currentDragPosX), Math.min(dragStartY, currentDragPosY)));
//        setWidth(Math.abs(currentDragPosX - dragStartX));
//        setHeight(Math.abs(currentDragPosY - dragStartY));
//    }
    public abstract void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY);


//    public abstract double getHeight();
//
//    public abstract void setHeight(double height);

    public Node getStyleableNode() {
        return styleableNode;
    }

    public abstract String getSVGClones(int tabIndent);

    public String getType() {
        return type;
    }

//    public abstract double getWidth();
//
//    public abstract void setWidth(double width);

    public void hide() {
        styleableNode.setVisible(false);
    }

//    public void setBorderDashArray(List<Double> parsedStrokeDashArray) {
//
//    }


    public abstract JSONObject toJSON();

//    protected abstract void bindBorder(Node binder);

    protected void bindBorder(Node binder) {
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        border.setMouseTransparent(true);
        border.setStrokeWidth(1);
        border.getStrokeDashArray().addAll(5.0, 6.0, 6.0, 6.0);
        border.setVisible(false);
        binder.boundsInParentProperty().addListener((a, b, c) -> {
            border.setX(c.getMinX());
            border.setY(c.getMinY());
            border.setWidth(c.getWidth());
            border.setHeight(c.getHeight());
        });
    }


    protected void modifyType(String newType) {
        this.type = newType;
    }


}