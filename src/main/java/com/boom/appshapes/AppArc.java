package com.boom.appshapes;

import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.structures.abstracts.AppAreaShape;
import com.boom.structures.abstracts.AppPaint;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.transform.MatrixType;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import static com.boom.tools.Tools.*;

public final class AppArc extends AppAreaShape {

    public DoubleProperty radiusX, radiusY, startAngle, length;
    public ObjectProperty<ArcType> arcType;


    public AppArc(double radiusX, double radiusY, double startAngle, double length, ArcType arcType) {
        super(new Arc(0, 0, radiusX, radiusY, startAngle, length));
        ((Arc) shape).setType(arcType);
        this.radiusX = ((Arc) shape).radiusXProperty();
        this.radiusY = ((Arc) shape).radiusYProperty();
        this.startAngle = ((Arc) shape).startAngleProperty();
        this.length = ((Arc) shape).lengthProperty();
        this.arcType = ((Arc) shape).typeProperty();
    }


//    public String toTeX() {
////        Color fillColor = Color.valueOf(((Arc) node).getFill().toString());
////        Color strokeColor = Color.valueOf(((Arc) node).getStroke().toString());
////
////        return "\n\\begin{scope}[transform canvas = {cm = {%f,%f,%f,%f,(%fpt,%fpt)}}]".formatted(affineTransform.getMxx(), affineTransform.getMyx(), affineTransform.getMxy(), affineTransform.getMyy(), affineTransform.getTx(), affineTransform.getTy()) +
////                "\n\t\\draw[" +
////                "\n\t\tfill = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(fillColor.getRed() * 255), Math.round(fillColor.getGreen() * 255), Math.round(fillColor.getBlue() * 255)) +
////                "\n\t\tdraw = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)) +
////                "\n\t\tfill opacity = %f,".formatted(fillColor.getOpacity()) +
////                "\n\t\tdraw opacity = %f,".formatted(strokeColor.getOpacity()) +
////                "\n\t\tline width = %fpt,".formatted(((Arc) node).getStrokeWidth()) +
////                (((Arc) node).getStrokeDashArray().size() >= 2 ? "\n\t\tdashed, dash pattern = " + ((Arc) node).getStrokeDashArray().stream().map(obj -> ((Arc) node).getStrokeDashArray().indexOf(obj) % 2 == 0 ? "on " + obj : "off " + obj).collect(Collectors.joining(" ")) : "\n\t\tsolid") + "," +
////                "\n\t]" +
////                "\n\t(%fpt,%fpt) ellipse (%fpt and %fpt);".formatted(((Arc) node).getCenterX(), ((Arc) node).getCenterY(), ((Arc) node).radiusX.get(), ((Arc) node).radiusY.get()) +
////                "\n\\end{scope}";
//        return null;
//    }


//    public String toSVG() {
//        return null;
//    }

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
            double angle = Math.atan2(moveY - affineTransform.getTy(), moveX - affineTransform.getTx());
            if (!mouseEvent.isControlDown()) {
                length.set(-angle * 180 / Math.PI - (angle > 0 ? 0 : 360));
            } else {
                startAngle.set(-angle * 180 / Math.PI - (angle > 0 ? 0 : 360));
            }
            if (mouseEvent.isAltDown()) {
                arcType.set(ArcType.CHORD);

            } else if (mouseEvent.isShiftDown()) {
                arcType.set(ArcType.OPEN);

            } else {
                arcType.set(ArcType.ROUND);
            }
        } else if (configStep == 2 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            if (mouseEvent.isAltDown()) {
                arcType.set(ArcType.CHORD);

            } else if (mouseEvent.isShiftDown()) {
                arcType.set(ArcType.OPEN);

            } else {
                arcType.set(ArcType.ROUND);
            }
            mainCanvasItemsHandler.copyToMainCanvas(this);
            startAngle.set(0);
            length.set(270);
            arcType.set(ArcType.ROUND);
            configStep = 0;
        }
    }

    @Override
    public AppArc copy() {
        if (radiusX.get() == 0 || radiusY.get() == 0 || length.get() == 0)
            return null;
        AppArc newAppArc = new AppArc(radiusX.get(), radiusY.get(), startAngle.get(), length.get(), arcType.get());
        deepCopy(affineTransform, newAppArc.affineTransform);
        deepCopy(backgroundStyle, newAppArc.backgroundStyle);
        return newAppArc;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
        affineTransform.setToTransform(new Translate(
                Math.min(dragStartX, currentDragPosX) + Math.abs(currentDragPosX - dragStartX) / 2,
                Math.min(dragStartY, currentDragPosY) + Math.abs(currentDragPosY - dragStartY) / 2)
        );
        radiusX.set(Math.abs(currentDragPosX - dragStartX) / 2);
        radiusY.set(Math.abs(currentDragPosY - dragStartY) / 2);
    }

    @Override
    public String getSVGClones(int tabIndent) {
        double[] dissectedTransform = dissectAffineTransform(affineTransform);
        StringBuilder stringBuilder = new StringBuilder();
        for (AppPaint appPaint : backgroundStyle.getFillArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<ellipse cx=\"%f\" cy=\"%f\" rx=\"%f\" ry=\"%f\" fill=\"url(#%s)\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(radiusX.get(), radiusY.get(), radiusX.get(), radiusY.get(), appPaint.id, affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
        }
        for (AppPaint appPaint : backgroundStyle.getStrokeArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<ellipse cx=\"%f\" cy=\"%f\" rx=\"%f\" ry=\"%f\" fill=\"transparent\" stroke=\"url(#%s)\" stroke-width=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(radiusX.get(), radiusY.get(), radiusX.get(), radiusY.get(), appPaint.id, backgroundStyle.getStrokeWidth(), affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
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
        jsonObject.put("radiusX", radiusX.get());
        jsonObject.put("radiusY", radiusY.get());
        jsonObject.put("startAngle", startAngle.get());
        jsonObject.put("length", length.get());
        jsonObject.put("arcType", arcType.get().name());
        return jsonObject;
    }


}