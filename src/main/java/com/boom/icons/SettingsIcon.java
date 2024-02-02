package com.boom.icons;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.List;

public  class SettingsIcon extends Group {

    public SettingsIcon(int cogsNumber,double offset,double radius,double thickness, double L,double scale,Color color){

        super();

        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < cogsNumber; i++) {
            lines.add(new Line(offset/scale,0,offset/scale+L/scale,0));
        }

        lines.forEach(line -> {
            int i=lines.indexOf(line);
            line.setStroke(color);
            line.setStrokeWidth(thickness/scale);
            line.getTransforms().add(new Rotate(360.0/cogsNumber*i,0,0));
            line.setStrokeLineCap(StrokeLineCap.ROUND);
        });



        Circle circle=new Circle(radius/scale);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(color);
        circle.setStrokeType(StrokeType.CENTERED);
        circle.setStrokeWidth(thickness/scale);


        getChildren().addAll(lines);
        getChildren().add(circle);

    }

}
