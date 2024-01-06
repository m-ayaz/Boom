package com.boom.controllers.eventhandlers.mousehandler;

import com.boom.appcharts.number_number.AppAreaChart_NumberNumber;
import com.boom.appcharts.number_number.AppLineChart_NumberNumber;
import com.boom.appcharts.number_number.AppScatterChart_NumberNumber;
import com.boom.appcharts.number_string.AppAreaChart_NumberString;
import com.boom.appcharts.number_string.AppLineChart_NumberString;
import com.boom.appcharts.number_string.AppScatterChart_NumberString;
import com.boom.appcharts.string_number.AppAreaChart_StringNumber;
import com.boom.appcharts.string_number.AppLineChart_StringNumber;
import com.boom.appcharts.string_number.AppScatterChart_StringNumber;
import com.boom.appshapes.*;
import com.boom.controllers.DynamicDragRectangle;
import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.exceptions.AppException;
import com.boom.icons.RotationIcon;
import com.boom.icons.ScalingIcon;
import com.boom.indicators.*;
import com.boom.structures.abstracts.AppNode;
import com.boom.structures.enums.AppExceptionEnum;
import com.boom.structures.enums.AppNodeTypeEnum;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.List;

/**
 * {link #setOnMouseMoved()} )} )} )}
 * This event is invoked when the mouse cursor is moving over the main canvas without dragging.
 * 3- The cursor position should be permanently tracked and shown in a small label at bottom right of the canvas.
 * {link #setOnMouseReleased(Pane, StringProperty, DynamicDragRectangle, MainCanvasItemsHandler)}
 * This event is invoked when the user has finished dragging and pressing down the cursor (the onMousePressed event has already been invoked.).
 * 1- todo If the user had chosen to draw shapes or charts (not pens), add a clone of the item to the mainCanvas.
 * 2- todo If the user had chosen the the dynamic drag rectangle and there where some selected shapes, do nothing! (The proper behavior has been made through the onMousePressed and onMouseDragged methods.)
 * 3- todo If the user had chosen the the dynamic drag rectangle and there where no selected shapes, select all the objects included by the drag.
 * {link #setOnMouseDragged(Pane, Label, StringProperty, DynamicDragRectangle, MainCanvasItemsHandler, LittleLineChartOnCursor, LittleScatterChartOnCursor, LittleAreaChartOnCursor, LittleEllipseOnCursor, LittleRectangleOnCursor, LittleLineOnCursor, DoubleProperty, DoubleProperty, DoubleProperty, DoubleProperty, DoubleProperty, DoubleProperty, List)} )}
 * This event is invoked when the user is dragging the cursor (the onMousePressed event has already been invoked.).
 * After the user has pressed down the mouse key, the following cases emerge:
 * 1- todo If any of the items (shapes or charts not pens) has been chosen for drawing, dynamically draw the item.
 * 2- todo If the user has chosen the dynamic drag rectangle and there are any selected shapes, dynamically move them all on drag.
 * 3- todo If the user has chosen the dynamic drag rectangle and there are no selected shapes, draw the dynamic drag rectangle.
 * 4- todo If any of the pen items has been chosen for drawing, add the pen trace to the canvas.
 * {link #setOnMousePressed(Pane, StringProperty, MainCanvasItemsHandler, DoubleProperty, DoubleProperty, DoubleProperty, DoubleProperty, List, RotationIcon, List)}
 * This event is invoked when the user has pressed down a mouse key (whether left or right).
 * todo At the time, I do not know whether it is possible to discriminate between the left and right keys. I need to know!
 * 0- At either case, record the mouse press position within variables.
 * 1- todo If the user has chosen to draw an item (shape, plot, etc.), do nothing! (The rest is handled by mainCanvasOnMouseDragged.)
 * 2- todo If the user has chosen the dynamic drag rectangle, toggle selection of any object containing the press position (i.e. select the unselected and unselect the selected) and unselect all other objects nonetheless (i.e. those not containing the press position).
 */

public class MainCanvasMouseHandler extends AppMouseEventHandler {

