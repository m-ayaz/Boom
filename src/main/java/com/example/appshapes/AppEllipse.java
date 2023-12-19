package com.example.appshapes;

import com.example.structures.abstracts.AppRegion;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.json.JSONObject;

import static com.example.tools.Tools.deepCopy;

public class AppEllipse extends AppRegion {

    public AppEllipse(double width, double height) {
        super(new Ellipse(1,1));
        getRegion().setBackground(Background.fill(Color.TRANSPARENT));
        getRegion().setBorder(Border.stroke(Color.BLACK));
        setWidth(width);
        setHeight(height);
    }

    @Override
    public String toTeX() {
//        Color fillColor = Color.valueOf(((Ellipse) node).getFill().toString());
//        Color strokeColor = Color.valueOf(((Ellipse) node).getStroke().toString());
//
//        return "\n\\begin{scope}[transform canvas = {cm = {%f,%f,%f,%f,(%fpt,%fpt)}}]".formatted(affineTransform.getMxx(), affineTransform.getMyx(), affineTransform.getMxy(), affineTransform.getMyy(), affineTransform.getTx(), affineTransform.getTy()) +
//                "\n\t\\draw[" +
//                "\n\t\tfill = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(fillColor.getRed() * 255), Math.round(fillColor.getGreen() * 255), Math.round(fillColor.getBlue() * 255)) +
//                "\n\t\tdraw = {rgb,255:red,%d;green,%d;blue,%d},".formatted(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)) +
//                "\n\t\tfill opacity = %f,".formatted(fillColor.getOpacity()) +
//                "\n\t\tdraw opacity = %f,".formatted(strokeColor.getOpacity()) +
//                "\n\t\tline width = %fpt,".formatted(((Ellipse) node).getStrokeWidth()) +
//                (((Ellipse) node).getStrokeDashArray().size() >= 2 ? "\n\t\tdashed, dash pattern = " + ((Ellipse) node).getStrokeDashArray().stream().map(obj -> ((Ellipse) node).getStrokeDashArray().indexOf(obj) % 2 == 0 ? "on " + obj : "off " + obj).collect(Collectors.joining(" ")) : "\n\t\tsolid") + "," +
//                "\n\t]" +
//                "\n\t(%fpt,%fpt) ellipse (%fpt and %fpt);".formatted(((Ellipse) node).getCenterX(), ((Ellipse) node).getCenterY(), ((Ellipse) node).getRadiusX(), ((Ellipse) node).getRadiusY()) +
//                "\n\\end{scope}";
        return null;
    }

    @Override
    public String toSVG() {
        return null;
    }


    @Override
    public JSONObject toJSON() {
//        Color fillColor = Color.valueOf(((Ellipse) node).getFill().toString());
//        Color strokeColor = Color.valueOf(((Ellipse) node).getStroke().toString());
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("object", NodeTypeEnum.Ellipse.getNodeType());
//        jsonObject.put("centerX", ((Ellipse) node).getCenterX());
//        jsonObject.put("centerY", ((Ellipse) node).getCenterY());
//        jsonObject.put("radiusX", ((Ellipse) node).getRadiusX());
//        jsonObject.put("radiusY", ((Ellipse) node).getRadiusY());
//        jsonObject.put("fillColor", Arrays.asList(Math.round(fillColor.getRed() * 255), Math.round(fillColor.getGreen() * 255), Math.round(fillColor.getBlue() * 255)));
//        jsonObject.put("fillOpacity", Math.round(fillColor.getOpacity()));
//        jsonObject.put("strokeColor", Arrays.asList(Math.round(strokeColor.getRed() * 255), Math.round(strokeColor.getGreen() * 255), Math.round(strokeColor.getBlue() * 255)));
//        jsonObject.put("strokeOpacity", Math.round(strokeColor.getOpacity()));
//        jsonObject.put("strokeWidth", ((Ellipse) node).getStrokeWidth());
////        print(Arrays.asList(affineTransform.getMxx(), affineTransform.getMxy(), affineTransform.getTx(), affineTransform.getMyx(), affineTransform.getMyy(), affineTransform.getTy()));
//        jsonObject.put("affineTransformation", Arrays.asList(affineTransform.getMxx(), affineTransform.getMxy(), affineTransform.getTx(), affineTransform.getMyx(), affineTransform.getMyy(), affineTransform.getTy()));
        return jsonObject;
    }


    @Override
    public AppEllipse copy()  {
        if (getWidth()==0||getHeight()==0)
            return null;
        AppEllipse newAppEllipse = new AppEllipse(0, 0);
        deepCopy(affineTransform, newAppEllipse.affineTransform);
        deepCopy(getRegion(),newAppEllipse.getRegion());

        return newAppEllipse;
    }

}
