package com.boom.appcharts;

import com.boom.structures.abstracts.AppNode;
import com.boom.structures.abstracts.AppPaint;
import com.boom.structures.interfaces.JSONSerializable;
import com.boom.structures.interfaces.SVGSerializable;
import com.boom.structures.interfaces.TeXSerializable;
import com.boom.styles.CSSProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import javafx.scene.transform.MatrixType;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.boom.configuration.Configs.ID_LENGTH;
import static com.boom.tools.Tools.*;

public class AppLegendRegion extends GridPane implements JSONSerializable, SVGSerializable, TeXSerializable {

//    public final SimpleStringProperty css = new SimpleStringProperty();

//    public final SimpleDoubleProperty centerWidth = new SimpleDoubleProperty();
//    public final SimpleDoubleProperty centerHeight = new SimpleDoubleProperty();

    public final SimpleDoubleProperty topMargin = new SimpleDoubleProperty(0.25);
    public final SimpleDoubleProperty bottomMargin = new SimpleDoubleProperty(0.25);
    public final SimpleDoubleProperty leftMargin = new SimpleDoubleProperty(0.25);
    public final SimpleDoubleProperty rightMargin = new SimpleDoubleProperty(0.25);
    public final SimpleDoubleProperty titleVisualMargin = new SimpleDoubleProperty(0);
    public final SimpleDoubleProperty fontSize = new SimpleDoubleProperty(10);
    public final SimpleObjectProperty<Font> font = new SimpleObjectProperty<>(Font.getDefault());
    public final String id = uuid(ID_LENGTH);
    private final GridPane container = new GridPane();
    //    public final CSSProperty backgroundStyle=new CSSProperty();
    public Affine affineTransform = new Affine();
    public CSSProperty backgroundStyle = new CSSProperty("-fx-background-color", "-fx-border-color", "-fx-border-width");
    protected Translate offset = new Translate(0, 0);
    Rectangle nw = new Rectangle();
    Rectangle se = new Rectangle();

    public AppLegendRegion() {

        setAlignment(Pos.CENTER);

        initializeVisuals();

    }

//    public void setCustomWidth(){
//
//    }

    private final List<SimpleObjectProperty<AppNode>> appSeriesVisualLegends=new ArrayList<>();
    private final List<Label> appSeriesTitles=new ArrayList<>();

    public void addSeries(int seriesIndex, SimpleObjectProperty<AppNode> appSeriesVisualLegend, Label appSeriesTitle) {
        Rectangle newEmptySpace = new Rectangle();
        newEmptySpace.widthProperty().bindBidirectional(titleVisualMargin);
        appSeriesVisualLegends.add(seriesIndex,appSeriesVisualLegend);
        appSeriesTitles.add(seriesIndex,appSeriesTitle);
        container.addRow(seriesIndex, appSeriesVisualLegend.get().getStyleableNode(), newEmptySpace, appSeriesTitle);
        appSeriesVisualLegend.addListener((a,b,c)-> container.getChildren().set(3*seriesIndex, c.getStyleableNode()));
    }

    public void removeSeries(int seriesIndex) {
        container.getChildren().remove(3 * seriesIndex + 2);
        container.getChildren().remove(3 * seriesIndex + 1);
        container.getChildren().remove(3 * seriesIndex);
        appSeriesVisualLegends.remove(seriesIndex);
        appSeriesTitles.remove(seriesIndex);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", getClass().getName());
        jsonObject.put("id", id);
        jsonObject.put("topMargin", topMargin.get());
        jsonObject.put("bottomMargin", bottomMargin.get());
        jsonObject.put("leftMargin", leftMargin.get());
        jsonObject.put("rightMargin", rightMargin.get());
        jsonObject.put("fontSize", fontSize.get());
        jsonObject.put("affine", arrayToList(affineTransform.toArray(MatrixType.MT_2D_2x3)));
        jsonObject.put("backgroundStyle", backgroundStyle.toJSON());
        jsonObject.put("width", getWidth());
        jsonObject.put("height", getHeight());
//        jsonObject.put("arcWidth", arcWidth.get());
//        jsonObject.put("arcHeight", arcHeight.get());
        return jsonObject;
    }

    @Override
    public String toSVG(int tabIndent) {

//        GridPane gridPane=new GridPane();
//        gridPane.get
//        RowConstraints rowConstraints=new RowConstraints();
//        Colum
//        rowConstraints.set
//        gridPane.getRowConstraints().add(new RowConstraints());
        return null;
//        double[] dissectedTransform = dissectAffineTransform(affineTransform);
//        StringBuilder stringBuilder = new StringBuilder();
//        for (AppPaint appPaint : backgroundStyle.getFillArray()) {
//            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<rect x=\"0\" y=\"0\" rx=\"0\" ry=\"0\" width=\"%f\" height=\"%f\" fill=\"url(#%s)\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(getWidth(),getHeight(), appPaint.id, affineTransform.getTx() + offset.getX(), affineTransform.getTy() + offset.getY(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
//        }
//        for (AppPaint appPaint : backgroundStyle.getStrokeArray()) {
//            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<rect x=\"0\" y=\"0\" rx=\"0\" ry=\"0\" width=\"%f\" height=\"%f\" fill=\"transparent\" stroke=\"url(#%s)\" stroke-width=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(getWidth(),getHeight(), appPaint.id, backgroundStyle.getStrokeWidth(), affineTransform.getTx() + offset.getX(), affineTransform.getTy() + offset.getY(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
//        }
//        double cursorX=nw.getWidth();
//        double cursorY=nw.getHeight();
////        print("as");
//        for (int i = 0; i <appSeriesVisualLegends.size(); i++) {
////            print(cursorY);
////            print(container.getChildren().size());
//            print(appSeriesVisualLegends.get(i).toSVG(0));
////            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<rect x=\"0\" y=\"0\" rx=\"0\" ry=\"0\" width=\"%f\" height=\"%f\" fill=\"transparent\" stroke=\"url(#%s)\" stroke-width=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(getWidth(),getHeight(), appPaint.id, backgroundStyle.getStrokeWidth(), affineTransform.getTx() + offset.getX(), affineTransform.getTy() + offset.getY(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
//            cursorY+=container.getChildren().get(i).getBoundsInParent().getHeight();
//        }
////        print("aslaslak = "+stringBuilder);
//        return stringBuilder.toString();
    }

    @Override
    public String toTeX() {
        return null;
    }

    private void bindProps() {

//        container.prefWidthProperty().bindBidirectional(centerWidth);
//        container.minWidthProperty().bindBidirectional(centerWidth);
//        container.maxWidthProperty().bindBidirectional(centerWidth);
//
//        container.prefHeightProperty().bindBidirectional(centerHeight);
//        container.minHeightProperty().bindBidirectional(centerHeight);
//        container.maxHeightProperty().bindBidirectional(centerHeight);

    }

    private void initializeVisuals() {


        nw.widthProperty().bind(leftMargin.multiply(widthProperty()));
        nw.heightProperty().bind(topMargin.multiply(heightProperty()));

        se.widthProperty().bind(rightMargin.multiply(widthProperty()));
        se.heightProperty().bind(bottomMargin.multiply(heightProperty()));

        nw.setFill(Color.TRANSPARENT);
        se.setFill(Color.TRANSPARENT);

        addRow(0, nw, new Pane(), new Pane());
        addRow(1, new Pane(), container, new Pane());
        addRow(2, new Pane(), new Pane(), se);

    }

}