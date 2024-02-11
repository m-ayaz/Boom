package com.boom.tools;

import com.boom.appcharts.AppAxisChart;
import com.boom.appcharts.AppSeries;
import com.boom.appshapes.AppText;
import com.boom.exceptions.AppException;
import com.boom.structures.abstracts.AppXYChart;
import com.boom.structures.enums.AppExceptionEnum;
import com.boom.styles.CSSProperty;
import com.boom.styles.SeriesLineStyleProperty;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import javafx.scene.transform.Affine;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.lang.Math.*;

public class Tools {

//    public static double getMin(List<Double> list){
//        if(list.size()==0){
//            return Double.POSITIVE_INFINITY;
//        }else{
//            list.stream().mapToDouble(x->x).min().orElse()
//        }
//    }

    public static void print(Object obj) {
        System.out.println(obj);
    }

    public static void print() {
        System.out.println();
    }

    public static void deepCopy(Affine affineFrom, Affine affineTo) {
        affineTo.setMxx(affineFrom.getMxx());
        affineTo.setMxy(affineFrom.getMxy());
        affineTo.setMyx(affineFrom.getMyx());
        affineTo.setMxz(affineFrom.getMxz());
        affineTo.setMzx(affineFrom.getMzx());
        affineTo.setMyz(affineFrom.getMyz());
        affineTo.setMzy(affineFrom.getMzy());
        affineTo.setMzz(affineFrom.getMzz());
        affineTo.setMyy(affineFrom.getMyy());
        affineTo.setTx(affineFrom.getTx());
        affineTo.setTy(affineFrom.getTy());
        affineTo.setTz(affineFrom.getTz());
    }

    public static Affine parseAffine(JSONArray jsonArray) {
        Affine affine = new Affine();
        affine.setMxx(jsonArray.getDouble(0));
        affine.setMxy(jsonArray.getDouble(1));
        affine.setTx(jsonArray.getDouble(2));
        affine.setMyx(jsonArray.getDouble(3));
        affine.setMyy(jsonArray.getDouble(4));
        affine.setTy(jsonArray.getDouble(5));
        return affine;
    }

//    public static Affine parseAffine(JSONArray jsonArray){
//        Affine affine=new Affine();
//        affine.setMxx(jsonArray.getDouble(0));
//        affine.setMxy(jsonArray.getDouble(1));
//        affine.setTx(jsonArray.getDouble(2));
//        affine.setMyx(jsonArray.getDouble(3));
//        affine.setMyy(jsonArray.getDouble(4));
//        affine.setTy(jsonArray.getDouble(5));
//        return affine;
//    }

    public static List<Double> arrayToList(Object[] array) {
        List<Double> list = new ArrayList<>();
        for (Object v : array) {
            list.add(Double.valueOf(v.toString()));
        }
        return list;
    }

    public static double[] arrayToArray(JSONArray jsonArray) {
        double[] array = new double[jsonArray.length()];
        for (int i = 0; i < array.length; i++) {
            array[i] = jsonArray.getDouble(i);
        }
        return array;
//        List<Double> list=new ArrayList<>();
//        array.forEach(obj->list.add(Double.parseDouble(obj.toString())));
//        return list;
    }

    public static List<Double> arrayToList(double[] array) {
        List<Double> list = new ArrayList<>();
        for (double v : array) {
            list.add(v);
        }
        return list;
    }