    ObservableList<Node> mainCanvasChildren;
    List<AppNode> validObjects;
    SelectedObjectsController selectedObjectsController;
    Line rotationHandle;
    Circle rotationFixedPoint;
    Circle scalingFixedPoint;
    Label cursorPositionLabel;
    StringProperty tempObjectName;
    DynamicDragRectangle dynamicDragRectangle;
    AppEllipse tempEllipse;
    AppPolyline tempPolyline;
    AppCubicCurve tempCubicCurve;
    AppArc tempArc;
    AppRectangle tempRectangle;
    AppPolygon tempPolygon;
    AppLine tempLine;
    AppQuadCurve tempQuadCurve;
    AppLineChart_NumberNumber tempLineChart_NN;
    AppLineChart_NumberString tempLineChart_NS;
    AppLineChart_StringNumber tempLineChart_SN;
    AppAreaChart_NumberNumber tempAreaChart_NN;
    AppAreaChart_NumberString tempAreaChart_NS;
    AppAreaChart_StringNumber tempAreaChart_SN;
    ////    AppBarChart_NumberString tempBarChart_NS = new AppBarChart_NumberString(0,0);
////    AppBarChart_StringNumber tempBarChart_SN = new AppBarChart_StringNumber(0,0);
    AppScatterChart_NumberNumber tempScatterChart_NN;
    AppScatterChart_NumberString tempScatterChart_NS;
    AppScatterChart_StringNumber tempScatterChart_SN;
    LittleLineChartOnCursor littleLineChartOnCursor;
    LittleBarChartOnCursor littleBarChartOnCursor;
    LittleScatterChartOnCursor littleScatterChartOnCursor;
    LittleAreaChartOnCursor littleAreaChartOnCursor;
    LittleEllipseOnCursor littleEllipseOnCursor;
    LittleCubicCurveOnCursor littleCubicCurveOnCursor;
    LittleArcOnCursor littleArcOnCursor;
    LittleRectangleOnCursor littleRectangleOnCursor;
    LittleLineOnCursor littleLineOnCursor;
    LittleQuadCurveOnCursor littleQuadCurveOnCursor;
    LittlePolygonOnCursor littlePolygonOnCursor;
    LittlePolylineOnCursor littlePolylineOnCursor;
    MainCanvasItemsHandler mainCanvasItemsHandler;
    List<Double> parsedStrokeDashArray;
    List<ScalingIcon> scalingIcons;
    RotationIcon rotationIcon;


    public MainCanvasMouseHandler(
            ObservableList<Node> mainCanvasChildren,
            List<AppNode> validObjects,
            SelectedObjectsController selectedObjectsController,
            Line rotationHandle,
            Circle rotationFixedPoint,
            Circle scalingFixedPoint,
            Label cursorPositionLabel,
            StringProperty tempObjectName,
            DynamicDragRectangle dynamicDragRectangle,
            AppEllipse tempEllipse,
            AppPolyline tempPolyline,
            AppCubicCurve tempCubicCurve,
            AppQuadCurve tempQuadCurve,
            AppPolygon tempPolygon,
            AppArc tempArc,
            AppRectangle tempRectangle,
            AppLine tempLine,
            AppLineChart_NumberNumber tempLineChart_NN,
            AppLineChart_NumberString tempLineChart_NS,
            AppLineChart_StringNumber tempLineChart_SN,
            AppAreaChart_NumberNumber tempAreaChart_NN,
            AppAreaChart_NumberString tempAreaChart_NS,
            AppAreaChart_StringNumber tempAreaChart_SN,
////    AppBarChart_NumberString tempBarChart_NS ,
////    AppBarChart_StringNumber tempBarChart_SN ,
            AppScatterChart_NumberNumber tempScatterChart_NN,
            AppScatterChart_NumberString tempScatterChart_NS,
            AppScatterChart_StringNumber tempScatterChart_SN,
            LittleLineChartOnCursor littleLineChartOnCursor,
            LittleBarChartOnCursor littleBarChartOnCursor,
            LittleScatterChartOnCursor littleScatterChartOnCursor,
            LittleAreaChartOnCursor littleAreaChartOnCursor,
            LittleEllipseOnCursor littleEllipseOnCursor,
            LittlePolylineOnCursor littlePolylineOnCursor,
            LittleCubicCurveOnCursor littleCubicCurveOnCursor,
            LittleQuadCurveOnCursor littleQuadCurveOnCursor,
            LittlePolygonOnCursor littlePolygonOnCursor,
            LittleArcOnCursor littleArcOnCursor,
            LittleRectangleOnCursor littleRectangleOnCursor,
            LittleLineOnCursor littleLineOnCursor,
            MainCanvasItemsHandler mainCanvasItemsHandler,
            //                                  ColorPicker fillSolidColorPicker,
//                                  ColorPicker strokeSolidColorPicker,
//                                  TextField strokeWidthInput,
            List<Double> parsedStrokeDashArray,
            List<ScalingIcon> scalingIcons,
            RotationIcon rotationIcon


    ) {

        this.mainCanvasChildren = mainCanvasChildren;
        this.validObjects = validObjects;
        this.selectedObjectsController = selectedObjectsController;
        this.rotationHandle = rotationHandle;
        this.rotationFixedPoint = rotationFixedPoint;
        this.scalingFixedPoint = scalingFixedPoint;
        this.cursorPositionLabel = cursorPositionLabel;
        this.tempObjectName = tempObjectName;
        this.dynamicDragRectangle = dynamicDragRectangle;
        this.tempEllipse = tempEllipse;
        this.tempPolyline = tempPolyline;
        this.tempCubicCurve = tempCubicCurve;
        this.tempQuadCurve = tempQuadCurve;
        this.tempPolygon = tempPolygon;
        this.tempArc = tempArc;
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
        this.littleBarChartOnCursor = littleBarChartOnCursor;
        this.littleScatterChartOnCursor = littleScatterChartOnCursor;
        this.littleAreaChartOnCursor = littleAreaChartOnCursor;
        this.littleEllipseOnCursor = littleEllipseOnCursor;
        this.littlePolylineOnCursor = littlePolylineOnCursor;
        this.littleCubicCurveOnCursor = littleCubicCurveOnCursor;
        this.littleQuadCurveOnCursor = littleQuadCurveOnCursor;
        this.littlePolygonOnCursor = littlePolygonOnCursor;
        this.littleArcOnCursor = littleArcOnCursor;
        this.littleRectangleOnCursor = littleRectangleOnCursor;
        this.littleLineOnCursor = littleLineOnCursor;
        this.mainCanvasItemsHandler = mainCanvasItemsHandler;
        this.parsedStrokeDashArray = parsedStrokeDashArray;
        this.scalingIcons = scalingIcons;
        this.rotationIcon = rotationIcon;


    }

