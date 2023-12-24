package com.boom.structures.abstracts;


import com.boom.apppaints.AppColor;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;

import static com.boom.tools.Tools.setCustomSize;
import static com.boom.tools.Tools.uuid;

public abstract  class AppLineShape extends AppNode {

    Translate offset = new Translate();

    public AppLineShape(Shape shape) {
        super(shape, "-fx-fill", "-fx-stroke", "-fx-stroke-width");
        this.type = shape.getClass().getName();

        bindBorder(shape);

        shape.boundsInLocalProperty().addListener((a, b, c) -> {
            setCustomSize((Region) styleableNode, c.getWidth(), c.getHeight());
            offset.setX(c.getMinX());
            offset.setY(c.getMinY());
        });

        backgroundStyle.addFill(0, new AppColor(Color.TRANSPARENT, uuid(50)));
        backgroundStyle.addStroke(0, new AppColor(Color.BLACK, uuid(50)));

        styleableNode.getTransforms().add(offset);

        shape.setMouseTransparent(true);

    }

}
