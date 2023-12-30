package com.boom.specialfeatures.mindmap;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

/**
 * MindMap nodes are Circles, without transforms.
 */
public class MindMapConnection extends SVGPath {




     Rotate rotate = new Rotate();
     Translate translate = new Translate();
    ReadOnlyDoubleProperty leftRadius, rightRadius, leftFactor, rightFactor, middleThickness, leftCenterX, leftCenterY, rightCenterX, rightCenterY;
    Color leftColor, rightColor;

    public MindMapConnection(DoubleProperty leftRadius, DoubleProperty rightRadius, DoubleProperty leftCenterX, DoubleProperty leftCenterY, DoubleProperty rightCenterX, DoubleProperty rightCenterY, DoubleProperty middleThickness, DoubleProperty leftFactor, DoubleProperty rightFactor, Color leftColor, Color rightColor) {



        this.leftRadius = leftRadius;
        this.rightRadius = rightRadius;
        this.leftFactor = leftFactor;
        this.rightFactor = rightFactor;
        this.middleThickness = middleThickness;
        this.leftCenterX = leftCenterX;
        this.leftCenterY = leftCenterY;
        this.rightCenterX = rightCenterX;
        this.rightCenterY = rightCenterY;
        this.leftColor=leftColor;
        this.rightColor=rightColor;

        translate.xProperty().bind(leftCenterX);
        translate.yProperty().bind(leftCenterY);

        rotate.pivotXProperty().bind(leftCenterX);
        rotate.pivotYProperty().bind(leftCenterY);

        setStroke(Color.TRANSPARENT);
        setStrokeWidth(0);

        getTransforms().addAll(rotate, translate);

        update();
        leftRadius.addListener((a, b, c) -> update());
        rightRadius.addListener((a, b, c) -> update());
        leftFactor.addListener((a, b, c) -> update());
        rightFactor.addListener((a, b, c) -> update());
        middleThickness.addListener((a, b, c) -> update());
        leftCenterX.addListener((a, b, c) -> update());
        leftCenterY.addListener((a, b, c) -> update());
        rightCenterX.addListener((a, b, c) -> update());
        rightCenterY.addListener((a, b, c) -> update());

    }

    void update() {

        double l1Left = leftRadius.get() * leftFactor.get();
        double l2Left = leftRadius.get();
        double l1Right = rightRadius.get() * rightFactor.get();
        double l2Right = rightRadius.get();

        double l1Leftp = Math.pow(leftRadius.get(), 2) / l1Left;
        double hLeft = Math.sqrt(1 - Math.pow(leftRadius.get(), 2) / Math.pow(l1Left, 2)) * leftRadius.get();
        double l2Leftp = l1Left - middleThickness.get() / 2 * Math.sqrt(Math.pow(l1Left, 2) / Math.pow(leftRadius.get(), 2) - 1);

        double l1Rightp = Math.pow(rightRadius.get(), 2) / l1Right;
        double hRight = Math.sqrt(1 - Math.pow(rightRadius.get(), 2) / Math.pow(l1Right, 2)) * rightRadius.get();
        double l2Rightp = l1Right - middleThickness.get() / 2 * Math.sqrt(Math.pow(l1Right, 2) / Math.pow(rightRadius.get(), 2) - 1);

        double distance = Math.sqrt(Math.pow(leftCenterX.get() - rightCenterX.get(), 2) + Math.pow(leftCenterY.get() - rightCenterY.get(), 2));

        setContent(("M%f,%f Q%f,%f %f,%f L%f,%f Q%f,%f %f,%f L%f,%f Q%f,%f %f,%f L%f,%f Q%f,%f %f,%f z").formatted(l1Leftp, hLeft, l2Leftp, middleThickness.get() / 2, l1Left + l2Left, middleThickness.get() / 2, distance - l1Right - l2Right, middleThickness.get() / 2, distance - l2Rightp, middleThickness.get() / 2, distance - l1Rightp, hRight, distance - l1Rightp, -hRight, distance - l2Rightp, -middleThickness.get() / 2, distance - l1Right - l2Right, -middleThickness.get() / 2, l1Left + l2Left, -middleThickness.get() / 2, l2Leftp, -middleThickness.get() / 2, l1Leftp, -hLeft));

        rotate.setAngle(Math.atan2(rightCenterY.get() - leftCenterY.get(), rightCenterX.get() - leftCenterX.get()) * 180 / Math.PI);

        setFill(new LinearGradient(leftRadius.get(), 0, distance - rightRadius.get(), 0, false, CycleMethod.NO_CYCLE, new Stop(0, leftColor), new Stop(1, rightColor)));
    }

}