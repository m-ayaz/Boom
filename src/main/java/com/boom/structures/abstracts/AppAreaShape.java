package com.boom.structures.abstracts;


import com.boom.apppaints.AppColor;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;

import static com.boom.tools.Tools.setCustomSize;

public abstract   class AppAreaShape extends AppNode {

    protected Shape shape;

    protected Translate offset = new Translate();

    public AppAreaShape(Shape shape) {
        super(new Region(), "-fx-background-color", "-fx-border-color", "-fx-border-width");
        this.shape = shape;
        type = shape.getClass().getName();

        bindBorder(shape);

        shape.getTransforms().add(affineTransform);

        shape.boundsInLocalProperty().addListener((a, b, c) -> {
            setCustomSize((Region) styleableNode, c.getWidth(), c.getHeight());
            offset.setX(c.getMinX());
            offset.setY(c.getMinY());
        });

        //todo remove this and replace with proper modifications elsewhere in the code.
        backgroundStyle.addFill(0, new AppColor(Color.TRANSPARENT));
        backgroundStyle.addStroke(0, new AppColor(Color.BLACK));
        backgroundStyle.setStrokeWidth(1);

        shape.setFill(Color.TRANSPARENT);
        shape.setStroke(Color.TRANSPARENT);
        shape.setStrokeWidth(0);

        styleableNode.getTransforms().add(offset);

        shape.setMouseTransparent(true);

        ((Region) styleableNode).setShape(shape);


    }

    @Override
    public boolean contains(double x,double y){
        return styleableNode.contains(styleableNode.parentToLocal(x,y))&& shape.contains(shape.parentToLocal(x,y));
    }

}
