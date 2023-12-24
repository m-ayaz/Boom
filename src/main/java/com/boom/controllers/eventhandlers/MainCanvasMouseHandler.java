package com.boom.controllers.eventhandlers;

import com.boom.controllers.DynamicDragRectangle;
import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.exceptions.AppException;
import com.boom.icons.RotationIcon;
import com.boom.icons.ScalingIcon;
import com.boom.indicators.*;
import com.boom.structures.enums.AppExceptionEnum;
import com.boom.structures.abstracts.AppNode;
import com.boom.structures.enums.NodeTypeEnum;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.List;

import static com.boom.tools.Tools.print;

/**
 *{@link #setOnMouseMoved(Pane, Label, StringProperty, LittleLineChartOnCursor, LittleScatterChartOnCursor, LittleAreaChartOnCursor, LittleEllipseOnCursor, LittleRectangleOnCursor, LittleLineOnCursor)} )} )} )}
 * This event is invoked when the mouse cursor is moving over the main canvas without dragging.
 * 1- todo If any of the shapes or charts has been selected, the cursor should denote a proper small icon.
 * 2- todo If nothing has been selected, the cursor should show nothing.
 * 3- The cursor position should be permanently tracked and shown in a small label at bottom right of the canvas.
 {@link #setOnMouseReleased(Pane, StringProperty, DynamicDragRectangle, MainCanvasItemsHandler)}
 * This event is invoked when the user has finished dragging and pressing down the cursor (the onMousePressed event has already been invoked.).
 * 1- todo If the user had chosen to draw shapes or charts (not pens), add a clone of the item to the mainCanvas.
 * 2- todo If the user had chosen the the dynamic drag rectangle and there where some selected shapes, do nothing! (The proper behavior has been made through the onMousePressed and onMouseDragged methods.)
 * 3- todo If the user had chosen the the dynamic drag rectangle and there where no selected shapes, select all the objects included by the drag.
 {@link #setOnMouseDragged(Pane, Label, StringProperty, DynamicDragRectangle, MainCanvasItemsHandler, DoubleProperty, DoubleProperty, DoubleProperty, DoubleProperty, DoubleProperty, DoubleProperty, List)} )}
 * This event is invoked when the user is dragging the cursor (the onMousePressed event has already been invoked.).
 * After the user has pressed down the mouse key, the following cases emerge:
 * 1- todo If any of the items (shapes or charts not pens) has been chosen for drawing, dynamically draw the item.
 * 2- todo If the user has chosen the dynamic drag rectangle and there are any selected shapes, dynamically move them all on drag.
 * 3- todo If the user has chosen the dynamic drag rectangle and there are no selected shapes, draw the dynamic drag rectangle.
 * 4- todo If any of the pen items has been chosen for drawing, add the pen trace to the canvas.
 * {@link #setOnMousePressed(Pane, StringProperty, MainCanvasItemsHandler, DoubleProperty, DoubleProperty, DoubleProperty, DoubleProperty, List, RotationIcon, List)}
 * This event is invoked when the user has pressed down a mouse key (whether left or right).
 * todo At the time, I do not know whether it is possible to discriminate between the left and right keys. I need to know!
 * 0- At either case, record the mouse press position within variables.
 * 1- todo If the user has chosen to draw an item (shape, plot, etc.), do nothing! (The rest is handled by mainCanvasOnMouseDragged.)
 * 2- todo If the user has chosen the dynamic drag rectangle, toggle selection of any object containing the press position (i.e. select the unselected and unselect the selected) and unselect all other objects nonetheless (i.e. those not containing the press position).
 */

public class MainCanvasMouseHandler {

