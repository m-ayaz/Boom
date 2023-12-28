package com.boom.appcharts.number_number;

import com.boom.configuration.Configs;
import com.boom.structures.abstracts.AppXYChart;
import com.boom.styles.SeriesLineStyleProperty;
import com.boom.styles.SeriesMarkersStyleProperty;
import com.boom.tools.TeXConversion;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.boom.tools.Tools.deepCopy;

@SuppressWarnings("unchecked")
public final class AppLineChart_NumberNumber extends AppXYChart<Number,Number> {
    public AppLineChart_NumberNumber(double width, double height) {
        super(new LineChart<>(new NumberAxis(), new NumberAxis()), width, height);
        modifyType(getType() + "_NN");
        seriesMarkersStyles = new ArrayList<>();
        seriesLineStyles = new ArrayList<>();
    }



    @Override
    public AppXYChart<Number, Number> copy() {
        if (getWidth() == 0 || getHeight() == 0) {
            return null;
        }
        AppXYChart<Number, Number> newChart = new AppLineChart_NumberNumber(getWidth(), getHeight());
        deepCopy(this, newChart);
        return newChart;
    }

    @Override
    public String getSVGClones(int tabIndent) {
        return null;
    }

    @Override
    public XYChart.Series<Number,Number> addSeries(int index) {
        XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
        ((XYChart<Number, Number>) getStyleableNode()).getData().add(index, newSeries);
        SeriesLineStyleProperty seriesLineStyle = new SeriesLineStyleProperty();
//        seriesLineStyle.color.set((Color) ((Path)newSeries.getNode().lookup(".chart-series-line")).getStroke());
        newSeries.getNode().lookup(".chart-series-line").styleProperty().bind(seriesLineStyle);
        seriesLineStyle.color.set(Color.valueOf(Configs.SERIES_LINE_COLORS_List.get(index % Configs.SERIES_LINE_COLORS_List.size())));
        seriesLineStyles.add(index, seriesLineStyle);
        seriesMarkersStyles.add(index, new SeriesMarkersStyleProperty());
        return newSeries;
    }

    @Override
    public void removeSeries(int index) {
        ((XYChart<Number, Number>) getStyleableNode()).getData().remove(index);
        seriesLineStyles.remove(index);
        seriesMarkersStyles.remove(index);
    }

    @Override
    public XYChart.Data<Number, Number> addData(Number x, Number y, int seriesIndex, int dataIndex) {
        XYChart.Data<Number, Number> newData = new XYChart.Data<>(x, y);
        ((XYChart<Number, Number>) getStyleableNode()).getData().get(seriesIndex).getData().add(dataIndex, newData);
        newData.getNode().styleProperty().bind(seriesMarkersStyles.get(seriesIndex));
        return newData;
    }


    @Override
    public JSONObject toJSON() {
        return null;
    }


    public String toTeX() {
        Affine plotAreaTransform = new Affine();
        plotAreaTransform.prepend(new Translate(plotAreaBounds.get().getMinX(), plotAreaBounds.get().getMinY()));
        plotAreaTransform.prepend(new Scale(1, -1, plotAreaBounds.get().getCenterX(), plotAreaBounds.get().getCenterY()));
        plotAreaTransform.prepend(affineTransform);
        StringBuilder dataString = new StringBuilder();
        for (XYChart.Series<?, ?> series : ((XYChart<?, ?>) getStyleableNode()).getData()) {
            int seriesIndex = ((XYChart<?, ?>) getStyleableNode()).getData().indexOf(series);
            dataString.append("\n\t\\addplot[");
            dataString.append(TeXConversion.colorAsStroke(seriesLineStyles.get(seriesIndex).color.get(), 2)).append(",");
            dataString.append("\n\t]");
            dataString.append("\n\tcoordinates{").append(series.getData().stream().map(obj -> "(" + obj.getXValue() + "," + obj.getYValue() + ")").collect(Collectors.joining())).append("};");
        }
        return "\n\\begin{scope}[transform canvas = {cm = {%f,%f,%f,%f,(%fpt,%fpt)}}]".formatted(plotAreaTransform.getMxx(), plotAreaTransform.getMyx(), plotAreaTransform.getMxy(), plotAreaTransform.getMyy(), plotAreaTransform.getTx(), plotAreaTransform.getTy()) +
                "\n\t\\begin{axis}[" +
                "\n\t\taxis background/.style={" +
//                TeXConversion.colorAsFill(backgroundStyle.fill.get(),3)+","+
//                TeXConversion.colorAsStroke(backgroundStyle.stroke.get(),3)+","+
                "\n\t\t}," +
                TeXConversion.tickMarks(xAxisArea.getTickMarks(), 2) + "," +
                TeXConversion.tickMarks(yAxisArea.getTickMarks(), 2) + "," +
                "\n\t\twidth = %fpt,".formatted(plotAreaBounds.get().getWidth()) +
                "\n\t\theight = %fpt,".formatted(plotAreaBounds.get().getHeight()) +
                "\n\t\tscale only axis" +
                "\n\t]" +
                dataString +
                "\n\t\\end{axis}" +
                "\n\\end{scope}";
    }


    public String toSVG() {
        return null;
    }

}