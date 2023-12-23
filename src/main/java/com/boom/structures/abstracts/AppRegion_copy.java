package com.boom.structures.abstracts;

import com.boom.exceptions.AppException;
import com.boom.structures.enums.AppExceptionEnum;
import com.boom.structures.enums.NodeTypeEnum;
import com.boom.styles.CSSProperty;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import java.util.List;

import static com.boom.tools.Tools.*;

public abstract class AppRegion_copy {

    public Affine affineTransform = new Affine();
    public Rectangle border = new Rectangle();
    public CSSProperty backgroundStyle;
    protected Region background;
    protected Shape shape;

    String type;
    double w, h;
    boolean isAreaShape = true;

    public AppRegion_copy(Shape shape, boolean isAreaShape) {

        this.background = new Region();
        this.shape = shape;
        this.type = shape.getClass().getName();
        this.isAreaShape = isAreaShape;
        bindBorder();
        shape.getTransforms().add(affineTransform);
        background.getTransforms().add(affineTransform);
        if (isAreaShape) {
            backgroundStyle = new CSSProperty("-fx-background-color", "-fx-border-color", "-fx-border-width");
            background.setShape(shape);
            background.styleProperty().bind(backgroundStyle);
        } else {
            backgroundStyle = new CSSProperty("-fx-fill", "-fx-stroke", "-fx-stroke-width");
            shape.styleProperty().bind(backgroundStyle);
        }


    }

    public AppRegion_copy(Region background) {

        this.background = background;
        this.shape = null;
        this.type = background.getClass().getName();
        bindBorder();
        background.getTransforms().add(affineTransform);
        backgroundStyle = new CSSProperty("-fx-background-color", "-fx-border-color", "-fx-border-width");
        background.styleProperty().bind(backgroundStyle);

    }

    public abstract AppRegion_copy copy();

    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        background.setVisible(true);
//        print("==================================================");
//        print(Math.min(dragStartX, currentDragPosX)+" , "+ Math.min(dragStartY, currentDragPosY));
//        print(Math.abs(currentDragPosX - dragStartX)+" , "+ Math.abs(currentDragPosY - dragStartY));
        affineTransform.setToTransform(new Translate(Math.min(dragStartX, currentDragPosX), Math.min(dragStartY, currentDragPosY)));
        setCustomSize(background, Math.abs(currentDragPosX - dragStartX), Math.abs(currentDragPosY - dragStartY));
    }

    public String fillsToSVG(int tabIndent) {
        return String.join("", backgroundStyle.getFillArray().stream().map(appPaint -> appPaint.toSVG(tabIndent)).toList());
    }

    public String fillsToTeX() {
        return null;
    }

    public double getHeight() {
        return background.getPrefHeight();
    }

    public void setHeight(double height) {
        setCustomHeight(background, height);
    }

    public Node getNode() {
        if (isAreaShape) {
            return background;
        } else {
            return shape;
        }
    }
//
//    public abstract String toTeX();
//
//    public abstract String toSVG();

    public abstract String getSVGClones(int tabIndent);

    public String getType() {
        return type;
    }

    public double getWidth() {
        return background.getPrefWidth();
    }

    public void setWidth(double width) {
        setCustomWidth(background, width);
    }

    public void hide() {
        background.setVisible(false);
    }

    public void setAffineTransformFromList(List<Double> list) {
        affineTransform.setMxx(Double.parseDouble(String.valueOf(list.get(0))));
        affineTransform.setMxy(Double.parseDouble(String.valueOf(list.get(1))));
        affineTransform.setTx(Double.parseDouble(String.valueOf(list.get(2))));
        affineTransform.setMyx(Double.parseDouble(String.valueOf(list.get(3))));
        affineTransform.setMyy(Double.parseDouble(String.valueOf(list.get(4))));
        affineTransform.setTy(Double.parseDouble(String.valueOf(list.get(5))));

    }

    public void setBorderDashArray(List<Double> parsedStrokeDashArray) {

    }

    public String strokesToSVG(int tabIndent) {
        return String.join("", backgroundStyle.getStrokeArray().stream().map(appPaint -> appPaint.toSVG(tabIndent)).toList());
    }

    public abstract JSONObject toJSON();

    void bindBorder() {
        border.setFill(new Color(0, 0, 0, 0));
        border.setStroke(new Color(0, 0, 0, 1));
        border.setMouseTransparent(true);
        border.setStrokeWidth(1);
        border.getStrokeDashArray().addAll(5.0, 6.0, 6.0, 6.0);
        border.setVisible(false);
        shape.boundsInParentProperty().addListener((a,b,c)->{

        });
        background.boundsInParentProperty().addListener((_1, _2, newVal) -> {
            if (shape != null) {
                if (shape.getClass().getName().equals(NodeTypeEnum.Ellipse.getNodeType())) {
                    ((Ellipse) shape).setRadiusX(getWidth() / 2);
                    ((Ellipse) shape).setRadiusY(getHeight() / 2);
                } else if (shape.getClass().getName().equals(NodeTypeEnum.Rectangle.getNodeType())) {
                    ((Rectangle) shape).setWidth(getWidth());
                    ((Rectangle) shape).setHeight(getHeight());
//                } else if (shape.getClass().getName().equals(NodeTypeEnum.Arc.getNodeType())) {
//                        ((Arc) shape).setWidth(getWidth());
//                        ((Arc) shape).setHeight(getHeight());

//                } else if (shape.getClass().getName().equals(NodeTypeEnum.Line.getNodeType())) {
//                    ((Line) shape).setEndX(getWidth());
//                    ((Line) shape).setEndY(getHeight());
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

    Translate offset = new Translate();

    void temp() {


        shape.boundsInLocalProperty().addListener((a, b, c) -> {
            setCustomSize(background, c.getWidth(), c.getHeight());
            offset.setX(c.getMinX());
            offset.setY(c.getMinY());
        });

        background.setMouseTransparent(true);

        background.getTransforms().add(affineTransform);
        shape.getTransforms().add(affineTransform);

        shape.setFill(Color.TRANSPARENT);
        shape.setStroke(Color.TRANSPARENT);
        shape.setStrokeWidth(0);
        background.styleProperty().bind(backgroundStyle);


        background.setShape(shape);


    }

    protected void modifyType(String newType) {
        this.type = newType;
    }


}