    public MainCanvasMouseHandler(Pane mainCanvas, Label cursorPositionLabel,
                                  StringProperty tempObjectName,
                                  DynamicDragRectangle dynamicDragRectangle,
                                  LittleLineChartOnCursor littleLineChartOnCursor,
//                                  LittleBarChartOnCursor littleBarChartOnCursor,
                                  LittleScatterChartOnCursor littleScatterChartOnCursor,
                                  LittleAreaChartOnCursor littleAreaChartOnCursor,
                                  LittleEllipseOnCursor littleEllipseOnCursor,
                                  LittleRectangleOnCursor littleRectangleOnCursor,
                                  LittleLineOnCursor littleLineOnCursor,
                                  MainCanvasItemsHandler mainCanvasItemsHandler,
                                  DoubleProperty currentPosX,
                                  DoubleProperty currentPosY,
                                  DoubleProperty previousPosX,
                                  DoubleProperty previousPosY,
                                  DoubleProperty dragStartPosX,
                                  DoubleProperty dragStartPosY,
//                                  ColorPicker fillSolidColorPicker,
//                                  ColorPicker strokeSolidColorPicker,
//                                  TextField strokeWidthInput,
                                  List<Double> parsedStrokeDashArray,
                                  List<ScalingIcon> scalingIcons,
                                  RotationIcon rotationIcon,
                                  List<AppNode> canvasPermanentObjects

    ) {

        setOnMouseMoved(mainCanvas, cursorPositionLabel,
                tempObjectName,
                littleLineChartOnCursor,
//                littleBarChartOnCursor,
                littleScatterChartOnCursor,
                littleAreaChartOnCursor,
                littleEllipseOnCursor,
                littleRectangleOnCursor,
                littleLineOnCursor);

        setOnMouseDragged(mainCanvas, cursorPositionLabel,
                tempObjectName,
                dynamicDragRectangle,
                mainCanvasItemsHandler,
                currentPosX,
                currentPosY,
                previousPosX,
                previousPosY,
                dragStartPosX,
                dragStartPosY,
//                fillSolidColorPicker,
//                strokeSolidColorPicker,
//                strokeWidthInput,
                parsedStrokeDashArray);

        setOnMouseExited(mainCanvas, cursorPositionLabel,
                littleLineChartOnCursor,
//                littleBarChartOnCursor,
                littleScatterChartOnCursor,
                littleAreaChartOnCursor,
                littleEllipseOnCursor,
                littleRectangleOnCursor,
                littleLineOnCursor);

        setOnMousePressed(mainCanvas,
                tempObjectName,
                mainCanvasItemsHandler,
                currentPosX,
                currentPosY,
                dragStartPosX,
                dragStartPosY,
                scalingIcons,
                rotationIcon,
                canvasPermanentObjects);

        setOnMouseReleased(mainCanvas,
                tempObjectName,
                dynamicDragRectangle,
                mainCanvasItemsHandler);


    }

    void setOnMouseMoved(Pane mainCanvas, Label cursorPositionLabel,
                         StringProperty tempObjectName,
                         LittleLineChartOnCursor littleLineChartOnCursor,
//                         LittleBarChartOnCursor littleBarChartOnCursor,
                         LittleScatterChartOnCursor littleScatterChartOnCursor,
                         LittleAreaChartOnCursor littleAreaChartOnCursor,
                         LittleEllipseOnCursor littleEllipseOnCursor,
                         LittleRectangleOnCursor littleRectangleOnCursor,
                         LittleLineOnCursor littleLineOnCursor) {

        mainCanvas.setOnMouseMoved(mouseEvent -> {
            double currentPosX1 = mouseEvent.getX();
            double currentPosY1 = mouseEvent.getY();
            cursorPositionLabel.setText(currentPosX1 + "," + currentPosY1);
            if (tempObjectName.get().equals(NodeTypeEnum.DynamicDragRectangle.getNodeType())) {

            } else if (tempObjectName.get().equals(NodeTypeEnum.Ellipse.getNodeType())) {
                littleEllipseOnCursor.show(currentPosX1, currentPosY1);
            } else if (tempObjectName.get().equals(NodeTypeEnum.Rectangle.getNodeType())) {
                littleRectangleOnCursor.show(currentPosX1, currentPosY1);
            } else if (tempObjectName.get().equals(NodeTypeEnum.Line.getNodeType())) {
                littleLineOnCursor.show(currentPosX1, currentPosY1);
            } else if (tempObjectName.get().equals(NodeTypeEnum.LineChart_NN.getNodeType()) ||
                    tempObjectName.get().equals(NodeTypeEnum.LineChart_NS.getNodeType()) ||
                    tempObjectName.get().equals(NodeTypeEnum.LineChart_SN.getNodeType())) {
                littleLineChartOnCursor.show(currentPosX1, currentPosY1);
//            } else if (tempObjectName.get().equals(NodeTypeEnum.BarChart_NS.getNodeType())||
//                    tempObjectName.get().equals(NodeTypeEnum.BarChart_SN.getNodeType())) {
//                littleBarChartOnCursor.show(currentPosX1, currentPosY1);
            } else if (tempObjectName.get().equals(NodeTypeEnum.AreaChart_NN.getNodeType()) ||
                    tempObjectName.get().equals(NodeTypeEnum.AreaChart_NS.getNodeType()) ||
                    tempObjectName.get().equals(NodeTypeEnum.AreaChart_SN.getNodeType())) {
                littleAreaChartOnCursor.show(currentPosX1, currentPosY1);
            } else if (tempObjectName.get().equals(NodeTypeEnum.ScatterChart_NN.getNodeType()) ||
                    tempObjectName.get().equals(NodeTypeEnum.ScatterChart_NS.getNodeType()) ||
                    tempObjectName.get().equals(NodeTypeEnum.ScatterChart_SN.getNodeType())) {
                littleScatterChartOnCursor.show(currentPosX1, currentPosY1);
            } else {
                throw new AppException(AppExceptionEnum.AppNodeNotRegistered);
            }

        });
    }

