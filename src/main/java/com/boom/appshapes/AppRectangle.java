package com.boom.appshapes;

import com.boom.structures.abstracts.AppAreaShape;
import com.boom.structures.abstracts.AppPaint;
import com.boom.structures.enums.NodeTypeEnum;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.MatrixType;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import static com.boom.tools.Tools.deepCopy;
import static com.boom.tools.Tools.dissectAffineTransform;

public final class AppRectangle extends AppAreaShape {

    public AppRectangle(double width, double height) {
        super(new Rectangle(width, height));
    }

    @Override
    public AppRectangle copy() {
        if (getWidth() == 0 || getHeight() == 0)
            return null;
        AppRectangle newAppRectangle = new AppRectangle(getWidth(), getHeight());
        deepCopy(affineTransform, newAppRectangle.affineTransform);
        deepCopy(backgroundStyle, newAppRectangle.backgroundStyle);
        return newAppRectangle;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
        affineTransform.setToTransform(new Translate(Math.min(dragStartX, currentDragPosX), Math.min(dragStartY, currentDragPosY)));
        setWidth(Math.abs(currentDragPosX - dragStartX));
        setHeight(Math.abs(currentDragPosY - dragStartY));
    }

    public double getArcHeight() {
        return ((Rectangle) shape).getArcHeight();
    }

    public void setArcHeight(double arcHeight) {
        ((Rectangle) shape).setArcHeight(arcHeight);
    }

    public double getArcWidth() {
        return ((Rectangle) shape).getArcWidth();
    }

    public void setArcWidth(double arcWidth) {
        ((Rectangle) shape).setArcWidth(arcWidth);
    }


//    @Override
//    public AppNode parseFromJSON(JSONObject jsonObject) {
//        return null;
//    }

    public double getHeight() {
        return ((Rectangle) shape).getHeight();
    }

    public void setHeight(double height) {
        ((Rectangle) shape).setHeight(height);
    }

    public String getSVGClones(int tabIndent) {
        double[] dissectedTransform = dissectAffineTransform(affineTransform);
        StringBuilder stringBuilder = new StringBuilder();
        for (AppPaint appPaint : backgroundStyle.getFillArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<rect x=\"0\" y=\"0\" rx=\"0\" ry=\"0\" width=\"%f\" height=\"%f\" fill=\"url(#%s)\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(getWidth(), getHeight(), appPaint.id, affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
        }
        for (AppPaint appPaint : backgroundStyle.getStrokeArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<rect x=\"0\" y=\"0\" rx=\"0\" ry=\"0\" width=\"%f\" height=\"%f\" fill=\"transparent\" stroke=\"url(#%s)\" stroke-width=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(getWidth(), getHeight(), appPaint.id, backgroundStyle.getStrokeWidth(), affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
        }
        return stringBuilder.toString();
    }

    public double getWidth() {
        return ((Rectangle) shape).getWidth();
    }

    public void setWidth(double width) {
        ((Rectangle) shape).setWidth(width);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("id", id);
        jsonObject.put("affine",affineTransform.toArray(MatrixType.MT_2D_2x3));
        jsonObject.put("backgroundStyle",backgroundStyle.toJSON());
        jsonObject.put("width", getWidth());
        jsonObject.put("height", getHeight());
        jsonObject.put("arcWidth", getArcWidth());
        jsonObject.put("arcHeight", getArcHeight());
        return jsonObject;
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
