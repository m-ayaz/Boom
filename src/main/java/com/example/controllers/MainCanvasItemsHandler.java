package com.example.controllers;

import com.example.appcharts.number_number.AppAreaChart_NumberNumber;
import com.example.appcharts.number_number.AppLineChart_NumberNumber;
import com.example.appcharts.number_number.AppScatterChart_NumberNumber;
import com.example.appcharts.number_string.AppAreaChart_NumberString;
import com.example.appcharts.number_string.AppLineChart_NumberString;
import com.example.appcharts.number_string.AppScatterChart_NumberString;
import com.example.appcharts.string_number.AppAreaChart_StringNumber;
import com.example.appcharts.string_number.AppLineChart_StringNumber;
import com.example.appcharts.string_number.AppScatterChart_StringNumber;
import com.example.apppaints.AppColor;
import com.example.appshapes.AppEllipse;
import com.example.appshapes.AppLine;
import com.example.appshapes.AppRectangle;
import com.example.exceptions.AppException;
import com.example.icons.RotationIcon;
import com.example.icons.ScalingIcon;
import com.example.indicators.*;
import com.example.structures.enums.AppExceptionEnum;
import com.example.structures.abstracts.AppRegion;
import com.example.structures.abstracts.AppXYChart;
import com.example.structures.enums.NodeTypeEnum;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

import static com.example.tools.Tools.uuid;

public class MainCanvasItemsHandler {

    ObservableList<Node> mainCanvasChildren;
    int numOfValidObjects;
    DynamicDragRectangle dynamicDragRectangle;
    AppEllipse tempEllipse;
    AppRectangle tempRectangle;
    AppLine tempLine;
    AppLineChart_NumberNumber tempLineChart_NN;
    AppLineChart_NumberString tempLineChart_NS;
    AppLineChart_StringNumber tempLineChart_SN;
    //
    AppAreaChart_NumberNumber tempAreaChart_NN;
    AppAreaChart_NumberString tempAreaChart_NS;
    AppAreaChart_StringNumber tempAreaChart_SN;
    //
////    AppBarChart_NumberString tempBarChart_NS = new AppBarChart_NumberString(0,0);
////    AppBarChart_StringNumber tempBarChart_SN = new AppBarChart_StringNumber(0,0);
//
    AppScatterChart_NumberNumber tempScatterChart_NN;
    AppScatterChart_NumberString tempScatterChart_NS;
    AppScatterChart_StringNumber tempScatterChart_SN;

    //    AppText tempText = new AppText(0, 0, "");
    LittleLineChartOnCursor littleLineChartOnCursor;
    //    LittleBarChartOnCursor littleBarChartOnCursor;
    LittleScatterChartOnCursor littleScatterChartOnCursor;
    LittleAreaChartOnCursor littleAreaChartOnCursor;
    LittleEllipseOnCursor littleEllipseOnCursor;
    LittleRectangleOnCursor littleRectangleOnCursor;
    LittleLineOnCursor littleLineOnCursor;
    //    LittleTextOnCursor littleTextOnCursor = new LittleTextOnCursor();
    SelectedObjectsController selectedObjectsController;
    List<AppRegion> validObjects;