    void setOnMouseExited(Pane mainCanvas, Label cursorPositionLabel,
                          LittleLineChartOnCursor littleLineChartOnCursor,
//                          LittleBarChartOnCursor littleBarChartOnCursor,
                          LittleScatterChartOnCursor littleScatterChartOnCursor,
                          LittleAreaChartOnCursor littleAreaChartOnCursor,
                          LittleEllipseOnCursor littleEllipseOnCursor,
                          LittleRectangleOnCursor littleRectangleOnCursor,
                          LittleLineOnCursor littleLineOnCursor) {
        mainCanvas.setOnMouseExited(event -> {
            littleEllipseOnCursor.hide();
            littleRectangleOnCursor.hide();
            littleLineOnCursor.hide();
            littleLineChartOnCursor.hide();
//            littleBarChartOnCursor.hide();
            littleScatterChartOnCursor.hide();
            littleAreaChartOnCursor.hide();
            cursorPositionLabel.setText("Cursor out!");
        });
    }

    void setOnMouseReleased(Pane mainCanvas,
                            StringProperty tempObjectName,
                            DynamicDragRectangle dynamicDragRectangle,
                            MainCanvasItemsHandler mainCanvasItemsHandler) {
        mainCanvas.setOnMouseReleased(mouseEvent -> {
            if (tempObjectName.get().equals(NodeTypeEnum.DynamicDragRectangle.getNodeType())) {

            } else if (tempObjectName.get().equals(NodeTypeEnum.Ellipse.getNodeType())) {
                mainCanvasItemsHandler.copyTempEllipseOnMainCanvas();
            } else if (tempObjectName.get().equals(NodeTypeEnum.Rectangle.getNodeType())) {
                mainCanvasItemsHandler.copyTempRectangleOnMainCanvas();
            } else if (tempObjectName.get().equals(NodeTypeEnum.Line.getNodeType())) {
                mainCanvasItemsHandler.copyTempLineToMainCanvas();
            } else if (tempObjectName.get().equals(NodeTypeEnum.LineChart_NN.getNodeType())) {
                mainCanvasItemsHandler.copyTempLineChart_NNOnMainCanvas();
            } else if (tempObjectName.get().equals(NodeTypeEnum.LineChart_NS.getNodeType())) {
                mainCanvasItemsHandler.copyTempLineChart_NSOnMainCanvas();
            } else if (tempObjectName.get().equals(NodeTypeEnum.LineChart_SN.getNodeType())) {
                mainCanvasItemsHandler.copyTempLineChart_SNOnMainCanvas();
            } else if (tempObjectName.get().equals(NodeTypeEnum.ScatterChart_NN.getNodeType())) {
                mainCanvasItemsHandler.copyTempScatterChart_NNOnMainCanvas();
            } else if (tempObjectName.get().equals(NodeTypeEnum.ScatterChart_NS.getNodeType())) {
                mainCanvasItemsHandler.copyTempScatterChart_NSOnMainCanvas();
            } else if (tempObjectName.get().equals(NodeTypeEnum.ScatterChart_SN.getNodeType())) {
                mainCanvasItemsHandler.copyTempScatterChart_SNOnMainCanvas();
            } else if (tempObjectName.get().equals(NodeTypeEnum.AreaChart_NN.getNodeType())) {
                mainCanvasItemsHandler.copyTempAreaChart_NNOnMainCanvas();
            } else if (tempObjectName.get().equals(NodeTypeEnum.AreaChart_NS.getNodeType())) {
                mainCanvasItemsHandler.copyTempAreaChart_NSOnMainCanvas();
            } else if (tempObjectName.get().equals(NodeTypeEnum.AreaChart_SN.getNodeType())) {
                mainCanvasItemsHandler.copyTempAreaChart_SNOnMainCanvas();
//            } else if (tempObjectName.get().equals(NodeTypeEnum.BarChart_NS.getNodeType())) {
//                mainCanvasItemsHandler.copyTempBarChart_NSOnMainCanvas();
//            } else if (tempObjectName.get().equals(NodeTypeEnum.BarChart_SN.getNodeType())) {
//                mainCanvasItemsHandler.copyTempBarChart_SNOnMainCanvas();
            } else {
                throw new AppException(AppExceptionEnum.AppNodeNotRegistered);
            }

            dynamicDragRectangle.reset();
        });
    }

