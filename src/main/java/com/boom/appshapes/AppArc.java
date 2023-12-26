package com.boom.appshapes;

import com.boom.structures.abstracts.AppAreaShape;
import com.boom.structures.abstracts.AppNode;
import com.boom.structures.abstracts.AppPaint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import static com.boom.tools.Tools.deepCopy;
import static com.boom.tools.Tools.dissectAffineTransform;

public final class AppArc extends AppAreaShape {

    public AppArc(double radiusX, double radiusY, double startAngle, double length, ArcType arcType) {
        super(new Arc(0, 0, radiusX, radiusY, startAngle, length));
        setArcType(arcType);
//        backgroundStyle.addFill(0,new AppColor(Color.TRANSPARENT,uuid(50)));
//        backgroundStyle.addStroke(0,new AppColor(Color.BLACK,uuid(50)));
////        region.setBackground(Background.fill(Color.TRANSPARENT));
////        region.setBorder(Border.stroke(Color.BLACK));
//        setWidth(width);
//        setHeight(height);
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
////                "\n\t(%fpt,%fpt) ellipse (%fpt and %fpt);".formatted(((Arc) node).getCenterX(), ((Arc) node).getCenterY(), ((Arc) node).getRadiusX(), ((Arc) node).getRadiusY()) +
////                "\n\\end{scope}";
//        return null;
//    }


//    public String toSVG() {
//        return null;
//    }

    @Override
    public AppArc copy() {
        if (getRadiusX() == 0 || getRadiusY() == 0||getLength()==0)
            return null;
        AppArc newAppArc = new AppArc(getRadiusX(), getRadiusY(), getStartAngle(), getLength(),getArcType());
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
        setRadiusX(Math.abs(currentDragPosX - dragStartX) / 2);
        setRadiusY(Math.abs(currentDragPosY - dragStartY) / 2);
//        print("_________________________");
//        print(Math.abs(currentDragPosX - dragStartX) / 2+" , "+Math.abs(currentDragPosY - dragStartY) / 2);
    }

    public double getLength() {
        return ((Arc) shape).getLength();
    }

    public void setLength(double length) {
        ((Arc) shape).setLength(length);
    }

    public ArcType getArcType() {
        return ((Arc) shape).getType();
    }

    public void setArcType(ArcType arcType) {
        ((Arc) shape).setType(arcType);
    }

    public double getRadiusX() {
        return ((Arc) shape).getRadiusX();
    }

    public void setRadiusX(double radiusX) {
        ((Arc) shape).setRadiusX(radiusX);
    }

    public double getRadiusY() {
        return ((Arc) shape).getRadiusY();
    }

    public void setRadiusY(double radiusY) {
        ((Arc) shape).setRadiusY(radiusY);
    }

    @Override
    public String getSVGClones(int tabIndent) {
        double[] dissectedTransform = dissectAffineTransform(affineTransform);
        StringBuilder stringBuilder = new StringBuilder();
        for (AppPaint appPaint : backgroundStyle.getFillArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<ellipse cx=\"%f\" cy=\"%f\" rx=\"%f\" ry=\"%f\" fill=\"url(#%s)\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(getRadiusX(), getRadiusY(), getRadiusX(), getRadiusY(), appPaint.getId(), affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
        }
        for (AppPaint appPaint : backgroundStyle.getStrokeArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<ellipse cx=\"%f\" cy=\"%f\" rx=\"%f\" ry=\"%f\" fill=\"transparent\" stroke=\"url(#%s)\" stroke-width=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(getRadiusX(), getRadiusY(), getRadiusX(), getRadiusY(), appPaint.getId(), backgroundStyle.getStrokeWidth(), affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
        }
        return stringBuilder.toString();
    }

    public double getStartAngle() {
        return ((Arc) shape).getStartAngle();
    }

    public void setStartAngle(double startAngle) {
        ((Arc) shape).setStartAngle(startAngle);
    }

    @Override
    public AppNode parseFromJSON(JSONObject jsonObject) {
        return null;
    }

    @Override
    public JSONObject toJSON() {
//        Color fillColor = Color.valueOf(((Arc) node).getFill().toString());
//        Color strokeColor = Color.valueOf(((Arc) node).getStroke().toString());
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("object", NodeTypeEnum.Arc.getNodeType());
//        jsonObject.put("centerX", ((Arc) node).getCenterX());
//        jsonObject.put("centerY", ((Arc) node).getCenterY());
//        jsonObject.put("radiusX", ((Arc) node).getRadiusX());
//        jsonObject.put("radiusY", ((Arc) node).getRadiusY());
//        jsonObject.put("fillColor", Arrays.asList(Math.round(fillColor.getRed() * 255), Math.round(fillColor.getGreen() * 255), Math.round(fillColor.getBlue() * 255)));
//        jsonObject.put("fillOpacity", Math.round(fillColor.getOpacity()));
//        jsonObject.put("strokeColor", Arrays.asList(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)));
//        jsonObject.put("strokeOpacity", Math.round(strokeColor.getOpacity()));
//        jsonObject.put("strokeWidth", ((Arc) node).getStrokeWidth());
////        print(Arrays.asList(affineTransform.getMxx(), affineTransform.getMxy(), affineTransform.getTx(), affineTransform.getMyx(), affineTransform.getMyy(), affineTransform.getTy()));
//        jsonObject.put("affineTransformation", Arrays.asList(affineTransform.getMxx(), affineTransform.getMxy(), affineTransform.getTx(), affineTransform.getMyx(), affineTransform.getMyy(), affineTransform.getTy()));
        return jsonObject;
    }


}
