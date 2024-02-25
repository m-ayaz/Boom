package com.boom.controllers;

//import com.boom.appcharts.number_number.AppAreaChart_NumberNumber;
//import com.boom.appcharts.number_number.AppLineChart_NumberNumber;
//import com.boom.appcharts.number_number.AppScatterChart_NumberNumber;
//import com.boom.appcharts.number_string.AppAreaChart_NumberString;
//import com.boom.appcharts.number_string.AppLineChart_NumberString;
//import com.boom.appcharts.number_string.AppScatterChart_NumberString;
//import com.boom.appcharts.string_number.AppAreaChart_StringNumber;
//import com.boom.appcharts.string_number.AppLineChart_StringNumber;
//import com.boom.appcharts.string_number.AppScatterChart_StringNumber;
import com.boom.appcharts.AppAxisChartWrapper;
import com.boom.appshapes.*;
import com.boom.exceptions.AppException;
import com.boom.icons.RotationIcon;
import com.boom.icons.ScalingIcon;
import com.boom.indicators.*;
import com.boom.structures.abstracts.AppNode;
import com.boom.structures.enums.AppExceptionEnum;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

import static com.boom.tools.Tools.print;

public class MainCanvasItemsHandler {

    ObservableList<Node> mainCanvasChildren;
    DynamicDragRectangle dynamicDragRectangle;
    AppEllipse tempEllipse;
    AppPolyline tempPolyline;
    AppCubicCurve tempCubicCurve;
    AppPolygon tempPolygon;
    AppArc tempArc;
    AppRectangle tempRectangle;
    AppLine tempLine;
    AppQuadCurve tempQuadCurve;

    AppAxisChartWrapper tempAppAxisChartWrapper;
//    AppLineChart_NumberNumber tempLineChart_NN;
//    AppLineChart_NumberString tempLineChart_NS;
//    AppLineChart_StringNumber tempLineChart_SN;
//    //
//    AppAreaChart_NumberNumber tempAreaChart_NN;
//    AppAreaChart_NumberString tempAreaChart_NS;
//    AppAreaChart_StringNumber tempAreaChart_SN;
//    //
//////    AppBarChart_NumberString tempBarChart_NS = new AppBarChart_NumberString(0,0);
//////    AppBarChart_StringNumber tempBarChart_SN = new AppBarChart_StringNumber(0,0);
////
//    AppScatterChart_NumberNumber tempScatterChart_NN;
//    AppScatterChart_NumberString tempScatterChart_NS;
//    AppScatterChart_StringNumber tempScatterChart_SN;

    //    AppText tempText = new AppText(0, 0, "");
    LittleLineChartOnCursor littleLineChartOnCursor;
        LittleBarChartOnCursor littleBarChartOnCursor;
    LittleScatterChartOnCursor littleScatterChartOnCursor;
    LittleAreaChartOnCursor littleAreaChartOnCursor;
    LittleArcOnCursor littleArcOnCursor;
    LittleEllipseOnCursor littleEllipseOnCursor;
    LittleAppAxisChartWrapperOnCursor littleAppAxisChartWrapperOnCursor;
    LittlePolylineOnCursor littlePolylineOnCursor;
    LittleCubicCurveOnCursor littleCubicCurveOnCursor;
    LittlePolygonOnCursor littlePolygonOnCursor;
    LittleRectangleOnCursor littleRectangleOnCursor;
    LittleLineOnCursor littleLineOnCursor;
    LittleQuadCurveOnCursor littleQuadCurveOnCursor;
    //    LittleTextOnCursor littleTextOnCursor = new LittleTextOnCursor();
    SelectedObjectsController selectedObjectsController;
    List<AppNode> validObjects;

