package com.boom.appshapes;

import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.structures.abstracts.AppLineShape;
import com.boom.structures.abstracts.AppNode;
import com.boom.structures.abstracts.AppPaint;
import javafx.beans.property.DoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.CubicCurve;
import javafx.scene.transform.MatrixType;
import org.json.JSONObject;

import static com.boom.tools.Tools.*;

public  class AppCubicCurve extends AppLineShape {

    public DoubleProperty startX, startY, controlX1, controlY1, controlX2, controlY2, endX, endY;

    public AppCubicCurve(double startX, double startY, double controlX1, double controlY1, double controlX2, double controlY2, double endX, double endY) {
        super(new CubicCurve(startX, startY, controlX1, controlY1, controlX2, controlY2, endX, endY));
        this.startX = ((CubicCurve) styleableNode).startXProperty();
        this.startY = ((CubicCurve) styleableNode).startYProperty();
        this.controlX1 = ((CubicCurve) styleableNode).controlX1Property();
        this.controlY1 = ((CubicCurve) styleableNode).controlY1Property();
        this.controlX2 = ((CubicCurve) styleableNode).controlX2Property();
        this.controlY2 = ((CubicCurve) styleableNode).controlY2Property();
        this.endX = ((CubicCurve) styleableNode).endXProperty();
        this.endY = ((CubicCurve) styleableNode).endYProperty();
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
            controlX1.set(x);
            controlY1.set(y);
            controlX2.set(x);
            controlY2.set(y);
        } else if (configStep == 2 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            configStep++;
        } else if (configStep == 3 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
            selectedObjectsController.unselectAll();
            controlX2.set(x);
            controlY2.set(y);
        } else if (configStep == 3 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            mainCanvasItemsHandler.copyToMainCanvas(this);
            configStep = 0;
        }
    }

    @Override
    public AppNode copy() {
        if (startX.get() == startY.get() && endX.get() == endY.get())
            return null;
        AppCubicCurve newAppCubicCurve = new AppCubicCurve(startX.get(), startY.get(), controlX1.get(), controlY1.get(), controlX2.get(), controlY2.get(), endX.get(), endY.get());
        deepCopy(affineTransform, newAppCubicCurve.affineTransform);
        deepCopy(backgroundStyle, newAppCubicCurve.backgroundStyle);
        return newAppCubicCurve;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
        startX.set(dragStartX);
        startY.set(dragStartY);
        controlX1.set(dragStartX / 3 * 2 + currentDragPosX / 3);
        controlY1.set(dragStartY / 3 * 2 + currentDragPosY / 3);
        controlX2.set(dragStartX / 3 + currentDragPosX / 3 * 2);
        controlY2.set(dragStartY / 3 + currentDragPosY / 3 * 2);
        endX.set(currentDragPosX);
        endY.set(currentDragPosY);
    }

    @Override
    public String toSVG(int tabIndent) {
        double[] dissectedTransform = dissectAffineTransform(affineTransform);
        StringBuilder stringBuilder = new StringBuilder();
        for (AppPaint appPaint : backgroundStyle.getStrokeArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<path d=\"M %f %f C %f %f %f %f %f %f\" fill=\"transparent\" stroke=\"url(#%s)\" stroke-width=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(startX.get(), startY.get(),controlX1.get(),controlY1.get(),controlX2.get(),controlY2.get(), endX.get(), endY.get(), appPaint.id, backgroundStyle.getStrokeWidth(), affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
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
        jsonObject.put("controlX1", controlX1.get());
        jsonObject.put("controlY1", controlY1.get());
        jsonObject.put("controlX2", controlX2.get());
        jsonObject.put("controlY2", controlY2.get());
        jsonObject.put("endX", endX.get());
        jsonObject.put("endY", endY.get());
        return jsonObject;
    }

    @Override
    public String toTeX() {
        return null;
    }
}
