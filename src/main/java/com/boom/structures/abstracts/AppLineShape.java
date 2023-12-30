package com.boom.structures.abstracts;


import com.boom.apppaints.AppColor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract  class AppLineShape extends AppNode {

    @Override
    public boolean contains(double x,double y){
        return styleableNode.contains(styleableNode.parentToLocal(x,y));
    }

    public AppLineShape(Shape shape) {
        super(shape, "-fx-fill", "-fx-stroke", "-fx-stroke-width");
        this.type = shape.getClass().getName();

        bindBorder(shape);

        backgroundStyle.addFill(0, new AppColor(Color.TRANSPARENT));
        backgroundStyle.addStroke(0, new AppColor(Color.BLACK));
        backgroundStyle.setStrokeWidth(20);

    }

}