    public MainCanvasItemsHandler(ObservableList<Node> mainCanvasChildren, List<AppRegion> validObjects, Line rotationHandle, RotationIcon rotationIcon, List<ScalingIcon> scalingIcons, Circle rotationFixedPoint, Circle scalingFixedPoint, DynamicDragRectangle dynamicDragRectangle, AppEllipse tempEllipse, AppRectangle tempRectangle, AppLine tempLine, AppLineChart_NumberNumber tempLineChart_NN, AppLineChart_NumberString tempLineChart_NS, AppLineChart_StringNumber tempLineChart_SN, AppAreaChart_NumberNumber tempAreaChart_NN, AppAreaChart_NumberString tempAreaChart_NS, AppAreaChart_StringNumber tempAreaChart_SN, AppScatterChart_NumberNumber tempScatterChart_NN, AppScatterChart_NumberString tempScatterChart_NS, AppScatterChart_StringNumber tempScatterChart_SN, LittleLineChartOnCursor littleLineChartOnCursor,
//                                  LittleBarChartOnCursor littleBarChartOnCursor,
                                  LittleScatterChartOnCursor littleScatterChartOnCursor, LittleAreaChartOnCursor littleAreaChartOnCursor, LittleEllipseOnCursor littleEllipseOnCursor, LittleRectangleOnCursor littleRectangleOnCursor, LittleLineOnCursor littleLineOnCursor) {

//        this.mainCanvas = mainCanvas;

        this.mainCanvasChildren = mainCanvasChildren;
        this.validObjects = validObjects;
//        this.rotationHandle=rotationHandle;
//        this.rotationIcon=rotationIcon;
//        this.scalingIcons=scalingIcons;
//        this.rotationFixedPoint=rotationFixedPoint;
//        this.scalingFixedPoint=scalingFixedPoint;
        this.dynamicDragRectangle = dynamicDragRectangle;
        this.tempEllipse = tempEllipse;
        this.tempRectangle = tempRectangle;
        this.tempLine = tempLine;
        this.tempLineChart_NN = tempLineChart_NN;
        this.tempLineChart_NS = tempLineChart_NS;
        this.tempLineChart_SN = tempLineChart_SN;
        this.tempAreaChart_NN = tempAreaChart_NN;
        this.tempAreaChart_NS = tempAreaChart_NS;
        this.tempAreaChart_SN = tempAreaChart_SN;
        this.tempScatterChart_NN = tempScatterChart_NN;
        this.tempScatterChart_NS = tempScatterChart_NS;
        this.tempScatterChart_SN = tempScatterChart_SN;
        this.littleLineChartOnCursor = littleLineChartOnCursor;
//        this.littleBarChartOnCursor=littleBarChartOnCursor;
        this.littleScatterChartOnCursor = littleScatterChartOnCursor;
        this.littleAreaChartOnCursor = littleAreaChartOnCursor;
        this.littleEllipseOnCursor = littleEllipseOnCursor;
        this.littleRectangleOnCursor = littleRectangleOnCursor;
        this.littleLineOnCursor = littleLineOnCursor;


        selectedObjectsController = new SelectedObjectsController(rotationIcon, scalingIcons, rotationFixedPoint);

//        print(mainCanvasChildren);

        mainCanvasChildren.addAll(tempRectangle.getRegion(), tempEllipse.getRegion(), tempLine.getRegion(), tempLineChart_NN.getRegion(), tempLineChart_SN.getRegion(), tempLineChart_NS.getRegion(), tempScatterChart_NN.getRegion(), tempScatterChart_SN.getRegion(), tempScatterChart_NS.getRegion(),
//                tempBarChart_SN.node,
//                tempBarChart_NS.node,
                tempAreaChart_NN.getRegion(), tempAreaChart_SN.getRegion(), tempAreaChart_NS.getRegion(), littleEllipseOnCursor, littleRectangleOnCursor, littleLineOnCursor, littleLineChartOnCursor, littleAreaChartOnCursor,
//                littleBarChartOnCursor,
                littleScatterChartOnCursor, dynamicDragRectangle, rotationHandle, rotationFixedPoint, scalingFixedPoint, rotationIcon);
        mainCanvasChildren.addAll(scalingIcons);

        numOfValidObjects = 0;

        this.validObjects = validObjects;

    }

    public void addToMainCanvas(AppRegion appRegion) {
        validObjects.add(appRegion);
        mainCanvasChildren.add(numOfValidObjects, appRegion.getRegion());
        mainCanvasChildren.add(numOfValidObjects * 2 + 1, appRegion.border);
        numOfValidObjects++;
    }

