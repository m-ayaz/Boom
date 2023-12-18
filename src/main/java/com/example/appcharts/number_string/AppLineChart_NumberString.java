package com.example.appcharts.number_string;

import com.example.configuration.Configs;
import com.example.structures.AppXYChart;
import com.example.styles.SeriesLineStyleProperty;
import com.example.styles.SeriesMarkersStyleProperty;
import com.example.tools.TeXConversion;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.example.tools.Tools.deepCopy;
import static com.example.tools.Tools.print;

public class AppLineChart_NumberString extends AppXYChart<Number,String> {
    public AppLineChart_NumberString(double width, double height) {
        super(new LineChart<>(new NumberAxis(), new CategoryAxis()),width,height);
        modifyType(getType() +"_NN");
        seriesMarkersStyles=new ArrayList<>();
        seriesLineStyles=new ArrayList<>();
    }

    @Override
    public AppXYChart<Number,String> copy()  {
        if (getWidth()==0||getHeight()==0) {
            return null;
        }
        AppXYChart<Number,String> newChart = new AppLineChart_NumberString(getWidth(),getHeight());
        deepCopy(this, newChart);
        return newChart;
    }

    @Override
    public XYChart.Series addSeries(int index) {
        XYChart.Series<Number,String> newSeries = new XYChart.Series<>();
        ((XYChart<Number,String>) getRegion()).getData().add(index, newSeries);
        SeriesLineStyleProperty seriesLineStyle=new SeriesLineStyleProperty();
//        seriesLineStyle.color.set((Color) ((Path)newSeries.getNode().lookup(".chart-series-line")).getStroke());
        newSeries.getNode().lookup(".chart-series-line").styleProperty().bind(seriesLineStyle);
        seriesLineStyle.color.set(Color.valueOf(Configs.colorConfigs.get("SERIES_LINE_COLOR").get(index%Configs.colorConfigs.get("SERIES_LINE_COLOR").size())));
        seriesLineStyles.add(index,seriesLineStyle);
        seriesMarkersStyles.add(index,new SeriesMarkersStyleProperty());
        return newSeries;
    }

    @Override
    public void removeSeries(int index) {
        ((XYChart<Number,String>) getRegion()).getData().remove(index);
        seriesLineStyles.remove(index);
        seriesMarkersStyles.remove(index);
    }
    @Override
    public XYChart.Data<Number,String> addData(Number x,String y,int seriesIndex, int dataIndex) {
        XYChart.Data<Number,String> newData=new XYChart.Data<>(x,y);
        ((XYChart<Number,String>) getRegion()).getData().get(seriesIndex).getData().add(dataIndex,newData);
        newData.getNode().styleProperty().bind(seriesMarkersStyles.get(seriesIndex));
        return newData;
    }



    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public String toTeX() {
        Affine plotAreaTransform=new Affine();
        plotAreaTransform.prepend(new Translate(plotAreaBounds.get().getMinX(),plotAreaBounds.get().getMinY()));
        plotAreaTransform.prepend(new Scale(1,-1,plotAreaBounds.get().getCenterX(),plotAreaBounds.get().getCenterY()));
        plotAreaTransform.prepend(affineTransform);
        StringBuilder dataString = new StringBuilder();
        for(XYChart.Series<?,?> series: ((XYChart<?,?>) getRegion()).getData()){
            int seriesIndex = ((XYChart<?, ?>) getRegion()).getData().indexOf(series);
            dataString.append("\n\t\\addplot[");
            dataString.append(TeXConversion.colorAsStroke(seriesLineStyles.get(seriesIndex).color.get(),2)).append(",");
            dataString.append("\n\t]");
            dataString.append("\n\tcoordinates{").append(series.getData().stream().map(obj -> "(" + obj.getXValue() + "," + obj.getYValue() + ")").collect(Collectors.joining())).append("};");
        }
        return "\n\\begin{scope}[transform canvas = {cm = {%f,%f,%f,%f,(%fpt,%fpt)}}]".formatted(plotAreaTransform.getMxx(), plotAreaTransform.getMyx(), plotAreaTransform.getMxy(), plotAreaTransform.getMyy(), plotAreaTransform.getTx(), plotAreaTransform.getTy()) +
                "\n\t\\begin{axis}["+
                "\n\t\taxis background/.style={" +
//                TeXConversion.colorAsFill(backgroundStyle.fill.get(),3)+","+
//                TeXConversion.colorAsStroke(backgroundStyle.stroke.get(),3)+","+
                "\n\t\t}," +
                TeXConversion.tickMarks(xAxisArea.getTickMarks(),2)+","+
                TeXConversion.tickMarks(yAxisArea.getTickMarks(),2)+","+
                "\n\t\twidth = %fpt,".formatted(plotAreaBounds.get().getWidth())+
                "\n\t\theight = %fpt,".formatted(plotAreaBounds.get().getHeight())+
                "\n\t\tscale only axis"+
                "\n\t]" +
                dataString +
                "\n\t\\end{axis}" +
                "\n\\end{scope}";
    }

}
