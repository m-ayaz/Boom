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
import com.boom.appshapes.AppArc;
import com.boom.appshapes.AppEllipse;
import com.boom.appshapes.AppLine;
import com.boom.appshapes.AppRectangle;
import com.boom.controllers.DynamicDragRectangle;
import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.exceptions.AppException;
import com.boom.icons.RotationIcon;
import com.boom.icons.ScalingIcon;
import com.boom.indicators.*;
import com.boom.structures.abstracts.AppNode;
import com.boom.structures.enums.AppExceptionEnum;
import com.boom.structures.enums.NodeTypeEnum;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
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

public class MainCanvasMouseHandler implements EventHandler<MouseEvent> {

    ObservableList<Node> mainCanvasChildren;
    List<AppNode> validObjects;
    SelectedObjectsController selectedObjectsController;
    Line rotationHandle;
    Circle rotationFixedPoint;
    Circle scalingFixedPoint;
    Pane mainCanvas;
    Label cursorPositionLabel;
    StringProperty tempObjectName;
    DynamicDragRectangle dynamicDragRectangle;
    AppEllipse tempEllipse;
    AppArc tempArc;
    AppRectangle tempRectangle;
    AppLine tempLine;
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
    LittleArcOnCursor littleArcOnCursor;
    LittleRectangleOnCursor littleRectangleOnCursor;
    LittleLineOnCursor littleLineOnCursor;
    MainCanvasItemsHandler mainCanvasItemsHandler;
    //    DoubleProperty currentPosX;
//    DoubleProperty currentPosY;
//    DoubleProperty previousPosX;
//    DoubleProperty previousPosY;
//    DoubleProperty dragStartPosX;
//    DoubleProperty dragStartPosY;
    //                                  ColorPicker fillSolidColorPicker,
//                                  ColorPicker strokeSolidColorPicker,
//                                  TextField strokeWidthInput,
    List<Double> parsedStrokeDashArray;
    List<ScalingIcon> scalingIcons;
    RotationIcon rotationIcon;


    DoubleProperty moveX = new SimpleDoubleProperty();
    DoubleProperty moveY = new SimpleDoubleProperty();
    DoubleProperty dragX = new SimpleDoubleProperty();
    DoubleProperty dragY = new SimpleDoubleProperty();
    DoubleProperty pressX = new SimpleDoubleProperty();
    DoubleProperty pressY = new SimpleDoubleProperty();
    DoubleProperty releaseX = new SimpleDoubleProperty();
    DoubleProperty releaseY = new SimpleDoubleProperty();
    DoubleProperty clickX = new SimpleDoubleProperty();
    DoubleProperty clickY = new SimpleDoubleProperty();
    DoubleProperty x = new SimpleDoubleProperty();
    DoubleProperty y = new SimpleDoubleProperty();

    int drawingStage;


    //
////    AppBarChart_NumberString tempBarChart_NS = new AppBarChart_NumberString(0,0);
////    AppBarChart_StringNumber tempBarChart_SN = new AppBarChart_StringNumber(0,0);
//
    //    AppText tempText = new AppText(0, 0, "");
    //    LittleBarChartOnCursor littleBarChartOnCursor;
    //    LittleTextOnCursor littleTextOnCursor = new LittleTextOnCursor();


