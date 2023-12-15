package com.example.appcharts.string_number;

import com.example.configuration.Configs;
import com.example.structures.AppXYChart;
import com.example.styles.BackgroundsProperty;
import com.example.apppaints.AppColor;
import com.example.styles.SeriesLineStyleProperty;
import com.example.styles.SeriesMarkersStyleProperty;
import com.example.tools.TeXConversion;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.example.tools.Tools.deepCopy;

public class AppAreaChart_StringNumber extends AppXYChart<String,Number> {

    public AppAreaChart_StringNumber(double width, double height) {
        super(new AreaChart<>(new CategoryAxis(), new NumberAxis()),width,height);
        this.type += "_SN";
        seriesMarkersStyles=new ArrayList<>();
        seriesLineStyles=new ArrayList<>();
        seriesAreaStyles=new ArrayList<>();
    }

    @Override
    public AppXYChart<String,Number> copy()  {
        if (((XYChart<?, ?>) node).getMaxWidth() == 0 || ((XYChart<?, ?>) node).getMaxHeight() == 0) {
            return null;
        }
        AppXYChart<String,Number> newChart = new AppAreaChart_StringNumber(((XYChart<?, ?>) node).getMaxWidth(), ((XYChart<?, ?>) node).getMaxHeight());
        deepCopy(this, newChart);
        return newChart;
    }

    @Override
    public XYChart.Series<String,Number> addSeries(int index) {
        XYChart.Series<String,Number> newSeries = new XYChart.Series<>();
        ((XYChart<String,Number>) node).getData().add(index, newSeries);
        SeriesLineStyleProperty seriesLineStyle=new SeriesLineStyleProperty();
        BackgroundsProperty seriesAreaStyle=new BackgroundsProperty("-fx-fill","-fx-stroke","-fx-stroke-width");
//        seriesAreaStyle.fill.set((Color) ((Path)newSeries.getNode().lookup(".chart-series-area-fill")).getFill());
//        seriesAreaStyle.stroke.set((Color) ((Path)newSeries.getNode().lookup(".chart-series-area-fill")).getStroke());
//        seriesLineStyle.color.set((Color) ((Path)newSeries.getNode().lookup(".chart-series-area-line")).getStroke());
        newSeries.getNode().lookup(".chart-series-area-fill").styleProperty().bind(seriesAreaStyle);
        newSeries.getNode().lookup(".chart-series-area-line").styleProperty().bind(seriesLineStyle);
        seriesAreaStyle.addFill(0,new AppColor(Color.valueOf(Configs.colorConfigs.get("SERIES_AREA_COLOR").get(index%Configs.colorConfigs.get("SERIES_AREA_COLOR").size()))));
        seriesLineStyle.color.set(Color.valueOf(Configs.colorConfigs.get("SERIES_LINE_COLOR").get(index%Configs.colorConfigs.get("SERIES_LINE_COLOR").size())));
        seriesAreaStyles.add(index,seriesAreaStyle);
        seriesLineStyles.add(index,seriesLineStyle);
        seriesMarkersStyles.add(index,new SeriesMarkersStyleProperty());
        return newSeries;
    }

    @Override
    public XYChart.Data<String,Number> addData(String x,Number y,int seriesIndex, int dataIndex) {
        XYChart.Data<String,Number> newData=new XYChart.Data<>(x,y);
        ((XYChart<String,Number>) node).getData().get(seriesIndex).getData().add(dataIndex,newData);
        newData.getNode().styleProperty().bind(seriesMarkersStyles.get(seriesIndex));
        return newData;
    }

    @Override
    public void removeSeries(int index) {
        ((XYChart<String,Number>) node).getData().remove(index);
        seriesAreaStyles.remove(index);
        seriesLineStyles.remove(index);
        seriesMarkersStyles.remove(index);
    }


    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public String toTeX() {
        Affine plotAreaTransform = new Affine();
        plotAreaTransform.prepend(new Translate(plotAreaBounds.get().getMinX(), plotAreaBounds.get().getMinY()));
        plotAreaTransform.prepend(new Scale(1, -1, plotAreaBounds.get().getCenterX(), plotAreaBounds.get().getCenterY()));
        plotAreaTransform.prepend(affineTransform);
//        Color backgroundFillColor= backgroundStyle.fill.get();
//        Color backgroundStrokeColor= backgroundStyle.stroke.get();
        StringBuilder dataString = new StringBuilder();
        for (XYChart.Series<?, ?> series : ((XYChart<?, ?>) node).getData()) {
            int seriesIndex = ((XYChart<?, ?>) node).getData().indexOf(series);
            dataString.append("\n\t\\addplot[");
//            dataString.append(TeXConversion.colorAsFill(seriesAreaStyles.get(seriesIndex).fill.get(),2)).append(",");
            dataString.append(TeXConversion.colorAsStroke(seriesLineStyles.get(seriesIndex).color.get(),2)).append(",");
            dataString.append("\n\t]");
            dataString.append("\n\tcoordinates{").append(series.getData().stream().map(obj -> "(" + obj.getXValue() + "," + obj.getYValue() + ")").collect(Collectors.joining())).append("}\\closedcycle;");
        }
        return "\n\\begin{scope}[transform canvas = {cm = {%f,%f,%f,%f,(%fpt,%fpt)}}]".formatted(plotAreaTransform.getMxx(), plotAreaTransform.getMyx(), plotAreaTransform.getMxy(), plotAreaTransform.getMyy(), plotAreaTransform.getTx(), plotAreaTransform.getTy()) +
                "\n\t\\begin{axis}[" +
                "\n\t\taxis background/.style={" +
//                TeXConversion.colorAsFill(backgroundStyle.fill.get(),3)+","+
//                TeXConversion.colorAsStroke(backgroundStyle.stroke.get(),3)+","+
                "\n\t\t}," +
                TeXConversion.tickMarks(xAxisArea.getTickMarks(),2)+","+
                TeXConversion.tickMarks(yAxisArea.getTickMarks(),2)+","+
                "\n\t\twidth = %fpt,".formatted(plotAreaBounds.get().getWidth()) +
                "\n\t\theight = %fpt,".formatted(plotAreaBounds.get().getHeight()) +
                "\n\t\tscale only axis" +
                "\n\t]" +
                dataString +
                "\n\t\\end{axis}" +
                "\n\\end{scope}";
    }

}
