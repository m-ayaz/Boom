package com.boom.structures.abstracts;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;

public abstract class AppPaint extends  SimpleObjectProperty<Paint>{

//    protected SimpleObjectProperty<Paint> paintProperty = new SimpleObjectProperty<>();
    String type;
    protected String id;

    protected AppPaint(Paint paint, String id) {
        set(paint);
        type = paint.getClass().getName();
        this.id = id;
    }

    public String getFormatted() {
        return get().toString().replaceAll("0x", "#");
    }

    public String getId() {
        return id;
    }

    public abstract String toTeX();

    public abstract String toJSON();

    public abstract String toSVG(int tabIndent);

    public  abstract AppPaint copy(String id);



//    public String toSVG(int tabIndent) {
//        if (getClass().getName().equals(PaintTypeEnum.Color.getPaintType())) {
//            return "\n" + "\t".repeat(tabIndent) + "<linearGradient id=\"%s\">  <stop stop-color=\"%s\"/> </linearGradient>".formatted(id, paint.toString().replace("0x", "#"));
//        } else if (paint.getClass().getName().equals(PaintTypeEnum.LinearGradient.getPaintType())) {
//            StringBuilder stringBuilder = new StringBuilder();
//            LinearGradient linearGradient = (LinearGradient) this;
//            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<linearGradient id=\"%s\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" gradientUnits=\"%s\">".formatted(id,
//                    linearGradient.getStartX(),
//                    linearGradient.getStartY(),
//                    linearGradient.getEndX(),
//                    linearGradient.getEndY(),
//                    (linearGradient.isProportional() ? "objectBoundingBox" : "userSpaceOnUse")
//            ));
//            for (Stop stop : linearGradient.getStops()) {
//                stringBuilder.append(stopToSVG(stop, tabIndent + 1));
//            }
//            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("</linearGradient>");
//            return stringBuilder.toString();
//        } else {
//            throw new AppException(AppExceptionEnum.AppNodeNotRegistered);
//        }
//    }


//    public SimpleObjectProperty<Paint> getPaintProperty() {
//        return paintProperty;
//    }

    public String getType() {
        return type;
    }

}