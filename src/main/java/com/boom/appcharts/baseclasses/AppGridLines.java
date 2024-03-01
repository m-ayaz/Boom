package com.boom.appcharts.baseclasses;

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
import javafx.scene.transform.Affine;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import static com.boom.tools.Tools.dissectAffineTransform;

public class AppGridLines extends Pane implements JSONSerializable, SVGSerializable, TeXSerializable {

    public final ObservableList<Double> xGridLinesStrokeDashArray = FXCollections.observableList(new ArrayList<>());
    public final ObservableList<Double> yGridLinesStrokeDashArray = FXCollections.observableList(new ArrayList<>());
    public final SimpleObjectProperty<Color> color = new SimpleObjectProperty<>(Color.BLACK);
    public final Affine affineTransform = new Affine();
    public final SimpleBooleanProperty xGridLinesVisible = new SimpleBooleanProperty(true);
    public final SimpleBooleanProperty yGridLinesVisible = new SimpleBooleanProperty(true);
    private final Group xGridLines = new Group();
    private final Group yGridLines = new Group();

    public AppGridLines(SimpleDoubleProperty width, SimpleDoubleProperty height, ObservableMap<Double, String> xTicks, ObservableMap<Double, String> yTicks) {
        getTransforms().add(affineTransform);
        bindSize(width, height);
        bindProps(width, height, xTicks, yTicks);
        bindVisibilities();
//        xGridLines.getChildren().forEach(line-> ((Line)line).setStroke(color.get()));
//        yGridLines.getChildren().forEach(line-> ((Line)line).setStroke(color.get()));
        color.addListener((a, b, c) -> xGridLines.getChildren().forEach(line -> ((Line) line).setStroke(c)));
        color.addListener((a, b, c) -> yGridLines.getChildren().forEach(line -> ((Line) line).setStroke(c)));
    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public String toSVG(int tabIndent) {

        double[] dissectedTransform = dissectAffineTransform(affineTransform);

        StringBuilder stringBuilder = new StringBuilder();

        if (xGridLinesVisible.get()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<!-- X grid lines -->");
            xGridLines.getChildren().forEach(line -> stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" stroke=\"%s\" stroke-width=\"%f\" stroke-dasharray=\"%s\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(
                    ((Line) line).getStartX(), ((Line) line).getStartY(), ((Line) line).getEndX(), ((Line) line).getEndY(),
                    color.get().toString().replace("0x", "#"),
                    ((Line) line).getStrokeWidth(), xGridLinesStrokeDashArray.stream().map(Object::toString).collect(Collectors.joining(" ")),
                    affineTransform.getTx(), affineTransform.getTy(),
                    dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]
            )));
        }

        if (yGridLinesVisible.get()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<!-- Y grid lines -->");
            yGridLines.getChildren().forEach(line -> stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" stroke=\"%s\" stroke-width=\"%f\" stroke-dasharray=\"%s\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(
                    ((Line) line).getStartX(), ((Line) line).getStartY(), ((Line) line).getEndX(), ((Line) line).getEndY(),
                    color.get().toString().replace("0x", "#"),
                    ((Line) line).getStrokeWidth(), yGridLinesStrokeDashArray.stream().map(Object::toString).collect(Collectors.joining(" ")),
                    affineTransform.getTx(), affineTransform.getTy(),
                    dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]
            )));
        }


        return stringBuilder.toString();
    }

    @Override
    public String toTeX() {
        return null;
    }

    private void bindProps(SimpleDoubleProperty width, SimpleDoubleProperty height, ObservableMap<Double, String> xTicks, ObservableMap<Double, String> yTicks) {
        getChildren().addAll(xGridLines, yGridLines);
        xTicks.addListener((MapChangeListener<Double, String>) change -> updateXGridLines(height, xTicks.keySet()));
        yTicks.addListener((MapChangeListener<Double, String>) change -> updateYGridLines(width, yTicks.keySet()));
        xGridLinesStrokeDashArray.addListener((ListChangeListener<Double>) change -> updateXGridLines(height, xTicks.keySet()));
        yGridLinesStrokeDashArray.addListener((ListChangeListener<Double>) change -> updateYGridLines(width, yTicks.keySet()));
        width.addListener((a, b, c) -> updateYGridLines(width, yTicks.keySet()));
        height.addListener((a, b, c) -> updateXGridLines(height, xTicks.keySet()));
    }

    private void bindSize(SimpleDoubleProperty width, SimpleDoubleProperty height) {
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

    private void updateXGridLines(SimpleDoubleProperty height, Set<Double> xTickPositions) {
//        xGridLines.getChildren().clear();
        xGridLines.getChildren().setAll(xTickPositions.stream().map(d -> {
            Line line = new Line(d, 0, d, height.get());
            line.getStrokeDashArray().setAll(xGridLinesStrokeDashArray);
            return line;
        }).toList());
    }

    private void updateYGridLines(SimpleDoubleProperty width, Set<Double> yTickPositions) {
//        yGridLines.getChildren().clear();
        yGridLines.getChildren().setAll(yTickPositions.stream().map(d -> {
            Line line = new Line(0, d, width.get(), d);
            line.getStrokeDashArray().setAll(yGridLinesStrokeDashArray);
            return line;
        }).toList());
    }

}