    public void applyFillColorChangesToSelectedObjects(Color color) {
        selectedObjectsController.getBuffer().forEach(obj -> {
            obj.backgroundStyle.removeAllFills();
            obj.backgroundStyle.addFill(0, new AppColor(color,uuid(50)));
        });
    }

    public void applyStrokeColorChangesToSelectedObjects(Color color) {
        selectedObjectsController.getBuffer().forEach(obj -> {
            obj.backgroundStyle.removeAllStrokes();
            obj.backgroundStyle.addStroke(0, new AppColor(color,uuid(50)));
        });
    }

    public void applyStrokeWidthChangesToSelectedObjects(double strokeWidth) {
        selectedObjectsController.getBuffer().forEach(obj -> obj.backgroundStyle.setStrokeWidth(strokeWidth));
    }

    public void copyTempAreaChart_NNOnMainCanvas() {
        AppXYChart<Number, Number> areaChartClone = tempAreaChart_NN.copy();
        if (areaChartClone != null) {
            addToMainCanvas(areaChartClone);
            selectedObjectsController.select(areaChartClone);
            tempAreaChart_NN.hide();
        }
    }

    public void copyTempAreaChart_NSOnMainCanvas() {
        AppXYChart<Number, String> areaChartClone = tempAreaChart_NS.copy();
        if (areaChartClone != null) {
            addToMainCanvas(areaChartClone);
            selectedObjectsController.select(areaChartClone);
            tempAreaChart_NS.hide();
        }
    }

    public void copyTempAreaChart_SNOnMainCanvas() {
        AppXYChart<String, Number> areaChartClone = tempAreaChart_SN.copy();
        if (areaChartClone != null) {
            addToMainCanvas(areaChartClone);
            selectedObjectsController.select(areaChartClone);
            tempAreaChart_SN.hide();
        }
    }

    public void copyTempEllipseOnMainCanvas() {
        AppEllipse ellipseClone = tempEllipse.copy();
        if (ellipseClone != null) {
            addToMainCanvas(ellipseClone);
            selectedObjectsController.select(ellipseClone);
            tempEllipse.hide();
        }
    }

    public void copyTempLineChart_NNOnMainCanvas() {
        AppXYChart<Number, Number> lineChartClone = tempLineChart_NN.copy();
        if (lineChartClone != null) {
            addToMainCanvas(lineChartClone);
            selectedObjectsController.select(lineChartClone);
            tempLineChart_NN.hide();
        }
    }

    public void copyTempLineChart_NSOnMainCanvas() {
        AppXYChart<Number, String> lineChartClone = tempLineChart_NS.copy();
        if (lineChartClone != null) {
            addToMainCanvas(lineChartClone);
            selectedObjectsController.select(lineChartClone);
            tempLineChart_NS.hide();
        }
    }

    public void copyTempLineChart_SNOnMainCanvas() {
        AppXYChart<String, Number> lineChartClone = tempLineChart_SN.copy();
        if (lineChartClone != null) {
            addToMainCanvas(lineChartClone);
            selectedObjectsController.select(lineChartClone);
            tempLineChart_SN.hide();
        }
    }

    public void copyTempLineToMainCanvas() {
        AppLine lineClone = tempLine.copy();
        if (lineClone != null) {
            addToMainCanvas(lineClone);
            selectedObjectsController.select(lineClone);
            tempLine.hide();
        }
    }

    public void copyTempRectangleOnMainCanvas() {
        AppRectangle rectangleClone = tempRectangle.copy();
        if (rectangleClone != null) {
            addToMainCanvas(rectangleClone);
            selectedObjectsController.select(rectangleClone);
            tempRectangle.hide();
        }
    }

    public void copyTempScatterChart_NNOnMainCanvas() {
        AppXYChart<Number, Number> scatterChartClone = tempScatterChart_NN.copy();
        if (scatterChartClone != null) {
            addToMainCanvas(scatterChartClone);
            selectedObjectsController.select(scatterChartClone);
            tempScatterChart_NN.hide();
        }
    }

