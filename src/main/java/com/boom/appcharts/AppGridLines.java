package com.boom.appcharts;

import com.boom.structures.interfaces.JSONSerializable;
import com.boom.structures.interfaces.SVGSerializable;
import com.boom.structures.interfaces.TeXSerializable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.*;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;

public class AppGridLines extends Pane implements JSONSerializable, SVGSerializable, TeXSerializable {

    public final ObservableList<Double> xGridLinesStrokeDashArray = FXCollections.observableList(new ArrayList<>());
    public final ObservableList<Double> yGridLinesStrokeDashArray = FXCollections.observableList(new ArrayList<>());

    public final SimpleObjectProperty<Color> color=new SimpleObjectProperty<>();
    public final Group xGridLines = new Group();
    public final Group yGridLines = new Group();

    public final SimpleBooleanProperty xGridLinesVisible = new SimpleBooleanProperty(true);
    public final SimpleBooleanProperty yGridLinesVisible = new SimpleBooleanProperty(true);

    public AppGridLines(SimpleDoubleProperty width, SimpleDoubleProperty height, ObservableMap<Double,String> xTicks, ObservableMap<Double,String> yTicks){
        bindSize(width,height);
        bindProps(width,height,xTicks,yTicks);
        bindVisibilities();
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

    private void bindProps(SimpleDoubleProperty width,SimpleDoubleProperty height, ObservableMap<Double,String> xTicks, ObservableMap<Double,String> yTicks){
        getChildren().addAll(xGridLines,yGridLines);
        xTicks.addListener((MapChangeListener< Double,  String>) change -> updateXGridLines(height,xTicks.keySet()));
        yTicks.addListener((MapChangeListener< Double,  String>) change -> updateYGridLines(width,yTicks.keySet()));
        xGridLinesStrokeDashArray.addListener((ListChangeListener< Double>) change -> updateXGridLines(height, xTicks.keySet()));
        yGridLinesStrokeDashArray.addListener((ListChangeListener< Double>) change -> updateYGridLines(width, yTicks.keySet()));
        width.addListener((a, b, c) -> updateYGridLines(width, yTicks.keySet()));
        height.addListener((a, b, c) -> updateXGridLines(height, xTicks.keySet()));
    }

    private void updateXGridLines(SimpleDoubleProperty height, Set<Double>xTickPositions){
        xGridLines.getChildren().setAll(xTickPositions.stream().map(d -> {
            Line line=new Line(d, 0, d, height.get());
            line.getStrokeDashArray().setAll(xGridLinesStrokeDashArray);
            return line;
        }).toList());
    }

    private void updateYGridLines(SimpleDoubleProperty width,Set<Double> yTickPositions){
        yGridLines.getChildren().setAll(yTickPositions.stream().map(d -> {
            Line line=new Line(0, d, width.get(), d);
            line.getStrokeDashArray().setAll(yGridLinesStrokeDashArray);
            return line;
        }).toList());
    }

    private void bindSize(SimpleDoubleProperty width,SimpleDoubleProperty height) {
        minWidthProperty().bindBidirectional(width);
        maxWidthProperty().bindBidirectional(width);
        prefWidthProperty().bindBidirectional(width);

        minHeightProperty().bindBidirectional(height);
        maxHeightProperty().bindBidirectional(height);
        prefHeightProperty().bindBidirectional(height);
    }


    private void bindVisibilities() {
        xGridLines.visibleProperty().bindBidirectional(xGridLinesVisible);
        yGridLines.visibleProperty().bindBidirectional(yGridLinesVisible);
    }
    
}