    public MainCanvasMouseHandler(
            ObservableList<Node> mainCanvasChildren,
            List<AppNode> validObjects,
            SelectedObjectsController selectedObjectsController,
            Line rotationHandle,
            Circle rotationFixedPoint,
            Circle scalingFixedPoint,
            Pane mainCanvas,
            Label cursorPositionLabel,
            StringProperty tempObjectName,
            DynamicDragRectangle dynamicDragRectangle,
            AppEllipse tempEllipse,
            AppArc tempArc,
            AppRectangle tempRectangle,
            AppLine tempLine,
            AppLineChart_NumberNumber tempLineChart_NN,
            AppLineChart_NumberString tempLineChart_NS,
            AppLineChart_StringNumber tempLineChart_SN,
            AppAreaChart_NumberNumber tempAreaChart_NN,
            AppAreaChart_NumberString tempAreaChart_NS,
            AppAreaChart_StringNumber tempAreaChart_SN,
////    AppBarChart_NumberString tempBarChart_NS = new AppBarChart_NumberString(0,0);
////    AppBarChart_StringNumber tempBarChart_SN = new AppBarChart_StringNumber(0,0);
            AppScatterChart_NumberNumber tempScatterChart_NN,
            AppScatterChart_NumberString tempScatterChart_NS,
            AppScatterChart_StringNumber tempScatterChart_SN,
            LittleLineChartOnCursor littleLineChartOnCursor,
            LittleBarChartOnCursor littleBarChartOnCursor,
            LittleScatterChartOnCursor littleScatterChartOnCursor,
            LittleAreaChartOnCursor littleAreaChartOnCursor,
            LittleEllipseOnCursor littleEllipseOnCursor,
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
        this.mainCanvas = mainCanvas;
        this.cursorPositionLabel = cursorPositionLabel;
        this.tempObjectName = tempObjectName;
        this.dynamicDragRectangle = dynamicDragRectangle;
        this.tempEllipse = tempEllipse;
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
        this.littleArcOnCursor = littleArcOnCursor;
        this.littleRectangleOnCursor = littleRectangleOnCursor;
        this.littleLineOnCursor = littleLineOnCursor;
        this.mainCanvasItemsHandler = mainCanvasItemsHandler;
        this.parsedStrokeDashArray = parsedStrokeDashArray;
        this.scalingIcons = scalingIcons;
        this.rotationIcon = rotationIcon;


    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        x.set(mouseEvent.getX());
        y.set(mouseEvent.getY());
        if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
            moveX.set(mouseEvent.getX());
            moveY.set(mouseEvent.getY());
        } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            pressX.set(mouseEvent.getX());
            pressY.set(mouseEvent.getY());
        } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
            dragX.set(mouseEvent.getX());
            dragY.set(mouseEvent.getY());
        } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            clickX.set(mouseEvent.getX());
            clickY.set(mouseEvent.getY());
        } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            releaseX.set(mouseEvent.getX());
            releaseY.set(mouseEvent.getY());
        }

        if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
            littleEllipseOnCursor.hide();
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

            if (tempObjectName.get().equals(NodeTypeEnum.DynamicDragRectangle.getNodeType()) &&
                    !rotationIcon.contains(pressX.get(), pressY.get()) &&
                    !scalingIcons.get(0).contains(pressX.get(), pressY.get()) &&
                    !scalingIcons.get(1).contains(pressX.get(), pressY.get()) &&
                    !scalingIcons.get(2).contains(pressX.get(), pressY.get()) &&
                    !scalingIcons.get(3).contains(pressX.get(), pressY.get()) &&
                    !scalingIcons.get(4).contains(pressX.get(), pressY.get()) &&
                    !scalingIcons.get(5).contains(pressX.get(), pressY.get()) &&
                    !scalingIcons.get(6).contains(pressX.get(), pressY.get()) &&
                    !scalingIcons.get(7).contains(pressX.get(), pressY.get())

            ) {
//                print(uuid(50));
//                print("selectedObjectsController = "+selectedObjectsController.getBuffer());
//                print("validObjects = "+validObjects);

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
//                firstAppNode = null;
                for (int i = validObjects.size() - 1; i >= 0; i--) {
                    AppNode obj = validObjects.get(i);
//                    print(obj);
                    if (obj.getStyleableNode().contains(obj.getStyleableNode().parentToLocal(pressX.get(), pressY.get()))) {
//                    if (obj.isPressed()) {
                        firstAppNode = obj;
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

//        List<ScalingIcon> scalingIcons=new ArrayList<>();
//        for(int i=0;i<8;i++) {
//            scalingIcons.add(new ScalingIcon(0, 0, 0, 0, 0, Color.RED, Color.RED, 0));
//        }

//        print("========================================");
//        print(x.get() + "," + y.get());
//
//        print(mouseEvent.getEventType().getName());
//        print("drawingStage = " + drawingStage);

//        print(tempObjectName.get());
        if (tempObjectName.get() == null) {
            return;
        }

        cursorPositionLabel.setText(x.get() + "," + y.get());

        if (tempObjectName.get().equals(NodeTypeEnum.DynamicDragRectangle.getNodeType())) {
            dynamicDragRectangle.reset();
        } else if (tempObjectName.get().equals(NodeTypeEnum.Rectangle.getNodeType())) {
            littleRectangleOnCursor.show(x.get(), y.get());
            if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                tempRectangle.draw(pressX.get(), pressY.get(), moveX.get(), moveY.get());
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                mainCanvasItemsHandler.copyToMainCanvas(tempRectangle);
                drawingStage = 0;
            }
        } else if (tempObjectName.get().equals(NodeTypeEnum.Line.getNodeType())) {
            littleLineOnCursor.show(x.get(), y.get());
            if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                tempLine.draw(pressX.get(), pressY.get(), moveX.get(), moveY.get());
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                mainCanvasItemsHandler.copyToMainCanvas(tempLine);
                drawingStage = 0;
            }
        } else if (tempObjectName.get().equals(NodeTypeEnum.Ellipse.getNodeType())) {
            littleEllipseOnCursor.show(x.get(), y.get());
            if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                tempEllipse.draw(pressX.get(), pressY.get(), moveX.get(), moveY.get());

            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                mainCanvasItemsHandler.copyToMainCanvas(tempEllipse);
                drawingStage = 0;
            }
        } else if (tempObjectName.get().equals(NodeTypeEnum.Arc.getNodeType())) {
            littleArcOnCursor.show(x.get(), y.get());
            if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                tempArc.draw(pressX.get(), pressY.get(), moveX.get(), moveY.get());
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 2 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                double angle = Math.atan2(moveY.get() - tempArc.affineTransform.getTy(), moveX.get() - tempArc.affineTransform.getTx());
                if (!mouseEvent.isControlDown()) {
                    tempArc.setLength(-angle * 180 / Math.PI - (angle > 0 ? 0 : 360));
                } else {
                    tempArc.setStartAngle(-angle * 180 / Math.PI - (angle > 0 ? 0 : 360));
                }
                if (mouseEvent.isAltDown()) {
                    tempArc.setArcType(ArcType.CHORD);

                } else if (mouseEvent.isShiftDown()) {
                    tempArc.setArcType(ArcType.OPEN);

                } else {
                    tempArc.setArcType(ArcType.ROUND);
                }
            } else if (drawingStage == 2 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                if (mouseEvent.isAltDown()) {
                    tempArc.setArcType(ArcType.CHORD);

                } else if (mouseEvent.isShiftDown()) {
                    tempArc.setArcType(ArcType.OPEN);

                } else {
                    tempArc.setArcType(ArcType.ROUND);
                }
                mainCanvasItemsHandler.copyToMainCanvas(tempArc);
                tempArc.setStartAngle(0);
                tempArc.setLength(270);
                tempArc.setArcType(ArcType.ROUND);
                drawingStage = 0;
            }
        } else if (tempObjectName.get().equals(NodeTypeEnum.LineChart_NN.getNodeType())) {
            littleLineChartOnCursor.show(x.get(), y.get());
            if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                tempLineChart_NN.draw(pressX.get(), pressY.get(), moveX.get(), moveY.get());
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                mainCanvasItemsHandler.copyToMainCanvas(tempLineChart_NN);
                drawingStage = 0;
            }
        } else if (tempObjectName.get().equals(NodeTypeEnum.LineChart_NS.getNodeType())) {
            littleLineChartOnCursor.show(x.get(), y.get());
            if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                tempLineChart_NS.draw(pressX.get(), pressY.get(), moveX.get(), moveY.get());
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                mainCanvasItemsHandler.copyToMainCanvas(tempLineChart_NS);
                drawingStage = 0;
            }
        } else if (tempObjectName.get().equals(NodeTypeEnum.LineChart_SN.getNodeType())) {
            littleLineChartOnCursor.show(x.get(), y.get());
            if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                tempLineChart_SN.draw(pressX.get(), pressY.get(), moveX.get(), moveY.get());

            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                mainCanvasItemsHandler.copyToMainCanvas(tempLineChart_SN);
                drawingStage = 0;
            }
        } else if (tempObjectName.get().equals(NodeTypeEnum.ScatterChart_NN.getNodeType())) {
            littleScatterChartOnCursor.show(x.get(), y.get());
            if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                tempScatterChart_NN.draw(pressX.get(), pressY.get(), moveX.get(), moveY.get());

            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                mainCanvasItemsHandler.copyToMainCanvas(tempScatterChart_NN);
                drawingStage = 0;
            }
        } else if (tempObjectName.get().equals(NodeTypeEnum.ScatterChart_NS.getNodeType())) {
            littleScatterChartOnCursor.show(x.get(), y.get());
            if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                tempScatterChart_NS.draw(pressX.get(), pressY.get(), moveX.get(), moveY.get());
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                mainCanvasItemsHandler.copyToMainCanvas(tempScatterChart_NS);
                drawingStage = 0;
            }
        } else if (tempObjectName.get().equals(NodeTypeEnum.ScatterChart_SN.getNodeType())) {
            littleScatterChartOnCursor.show(x.get(), y.get());
            if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                tempScatterChart_SN.draw(pressX.get(), pressY.get(), moveX.get(), moveY.get());

            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                mainCanvasItemsHandler.copyToMainCanvas(tempScatterChart_SN);
                drawingStage = 0;
            }
        } else if (tempObjectName.get().equals(NodeTypeEnum.AreaChart_NN.getNodeType())) {
            littleAreaChartOnCursor.show(x.get(), y.get());
            if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                tempAreaChart_NN.draw(pressX.get(), pressY.get(), moveX.get(), moveY.get());

            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                mainCanvasItemsHandler.copyToMainCanvas(tempAreaChart_NN);
                drawingStage = 0;
            }
        } else if (tempObjectName.get().equals(NodeTypeEnum.AreaChart_NS.getNodeType())) {
            littleAreaChartOnCursor.show(x.get(), y.get());
            if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                tempAreaChart_NS.draw(pressX.get(), pressY.get(), moveX.get(), moveY.get());
//                littleAreaChartOnCursor.show(x.get(), y.get());
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                mainCanvasItemsHandler.copyToMainCanvas(tempAreaChart_NS);
                drawingStage = 0;
            }
        } else if (tempObjectName.get().equals(NodeTypeEnum.AreaChart_SN.getNodeType())) {
            littleAreaChartOnCursor.show(x.get(), y.get());
            if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                drawingStage++;
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
                selectedObjectsController.unselectAll();
                tempAreaChart_SN.draw(pressX.get(), pressY.get(), moveX.get(), moveY.get());
//                littleAreaChartOnCursor.show(x.get(), y.get());
            } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                mainCanvasItemsHandler.copyToMainCanvas(tempAreaChart_SN);
                drawingStage = 0;
            }
        } else {
            throw new AppException(AppExceptionEnum.AppNodeNotRegistered);
        }


//        if(mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
//        }


//        switch (mouseEvent.getEventType().getName()){
//            case MouseEvent.MOUSE_CLICKED.getName() -> {
//                int x = 1;
//            }
//        }

    }


}