    public void copyTempScatterChart_NSOnMainCanvas() {
        AppXYChart<Number, String> scatterChartClone = tempScatterChart_NS.copy();
        if (scatterChartClone != null) {
            addToMainCanvas(scatterChartClone);
            selectedObjectsController.select(scatterChartClone);
            tempScatterChart_NS.hide();
        }
    }

    public void copyTempScatterChart_SNOnMainCanvas() {
        AppXYChart<String, Number> scatterChartClone = tempScatterChart_SN.copy();
        if (scatterChartClone != null) {
            addToMainCanvas(scatterChartClone);
            selectedObjectsController.select(scatterChartClone);
            tempScatterChart_SN.hide();
        }
    }

    public void drawTempAreaChart_NN(double currentDragPosX, double currentDragPosY, double dragStartX, double dragStartY, Color fillColor, Color strokeColor, double strokeWidth, List<Double> parsedStrokeDashArray) {
        selectedObjectsController.unselectAll();
        littleAreaChartOnCursor.show(currentDragPosX, currentDragPosY);
        tempAreaChart_NN.draw(dragStartX, dragStartY, currentDragPosX, currentDragPosY);
        tempAreaChart_NN.backgroundStyle.removeAllFills();
        tempAreaChart_NN.backgroundStyle.addFill(0, new AppColor(fillColor,uuid(50)));
        tempAreaChart_NN.backgroundStyle.removeAllStrokes();
        tempAreaChart_NN.backgroundStyle.addStroke(0, new AppColor(strokeColor,uuid(50)));
        tempAreaChart_NN.backgroundStyle.setStrokeWidth(strokeWidth);
        tempAreaChart_NN.setBorderDashArray(parsedStrokeDashArray);
    }

    public void drawTempAreaChart_NS(double currentDragPosX, double currentDragPosY, double dragStartX, double dragStartY, Color fillColor, Color strokeColor, double strokeWidth, List<Double> parsedStrokeDashArray) {
        selectedObjectsController.unselectAll();
        littleAreaChartOnCursor.show(currentDragPosX, currentDragPosY);
        tempAreaChart_NS.draw(dragStartX, dragStartY, currentDragPosX, currentDragPosY);
        tempAreaChart_NS.backgroundStyle.removeAllFills();
        tempAreaChart_NS.backgroundStyle.addFill(0, new AppColor(fillColor,uuid(50)));
        tempAreaChart_NS.backgroundStyle.removeAllStrokes();
        tempAreaChart_NS.backgroundStyle.addStroke(0, new AppColor(strokeColor,uuid(50)));
        tempAreaChart_NS.backgroundStyle.setStrokeWidth(strokeWidth);
        tempAreaChart_NS.setBorderDashArray(parsedStrokeDashArray);
    }

    public void drawTempAreaChart_SN(double currentDragPosX, double currentDragPosY, double dragStartX, double dragStartY, Color fillColor, Color strokeColor, double strokeWidth, List<Double> parsedStrokeDashArray) {
        selectedObjectsController.unselectAll();
        littleAreaChartOnCursor.show(currentDragPosX, currentDragPosY);
        tempAreaChart_SN.draw(dragStartX, dragStartY, currentDragPosX, currentDragPosY);
        tempAreaChart_SN.backgroundStyle.removeAllFills();
        tempAreaChart_SN.backgroundStyle.addFill(0, new AppColor(fillColor,uuid(50)));
        tempAreaChart_SN.backgroundStyle.removeAllStrokes();
        tempAreaChart_SN.backgroundStyle.addStroke(0, new AppColor(strokeColor,uuid(50)));
        tempAreaChart_SN.backgroundStyle.setStrokeWidth(strokeWidth);
        tempAreaChart_SN.setBorderDashArray(parsedStrokeDashArray);
    }