    public static Affine deepCopy(Affine affine) {
        Affine newAffine = new Affine();
        newAffine.setMxx(affine.getMxx());
        newAffine.setMxy(affine.getMxy());
        newAffine.setMyx(affine.getMyx());
        newAffine.setMxz(affine.getMxz());
        newAffine.setMzx(affine.getMzx());
        newAffine.setMyz(affine.getMyz());
        newAffine.setMzy(affine.getMzy());
        newAffine.setMzz(affine.getMzz());
        newAffine.setMyy(affine.getMyy());
        newAffine.setTx(affine.getTx());
        newAffine.setTy(affine.getTy());
        newAffine.setTz(affine.getTz());
        return newAffine;
    }

//    public static void deepCopy(Rectangle rectangleFrom, Rectangle rectangleTo) {
//        rectangleTo.setX(rectangleFrom.getX());
//        rectangleTo.setY(rectangleFrom.getY());
//        rectangleTo.setWidth(rectangleFrom.getWidth());
//        rectangleTo.setHeight(rectangleFrom.getHeight());
//        rectangleTo.setFill(rectangleFrom.getFill());
//        rectangleTo.setStroke(rectangleFrom.getStroke());
//        rectangleTo.setStrokeWidth(rectangleFrom.getStrokeWidth());
//        rectangleTo.getStrokeDashArray().addAll(rectangleFrom.getStrokeDashArray());
//    }
//
//    public static void deepCopy(Ellipse ellipseFrom, Ellipse ellipseTo) {
//        ellipseTo.setCenterX(ellipseFrom.getCenterX());
//        ellipseTo.setCenterY(ellipseFrom.getCenterY());
//        ellipseTo.setRadiusX(ellipseFrom.getRadiusX());
//        ellipseTo.setRadiusY(ellipseFrom.getRadiusY());
//        ellipseTo.setStyle(ellipseFrom.getStyle());
//        ellipseTo.setFill(ellipseFrom.getFill());
//        ellipseTo.setStroke(ellipseFrom.getStroke());
//        ellipseTo.setStrokeWidth(ellipseFrom.getStrokeWidth());
//        ellipseTo.getStrokeDashArray().addAll(ellipseFrom.getStrokeDashArray());
//    }

//    public static void deepCopy(Region regionFrom, Region regionTo) {
//        setCustomSize(regionTo,regionFrom.getPrefWidth(),regionFrom.getPrefHeight());
//        regionTo.setBackground(Background.fill(regionFrom.getBackground().getFills().get(0).getFill()));
//        regionTo.setBorder(Border.stroke(regionFrom.getBorder().getStrokes().get(0).getTopStroke()));
////        ellipseTo.setStrokeWidth(ellipseFrom.getStrokeWidth());
////        ellipseTo.getStrokeDashArray().addAll(ellipseFrom.getStrokeDashArray());
//    }

    public static void deepCopy(CSSProperty cssPropertyFrom, CSSProperty cssPropertyTo) {
        cssPropertyTo.removeAllFills();
        cssPropertyTo.removeAllStrokes();
        cssPropertyFrom.getFillArray().forEach(appPaint -> cssPropertyTo.addFill(appPaint.copy()));
        cssPropertyFrom.getStrokeArray().forEach(appPaint -> cssPropertyTo.addStroke(appPaint.copy()));
        cssPropertyFrom.setStrokeWidth(cssPropertyTo.getStrokeWidth());
    }

//    public static CSSProperty deepCopy(CSSProperty cssProperty) {
//        CSSProperty  newCSSProperty=new CSSProperty(cssProperty.getFillColorFX(),cssProperty.getStrokeColorFX(),cssProperty.getStrokeWidthFX());
//        cssProperty.getFillArray().forEach(appPaint -> newCSSProperty.addFill(appPaint.copy()));
//        cssProperty.getStrokeArray().forEach(appPaint -> newCSSProperty.addStroke(appPaint.copy()));
//        cssProperty.setStrokeWidth(newCSSProperty.getStrokeWidth());
//        return newCSSProperty;
//    }

//    public static void deepCopy(AppAreaShape areaShapeFrom,AppAreaShape areaShapeTo){
//
//    }

//    public static void deepCopy(Line lineFrom, Line lineTo) {
//        lineTo.setStartX(lineFrom.getStartX());
//        lineTo.setStartY(lineFrom.getStartY());
//        lineTo.setEndX(lineFrom.getEndX());
//        lineTo.setEndY(lineFrom.getEndY());
//        lineTo.setStroke(lineFrom.getStroke());
//        lineTo.setStrokeWidth(lineFrom.getStrokeWidth());
//        lineTo.getStrokeDashArray().addAll(lineFrom.getStrokeDashArray());
//    }


    public static void deepCopy(AppText textFrom, AppText textTo) {
    }

