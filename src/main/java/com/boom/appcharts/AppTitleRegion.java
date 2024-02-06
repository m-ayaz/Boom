package com.boom.appcharts;

import com.boom.structures.interfaces.JSONSerializable;
import com.boom.structures.interfaces.SVGSerializable;
import com.boom.structures.interfaces.TeXSerializable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.json.JSONObject;


public class AppTitleRegion extends Pane implements JSONSerializable, SVGSerializable, TeXSerializable {

    public final Label title =new Label();
    
    public AppTitleRegion(SimpleDoubleProperty width){

        getChildren().add(title);

        bindSizes(width);
    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public String toSVG(int tabIndent) {
        return null;
    }

    @Override
    public String toTeX() {
        return null;
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