    public void drawTempEllipse(double currentDragPosX, double currentDragPosY, double dragStartX, double dragStartY, Color fillColor, Color strokeColor, double strokeWidth, List<Double> parsedStrokeDashArray) {
        selectedObjectsController.unselectAll();
        littleEllipseOnCursor.show(currentDragPosX, currentDragPosY);
        tempEllipse.draw(dragStartX, dragStartY, currentDragPosX, currentDragPosY);
        tempEllipse.backgroundStyle.removeAllFills();
        tempEllipse.backgroundStyle.addFill(0, new AppColor(fillColor,uuid(50)));
        tempEllipse.backgroundStyle.removeAllStrokes();
        tempEllipse.backgroundStyle.addStroke(0, new AppColor(strokeColor,uuid(50)));
        tempEllipse.backgroundStyle.setStrokeWidth(strokeWidth);
        tempEllipse.setBorderDashArray(parsedStrokeDashArray);
    }

    public void drawTempLine(double currentDragPosX, double currentDragPosY, double dragStartX, double dragStartY, Color fillColor, Color strokeColor, double strokeWidth, List<Double> parsedStrokeDashArray) {
        selectedObjectsController.unselectAll();
        littleLineOnCursor.show(currentDragPosX, currentDragPosY);
        tempLine.draw(dragStartX, dragStartY, currentDragPosX, currentDragPosY);
        tempLine.backgroundStyle.removeAllStrokes();
        tempLine.backgroundStyle.addStroke(0, new AppColor(strokeColor,uuid(50)));
        tempLine.backgroundStyle.setStrokeWidth(strokeWidth);
        tempLine.setBorderDashArray(parsedStrokeDashArray);
    }

    public void drawTempLineChart_NN(double currentDragPosX, double currentDragPosY, double dragStartX, double dragStartY, Color fillColor, Color strokeColor, double strokeWidth, List<Double> parsedStrokeDashArray) {
        selectedObjectsController.unselectAll();
        littleLineChartOnCursor.show(currentDragPosX, currentDragPosY);
        tempLineChart_NN.draw(dragStartX, dragStartY, currentDragPosX, currentDragPosY);
        tempLineChart_NN.backgroundStyle.removeAllFills();
        tempLineChart_NN.backgroundStyle.addFill(0, new AppColor(fillColor,uuid(50)));
        tempLineChart_NN.backgroundStyle.removeAllStrokes();
        tempLineChart_NN.backgroundStyle.addStroke(0, new AppColor(strokeColor,uuid(50)));
        tempLineChart_NN.backgroundStyle.setStrokeWidth(strokeWidth);
        tempLineChart_NN.setBorderDashArray(parsedStrokeDashArray);
    }

    public void drawTempLineChart_NS(double currentDragPosX, double currentDragPosY, double dragStartX, double dragStartY, Color fillColor, Color strokeColor, double strokeWidth, List<Double> parsedStrokeDashArray) {
        selectedObjectsController.unselectAll();
        littleLineChartOnCursor.show(currentDragPosX, currentDragPosY);
        tempLineChart_NS.draw(dragStartX, dragStartY, currentDragPosX, currentDragPosY);
        tempLineChart_NS.backgroundStyle.removeAllFills();
        tempLineChart_NS.backgroundStyle.addFill(0, new AppColor(fillColor,uuid(50)));
        tempLineChart_NS.backgroundStyle.removeAllStrokes();
        tempLineChart_NS.backgroundStyle.addStroke(0, new AppColor(strokeColor,uuid(50)));
        tempLineChart_NS.backgroundStyle.setStrokeWidth(strokeWidth);
        tempLineChart_NS.setBorderDashArray(parsedStrokeDashArray);
    }

