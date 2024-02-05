package com.boom.appcharts;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class AppTitleRegion extends Pane {

    public final Label title =new Label();
    
    public AppTitleRegion(SimpleDoubleProperty width){

        getChildren().add(title);

        bindSizes(width);
    }

    private void bindSizes(SimpleDoubleProperty width) {
        minWidthProperty().bindBidirectional(width);
        maxWidthProperty().bindBidirectional(width);
        prefWidthProperty().bindBidirectional(width);
        minWidthProperty().bindBidirectional(width);
        maxWidthProperty().bindBidirectional(width);
        prefWidthProperty().bindBidirectional(width);
        title.translateXProperty().bind(width.subtract(title.widthProperty()).divide(2));
    }

}
