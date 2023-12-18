package com.example.structures.abstracts;

import com.example.exceptions.AppException;
import com.example.structures.enums.AppExceptionEnum;
import com.example.structures.enums.NodeTypeEnum;
import com.example.styles.CSSProperty;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import java.util.List;

import static com.example.tools.Tools.*;

public abstract class AppRegion {

    public Affine affineTransform = new Affine();
    public Rectangle border = new Rectangle();
    public CSSProperty backgroundStyle = new CSSProperty("-fx-background-color", "-fx-border-color", "-fx-border-width");
    Region region;
    Shape shape;
    String type;

    public AppRegion(Shape shape) {

        this.region = new Region();
        this.shape = shape;
        this.type = shape.getClass().getName();
        region.setShape(shape);
        bindBorder();
        shape.getTransforms().add(affineTransform);
        region.getTransforms().add(affineTransform);
        region.styleProperty().bind(backgroundStyle);

    }

    public AppRegion(Region region) {

        this.region = region;
        this.shape = null;
        this.type = region.getClass().getName();
        bindBorder();
        region.getTransforms().add(affineTransform);
        region.styleProperty().bind(backgroundStyle);

    }

    public abstract AppRegion copy();

    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        region.setVisible(true);
        affineTransform.setToTransform(new Translate(Math.min(dragStartX, currentDragPosX), Math.min(dragStartY, currentDragPosY)));
        setCustomSize(region, Math.abs(currentDragPosX - dragStartX), Math.abs(currentDragPosY - dragStartY));
    }

    public Region getRegion() {
        return region;
    }

    public Shape getShape() {
        return shape;
    }

    public String getType() {
        return type;
    }

    public void hide() {
        region.setVisible(false);
    }

    public void setAffineTransformFromList(List<Double> list) {
        affineTransform.setMxx(Double.parseDouble(String.valueOf(list.get(0))));
        affineTransform.setMxy(Double.parseDouble(String.valueOf(list.get(1))));
        affineTransform.setTx(Double.parseDouble(String.valueOf(list.get(2))));
        affineTransform.setMyx(Double.parseDouble(String.valueOf(list.get(3))));
        affineTransform.setMyy(Double.parseDouble(String.valueOf(list.get(4))));
        affineTransform.setTy(Double.parseDouble(String.valueOf(list.get(5))));

    }

    public abstract JSONObject toJSON();

    public abstract String toTeX();


    void bindBorder() {
        border.setFill(new Color(0, 0, 0, 0));
        border.setStroke(new Color(0, 0, 0, 1));
        border.setMouseTransparent(true);
        border.setStrokeWidth(1);
        border.getStrokeDashArray().addAll(5.0, 6.0, 6.0, 6.0);
        border.setVisible(false);
        region.boundsInParentProperty().addListener((_1, _2, newVal) -> {
            double w, h;
            if (shape != null) {
                if (shape.getClass().getName().equals(NodeTypeEnum.Ellipse.getNodeType())) {
                    ((Ellipse) shape).setRadiusX(getWidth() / 2);
                    ((Ellipse) shape).setRadiusY(getHeight() / 2);
                } else if (shape.getClass().getName().equals(NodeTypeEnum.Rectangle.getNodeType())) {
                    ((Rectangle) shape).setWidth(getWidth());
                    ((Rectangle) shape).setHeight(getHeight());
                } else {
                    throw new AppException(AppExceptionEnum.AppNodeNotRegistered);
                }
                w = shape.getBoundsInParent().getWidth();
                h = shape.getBoundsInParent().getHeight();
            } else {
                w = newVal.getWidth();
                h = newVal.getHeight();
            }
            border.setWidth(w);
            border.setHeight(h);
            border.setX(newVal.getCenterX() - w / 2);
            border.setY(newVal.getCenterY() - h / 2);
        });

    }


    public double getWidth() {
        return region.getPrefWidth();
    }

    public double getHeight() {
        return region.getPrefHeight();
    }

    public void setWidth(double width) {
        setCustomWidth(region, width);
    }

    public void setHeight(double height) {
        setCustomHeight(region, height);
    }

    protected void modifyType(String newType) {
        this.type = newType;
    }


    public void setBorderDashArray(List<Double> parsedStrokeDashArray) {

    }


}