    public static double[][] matrixMultiplication(double[][] a, double[][] b) {
        if (a[0].length != b.length) {
            throw new InputMismatchException("Cannot multiply {%d}*{%d} matrix in {%d}*{%d} matrix.".formatted(a.length, a[0].length, b.length, b[0].length));
        }
        double[][] result = new double[a.length][b[0].length];
        int i, j, k;
        double temp;
        for (i = 0; i < a.length; i++) {
            for (j = 0; j < b[0].length; j++) {
                temp = 0;
                for (k = 0; k < b.length; k++) {
                    temp += a[i][k] * b[k][j];
                }
                result[i][j] = temp;
            }
        }
        return result;
    }

//    public static AppEllipse parseEllipseFromJSON(JSONObject jsonString) {
//        AppEllipse newEllipse = new AppEllipse(0, 0);
//        double radiusX = Double.parseDouble(String.valueOf(jsonString.get("radiusX")));
//        double radiusY = Double.parseDouble(String.valueOf(jsonString.get("radiusY")));
//        List<Double> fillColor = (List<Double>) jsonString.get("fillColor");
//        List<Double> strokeColor = (ArrayList<Double>) jsonString.get("strokeColor");
//        double fillOpacity = Double.parseDouble(String.valueOf(jsonString.get("fillOpacity")));
//        double strokeOpacity = Double.parseDouble(String.valueOf(jsonString.get("strokeOpacity")));
//        double strokeWidth = Double.parseDouble(String.valueOf(jsonString.get("strokeWidth")));
//        List<Double> affineTransform = (List<Double>) jsonString.get("affineTransformation");
//        newEllipse.setRadiusX(radiusX);
//        newEllipse.setRadiusY(radiusY);
//        newEllipse.setBackgroundColor(new Color(fillColor.get(0) / 255,fillColor.get(1) / 255, fillColor.get(2) / 255, fillOpacity));
//        newEllipse.setBorderColor(new Color(strokeColor.get(0) / 255,strokeColor.get(1) / 255, strokeColor.get(2) / 255, strokeOpacity));
//        newEllipse.setBorderWidth(strokeWidth);
//        newEllipse.setAffineTransformFromList(affineTransform);
//        return newEllipse;
//    }
//
//    public static AppRectangle parseRectangleFromJSON(JSONObject jsonString) {
//        AppRectangle newRectangle = new AppRectangle(0, 0);
//        double width = Double.parseDouble(String.valueOf(jsonString.get("width")));
//        double height = Double.parseDouble(String.valueOf(jsonString.get("height")));
//        List<Double> fillColor = (ArrayList<Double>) jsonString.get("fillColor");
//        List<Double> strokeColor = (ArrayList<Double>) jsonString.get("strokeColor");
//        double fillOpacity = Double.parseDouble(String.valueOf(jsonString.get("fillOpacity")));
//        double strokeOpacity = Double.parseDouble(String.valueOf(jsonString.get("strokeOpacity")));
//        double strokeWidth = Double.parseDouble(String.valueOf(jsonString.get("strokeWidth")));
//        List<Double> affineTransform = (List<Double>) jsonString.get("affineTransformation");
//        newRectangle.setWidth(width);
//        newRectangle.setHeight(height);
//        newRectangle.setBackgroundColor(new Color(fillColor.get(0) / 255, fillColor.get(1) / 255, fillColor.get(2) / 255, fillOpacity));
//        newRectangle.setBorderColor(new Color(strokeColor.get(0) / 255, strokeColor.get(1) / 255, strokeColor.get(2) / 255, strokeOpacity));
//        newRectangle.setBorderWidth(strokeWidth);
//        newRectangle.setAffineTransformFromList(affineTransform);
//        return newRectangle;
//    }

//    public static AppLineChart_NumberNumber parseLineChart_NNFromJSON(JSONObject jsonString) {
//        return new AppLineChart_NumberNumber(0, 0);
//    }
//
//    public static AppLineChart_NumberString parseLineChart_NSFromJSON(JSONObject jsonString) {
//        return new AppLineChart_NumberString(0, 0);
//    }
//
//    public static AppLineChart_StringNumber parseLineChart_SNFromJSON(JSONObject jsonString) {
//        return new AppLineChart_StringNumber(0, 0);
//    }
//
//    public static AppAreaChart_NumberNumber parseAreaChart_NNFromJSON(JSONObject jsonString) {
//        return new AppAreaChart_NumberNumber(0, 0);
//    }
//
//    public static AppAreaChart_NumberString parseAreaChart_NSFromJSON(JSONObject jsonString) {
//        return new AppAreaChart_NumberString(0, 0);
//    }
//
//    public static AppAreaChart_StringNumber parseAreaChart_SNFromJSON(JSONObject jsonString) {
//        return new AppAreaChart_StringNumber(0, 0);
//    }
//
//    public static AppScatterChart_NumberNumber parseScatterChart_NNFromJSON(JSONObject jsonString) {
//        return new AppScatterChart_NumberNumber(0, 0);
//    }
//
//    public static AppScatterChart_NumberString parseScatterChart_NSFromJSON(JSONObject jsonString) {
//        return new AppScatterChart_NumberString(0, 0);
//    }
//
//    public static AppScatterChart_StringNumber parseScatterChart_SNFromJSON(JSONObject jsonString) {
//        return new AppScatterChart_StringNumber(0, 0);
//    }

//    public static AppLine parseLineFromJSON(JSONObject jsonString) {
//        AppLine newLine = new AppLine(0, 0, 0, 0);
//        double startX = Double.parseDouble((String) jsonString.get("startX"));
//        double startY = Double.parseDouble((String) jsonString.get("startY"));
//        double endX = Double.parseDouble((String) jsonString.get("endX"));
//        double endY = Double.parseDouble((String) jsonString.get("endY"));
//        List<Double> fillColor = (ArrayList<Double>) jsonString.get("fillColor");
//        List<Double> strokeColor = (ArrayList<Double>) jsonString.get("strokeColor");
//        double fillOpacity = Double.parseDouble(String.valueOf(jsonString.get("fillOpacity")));
//        double strokeOpacity = Double.parseDouble(String.valueOf(jsonString.get("strokeOpacity")));
//        double strokeWidth = Double.parseDouble(String.valueOf(jsonString.get("strokeWidth")));
//        List<Double> affineTransform = (List<Double>) jsonString.get("affineTransformation");
//        newLine.setStartX(startX);
//        newLine.setStartY(startY);
//        newLine.setEndX(endX);
//        newLine.setEndY(endY);
//        newLine.setBackgroundColor(new Color(fillColor.get(0) / 255,fillColor.get(1) / 255, fillColor.get(2) / 255, fillOpacity));
//        newLine.setBorderColor(new Color(strokeColor.get(0) / 255,strokeColor.get(1) / 255, strokeColor.get(2) / 255, strokeOpacity));
//        newLine.setBorderWidth(strokeWidth);
//        newLine.setAffineTransformFromList(affineTransform);
//        return newLine;
//    }

