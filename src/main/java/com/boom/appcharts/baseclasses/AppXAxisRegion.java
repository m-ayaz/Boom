package com.boom.appcharts.baseclasses;

import com.boom.structures.interfaces.JSONSerializable;
import com.boom.structures.interfaces.SVGSerializable;
import com.boom.structures.interfaces.TeXSerializable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.util.HashMap;

public class AppXAxisRegion extends VBox implements JSONSerializable, SVGSerializable, TeXSerializable {

    private final Pane ticksContainer = new Pane();
    private final Pane labelContainer = new Pane();
    public final Label label = new Label();
    public final ObservableMap<Double, String> ticks = FXCollections.observableMap(new HashMap<>());

    public AppXAxisRegion(SimpleDoubleProperty width) {
        bindSizes(width);

        getChildren().addAll(ticksContainer, labelContainer);

        labelContainer.getChildren().add(label);

        ticks.addListener((MapChangeListener<Double, String>) change -> {
            ticksContainer.getChildren().clear();
            change.getMap().forEach((key, value) -> {
                ticksContainer.getChildren().add(new Line(key, 0, key, 40));
                ticksContainer.getChildren().add(new Text(key, 40, value));
            });
        });

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
        ticksContainer.minWidthProperty().bindBidirectional(width);
        ticksContainer.maxWidthProperty().bindBidirectional(width);
        ticksContainer.prefWidthProperty().bindBidirectional(width);
        labelContainer.minWidthProperty().bindBidirectional(width);
        labelContainer.maxWidthProperty().bindBidirectional(width);
        labelContainer.prefWidthProperty().bindBidirectional(width);
        label.translateXProperty().bind(width.subtract(label.widthProperty()).divide(2));
    }


}