    public MainCanvasItemsHandler(ObservableList<Node> mainCanvasChildren, List<AppNode> validObjects, Line rotationHandle, RotationIcon rotationIcon, List<ScalingIcon> scalingIcons, Circle rotationFixedPoint, Circle scalingFixedPoint, DynamicDragRectangle dynamicDragRectangle, AppEllipse tempEllipse,AppPolyline tempPolyline,AppCubicCurve tempCubicCurve,AppQuadCurve tempQuadCurve,AppPolygon tempPolygon,AppArc tempArc, AppRectangle tempRectangle, AppLine tempLine,
AppAxisChartWrapper tempAppAxisChartWrapper,
//                                  AppLineChart_NumberNumber tempLineChart_NN, AppLineChart_NumberString tempLineChart_NS, AppLineChart_StringNumber tempLineChart_SN, AppAreaChart_NumberNumber tempAreaChart_NN, AppAreaChart_NumberString tempAreaChart_NS, AppAreaChart_StringNumber tempAreaChart_SN, AppScatterChart_NumberNumber tempScatterChart_NN, AppScatterChart_NumberString tempScatterChart_NS, AppScatterChart_StringNumber tempScatterChart_SN,
                                  LittleLineChartOnCursor littleLineChartOnCursor,
                                  LittleBarChartOnCursor littleBarChartOnCursor,
                                  LittleScatterChartOnCursor littleScatterChartOnCursor, LittleAreaChartOnCursor littleAreaChartOnCursor, LittleEllipseOnCursor littleEllipseOnCursor,LittleAppAxisChartWrapperOnCursor littleAppAxisChartWrapperOnCursor,LittlePolylineOnCursor littlePolylineOnCursor,LittleCubicCurveOnCursor littleCubicCurveOnCursor,LittleQuadCurveOnCursor littleQuadCurveOnCursor,LittlePolygonOnCursor littlePolygonOnCursor,LittleArcOnCursor littleArcOnCursor, LittleRectangleOnCursor littleRectangleOnCursor, LittleLineOnCursor littleLineOnCursor,SelectedObjectsController selectedObjectsController) {

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
        this.tempArc=tempArc;
        this.tempPolygon=tempPolygon;
        this.tempRectangle = tempRectangle;
        this.tempLine = tempLine;
        this.tempQuadCurve=tempQuadCurve;
        this.tempCubicCurve=tempCubicCurve;
        this.tempAppAxisChartWrapper=tempAppAxisChartWrapper;
//        this.tempLineChart_NN = tempLineChart_NN;
//        this.tempLineChart_NS = tempLineChart_NS;
//        this.tempLineChart_SN = tempLineChart_SN;
//        this.tempAreaChart_NN = tempAreaChart_NN;
//        this.tempAreaChart_NS = tempAreaChart_NS;
//        this.tempAreaChart_SN = tempAreaChart_SN;
//        this.tempScatterChart_NN = tempScatterChart_NN;
//        this.tempScatterChart_NS = tempScatterChart_NS;
//        this.tempScatterChart_SN = tempScatterChart_SN;
        this.littleLineChartOnCursor = littleLineChartOnCursor;
        this.littleBarChartOnCursor=littleBarChartOnCursor;
        this.littleScatterChartOnCursor = littleScatterChartOnCursor;
        this.littleAreaChartOnCursor = littleAreaChartOnCursor;
        this.littleEllipseOnCursor = littleEllipseOnCursor;
        this.littleRectangleOnCursor = littleRectangleOnCursor;
        this.littleLineOnCursor = littleLineOnCursor;
        this.littleArcOnCursor=littleArcOnCursor;
        this.littlePolygonOnCursor=littlePolygonOnCursor;
        this.littleQuadCurveOnCursor=littleQuadCurveOnCursor;
        this.littleCubicCurveOnCursor=littleCubicCurveOnCursor;
this.littleAppAxisChartWrapperOnCursor=littleAppAxisChartWrapperOnCursor;



        this.selectedObjectsController=selectedObjectsController;

//        print("selectedObjectsController = "+(this.selectedObjectsController.buffer));



//        print(mainCanvasChildren);
        mainCanvasChildren.addAll(
                tempRectangle.styleableNode,
                tempEllipse.styleableNode,
                tempPolyline.styleableNode,
                tempPolygon.styleableNode,
                tempArc.styleableNode,
                tempLine.styleableNode,
                tempQuadCurve.styleableNode,
                tempCubicCurve.styleableNode,
                tempAppAxisChartWrapper.styleableNode,
//                tempLineChart_NN.styleableNode,
//                tempLineChart_SN.styleableNode,
//                tempLineChart_NS.styleableNode,
//                tempScatterChart_NN.styleableNode,
//                tempScatterChart_SN.styleableNode,
//                tempScatterChart_NS.styleableNode,
////                tempBarChart_SN.node,
////                tempBarChart_NS.node,
//                tempAreaChart_NN.styleableNode,
//                tempAreaChart_SN.styleableNode,
//                tempAreaChart_NS.styleableNode,
                littleEllipseOnCursor,
                littlePolylineOnCursor,
                littleCubicCurveOnCursor,
                littleQuadCurveOnCursor,
                littlePolygonOnCursor,
                littleArcOnCursor,
                littleRectangleOnCursor,
                littleLineOnCursor,
                littleLineChartOnCursor,
                littleAreaChartOnCursor,
                littleBarChartOnCursor,
                littleScatterChartOnCursor,
                littleAppAxisChartWrapperOnCursor,
                dynamicDragRectangle,
                rotationHandle,
                rotationFixedPoint,
                scalingFixedPoint,
                rotationIcon);
        mainCanvasChildren.addAll(scalingIcons);

        this.validObjects = validObjects;

    }

