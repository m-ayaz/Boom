package com.boom.appshapes;

import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.structures.abstracts.AppLineShape;
import com.boom.structures.abstracts.AppNode;
import com.boom.structures.abstracts.AppPaint;
import javafx.beans.property.DoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.QuadCurve;
import javafx.scene.transform.MatrixType;
import org.json.JSONObject;

import static com.boom.tools.Tools.*;

public  class AppQuadCurve extends AppLineShape {

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
        if (configStep == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            configStep++;
        } else if (configStep == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
            selectedObjectsController.unselectAll();
            draw(pressX, pressY, moveX, moveY);
        } else if (configStep == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            configStep++;
        } else if (configStep == 2 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
            selectedObjectsController.unselectAll();
            controlX.set(x);
            controlY.set(y);
        } else if (configStep == 2 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            mainCanvasItemsHandler.copyToMainCanvas(this);
            configStep = 0;
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
    public String toSVG(int tabIndent) {
        double[] dissectedTransform = dissectAffineTransform(affineTransform);
        StringBuilder stringBuilder = new StringBuilder();
        for (AppPaint appPaint : backgroundStyle.getStrokeArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<path d=\"M %f %f Q %f %f %f %f\" fill=\"transparent\" stroke=\"url(#%s)\" stroke-width=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(startX.get(), startY.get(),controlX.get(),controlY.get(), endX.get(), endY.get(), appPaint.id, backgroundStyle.getStrokeWidth(), affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
        }
        return stringBuilder.toString();
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("id", id);
        jsonObject.put("affine", arrayToList(affineTransform.toArray(MatrixType.MT_2D_2x3)));
        jsonObject.put("backgroundStyle", backgroundStyle.toJSON());
        jsonObject.put("startX", startX.get());
        jsonObject.put("startY", startY.get());
        jsonObject.put("controlX", controlX.get());
        jsonObject.put("controlY", controlY.get());
        jsonObject.put("endX", endX.get());
        jsonObject.put("endY", endY.get());
        return jsonObject;
    }

    @Override
    public String toTeX() {
        return null;
    }
}
