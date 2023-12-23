package com.boom.icons;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.boom.tools.Tools.matrixMultiplication;
import static java.lang.Math.*;

public class ScalingIcon extends Polygon {

    DoubleProperty centerX=new SimpleDoubleProperty();

    public double getCenterX() {
        return centerX.get();
    }

    public DoubleProperty centerXProperty() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX.set(centerX);
    }

    public double getCenterY() {
        return centerY.get();
    }

    public DoubleProperty centerYProperty() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY.set(centerY);
    }

    DoubleProperty centerY=new SimpleDoubleProperty();
    List<Double> initialPoints;
    int i;

    double[][] createUnRotatedInitialPoints(double arrowTipLength, double arrowTipRollOff, double stemWidth, double stemHeight) {
        return new double[][]{{stemWidth / 2 + arrowTipLength, stemWidth / 2, stemWidth / 2, -stemWidth / 2, -stemWidth / 2, -stemWidth / 2 - arrowTipLength, -stemWidth / 2, -stemWidth / 2, stemWidth / 2, stemWidth / 2}, {0.0, stemHeight / 2 + arrowTipRollOff, stemHeight / 2, stemHeight / 2, stemHeight / 2 + arrowTipRollOff, 0.0, -stemHeight / 2 - arrowTipRollOff, -stemHeight / 2, -stemHeight / 2, -stemHeight / 2 - arrowTipRollOff}};
    }
    public ScalingIcon(double arrowTipLength, double arrowTipRollOff, double stemWidth, double stemHeight, double rotationAngle, Paint backgroundColor, Paint borderColor, double borderWidth) {

        double[][] rotatedInitialPoints = matrixMultiplication(new double[][]{{cos(rotationAngle * PI / 180), -sin(rotationAngle * PI / 180)}, {sin(rotationAngle * PI / 180), cos(rotationAngle * PI / 180)}}, createUnRotatedInitialPoints(arrowTipLength, arrowTipRollOff, stemWidth, stemHeight));

        initialPoints = new ArrayList<>(Arrays.asList(rotatedInitialPoints[0][0], rotatedInitialPoints[1][0], rotatedInitialPoints[0][1], rotatedInitialPoints[1][1], rotatedInitialPoints[0][2], rotatedInitialPoints[1][2], rotatedInitialPoints[0][3], rotatedInitialPoints[1][3], rotatedInitialPoints[0][4], rotatedInitialPoints[1][4], rotatedInitialPoints[0][5], rotatedInitialPoints[1][5], rotatedInitialPoints[0][6], rotatedInitialPoints[1][6], rotatedInitialPoints[0][7], rotatedInitialPoints[1][7], rotatedInitialPoints[0][8], rotatedInitialPoints[1][8], rotatedInitialPoints[0][9], rotatedInitialPoints[1][9]));

        getPoints().setAll(initialPoints);

        setFill(backgroundColor);
        setStroke(borderColor);
        setStrokeWidth(borderWidth);

        centerX.addListener((_1,_2,newVal)->{
            for (i = 0; i < 20; i += 2) {
                getPoints().set(i, initialPoints.get(i) + newVal.doubleValue());
            }
        });

        centerY.addListener((_1,_2,newVal)->{
            for (i = 1; i < 20; i += 2) {
                getPoints().set(i, initialPoints.get(i) + newVal.doubleValue());
            }
        });

    }
}