    public void drawTempLineChart_SN(double currentDragPosX, double currentDragPosY, double dragStartX, double dragStartY, Color fillColor, Color strokeColor, double strokeWidth, List<Double> parsedStrokeDashArray) {
        selectedObjectsController.unselectAll();
        littleLineChartOnCursor.show(currentDragPosX, currentDragPosY);
        tempLineChart_SN.draw(dragStartX, dragStartY, currentDragPosX, currentDragPosY);
        tempLineChart_SN.backgroundStyle.removeAllFills();
        tempLineChart_SN.backgroundStyle.addFill(0, new AppColor(fillColor,uuid(50)));
        tempLineChart_SN.backgroundStyle.removeAllStrokes();
        tempLineChart_SN.backgroundStyle.addStroke(0, new AppColor(strokeColor,uuid(50)));
        tempLineChart_SN.backgroundStyle.setStrokeWidth(strokeWidth);
        tempLineChart_SN.setBorderDashArray(parsedStrokeDashArray);
    }

    public void drawTempRectangle(double currentDragPosX, double currentDragPosY, double dragStartX, double dragStartY, Color fillColor, Color strokeColor, double strokeWidth, List<Double> parsedStrokeDashArray) {
        selectedObjectsController.unselectAll();
        littleRectangleOnCursor.show(currentDragPosX, currentDragPosY);
        tempRectangle.draw(dragStartX, dragStartY, currentDragPosX, currentDragPosY);
        tempRectangle.backgroundStyle.removeAllFills();
        tempRectangle.backgroundStyle.addFill(0, new AppColor(fillColor,uuid(50)));
        tempRectangle.backgroundStyle.removeAllStrokes();
        tempRectangle.backgroundStyle.addStroke(0, new AppColor(strokeColor,uuid(50)));
        tempRectangle.backgroundStyle.setStrokeWidth(strokeWidth);
        tempRectangle.setBorderDashArray(parsedStrokeDashArray);
    }

    public void drawTempScatterChart_NN(double currentDragPosX, double currentDragPosY, double dragStartX, double dragStartY, Color fillColor, Color strokeColor, double strokeWidth, List<Double> parsedStrokeDashArray) {
        selectedObjectsController.unselectAll();
        littleScatterChartOnCursor.show(currentDragPosX, currentDragPosY);
        tempScatterChart_NN.draw(dragStartX, dragStartY, currentDragPosX, currentDragPosY);
        tempScatterChart_NN.backgroundStyle.removeAllFills();
        tempScatterChart_NN.backgroundStyle.addFill(0, new AppColor(fillColor,uuid(50)));
        tempScatterChart_NN.backgroundStyle.removeAllStrokes();
        tempScatterChart_NN.backgroundStyle.addStroke(0, new AppColor(strokeColor,uuid(50)));
        tempScatterChart_NN.backgroundStyle.setStrokeWidth(strokeWidth);
        tempScatterChart_NN.setBorderDashArray(parsedStrokeDashArray);
    }

    public void drawTempScatterChart_NS(double currentDragPosX, double currentDragPosY, double dragStartX, double dragStartY, Color fillColor, Color strokeColor, double strokeWidth, List<Double> parsedStrokeDashArray) {
        selectedObjectsController.unselectAll();
        littleScatterChartOnCursor.show(currentDragPosX, currentDragPosY);
        tempScatterChart_NS.draw(dragStartX, dragStartY, currentDragPosX, currentDragPosY);
        tempScatterChart_NS.backgroundStyle.removeAllFills();
        tempScatterChart_NS.backgroundStyle.addFill(0, new AppColor(fillColor,uuid(50)));
        tempScatterChart_NS.backgroundStyle.removeAllStrokes();
        tempScatterChart_NS.backgroundStyle.addStroke(0, new AppColor(strokeColor,uuid(50)));
        tempScatterChart_NS.backgroundStyle.setStrokeWidth(strokeWidth);
        tempScatterChart_NS.setBorderDashArray(parsedStrokeDashArray);
    }