    void setOnMousePressed(Pane mainCanvas,
                           StringProperty tempObjectName,
                           MainCanvasItemsHandler mainCanvasItemsHandler,
                           DoubleProperty currentPosX,
                           DoubleProperty currentPosY,
                           DoubleProperty dragStartPosX,
                           DoubleProperty dragStartPosY,
                           List<ScalingIcon> scalingIcons,
                           RotationIcon rotationIcon,
                           List<AppNode> canvasPermanentObjects) {

        mainCanvas.setOnMousePressed(mouseEvent -> {
            dragStartPosX.set(mouseEvent.getX());
            dragStartPosY.set(mouseEvent.getY());
            currentPosX.set(dragStartPosX.get());
            currentPosY.set(dragStartPosY.get());

            if (tempObjectName.get().equals(NodeTypeEnum.DynamicDragRectangle.getNodeType()) &&
                    !rotationIcon.contains(currentPosX.get(), currentPosY.get()) &&
                    !scalingIcons.get(0).contains(currentPosX.get(), currentPosY.get()) &&
                    !scalingIcons.get(1).contains(currentPosX.get(), currentPosY.get()) &&
                    !scalingIcons.get(2).contains(currentPosX.get(), currentPosY.get()) &&
                    !scalingIcons.get(3).contains(currentPosX.get(), currentPosY.get()) &&
                    !scalingIcons.get(4).contains(currentPosX.get(), currentPosY.get()) &&
                    !scalingIcons.get(5).contains(currentPosX.get(), currentPosY.get()) &&
                    !scalingIcons.get(6).contains(currentPosX.get(), currentPosY.get()) &&
                    !scalingIcons.get(7).contains(currentPosX.get(), currentPosY.get())

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

                AppNode firstOnMousePressedShape = null;
//                firstOnMousePressedShape = null;
                for (int i = canvasPermanentObjects.size() - 1; i >= 0; i--) {
                    AppNode obj = canvasPermanentObjects.get(i);
                    if (obj.getStyleableNode().contains(obj.getStyleableNode().parentToLocal(currentPosX.get(), currentPosY.get()))) {
//                    if (obj.isPressed()) {
                        firstOnMousePressedShape = obj;
                        break;
                    }
                }
                if (!mouseEvent.isControlDown()) {
                    if (firstOnMousePressedShape == null) {
                        mainCanvasItemsHandler.getSelectedObjectsController().unselectAll();
                    } else {
                        if (!mainCanvasItemsHandler.getSelectedObjectsController().isSelected(firstOnMousePressedShape)) {
                            mainCanvasItemsHandler.getSelectedObjectsController().unselectAll();
                            mainCanvasItemsHandler.getSelectedObjectsController().select(firstOnMousePressedShape);
                        }
                    }
                } else if (firstOnMousePressedShape != null) {
                    mainCanvasItemsHandler.getSelectedObjectsController().reverseSelection(firstOnMousePressedShape);
                }
            }
        });

    }