    public static String uuid(int idSize) {
        Random random = new Random();
        String alphabet = "qwertyuiopasdfghjklzxcvbnm7984563210";
        return random.ints(idSize, 0, alphabet.length()).mapToObj(i -> alphabet.split("")[i]).collect(Collectors.joining());
    }

    public static void setCustomSize(Region region, double width, double height) {
        region.setMaxSize(width, height);
        region.setPrefSize(width, height);
        region.setMinSize(width, height);
    }


    public static void setCustomWidth(Region region, double width) {
        region.setMaxWidth(width);
        region.setPrefWidth(width);
        region.setMinWidth(width);
    }

    public static void setCustomHeight(Region region, double height) {
        region.setMaxHeight(height);
        region.setPrefHeight(height);
        region.setMinHeight(height);
    }

//    static void deepCopy(SeriesAreaStyleProperty propertyFrom, SeriesAreaStyleProperty propertyTo) {
//        propertyTo.fill.set(propertyFrom.fill.get());
//        propertyTo.stroke.set(propertyFrom.stroke.get());
//    }

    static void deepCopy(SeriesLineStyleProperty propertyFrom, SeriesLineStyleProperty propertyTo) {
        propertyTo.color.set(propertyFrom.color.get());
        propertyTo.width.set(propertyFrom.width.get());
    }

//    static void deepCopy(BackgroundsProperty propertyFrom, BackgroundsProperty propertyTo) {
//        propertyTo.getFillArray().clear();
//        propertyTo.getStrokeArray().clear();
//        propertyTo.getFillArray().addAll(propertyFrom.getFillArray());
//        propertyTo.getStrokeArray().addAll(propertyFrom.getStrokeArray());
//        propertyTo.setStrokeWidth(propertyFrom.getStrokeArray().get());
//    }

    public static void dataCopy(AppSeries seriesFrom, AppSeries seriesTo){

//        chartFrom.getS
    }

    public static void dataCopy(AppAxisChart chartFrom,AppAxisChart chartTo){
//        chartFrom.getS
    }

