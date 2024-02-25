package com.boom.appcharts;


import com.boom.appcharts.baseclasses.AppAxisChart;
import com.boom.apppaints.AppColor;
import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.structures.abstracts.AppNode;
import com.boom.styles.CSSProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import static com.boom.tools.Tools.deepCopy;

//todo: disallow addition of duplicate series or data

public class AppAxisChartWrapper extends AppNode {

    public SimpleDoubleProperty width, height;

    public CSSProperty legendsContainerBackgroundStyle = new CSSProperty("-fx-background-color", "-fx-border-color", "-fx-border-width");

    public AppAxisChartWrapper(double width, double height) {
        super(new AppAxisChart(width, height), "-fx-background-color", "-fx-border-color", "-fx-border-width");
        ((AppAxisChart) styleableNode).getAppLegendStyleProperty().bind(legendsContainerBackgroundStyle);
        this.width = ((AppAxisChart) styleableNode).width;
        this.height = ((AppAxisChart) styleableNode).height;
        this.type = AppAxisChartWrapper.class.getSimpleName();

        bindBorder(styleableNode);

        backgroundStyle.addStroke(new AppColor(Color.BLACK));
        backgroundStyle.setStrokeWidth(1);
    }

    @Override
    public void configureOnMouseEvent(MouseEvent mouseEvent, MainCanvasItemsHandler mainCanvasItemsHandler, SelectedObjectsController selectedObjectsController, double moveX, double moveY, double dragX, double dragY, double pressX, double pressY, double releaseX, double releaseY, double clickX, double clickY, double x, double y) {
        if (configStep == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            configStep++;
        } else if (configStep == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
            selectedObjectsController.unselectAll();
            draw(pressX, pressY, moveX, moveY);
        } else if (configStep == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            mainCanvasItemsHandler.copyToMainCanvas(this);
            configStep = 0;
        }
    }

    @Override
    public AppAxisChartWrapper copy() {

        if (width.get() == 0 || height.get() == 0) {
            return null;
        }

//        print("width = %f , height = %f".formatted(width.get(),height.get()));

        AppAxisChartWrapper newAppAxisChartWrapper = new AppAxisChartWrapper(width.get(), height.get());
        deepCopy(affineTransform, newAppAxisChartWrapper.affineTransform);
        deepCopy(backgroundStyle, newAppAxisChartWrapper.backgroundStyle);
        deepCopy(legendsContainerBackgroundStyle, newAppAxisChartWrapper.legendsContainerBackgroundStyle);
//        return newAppRectangle;

        return newAppAxisChartWrapper;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
        affineTransform.setToTransform(new Translate(Math.min(dragStartX, currentDragPosX), Math.min(dragStartY, currentDragPosY)));
        width.set(Math.abs(currentDragPosX - dragStartX));
        height.set(Math.abs(currentDragPosY - dragStartY));
    }

    @Override
    public String toSVG(int tabIndent) {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public boolean contains(double x, double y) {
        return styleableNode.contains(styleableNode.parentToLocal(x, y));
    }

    @Override
    public String toTeX() {
        return null;
    }
}