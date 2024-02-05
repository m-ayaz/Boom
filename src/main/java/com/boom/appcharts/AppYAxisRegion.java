package com.boom.appcharts;

import com.boom.structures.interfaces.JSONSerializable;
import com.boom.structures.interfaces.SVGSerializable;
import com.boom.structures.interfaces.TeXSerializable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.util.HashMap;

public class AppYAxisRegion extends HBox implements JSONSerializable, SVGSerializable, TeXSerializable {

    private final Pane ticksContainer = new Pane();
    private final Pane labelContainer = new Pane();

    public final Label label = new Label();


    public final ObservableMap<Double, String> ticks = FXCollections.observableMap(new HashMap<>());

    public AppYAxisRegion(SimpleDoubleProperty height) {
        bindSizes(height);

        getChildren().addAll(labelContainer, ticksContainer);

        labelContainer.getChildren().add(label);

        ticks.addListener((MapChangeListener<Double, String>) change -> {
            ticksContainer.getChildren().clear();
            change.getMap().forEach((key, value) -> {
                ticksContainer.getChildren().add(new Line(0, key, 40, key));
                ticksContainer.getChildren().add(new Text(0, key, value));
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

    private void bindSizes(SimpleDoubleProperty height) {
        ticksContainer.minHeightProperty().bindBidirectional(height);
        ticksContainer.maxHeightProperty().bindBidirectional(height);
        ticksContainer.prefHeightProperty().bindBidirectional(height);
        labelContainer.minHeightProperty().bindBidirectional(height);
        labelContainer.maxHeightProperty().bindBidirectional(height);
        labelContainer.prefHeightProperty().bindBidirectional(height);
        label.translateYProperty().bind(height.subtract(label.heightProperty()).divide(2));
    }


}