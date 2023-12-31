package com.boom.appshapes;

import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.controllers.eventhandlers.mousehandler.AppMouseEventHandler;
import com.boom.structures.abstracts.AppAreaShape;
import com.boom.structures.abstracts.AppPaint;
import com.boom.structures.enums.NodeTypeEnum;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.MatrixType;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import static com.boom.tools.Tools.*;
import static com.boom.tools.Tools.print;

public final class AppRectangle extends AppAreaShape {

    public DoubleProperty width, height, arcWidth, arcHeight;

    public AppRectangle(double width, double height) {
        super(new Rectangle(width, height));
        this.width = ((Rectangle) shape).widthProperty();
        this.height = ((Rectangle) shape).heightProperty();
        this.arcWidth = ((Rectangle) shape).arcWidthProperty();
        this.arcHeight = ((Rectangle) shape).arcHeightProperty();

    }

    @Override
    public AppRectangle copy() {
        if (width.get() == 0 || height.get() == 0)
            return null;
        AppRectangle newAppRectangle = new AppRectangle(width.get(), height.get());
        deepCopy(affineTransform, newAppRectangle.affineTransform);
        deepCopy(backgroundStyle, newAppRectangle.backgroundStyle);
        return newAppRectangle;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
        affineTransform.setToTransform(new Translate(Math.min(dragStartX, currentDragPosX), Math.min(dragStartY, currentDragPosY)));
        width.set(Math.abs(currentDragPosX - dragStartX));
        height.set(Math.abs(currentDragPosY - dragStartY));
    }


    public String getSVGClones(int tabIndent) {
        double[] dissectedTransform = dissectAffineTransform(affineTransform);
        StringBuilder stringBuilder = new StringBuilder();
        for (AppPaint appPaint : backgroundStyle.getFillArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<rect x=\"0\" y=\"0\" rx=\"0\" ry=\"0\" width=\"%f\" height=\"%f\" fill=\"url(#%s)\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(width.get(), height.get(), appPaint.id, affineTransform.getTx() + offset.getX(), affineTransform.getTy() + offset.getY(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
        }
        for (AppPaint appPaint : backgroundStyle.getStrokeArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<rect x=\"0\" y=\"0\" rx=\"0\" ry=\"0\" width=\"%f\" height=\"%f\" fill=\"transparent\" stroke=\"url(#%s)\" stroke-width=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(width.get(), height.get(), appPaint.id, backgroundStyle.getStrokeWidth(), affineTransform.getTx() + offset.getX(), affineTransform.getTy() + offset.getY(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
        }
        return stringBuilder.toString();
    }


    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("id", id);
        jsonObject.put("affine", affineTransform.toArray(MatrixType.MT_2D_2x3));
        jsonObject.put("backgroundStyle", backgroundStyle.toJSON());
        jsonObject.put("width", width.get());
        jsonObject.put("height", height.get());
        jsonObject.put("arcWidth", arcWidth.get());
        jsonObject.put("arcHeight", arcHeight.get());
        return jsonObject;
    }



    @Override
    public void configureOnMouseEvent(MouseEvent mouseEvent, MainCanvasItemsHandler mainCanvasItemsHandler, SelectedObjectsController selectedObjectsController, double moveX, double moveY, double dragX, double dragY , double pressX , double pressY , double releaseX , double releaseY , double clickX , double clickY , double x , double y)    {
        if (drawingStage == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            drawingStage++;
        } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
            selectedObjectsController.unselectAll();
            draw(pressX, pressY, moveX, moveY);
        } else if (drawingStage == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            mainCanvasItemsHandler.copyToMainCanvas(this);
            drawingStage = 0;
        }
    }

    public String toTeX() {
//        Color fillColor = (Color) getRegion().getBackground().getFills().get(0).getFill();
//        Color strokeColor = (Color) getRegion().getBorder().getStrokes().get(0).getTopStroke();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\\begin{scope}[transform canvas = {cm = {%f,%f,%f,%f,(%fpt,%fpt)}}]".formatted(affineTransform.getMxx(), affineTransform.getMyx(), affineTransform.getMxy(), affineTransform.getMyy(), affineTransform.getTx(), affineTransform.getTy()));
//         for(int i=0;i<backgroundStyle.getFillArray().size();i++) {
//             stringBuilder
//                     .append("\n\t\\draw[")
//                     .append("\n\t\tfill = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(fillColor.getRed() * 255), Math.round(fillColor.getGreen() * 255), Math.round(fillColor.getBlue() * 255)))
//                     .append("\n\t\tdraw = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)))
//                     .append("\n\t\tfill opacity = %f,".formatted(fillColor.getOpacity()))
//                     .append("\n\t\tdraw opacity = %f,".formatted(strokeColor.getOpacity()))
//                     .append("\n\t\tline width = %fpt,".formatted(((Rectangle) node).getStrokeWidth()))
//                     .append(((Rectangle) node).getStrokeDashArray().size() >= 2 ? "\n\t\tdashed, dash pattern = " + ((Rectangle) node).getStrokeDashArray().stream().map(obj -> ((Rectangle) node).getStrokeDashArray().indexOf(obj) % 2 == 0 ? "on " + obj : "off " + obj).collect(Collectors.joining(" ")) : "\n\t\tsolid")
//                     .append(",")
//                     .append("\n\t]")
//                     .append("\n\t(%fpt,%fpt) rectangle ++(%fpt,%fpt);".formatted(((Rectangle) node).getX(), ((Rectangle) node).getY(), getWidth(), getHeight()));
//         }
//                "\n\\end{scope}";
//        return "\n\\begin{scope}[transform canvas = {cm = {%f,%f,%f,%f,(%fpt,%fpt)}}]".formatted(affineTransform.getMxx(), affineTransform.getMyx(), affineTransform.getMxy(), affineTransform.getMyy(), affineTransform.getTx(), affineTransform.getTy()) +
//                "\n\t\\draw[" +
//                "\n\t\tfill = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(fillColor.getRed() * 255), Math.round(fillColor.getGreen() * 255), Math.round(fillColor.getBlue() * 255)) +
//                "\n\t\tdraw = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)) +
//                "\n\t\tfill opacity = %f,".formatted(fillColor.getOpacity()) +
//                "\n\t\tdraw opacity = %f,".formatted(strokeColor.getOpacity()) +
//                "\n\t\tline width = %fpt,".formatted(((Rectangle) node).getStrokeWidth()) +
//                (((Rectangle) node).getStrokeDashArray().size() >= 2 ? "\n\t\tdashed, dash pattern = " + ((Rectangle) node).getStrokeDashArray().stream().map(obj -> ((Rectangle) node).getStrokeDashArray().indexOf(obj) % 2 == 0 ? "on " + obj : "off " + obj).collect(Collectors.joining(" ")) : "\n\t\tsolid") + "," +
//                "\n\t]" +
//                "\n\t(%fpt,%fpt) rectangle ++(%fpt,%fpt);".formatted(((Rectangle) node).getX(), ((Rectangle) node).getY(), getWidth(), getHeight()) +
//                "\n\\end{scope}";
        return null;
    }


}