    public static <T1, T2> void deepCopy(AppXYChart<T1, T2> chartFrom, AppXYChart<T1, T2> chartTo) {
//        try {
//            throw new RuntimeException("sdasd");
//        }catch (Exception e){
//            print(e);
//        }
        deepCopy(chartFrom.getBackgroundStyle(), chartTo.getBackgroundStyle());
        deepCopy(chartFrom.affineTransform, chartTo.affineTransform);
        for (int i = 0; i < ((XYChart<T1, T2>) chartFrom.styleableNode).getData().size(); i++) {
            XYChart.Series<T1, T2> series = ((XYChart<T1, T2>) chartFrom.styleableNode).getData().get(i);
            chartTo.addSeries(i);
            for (int j = 0; j < series.getData().size(); j++) {
                XYChart.Data<T1, T2> data = series.getData().get(j);
                chartTo.addData(data.getXValue(), data.getYValue(), i, j);
                try {
//                    throw new Exception("Fix here");
                    deepCopy(chartFrom.getSeriesAreaStyles().get(i), chartTo.getSeriesAreaStyles().get(i));
                } catch (Exception e) {
                    print(e);
                    print("%s does not have AREA".formatted(chartFrom.styleableNode.getClass().getSimpleName()));
                }
                try {
                    deepCopy(chartFrom.getSeriesLineStyles().get(i), chartTo.getSeriesLineStyles().get(i));
                } catch (Exception e) {
                    print(e);
                    print("%s does not have LINE".formatted(chartFrom.styleableNode.getClass().getSimpleName()));
                }
            }
        }
    }


    public static double[] dissectAffineTransform(Affine affine) {

        double a = affine.getMxx();
        double b = affine.getMxy();
        double c = affine.getMyx();
        double d = affine.getMyy();

        double N2 = a * a + b * b + c * c + d * d;
        double D = affine.determinant();
        if (D == 0) {
            throw new AppException(AppExceptionEnum.UnexpectedError);
        }

        double delta = sqrt(N2 * N2 - 4 * D * D);

        double sx = sqrt((N2 + delta) / 2);
        double sy = D > 0 ? sqrt((N2 - delta) / 2) : -sqrt((N2 - delta) / 2);

        double th1 = sx == sy ? 0 : atan2(c - b, a + d) / 2 + atan2(c + b, a - d) / 2;
        double th2 = th1 - atan2(c + b, a - d);

        return new double[]{th1 * 180 / PI, sx, sy, th2 * 180 / PI};
    }

    public static double[] getScientificRepresentation1(double x, int truncatedDigits) {
        if (x == 0) {
            return new double[]{0, 0};
        } else {
            double sciExp = floor(log10(abs(x)));
            double temp = pow(10, -sciExp) * x;
            return new double[]{temp, sciExp};
        }
    }

    public static double[] getTickBounds(double _min_, double _max_) {
        double[] lowerTemp = getScientificRepresentation1(_min_, 0);
        double[] upperTemp = getScientificRepresentation1(_max_, 0);
        return new double[]{
                ((int) floor(lowerTemp[0])) * pow(10, lowerTemp[1]),
                ((int) floor(upperTemp[0])) * pow(10, upperTemp[1])
        };
    }

    public static String[] getStringTicks(double lowerBound, double upperBound, int n) {
        if (n == 0) {
            throw new AppException(AppExceptionEnum.UnexpectedError);
        }
        String[] strings = new String[n];
        if (n <= 1) {
            strings[0] = getScientificRepresentation(lowerBound / 2 + upperBound / 2, 0);
        } else {
            for (int i = 0; i < n; i++) {
                strings[i] = getScientificRepresentation(lowerBound + (upperBound - lowerBound) * i / (n - 1), 0);
            }
        }
        return strings;
    }

    public static String getScientificRepresentation(double x, int truncatedDigits) {
        if(x==0){
            return "0.0";
        }
        int sciExp = (int) floor(log10(x));
//        print(sciExp);
        double temp = Math.round(pow(10, -sciExp+truncatedDigits) * x)/pow(10,truncatedDigits);
//        print(pow(10, truncatedDigits - sciExp) * x);
//        print(temp);
        return "%se%d".formatted(temp, sciExp);
    }

    public static void _TEMP1_(double _min_, double _max_) {
        double[] lowerBoundSciRep = getScientificRepresentation1(_min_, 0);
        double[] upperBoundSciRep = getScientificRepresentation1(_max_, 0);
        int lowerTick = (int) floor(lowerBoundSciRep[0]);
        int upperTick = (int) ceil(upperBoundSciRep[0] * pow(10, upperBoundSciRep[1] - lowerBoundSciRep[1]));
        print(lowerTick + " %%% " + upperTick);
    }

}