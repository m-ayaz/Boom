package com.example.appshapes;

import com.example.structures.NodeTypeEnum;
import com.example.structures.AppShape;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.tools.Tools.deepCopy;

public class AppRectangle extends AppShape {

    public AppRectangle(double width, double height) {
        super(new Rectangle(0,0,width, height));
        ((Rectangle) node).setFill(new Color(0, 0, 0, 0));
        ((Rectangle) node).setStroke(new Color(0, 0, 0, 1));
    }

    @Override
    public String toTeX() {
        Color fillColor = Color.valueOf(((Rectangle) node).getFill().toString());
        Color strokeColor = Color.valueOf(((Rectangle) node).getStroke().toString());
        return "\n\\begin{scope}[transform canvas = {cm = {%f,%f,%f,%f,(%fpt,%fpt)}}]".formatted(affineTransform.getMxx(), affineTransform.getMyx(), affineTransform.getMxy(), affineTransform.getMyy(), affineTransform.getTx(), affineTransform.getTy()) +
                "\n\t\\draw[" +
                "\n\t\tfill = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(fillColor.getRed() * 255), Math.round(fillColor.getGreen() * 255), Math.round(fillColor.getBlue() * 255)) +
                "\n\t\tdraw = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)) +
                "\n\t\tfill opacity = %f,".formatted(fillColor.getOpacity()) +
                "\n\t\tdraw opacity = %f,".formatted(strokeColor.getOpacity()) +
                "\n\t\tline width = %fpt,".formatted(((Rectangle) node).getStrokeWidth()) +
                (((Rectangle) node).getStrokeDashArray().size() >= 2 ? "\n\t\tdashed, dash pattern = " + ((Rectangle) node).getStrokeDashArray().stream().map(obj -> ((Rectangle) node).getStrokeDashArray().indexOf(obj) % 2 == 0 ? "on " + obj : "off " + obj).collect(Collectors.joining(" ")) : "\n\t\tsolid") + "," +
                "\n\t]" +
                "\n\t(%fpt,%fpt) rectangle ++(%fpt,%fpt);".formatted(((Rectangle) node).getX(), ((Rectangle) node).getY(), ((Rectangle) node).getWidth(), ((Rectangle) node).getHeight()) +
                "\n\\end{scope}";
    }

    @Override
    public JSONObject toJSON() {
        Color fillColor = Color.valueOf(((Rectangle) node).getFill().toString());
        Color strokeColor = Color.valueOf(((Rectangle) node).getStroke().toString());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("object", NodeTypeEnum.Rectangle.getNodeType());
        jsonObject.put("x",((Rectangle) node).getX());
        jsonObject.put("y",((Rectangle) node).getY());
        jsonObject.put("width",((Rectangle) node).getWidth());
        jsonObject.put("height",((Rectangle) node).getHeight());
        jsonObject.put("fillColor", Arrays.asList(Math.round(fillColor.getRed() * 255), Math.round(fillColor.getGreen() * 255), Math.round(fillColor.getBlue() * 255)));
        jsonObject.put("fillOpacity",Math.round(fillColor.getOpacity()));
        jsonObject.put("strokeColor",Arrays.asList(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)));
        jsonObject.put("strokeOpacity",Math.round(strokeColor.getOpacity()));
        jsonObject.put("strokeWidth",((Rectangle) node).getStrokeWidth());
        jsonObject.put("affineTransformation",Arrays.asList(affineTransform.getMxx(),affineTransform.getMxy(),affineTransform.getTx(),affineTransform.getMyx(),affineTransform.getMyy(),affineTransform.getTy()));
        return jsonObject;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        node.setVisible(true);
        affineTransform.setToTransform(new Translate(Math.min(dragStartX, currentDragPosX),Math.min(dragStartY, currentDragPosY)) );
        ((Rectangle) node).setWidth(Math.abs(currentDragPosX - dragStartX));
        ((Rectangle) node).setHeight(Math.abs(currentDragPosY - dragStartY));
    }

    @Override
    public AppRectangle copy()  {

        if (((Rectangle) node).getWidth() == 0 || ((Rectangle) node).getHeight() == 0)
            return null;
        AppRectangle newAppRectangle = new AppRectangle(0,0);
        deepCopy(affineTransform,newAppRectangle.affineTransform);
        deepCopy((Rectangle) node, (Rectangle) newAppRectangle.node);

        return newAppRectangle;
    }

    @Override
    public void setBackgroundColor(Paint paint) {
        ((Rectangle) node).setFill(paint);
    }

    @Override
    public void setBorderColor(Paint paint) {
        ((Rectangle) node).setStroke(paint);
    }

    @Override
    public void setBorderWidth(double borderWidth) {
        ((Rectangle) node).setStrokeWidth(borderWidth);
    }

    @Override
    public void setBorderDashArray(List<Double> borderDashArray) {
        ((Rectangle) node).getStrokeDashArray().addAll(borderDashArray);
    }

    public double getWidth() {
        return ((Rectangle) node).getWidth();
    }

    public double getHeight() {
        return ((Rectangle) node).getHeight();
    }

    public void setWidth(double width){
        ((Rectangle) node).setWidth(width);
    }

    public void setHeight(double height){
        ((Rectangle) node).setHeight(height);
    }

//    public double getX(){
//        return ((Rectangle) node).getX();
//    }

}
