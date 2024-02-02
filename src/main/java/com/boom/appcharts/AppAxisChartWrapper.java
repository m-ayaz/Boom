package com.boom.appcharts;


import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.structures.abstracts.AppNode;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import org.json.JSONObject;

// todo: disallow addition of duplicate series or data

public class AppAxisChartWrapper extends AppNode {

    public AppAxisChartWrapper(Node styleableNode, String fillColorFX, String strokeColorFX, String strokeWidthFX) {
        super(styleableNode, fillColorFX, strokeColorFX, strokeWidthFX);
    }

    @Override
    public void configureOnMouseEvent(MouseEvent mouseEvent, MainCanvasItemsHandler mainCanvasItemsHandler, SelectedObjectsController selectedObjectsController, double moveX, double moveY, double dragX, double dragY, double pressX, double pressY, double releaseX, double releaseY, double clickX, double clickY, double x, double y) {

    }

    @Override
    public AppNode copy() {
        return null;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {

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
        return false;
    }
}