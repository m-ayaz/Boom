package com.boom.appshapes;

import com.boom.structures.abstracts.AppLineShape;
import com.boom.structures.abstracts.AppPaint;
import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Line;
import javafx.scene.transform.MatrixType;
import org.json.JSONObject;

import static com.boom.tools.Tools.deepCopy;
import static com.boom.tools.Tools.dissectAffineTransform;

public final class AppLine extends AppLineShape {

    public DoubleProperty startX, startY, endX, endY;

    public AppLine(double startX, double startY, double endX, double endY) {
        super(new Line(startX, startY, endX, endY));
        this.startX = ((Line) styleableNode).startXProperty();
        this.startY = ((Line) styleableNode).startYProperty();
        this.endX = ((Line) styleableNode).endXProperty();
        this.endY = ((Line) styleableNode).endYProperty();
    }

    public String toTeX() {
//        Color strokeColor = Color.valueOf(((Line) node).getStroke().toString());
//        return "\n\\begin{scope}[transform canvas = {cm = {%f,%f,%f,%f,(%fpt,%fpt)}}]".formatted(affineTransform.getMxx(), affineTransform.getMyx(), affineTransform.getMxy(), affineTransform.getMyy(), affineTransform.getTx(), affineTransform.getTy()) +
//                "\n\t\\draw[" +
//                "\n\t\tdraw = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)) +
//                "\n\t\tdraw opacity = %f,".formatted(strokeColor.getOpacity()) +
//                "\n\t\tline width = %fpt,".formatted(((Line) node).getStrokeWidth()) +
//                (((Line) node).getStrokeDashArray().size() >= 2 ? "\n\t\tdashed, dash pattern = " + ((Line) node).getStrokeDashArray().stream().map(obj -> ((Line) node).getStrokeDashArray().indexOf(obj) % 2 == 0 ? "on " + obj : "off " + obj).collect(Collectors.joining(" ")) : "\n\t\tsolid") + "," +
//                "\n\t]" +
//                "\n\t(%fpt,%fpt) -- (%fpt,%fpt);".formatted(((Line) node).startX.get(), ((Line) node).startY.get(), ((Line) node).endX.get(), ((Line) node).endY.get()) +
//                "\n\\end{scope}";
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
        jsonObject.put("endX", endX.get());
        jsonObject.put("endY", endY.get());
        return jsonObject;
    }

    @Override
    public String getSVGClones(int tabIndent) {
        double[] dissectedTransform = dissectAffineTransform(affineTransform);
        StringBuilder stringBuilder = new StringBuilder();
        for (AppPaint appPaint : backgroundStyle.getStrokeArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" stroke=\"url(#%s)\" stroke-width=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(startX.get(), startY.get(), endX.get(), endY.get(), appPaint.id, backgroundStyle.getStrokeWidth(), affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
        }
        return stringBuilder.toString();
    }

    @Override
    public AppLine copy() {
        if (startX.get() == startY.get() && endX.get() == endY.get())
            return null;
        AppLine newAppLine = new AppLine(startX.get(), startY.get(), endX.get(), endY.get());
        deepCopy(affineTransform, newAppLine.affineTransform);
        deepCopy(backgroundStyle, newAppLine.backgroundStyle);
        return newAppLine;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
        startX.set(dragStartX);
        startY.set(dragStartY);
        endX.set(currentDragPosX);
        endY.set(currentDragPosY);
    }

}