    @Override
    protected void handleLast(MouseEvent mouseEvent) {

        if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
            littleEllipseOnCursor.hide();
            littlePolylineOnCursor.hide();
            littleCubicCurveOnCursor.hide();
            littleQuadCurveOnCursor.hide();
            littlePolygonOnCursor.hide();
            littleArcOnCursor.hide();
            littleRectangleOnCursor.hide();
            littleLineOnCursor.hide();
            littleLineChartOnCursor.hide();
            littleBarChartOnCursor.hide();
            littleScatterChartOnCursor.hide();
            littleAreaChartOnCursor.hide();
            cursorPositionLabel.setText("Cursor out!");
        }

        if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            dynamicDragRectangle.reset();
        }

        if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {

            if (tempObjectName.get().equals(AppNodeTypeEnum.DynamicDragRectangle.getNodeType()) &&
                    !rotationIcon.contains(pressX, pressY) &&
                    !scalingIcons.get(0).contains(pressX, pressY) &&
                    !scalingIcons.get(1).contains(pressX, pressY) &&
                    !scalingIcons.get(2).contains(pressX, pressY) &&
                    !scalingIcons.get(3).contains(pressX, pressY) &&
                    !scalingIcons.get(4).contains(pressX, pressY) &&
                    !scalingIcons.get(5).contains(pressX, pressY) &&
                    !scalingIcons.get(6).contains(pressX, pressY) &&
                    !scalingIcons.get(7).contains(pressX, pressY)

            ) {

                /*
                (a) ctrl button is up
                1- Fetch the first shape index of mainCanvas that contains the press position. If no such shape was found, set the index to -1 (the code enumerates the shape from the end of the mainCanvas.getChildren(), i.e. from the highest layer.).
                2- If the user pressed mouse on no shape, unselect all, otherwise:
                    2a- If the shape had not been selected, unselect all and select the corresponding shape.
                    2b- If the shape had been selected, do nothing.
                (b) ctrl button is down
                1- Fetch the first shape index of mainCanvas that contains the press position. If no such shape was found, set the index to -1 (the code enumerates the shape from the end of the mainCanvas.getChildren(), i.e. from the highest layer.).
                2- If the user had pressed on a shape, toggle its selection (i.e. select if unselected, unselect if selected). Otherwise, do nothing.
                 */

                AppNode firstAppNode = null;
                for (int i = validObjects.size() - 1; i >= 0; i--) {
                    AppNode appNode = validObjects.get(i);
                    if (appNode.contains(pressX, pressY)) {
                        firstAppNode = appNode;
                        break;
                    }
                }
                if (!mouseEvent.isControlDown()) {
                    if (firstAppNode == null) {
                        mainCanvasItemsHandler.getSelectedObjectsController().unselectAll();
                    } else {
                        if (!mainCanvasItemsHandler.getSelectedObjectsController().isSelected(firstAppNode)) {
                            mainCanvasItemsHandler.getSelectedObjectsController().unselectAll();
                            mainCanvasItemsHandler.getSelectedObjectsController().select(firstAppNode);
                        }
                    }
                } else if (firstAppNode != null) {
                    mainCanvasItemsHandler.getSelectedObjectsController().reverseSelection(firstAppNode);
                }
            }
        }

        if (tempObjectName.get() == null) {
            return;
        }

        cursorPositionLabel.setText(x + "," + y);

        if (tempObjectName.get().equals(AppNodeTypeEnum.DynamicDragRectangle.getNodeType())) {
            dynamicDragRectangle.reset();
        } else if (tempObjectName.get().equals(AppNodeTypeEnum.LineChart_NN.getNodeType())) {
            littleLineChartOnCursor.show(x, y);
            tempLineChart_NN.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
        } else if (tempObjectName.get().equals(AppNodeTypeEnum.LineChart_NS.getNodeType())) {
            littleLineChartOnCursor.show(x, y);
            tempLineChart_NS.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
        } else if (tempObjectName.get().equals(AppNodeTypeEnum.LineChart_SN.getNodeType())) {
            littleLineChartOnCursor.show(x, y);
            tempLineChart_SN.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
        } else if (tempObjectName.get().equals(AppNodeTypeEnum.ScatterChart_NN.getNodeType())) {
            littleScatterChartOnCursor.show(x, y);
            tempScatterChart_NN.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
        } else if (tempObjectName.get().equals(AppNodeTypeEnum.ScatterChart_NS.getNodeType())) {
            littleScatterChartOnCursor.show(x, y);
            tempScatterChart_NS.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
        } else if (tempObjectName.get().equals(AppNodeTypeEnum.ScatterChart_SN.getNodeType())) {
            littleScatterChartOnCursor.show(x, y);
            tempScatterChart_SN.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
        } else if (tempObjectName.get().equals(AppNodeTypeEnum.AreaChart_NN.getNodeType())) {
            littleAreaChartOnCursor.show(x, y);
            tempAreaChart_NN.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
        } else if (tempObjectName.get().equals(AppNodeTypeEnum.AreaChart_NS.getNodeType())) {
            littleAreaChartOnCursor.show(x, y);
            tempAreaChart_NS.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
        } else if (tempObjectName.get().equals(AppNodeTypeEnum.AreaChart_SN.getNodeType())) {
            littleAreaChartOnCursor.show(x, y);
            tempAreaChart_SN.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
        } else {
            switch (tempObjectName.get()) {
                case "Rectangle" -> {
                    littleRectangleOnCursor.show(x, y);
                    tempRectangle.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
                }
                case "Ellipse" -> {
                    littleEllipseOnCursor.show(x, y);
                    tempEllipse.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
                }
                case "Line" -> {
                    littleLineOnCursor.show(x, y);
                    tempLine.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
                }
                case "QuadCurve" -> {
                    littleQuadCurveOnCursor.show(x, y);
                    tempQuadCurve.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
                }
                case "CubicCurve" -> {
                    littleCubicCurveOnCursor.show(x, y);
                    tempCubicCurve.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
                }
                case "Polygon" -> {
                    littlePolygonOnCursor.show(x, y);
                    tempPolygon.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
                }
                case "Polyline" -> {
                    littlePolylineOnCursor.show(x, y);
                    tempPolyline.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
                }
                case "Arc" -> {
                    littleArcOnCursor.show(x, y);
                    tempArc.configureOnMouseEvent(mouseEvent, mainCanvasItemsHandler, selectedObjectsController, moveX, moveY, dragX, dragY, pressX, pressY, releaseX, releaseY, clickX, clickY, x, y);
                }
                default -> throw new AppException(AppExceptionEnum.AppNodeNotRegistered);
            }
        }


    }

}