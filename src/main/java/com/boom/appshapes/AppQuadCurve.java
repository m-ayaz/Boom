package com.boom.appshapes;

import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.structures.abstracts.AppLineShape;
import com.boom.structures.abstracts.AppNode;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.QuadCurve;
import javafx.scene.transform.MatrixType;
import org.json.JSONObject;

import static com.boom.tools.Tools.deepCopy;

public final class AppQuadCurve extends AppLineShape {

    public DoubleProperty startX, startY, controlX, controlY, endX, endY;

    public AppQuadCurve(double startX,double startY,double controlX,double controlY,double endX,double endY) {
        super(new QuadCurve(startX, startY, controlX, controlY, endX, endY));
        this.startX=((QuadCurve) styleableNode).startXProperty();
        this.startY=((QuadCurve) styleableNode).startYProperty();
        this.controlX=((QuadCurve) styleableNode).controlXProperty();
        this.controlY=((QuadCurve) styleableNode).controlYProperty();
        this.endX=((QuadCurve) styleableNode).endXProperty();
        this.endY=((QuadCurve) styleableNode).endYProperty();
    }

    @Override
    public void configureOnMouseEvent(MouseEvent mouseEvent, MainCanvasItemsHandler mainCanvasItemsHandler, SelectedObjectsController selectedObjectsController, double moveX, double moveY, double dragX, double dragY, double pressX, double pressY, double releaseX, double releaseY, double clickX, double clickY, double x, double y) {
        if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            drawingStage++;
        } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
            selectedObjectsController.unselectAll();
            draw(pressX, pressY, moveX, moveY);
        } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            drawingStage++;
        } else if (drawingStage == 2 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
            selectedObjectsController.unselectAll();
            controlX.set(x);
            controlY.set(y);
        } else if (drawingStage == 2 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            mainCanvasItemsHandler.copyToMainCanvas(this);
            drawingStage = 0;
        }
    }

    @Override
    public AppNode copy() {
        if (startX.get() == startY.get() && endX.get() == endY.get())
            return null;
        AppQuadCurve  newAppQuadCurve= new AppQuadCurve(startX.get(), startY.get(),controlX.get(),controlY.get(), endX.get(), endY.get());
        deepCopy(affineTransform, newAppQuadCurve.affineTransform);
        deepCopy(backgroundStyle, newAppQuadCurve.backgroundStyle);
        return newAppQuadCurve;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
        startX.set(dragStartX);
        startY.set(dragStartY);
        controlX.set(dragStartX/2+currentDragPosX/2);
        controlY.set(dragStartY/2+currentDragPosY/2);
        endX.set(currentDragPosX);
        endY.set(currentDragPosY);
    }

    @Override
    public String getSVGClones(int tabIndent) {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("id", id);
        jsonObject.put("affine", affineTransform.toArray(MatrixType.MT_2D_2x3));
        jsonObject.put("backgroundStyle", backgroundStyle.toJSON());
        jsonObject.put("startX", startX.get());
        jsonObject.put("startY", startY.get());
        jsonObject.put("controlX", controlX.get());
        jsonObject.put("controlY", controlY.get());
        jsonObject.put("endX", endX.get());
        jsonObject.put("endY", endY.get());
        return jsonObject;
    }
}
