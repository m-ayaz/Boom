package com.boom.structures.abstracts;


import com.boom.apppaints.AppColor;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;

import static com.boom.tools.Tools.setCustomSize;
import static com.boom.tools.Tools.uuid;

public abstract   class AppAreaShape extends AppNode {

    protected Shape shape;

//    @Override
//    protected void bindBorder(Node binder) {
//
//    }

    Translate offset = new Translate();

    public AppAreaShape(Shape shape) {
        super(new Region(), "-fx-background-color", "-fx-border-color", "-fx-border-width");
        this.shape = shape;
        this.type = shape.getClass().getName();

        bindBorder(shape);

        shape.getTransforms().add(affineTransform);

        shape.boundsInLocalProperty().addListener((a, b, c) -> {
            setCustomSize((Region) styleableNode, c.getWidth(), c.getHeight());
            offset.setX(c.getMinX());
            offset.setY(c.getMinY());
        });

        backgroundStyle.addFill(0, new AppColor(Color.TRANSPARENT, uuid(50)));
        backgroundStyle.addStroke(0, new AppColor(Color.BLACK, uuid(50)));

        shape.setFill(Color.TRANSPARENT);
        shape.setStroke(Color.TRANSPARENT);
        shape.setStrokeWidth(0);

        styleableNode.getTransforms().add(offset);

        shape.setMouseTransparent(true);

        ((Region) styleableNode).setShape(shape);


    }

}
