package com.example.appshapes;

import com.example.structures.NodeTypeEnum;
import com.example.structures.AppShape;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static com.example.tools.Tools.deepCopy;
public class AppLine extends AppShape {
    public AppLine(double startX, double startY, double endX, double endY) {
        super(new Line(startX, startY, endX, endY));
        ((Line) node).setStroke(new Color(0, 0, 0, 1));
    }
    @Override
    public String toTeX() {
        Color strokeColor = Color.valueOf(((Line) node).getStroke().toString());
        return "\n\\begin{scope}[transform canvas = {cm = {%f,%f,%f,%f,(%fpt,%fpt)}}]".formatted(affineTransform.getMxx(), affineTransform.getMyx(), affineTransform.getMxy(), affineTransform.getMyy(), affineTransform.getTx(), affineTransform.getTy()) +
                "\n\t\\draw[" +
                "\n\t\tdraw = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)) +
                "\n\t\tdraw opacity = %f,".formatted(strokeColor.getOpacity()) +
                "\n\t\tline width = %fpt,".formatted(((Line) node).getStrokeWidth()) +
                (((Line) node).getStrokeDashArray().size() >= 2 ? "\n\t\tdashed, dash pattern = " + ((Line) node).getStrokeDashArray().stream().map(obj -> ((Line) node).getStrokeDashArray().indexOf(obj) % 2 == 0 ? "on " + obj : "off " + obj).collect(Collectors.joining(" ")) : "\n\t\tsolid") + "," +
                "\n\t]" +
                "\n\t(%fpt,%fpt) -- (%fpt,%fpt);".formatted(((Line) node).getStartX(), ((Line) node).getStartY(), ((Line) node).getEndX(), ((Line) node).getEndY()) +
                "\n\\end{scope}";
    }
    @Override
    public JSONObject toJSON() {
        Color strokeColor = Color.valueOf(((Line) node).getStroke().toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("object", NodeTypeEnum.Line.getNodeType());
        jsonObject.put("startX", ((Line) node).getStartX());
        jsonObject.put("startY", ((Line) node).getStartY());
        jsonObject.put("endX", ((Line) node).getEndX());
        jsonObject.put("endY", ((Line) node).getEndY());
        jsonObject.put("strokeColor", Arrays.asList(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)));
        jsonObject.put("strokeOpacity", Math.round(strokeColor.getOpacity()));
        jsonObject.put("strokeWidth", ((Line) node).getStrokeWidth());
        jsonObject.put("affineTransformation", Arrays.asList(affineTransform.getMxx(), affineTransform.getMxy(), affineTransform.getTx(), affineTransform.getMyx(), affineTransform.getMyy(), affineTransform.getTy()));
        return jsonObject;
    }
    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        node.setVisible(true);
        ((Line) node).setStartX(dragStartX);
        ((Line) node).setStartY(dragStartY);
        ((Line) node).setEndX(currentDragPosX);
        ((Line) node).setEndY(currentDragPosY);
    }
    @Override
    public AppLine copy()  {
        if (((Line) node).getStartX() == ((Line) node).getEndX() || ((Line) node).getStartY() == ((Line) node).getEndY())
            return null;
        AppLine newAppLine = new AppLine(0,0,0,0);
        deepCopy(affineTransform,newAppLine.affineTransform);
        deepCopy((Line) node, (Line) newAppLine.node);

        return newAppLine;
    }
    @Override
    public void setBackgroundColor(Paint paint) {
        ((Line) node).setFill(paint);
    }
    @Override
    public void setBorderColor(Paint paint) {
        ((Line) node).setStroke(paint);
    }
    @Override
    public void setBorderWidth(double borderWidth) {
        ((Line) node).setStrokeWidth(borderWidth);
    }
    @Override
    public void setBorderDashArray(List<Double> borderDashArray) {
        ((Line) node).getStrokeDashArray().addAll(borderDashArray);
    }
    public double getStartX() {
        return ((Line) node).getStartX();
    }
    public double getStartY() {
        return ((Line) node).getStartX();
    }
    public void setStartX(double startX) {
        ((Line) node).setStartX(startX);
    }
    public void setStartY(double startY) {
        ((Line) node).setStartY(startY);
    }

    public void setEndX(double endX) {
        ((Line) node).setStartX(endX);
    }
    public void setEndY(double endY) {
        ((Line) node).setStartY(endY);
    }

}