    public void drawTempScatterChart_SN(double currentDragPosX, double currentDragPosY, double dragStartX, double dragStartY, Color fillColor, Color strokeColor, double strokeWidth, List<Double> parsedStrokeDashArray) {
        selectedObjectsController.unselectAll();
        littleScatterChartOnCursor.show(currentDragPosX, currentDragPosY);
        tempScatterChart_SN.draw(dragStartX, dragStartY, currentDragPosX, currentDragPosY);
        tempScatterChart_SN.backgroundStyle.removeAllFills();
        tempScatterChart_SN.backgroundStyle.addFill(0, new AppColor(fillColor,uuid(50)));
        tempScatterChart_SN.backgroundStyle.removeAllStrokes();
        tempScatterChart_SN.backgroundStyle.addStroke(0, new AppColor(strokeColor,uuid(50)));
        tempScatterChart_SN.backgroundStyle.setStrokeWidth(strokeWidth);
        tempScatterChart_SN.setBorderDashArray(parsedStrokeDashArray);
    }

    public SelectedObjectsController getSelectedObjectsController() {
        return selectedObjectsController;
    }

    public void pasteCopiedSelectedObjects(double currentCursorPosX, double currentCursorPosY) {

        double finalSelectedObjectsCOMX = selectedObjectsController.centerXProperty().get();
        double finalSelectedObjectsCOMY = selectedObjectsController.centerYProperty().get();

        List<AppRegion> copiedObjects = new ArrayList<>();

        selectedObjectsController.getBuffer().forEach(obj -> {
            AppRegion objCopy;
            if (obj.getType().equals(NodeTypeEnum.Ellipse.getNodeType())) {
                objCopy = ((AppEllipse) obj).copy();
            } else if (obj.getType().equals(NodeTypeEnum.Rectangle.getNodeType())) {
                objCopy = ((AppRectangle) obj).copy();
            } else if (obj.getType().equals(NodeTypeEnum.Line.getNodeType())) {
                objCopy = ((AppLine) obj).copy();
            } else if (obj.getType().equals(NodeTypeEnum.LineChart_NN.getNodeType()) ||
                    obj.getType().equals(NodeTypeEnum.LineChart_NS.getNodeType()) ||
                    obj.getType().equals(NodeTypeEnum.LineChart_SN.getNodeType()) ||
                    obj.getType().equals(NodeTypeEnum.ScatterChart_NN.getNodeType()) ||
                    obj.getType().equals(NodeTypeEnum.ScatterChart_NS.getNodeType()) ||
                    obj.getType().equals(NodeTypeEnum.ScatterChart_SN.getNodeType()) ||
                    obj.getType().equals(NodeTypeEnum.AreaChart_NN.getNodeType()) ||
                    obj.getType().equals(NodeTypeEnum.AreaChart_NS.getNodeType()) ||
                    obj.getType().equals(NodeTypeEnum.AreaChart_SN.getNodeType())) {
                objCopy = obj.copy();
            } else {
                throw new AppException(AppExceptionEnum.UnexpectedError);
            }

            if (objCopy != null) {
                objCopy.affineTransform.prependTranslation(currentCursorPosX - finalSelectedObjectsCOMX, currentCursorPosY - finalSelectedObjectsCOMY);
            }
            addToMainCanvas(objCopy);
            copiedObjects.add(objCopy);
        });
        copiedObjects.forEach(obj -> selectedObjectsController.select(obj));
    }

    public void pasteCutSelectedObjects(double currentCursorPosX, double currentCursorPosY) {
        selectedObjectsController.buffer.forEach(obj -> obj.affineTransform.prependTranslation(currentCursorPosX - selectedObjectsController.centerXProperty().get(), currentCursorPosY - selectedObjectsController.centerYProperty().get()));
    }

    public void removeSelectedObjectsFromMainCanvas() {
        selectedObjectsController.getBuffer().forEach(obj -> mainCanvasChildren.remove(obj.getRegion()));
        selectedObjectsController.getBuffer().forEach(obj -> mainCanvasChildren.remove(obj.border));
        selectedObjectsController.getBuffer().forEach(obj -> validObjects.remove(obj));
        numOfValidObjects -= selectedObjectsController.getBuffer().size();
        selectedObjectsController.unselectAll();
    }

    public void selectAll() {
        validObjects.forEach(obj -> selectedObjectsController.select(obj));
    }
}