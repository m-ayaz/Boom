package com.boom.appshapes;

import com.boom.structures.abstracts.AppLineShape;
import com.boom.structures.abstracts.AppNode;
import com.boom.structures.abstracts.AppPaint;
import javafx.scene.shape.Line;
import org.json.JSONObject;

import static com.boom.tools.Tools.deepCopy;
import static com.boom.tools.Tools.dissectAffineTransform;

public class AppLine extends AppLineShape {
    public AppLine(double startX, double startY,double  endX, double endY) {
//        super(new Line(0,0,x,y));
//        backgroundStyle.addFill(0,new AppColor(Color.TRANSPARENT,uuid(50)));
//        backgroundStyle.addStroke(0,new AppColor(Color.BLACK,uuid(50)));
//        super(new Line(startX,startY,endX,endY),false);
        super(new Line(startX, startY, endX, endY));
//        ((Line) node).setStroke(new Color(0, 0, 0, 1));
//        region.setBackground(Background.fill(Color.TRANSPARENT));
//        region.setBorder(Border.stroke(Color.BLACK));
//        setWidth(x);
//        setHeight(y);
    }

//    public Region getRegion______________temp(){
//        return region;
//    }

    public String toTeX() {
//        Color strokeColor = Color.valueOf(((Line) node).getStroke().toString());
//        return "\n\\begin{scope}[transform canvas = {cm = {%f,%f,%f,%f,(%fpt,%fpt)}}]".formatted(affineTransform.getMxx(), affineTransform.getMyx(), affineTransform.getMxy(), affineTransform.getMyy(), affineTransform.getTx(), affineTransform.getTy()) +
//                "\n\t\\draw[" +
//                "\n\t\tdraw = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)) +
//                "\n\t\tdraw opacity = %f,".formatted(strokeColor.getOpacity()) +
//                "\n\t\tline width = %fpt,".formatted(((Line) node).getStrokeWidth()) +
//                (((Line) node).getStrokeDashArray().size() >= 2 ? "\n\t\tdashed, dash pattern = " + ((Line) node).getStrokeDashArray().stream().map(obj -> ((Line) node).getStrokeDashArray().indexOf(obj) % 2 == 0 ? "on " + obj : "off " + obj).collect(Collectors.joining(" ")) : "\n\t\tsolid") + "," +
//                "\n\t]" +
//                "\n\t(%fpt,%fpt) -- (%fpt,%fpt);".formatted(((Line) node).getStartX(), ((Line) node).getStartY(), ((Line) node).getEndX(), ((Line) node).getEndY()) +
//                "\n\\end{scope}";
        return null;
    }


//    public String toSVG() {
//        return null;
//    }

    @Override
    public JSONObject toJSON() {
//        Color strokeColor = Color.valueOf(((Line) node).getStroke().toString());
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("object", NodeTypeEnum.Line.getNodeType());
//        jsonObject.put("startX", ((Line) node).getStartX());
//        jsonObject.put("startY", ((Line) node).getStartY());
//        jsonObject.put("endX", ((Line) node).getEndX());
//        jsonObject.put("endY", ((Line) node).getEndY());
//        jsonObject.put("strokeColor", Arrays.asList(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)));
//        jsonObject.put("strokeOpacity", Math.round(strokeColor.getOpacity()));
//        jsonObject.put("strokeWidth", ((Line) node).getStrokeWidth());
//        jsonObject.put("affineTransformation", Arrays.asList(affineTransform.getMxx(), affineTransform.getMxy(), affineTransform.getTx(), affineTransform.getMyx(), affineTransform.getMyy(), affineTransform.getTy()));
//        return jsonObject;
        return null;
    }

    @Override
    public String getSVGClones(int tabIndent) {
        double[] dissectedTransform = dissectAffineTransform(affineTransform);
        StringBuilder stringBuilder = new StringBuilder();
//        for (AppPaint appPaint : backgroundStyle.getFillArray()) {
//            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<ellipse cx=\"%f\" cy=\"%f\" rx=\"%f\" ry=\"%f\" fill=\"url(#%s)\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(getWidth()/2, getHeight()/2,getWidth()/2, getHeight()/2, appPaint.getId(), affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
//        }
        for (AppPaint appPaint : backgroundStyle.getStrokeArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" stroke=\"url(#%s)\" stroke-width=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(getStartX(),getStartY(),getEndX(),getEndY(),appPaint.getId(),backgroundStyle.getStrokeWidth(),affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
//            " <line x1=\"0\" y1=\"0\" x2=\"%f\" y2=\"%f\" stroke=\"url(#%s)\" stroke-width=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(getWidth(),getHeight(),appPaint.getId(),backgroundStyle.getStrokeWidth(),affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]);
        }
        return stringBuilder.toString();
    }

    public double getStartX() {
        return ((Line) styleableNode).getStartX();
    }

    public void setStartX(double startX) {
        ((Line) styleableNode).setStartX(startX);
    }

    public double getStartY() {
        return ((Line) styleableNode).getStartY();
    }

    public void setStartY(double startY) {
        ((Line) styleableNode).setStartY(startY);
    }

    public double getEndX() {
        return ((Line) styleableNode).getEndX();
    }

    public void setEndX(double endX) {
        ((Line) styleableNode).setEndX(endX);
    }

    public double getEndY() {
        return ((Line) styleableNode).getEndY();
    }

    public void setEndY(double endY) {
        ((Line) styleableNode).setEndY(endY);
    }

    @Override
    public AppNode parseFromJSON(JSONObject jsonObject) {
        return null;
    }

    //    @Override
//    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
//        node.setVisible(true);
//        ((Line) node).setStartX(dragStartX);
//        ((Line) node).setStartY(dragStartY);
//        ((Line) node).setEndX(currentDragPosX);
//        ((Line) node).setEndY(currentDragPosY);
//    }
    @Override
    public AppLine copy()  {
        if (getStartX()==getStartY()||getEndX()==getEndY())
            return null;
        AppLine newAppLine = new AppLine(getStartX(),getStartY(),getEndX(),getEndY());
        deepCopy(affineTransform,newAppLine.affineTransform);

//        deepCopy((Line) node, (Line) newAppLine.node);

        return newAppLine;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {

    }

//    public double getHeight() {
//        return 0;
//    }
//
//    public void setHeight(double height) {
//
//    }
//    @Override
//    public void setBackgroundColor(Paint paint) {
//        ((Line) node).setFill(paint);
//    }
//    @Override
//    public void setBorderColor(Paint paint) {
//        ((Line) node).setStroke(paint);
//    }
//    @Override
//    public void setBorderWidth(double borderWidth) {
//        ((Line) node).setStrokeWidth(borderWidth);
//    }
//    @Override
//    public void setBorderDashArray(List<Double> borderDashArray) {
//        ((Line) node).getStrokeDashArray().addAll(borderDashArray);
//    }
//    public double getStartX() {
//        return ((Line) node).getStartX();
//    }
//    public double getStartY() {
//        return ((Line) node).getStartX();
//    }
//    public void setStartX(double startX) {
//        ((Line) node).setStartX(startX);
//    }
//    public void setStartY(double startY) {
//        ((Line) node).setStartY(startY);
//    }
//
//    public void setEndX(double endX) {
//        ((Line) node).setStartX(endX);
//    }
//    public void setEndY(double endY) {
//        ((Line) node).setStartY(endY);
//    }

}