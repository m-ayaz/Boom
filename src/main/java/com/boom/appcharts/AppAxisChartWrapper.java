package com.boom.appcharts;


import com.boom.appshapes.AppRectangle;
import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.structures.abstracts.AppNode;
import com.boom.styles.CSSProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import static com.boom.tools.Tools.deepCopy;

//todo: disallow addition of duplicate series or data

public class AppAxisChartWrapper extends AppNode {

    public DoubleProperty width, height;

    public CSSProperty legendsContainerBackgroundStyle=new CSSProperty("-fx-background-color", "-fx-border-color", "-fx-border-width");

    public AppAxisChartWrapper(double width, double height) {
        super(new AppAxisChart(width, height), "-fx-background-color", "-fx-border-color", "-fx-border-width");
        ((AppAxisChart) styleableNode).legendsContainerCSS.bind(legendsContainerBackgroundStyle);
        this.width = ((AppAxisChart) styleableNode).plotRegionWidth;
        this.height = ((AppAxisChart) styleableNode).plotRegionHeight;
        this.type = AppAxisChartWrapper.class.getName();
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

        if (width.get() == 0 || height.get() == 0)
            return null;
        AppAxisChartWrapper newAppAxisChartWrapper = new AppAxisChartWrapper(width.get(), height.get());
        deepCopy(affineTransform, newAppAxisChartWrapper.affineTransform);
        deepCopy(backgroundStyle, newAppAxisChartWrapper.backgroundStyle);
        deepCopy(legendsContainerBackgroundStyle,newAppAxisChartWrapper.legendsContainerBackgroundStyle);
//        return newAppRectangle;

        return null;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
        affineTransform.setToTransform(new Translate(Math.min(dragStartX, currentDragPosX), Math.min(dragStartY, currentDragPosY)));
        width.set(Math.abs(currentDragPosX - dragStartX));
        height.set(Math.abs(currentDragPosY - dragStartY));
    }

    @Override
    public String getSVGClones(int tabIndent) {
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
}