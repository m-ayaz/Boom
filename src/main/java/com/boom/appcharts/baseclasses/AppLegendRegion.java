package com.boom.appcharts.baseclasses;

import com.boom.structures.abstracts.AppNode;
import com.boom.structures.interfaces.JSONSerializable;
import com.boom.structures.interfaces.SVGSerializable;
import com.boom.structures.interfaces.TeXSerializable;
import com.boom.styles.CSSProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
//    public final SimpleDoubleProperty legendsMargin = new SimpleDoubleProperty(0);
    public final SimpleDoubleProperty fontSize = new SimpleDoubleProperty(10);
    public final SimpleObjectProperty<Font> font = new SimpleObjectProperty<>(Font.getDefault());
    public final String id = uuid(ID_LENGTH);
    public final GridPane container = new GridPane();
    //    public final CSSProperty backgroundStyle=new CSSProperty();
    public Affine affineTransform = new Affine();
    public CSSProperty backgroundStyle = new CSSProperty("-fx-background-color", "-fx-border-color", "-fx-border-width");
    protected Translate offset = new Translate(0, 0);
    public Rectangle nw = new Rectangle();
    public Rectangle se = new Rectangle();

    public AppLegendRegion() {

//        setAlignment(Pos.CENTER);

        styleProperty().bindBidirectional(backgroundStyle);
        getTransforms().add(affineTransform);

        initializeVisuals();

    }

//    public void setCustomWidth(){
//
//    }

    public final List<SimpleObjectProperty<AppNode>> appSeriesVisualLegends=new ArrayList<>();
    public final List<Text> appSeriesTitles=new ArrayList<>();

    public void addSeries(int seriesIndex, SimpleObjectProperty<AppNode> appSeriesVisualLegend, Text appSeriesTitle) {
        Rectangle newEmptySpace = new Rectangle();
        newEmptySpace.widthProperty().bindBidirectional(titleVisualMargin);
//        print("vis = "+appSeriesVisualLegend.get());
        appSeriesVisualLegends.add(seriesIndex,appSeriesVisualLegend);
        appSeriesTitles.add(seriesIndex,appSeriesTitle);
        print("al;sla;sla;sl;als;als;als;as");
        container.addRow(seriesIndex, appSeriesVisualLegend.get().wrappedNode, newEmptySpace, appSeriesTitle);
        appSeriesVisualLegend.addListener((a,b,c)-> container.getChildren().set(3*seriesIndex, c.wrappedNode));
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

        StringBuilder stringBuilder = new StringBuilder();

        List<AppNode> appSeriesVisualLegendsCopy = new ArrayList<>();
        appSeriesVisualLegends.forEach(appSeriesVisualLegend -> {
            AppNode appNodeCopy = appSeriesVisualLegend.get().copy();
            appNodeCopy.backgroundStyle = appSeriesVisualLegend.get().backgroundStyle;
            appSeriesVisualLegendsCopy.add(appNodeCopy);
        });

        print("<!-- Marker Bounds -->");

        for (int i = 0; i < appSeriesVisualLegendsCopy.size(); i++) {
            AppNode appNode = appSeriesVisualLegends.get(i).get();
            AppNode appNodeCopy = appSeriesVisualLegendsCopy.get(i);
            appNodeCopy.affineTransform.prependTranslation(
                    nw.getWidth() + appNode.wrappedNode.getBoundsInParent().getMinX(),
                    nw.getHeight() + appNode.wrappedNode.getBoundsInParent().getMinY()
            );
            appNodeCopy.affineTransform.prepend(affineTransform);
            stringBuilder.append(appNodeCopy.toSVG(tabIndent));

            print("<!-- "+appNodeCopy.wrappedNode.getBoundsInParent()+" -->");

        }

//        print("");
        print("<!-- Text Bounds -->");

        for (int i = 0; i < appSeriesTitles.size(); i++) {
            Text text = appSeriesTitles.get(i);
            Text textCopy = new Text(appSeriesTitles.get(i).getText());
            Affine affine = new Affine();
            textCopy.getTransforms().add(affine);
            affine.prepend(affineTransform);



//            Rectangle rectangle=new Rectangle(
//                    text.getBoundsInParent().getMinX(),
//                    text.getBoundsInParent().getMinY(),
//                    text.getBoundsInParent().getWidth(),
//                    text.getBoundsInParent().getHeight()
//            );
//
//            getChildren().add(rectangle);
//            rectangle.setFill(new Color(1,1,0,0.3));

//            text.setFont(Font.font(30));

//            text.setBoundsType(TextBoundsType.LOGICAL_VERTICAL_CENTER);


//            AppNode appNode=appSeriesVisualLegends.get(i).get();
//            AppNode appNodeCopy=appSeriesVisualLegendsCopy.get(i);
            affine.prependTranslation(
                    nw.getWidth() + text.getBoundsInParent().getMinX(),
                    nw.getHeight() + text.getBoundsInParent().getMinY()
            );

            print("<!-- "+text.getBoundsInParent()+" -->");

//            print(text.getBoundsInParent());
//            print();


//            print(affine);

            double[] dissectedTransform = dissectAffineTransform(affine);
//        print("Hey there");
//        print(affineTransform);
//        for (double v : dissectedTransform) {
//            print(v);
//        }
//            print("<text x=\"0\" y=\"0\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(  affine.getTx()+offset.getX()*0, affine.getTy()+0*offset.getY(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<text x=\"0\" y=\"0\" font-size=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\">%s</text>".formatted(text.getFont().getSize(),affine.getTx() + offset.getX() * 0, affine.getTy() + 0 * offset.getY(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3], textCopy.getText()));

//            appNodeCopy.affineTransform.prepend(affineTransform);
//            stringBuilder.append(la.toSVG(tabIndent));
        }

//            //todo: also register labels.

        return stringBuilder.toString();
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