    void setOnMouseDragged(Pane mainCanvas, Label cursorPositionLabel,
                           StringProperty tempObjectName,
                           DynamicDragRectangle dynamicDragRectangle,
                           MainCanvasItemsHandler mainCanvasItemsHandler,
                           DoubleProperty currentPosX,
                           DoubleProperty currentPosY,
                           DoubleProperty previousPosX,
                           DoubleProperty previousPosY,
                           DoubleProperty dragStartPosX,
                           DoubleProperty dragStartPosY,
//                           ColorPicker fillSolidColorPicker,
//                           ColorPicker strokeSolidColorPicker,
//                           TextField strokeWidthInput,
                           List<Double> parsedStrokeDashArray) {

        mainCanvas.setOnMouseDragged(mouseEvent -> {

            previousPosX.set(currentPosX.get());
            previousPosY.set(currentPosY.get());

            currentPosX.set(mouseEvent.getX());
            currentPosY.set(mouseEvent.getY());

            cursorPositionLabel.setText(currentPosX + "," + currentPosY);

            print(tempObjectName.get());

            if (tempObjectName.get().equals(NodeTypeEnum.DynamicDragRectangle.getNodeType())) {
                dynamicDragRectangle.reset();
            } else if (tempObjectName.get().equals(NodeTypeEnum.Ellipse.getNodeType())) {
                mainCanvasItemsHandler.drawTempEllipse(currentPosX.get(), currentPosY.get(), dragStartPosX.get(), dragStartPosY.get(), Color.TRANSPARENT, Color.BLACK, 1, parsedStrokeDashArray);
            } else if (tempObjectName.get().equals(NodeTypeEnum.Rectangle.getNodeType())) {
                mainCanvasItemsHandler.drawTempRectangle(currentPosX.get(), currentPosY.get(), dragStartPosX.get(), dragStartPosY.get(), Color.TRANSPARENT, Color.BLACK, 1, parsedStrokeDashArray);
            } else if (tempObjectName.get().equals(NodeTypeEnum.Line.getNodeType())) {
                mainCanvasItemsHandler.drawTempLine(currentPosX.get(), currentPosY.get(), dragStartPosX.get(), dragStartPosY.get(), Color.TRANSPARENT, Color.BLACK, 1, parsedStrokeDashArray);
            } else if (tempObjectName.get().equals(NodeTypeEnum.LineChart_NN.getNodeType())) {
                mainCanvasItemsHandler.drawTempLineChart_NN(currentPosX.get(), currentPosY.get(), dragStartPosX.get(), dragStartPosY.get(), Color.TRANSPARENT, Color.BLACK, 1, parsedStrokeDashArray);
            } else if (tempObjectName.get().equals(NodeTypeEnum.LineChart_NS.getNodeType())) {
                mainCanvasItemsHandler.drawTempLineChart_NS(currentPosX.get(), currentPosY.get(), dragStartPosX.get(), dragStartPosY.get(), Color.TRANSPARENT, Color.BLACK, 1, parsedStrokeDashArray);
            } else if (tempObjectName.get().equals(NodeTypeEnum.LineChart_SN.getNodeType())) {
                mainCanvasItemsHandler.drawTempLineChart_SN(currentPosX.get(), currentPosY.get(), dragStartPosX.get(), dragStartPosY.get(), Color.TRANSPARENT, Color.BLACK, 1, parsedStrokeDashArray);
            } else if (tempObjectName.get().equals(NodeTypeEnum.AreaChart_NN.getNodeType())) {
                mainCanvasItemsHandler.drawTempAreaChart_NN(currentPosX.get(), currentPosY.get(), dragStartPosX.get(), dragStartPosY.get(), Color.TRANSPARENT, Color.BLACK, 1, parsedStrokeDashArray);
            } else if (tempObjectName.get().equals(NodeTypeEnum.AreaChart_NS.getNodeType())) {
                mainCanvasItemsHandler.drawTempAreaChart_NS(currentPosX.get(), currentPosY.get(), dragStartPosX.get(), dragStartPosY.get(), Color.TRANSPARENT, Color.BLACK, 1, parsedStrokeDashArray);
            } else if (tempObjectName.get().equals(NodeTypeEnum.AreaChart_SN.getNodeType())) {
                mainCanvasItemsHandler.drawTempAreaChart_SN(currentPosX.get(), currentPosY.get(), dragStartPosX.get(), dragStartPosY.get(), Color.TRANSPARENT, Color.BLACK, 1, parsedStrokeDashArray);
            } else if (tempObjectName.get().equals(NodeTypeEnum.ScatterChart_NN.getNodeType())) {
                mainCanvasItemsHandler.drawTempScatterChart_NN(currentPosX.get(), currentPosY.get(), dragStartPosX.get(), dragStartPosY.get(), Color.TRANSPARENT, Color.BLACK, 1, parsedStrokeDashArray);
            } else if (tempObjectName.get().equals(NodeTypeEnum.ScatterChart_NS.getNodeType())) {
                mainCanvasItemsHandler.drawTempScatterChart_NS(currentPosX.get(), currentPosY.get(), dragStartPosX.get(), dragStartPosY.get(), Color.TRANSPARENT, Color.BLACK, 1, parsedStrokeDashArray);
            } else if (tempObjectName.get().equals(NodeTypeEnum.ScatterChart_SN.getNodeType())) {
                mainCanvasItemsHandler.drawTempScatterChart_SN(currentPosX.get(), currentPosY.get(), dragStartPosX.get(), dragStartPosY.get(), Color.TRANSPARENT, Color.BLACK, 1, parsedStrokeDashArray);
//            } else if (tempObjectName.get().equals(NodeTypeEnum.BarChart_NS.getNodeType())) {
//                mainCanvasItemsHandler.drawTempBarChart_NS(currentPosX, currentPosY, dragStartPosX, dragStartPosY, Color.TRANSPARENT,Color.BLACK,1, parsedStrokeDashArray);
//            } else if (tempObjectName.get().equals(NodeTypeEnum.BarChart_SN.getNodeType())) {
//                mainCanvasItemsHandler.drawTempBarChart_SN(currentPosX, currentPosY, dragStartPosX, dragStartPosY, Color.TRANSPARENT,Color.BLACK,1, parsedStrokeDashArray);
            } else {
                throw new AppException(AppExceptionEnum.AppNodeNotRegistered);
            }
//        else if (tempObjectName.equals(ElementTypes.AppLineChart.getSimpleName())) {
//            mainCanvasItemsHandler.drawTempLineChart(currentPosX, currentPosY, dragStartPosX, dragStartPosY, fillRGBARedSlider.getValue(), fillRGBAGreenSlider.getValue(), fillRGBABlueSlider.getValue(), fillRGBAAlphaSlider.getValue(), strokeRGBARedSlider.getValue(), strokeRGBAGreenSlider.getValue(), strokeRGBABlueSlider.getValue(), strokeRGBAAlphaSlider.getValue(), Double.parseDouble(strokeWidthInput.getText()), parsedStrokeDashArray);
//        } else if (tempObjectName.equals(ElementTypes.AppBarChart.getSimpleName())) {
//            mainCanvasItemsHandler.drawTempBarChart(currentPosX, currentPosY, dragStartPosX, dragStartPosY, fillRGBARedSlider.getValue(), fillRGBAGreenSlider.getValue(), fillRGBABlueSlider.getValue(), fillRGBAAlphaSlider.getValue(), strokeRGBARedSlider.getValue(), strokeRGBAGreenSlider.getValue(), strokeRGBABlueSlider.getValue(), strokeRGBAAlphaSlider.getValue(), Double.parseDouble(strokeWidthInput.getText()), parsedStrokeDashArray);
//        } else if (tempObjectName.equals(ElementTypes.AppAreaChart.getSimpleName())) {
//            mainCanvasItemsHandler.drawTempAreaChart(currentPosX, currentPosY, dragStartPosX, dragStartPosY, fillRGBARedSlider.getValue(), fillRGBAGreenSlider.getValue(), fillRGBABlueSlider.getValue(), fillRGBAAlphaSlider.getValue(), strokeRGBARedSlider.getValue(), strokeRGBAGreenSlider.getValue(), strokeRGBABlueSlider.getValue(), strokeRGBAAlphaSlider.getValue(), Double.parseDouble(strokeWidthInput.getText()), parsedStrokeDashArray);
//        } else if (tempObjectName.equals(ElementTypes.AppScatterChart.getSimpleName())) {
//            mainCanvasItemsHandler.drawScatterChart(currentPosX, currentPosY, dragStartPosX, dragStartPosY, fillRGBARedSlider.getValue(), fillRGBAGreenSlider.getValue(), fillRGBABlueSlider.getValue(), fillRGBAAlphaSlider.getValue(), strokeRGBARedSlider.getValue(), strokeRGBAGreenSlider.getValue(), strokeRGBABlueSlider.getValue(), strokeRGBAAlphaSlider.getValue(), Double.parseDouble(strokeWidthInput.getText()), parsedStrokeDashArray);
//        }
//        else if (tempObjectName.equals(ElementTypes.AppText.getSimpleName())) {
//            mainCanvasItemsHandler.drawTempText(currentPosX, currentPosY, dragStartPosX, dragStartPosY, fillRGBARedSlider.getValue(), fillRGBAGreenSlider.getValue(), fillRGBABlueSlider.getValue(), fillRGBAAlphaSlider.getValue(), strokeRGBARedSlider.getValue(), strokeRGBAGreenSlider.getValue(), strokeRGBABlueSlider.getValue(), strokeRGBAAlphaSlider.getValue(), Double.parseDouble(strokeWidthInput.getText()), parsedStrokeDashArray);
//        }


        });

    }

}