    public void copyToMainCanvas(AppNode tempAppNode) {
        AppNode appNodeClone = tempAppNode.copy();
        if (appNodeClone != null) {
            addToMainCanvas(appNodeClone);
//            print("appNodeClone = "+appNodeClone);
//            print("selectedObjectsController.buffer = "+selectedObjectsController.bufferSize.get());
            selectedObjectsController.select(appNodeClone);
            tempAppNode.hide();
        }
    }

//    public void copyToMainCanvas(AppNode tempAppNode) {
//        copyToMainCanvas(tempAppNode, validObjects, mainCanvasChildren, selectedObjectsController);
//    }

    public void addToMainCanvas(AppNode appNode) {
        mainCanvasChildren.add(validObjects.size(), appNode.styleableNode);
        mainCanvasChildren.add(validObjects.size() * 2 + 1, appNode.border);
        validObjects.add(appNode);
    }

//    public void addToMainCanvas(AppNode appNode) {
//        addToMainCanvas(appNode, validObjects, mainCanvasChildren);
//    }

    public SelectedObjectsController getSelectedObjectsController() {
        return selectedObjectsController;
    }

    public void pasteCopiedSelectedObjects(double currentCursorPosX, double currentCursorPosY) {
        List<AppNode> copiedObjects = new ArrayList<>();
//        Clipboard clipboard;
//        clipboard.
        selectedObjectsController.getBuffer().forEach(obj -> {
            AppNode objCopy=obj.copy();
            if (objCopy != null) {
                objCopy.affineTransform.prependTranslation(currentCursorPosX - selectedObjectsController.centerXProperty().get(), currentCursorPosY - selectedObjectsController.centerYProperty().get());
            }else{
                throw new AppException(AppExceptionEnum.UnexpectedError);
            }
            print("________________________________________");
            print(currentCursorPosX+","+ currentCursorPosY);
//            print(cu);
//            print(( selectedObjectsController.centerXProperty().get()-currentCursorPosX)+",,,,,,,,,,,,,,,"+ (selectedObjectsController.centerYProperty().get()-currentCursorPosY));
            addToMainCanvas(objCopy);
            copiedObjects.add(objCopy);
        });
        selectedObjectsController.unselectAll();
        copiedObjects.forEach(obj -> selectedObjectsController.select(obj));
    }

    public void pasteCutSelectedObjects(double currentCursorPosX, double currentCursorPosY) {
        selectedObjectsController.buffer.forEach(obj -> obj.affineTransform.prependTranslation(currentCursorPosX - selectedObjectsController.centerXProperty().get(), currentCursorPosY - selectedObjectsController.centerYProperty().get()));
    }

    public void removeSelectedObjectsFromMainCanvas() {
        selectedObjectsController.getBuffer().forEach(obj -> mainCanvasChildren.remove(obj.styleableNode));
        selectedObjectsController.getBuffer().forEach(obj -> mainCanvasChildren.remove(obj.border));
        selectedObjectsController.getBuffer().forEach(obj -> validObjects.remove(obj));
        selectedObjectsController.unselectAll();
    }

    public void selectAll() {
        validObjects.forEach(obj -> selectedObjectsController.select(obj));
    }

//    public void drawTempAppNode(AppNode tempAppNode, double currentDragPosX, double currentDragPosY, double dragStartX, double dragStartY, Color fillColor, Color strokeColor, double strokeWidth, List<Double> parsedStrokeDashArray) {
//        selectedObjectsController.unselectAll();
//        tempAppNode.draw(dragStartX, dragStartY, currentDragPosX, currentDragPosY);
//        tempAppNode.backgroundStyle.removeAllFills();
//        tempAppNode.backgroundStyle.removeAllStrokes();
//        tempAppNode.backgroundStyle.addFill(0, new AppColor(fillColor, uuid(50)));
//        tempAppNode.backgroundStyle.addStroke(0, new AppColor(strokeColor, uuid(50)));
//        tempAppNode.backgroundStyle.setStrokeWidth(strokeWidth);
////        tempAppNode.setBorderDashArray(parsedStrokeDashArray);
//    }
}















