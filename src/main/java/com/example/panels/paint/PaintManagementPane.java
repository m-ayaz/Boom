package com.example.panels.paint;

import com.example.styles.AppColor;
import com.example.styles.AppLinearGradient;
import com.example.styles.BackgroundsProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PaintManagementPane extends Pane {

    BackgroundsProperty backgrounds;

    VBox backgroundsPane = new VBox();

    ObservableList<Node> children = backgroundsPane.getChildren();

    public PaintManagementPane() {
        super();
        setBackground(Background.fill(Color.rgb(255, 0, 0, 0.1)));
        getChildren().add(backgroundsPane);
    }

    public void registerBackgrounds(BackgroundsProperty backgrounds) {
        this.backgrounds = backgrounds;
        backgrounds.fillArray.forEach(paint -> {
            if (paint.getClass().getName().equals(AppColor.class.getName())) {
                children.add(new ColorField(backgrounds.fillArray, children, (AppColor) paint));
            } else if (paint.getClass().getName().equals(AppLinearGradient.class.getName())) {
                children.add(new LinearGradientField(backgrounds.fillArray, children, (AppLinearGradient) paint));
            }
        });
    }

}
