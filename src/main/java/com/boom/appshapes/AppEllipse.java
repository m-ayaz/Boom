package com.boom.appshapes;

import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.structures.abstracts.AppAreaShape;
import com.boom.structures.abstracts.AppPaint;
import javafx.beans.property.DoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.MatrixType;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import java.util.List;

import static com.boom.tools.Tools.*;

public  class AppEllipse extends AppAreaShape {
    
    public DoubleProperty radiusX,radiusY;

    public AppEllipse(double radiusX, double radiusY) {
        super(new Ellipse(radiusX, radiusY));
        this.radiusX=((Ellipse) shape).radiusXProperty();
        this.radiusY=((Ellipse) shape).radiusYProperty();
    }


//    public String toTeX() {
////        Color fillColor = Color.valueOf(((Ellipse) node).getFill().toString());
////        Color strokeColor = Color.valueOf(((Ellipse) node).getStroke().toString());
////
////        return "\n\\begin{scope}[transform canvas = {cm = {%f,%f,%f,%f,(%fpt,%fpt)}}]".formatted(affineTransform.getMxx(), affineTransform.getMyx(), affineTransform.getMxy(), affineTransform.getMyy(), affineTransform.getTx(), affineTransform.getTy()) +
////                "\n\t\\draw[" +
////                "\n\t\tfill = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(fillColor.getRed() * 255), Math.round(fillColor.getGreen() * 255), Math.round(fillColor.getBlue() * 255)) +
////                "\n\t\tdraw = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)) +
////                "\n\t\tfill opacity = %f,".formatted(fillColor.getOpacity()) +
////                "\n\t\tdraw opacity = %f,".formatted(strokeColor.getOpacity()) +
////                "\n\t\tline width = %fpt,".formatted(((Ellipse) node).getStrokeWidth()) +
////                (((Ellipse) node).getStrokeDashArray().size() >= 2 ? "\n\t\tdashed, dash pattern = " + ((Ellipse) node).getStrokeDashArray().stream().map(obj -> ((Ellipse) node).getStrokeDashArray().indexOf(obj) % 2 == 0 ? "on " + obj : "off " + obj).collect(Collectors.joining(" ")) : "\n\t\tsolid") + "," +
////                "\n\t]" +
////                "\n\t(%fpt,%fpt) ellipse (%fpt and %fpt);".formatted(((Ellipse) node).getCenterX(), ((Ellipse) node).getCenterY(), ((Ellipse) node).radiusX.get(), ((Ellipse) node).radiusY.get()) +
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
            mainCanvasItemsHandler.copyToMainCanvas(this);
            configStep = 0;
        }
    }

    @Override
    public AppEllipse copy() {
        if (radiusX.get() == 0 || radiusY.get() == 0)
            return null;
        AppEllipse newAppEllipse = new AppEllipse(radiusX.get(), radiusY.get());
        deepCopy(affineTransform, newAppEllipse.affineTransform);
        deepCopy(backgroundStyle, newAppEllipse.backgroundStyle);
        return newAppEllipse;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
        affineTransform.setToTransform(new Translate(Math.min(dragStartX, currentDragPosX) + Math.abs(currentDragPosX - dragStartX) / 2, Math.min(dragStartY, currentDragPosY) + Math.abs(currentDragPosY - dragStartY) / 2));
        radiusX.set(Math.abs(currentDragPosX - dragStartX) / 2);
        radiusY.set(Math.abs(currentDragPosY - dragStartY) / 2);
    }

    @Override
    public String toSVG(int tabIndent) {
        double[] dissectedTransform = dissectAffineTransform(affineTransform);
//        print("Hey there");
//        print(affineTransform);
//        for (double v : dissectedTransform) {
//            print(v);
//        }
        StringBuilder stringBuilder = new StringBuilder();
        for (AppPaint appPaint : backgroundStyle.getFillArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<ellipse cx=\"0\" cy=\"0\" rx=\"%f\" ry=\"%f\" fill=\"url(#%s)\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted( radiusX.get(), radiusY.get(), appPaint.id, affineTransform.getTx()+offset.getX()*0, affineTransform.getTy()+0*offset.getY(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
        }
        for (AppPaint appPaint : backgroundStyle.getStrokeArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<ellipse cx=\"0\" cy=\"0\" rx=\"%f\" ry=\"%f\" fill=\"transparent\" stroke=\"url(#%s)\" stroke-width=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted( radiusX.get(), radiusY.get(), appPaint.id, backgroundStyle.getStrokeWidth(), affineTransform.getTx()+offset.getX()*0, affineTransform.getTy()+offset.getY()*0, dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
        }
        return stringBuilder.toString();
    }


    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("id", id);
        jsonObject.put("affine",arrayToList(affineTransform.toArray(MatrixType.MT_2D_2x3)));
        jsonObject.put("backgroundStyle",backgroundStyle.toJSON());
        jsonObject.put("radiusX", radiusX.get());
        jsonObject.put("radiusY", radiusY.get());
        return jsonObject;
    }


    @Override
    public String toTeX() {
        return null;
    }
}
