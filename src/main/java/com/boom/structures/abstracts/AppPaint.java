package com.boom.structures.abstracts;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Paint;
import org.json.JSONObject;

import static com.boom.tools.Tools.uuid;


public abstract class AppPaint extends  SimpleObjectProperty<Paint> {

    final String type;
    final String id;

    protected AppPaint(Paint paint) {
        id = uuid(100);
        set(paint);
        type = paint.getClass().getName();
    }

    public String getFormatted() {
        return get().toString().replaceAll("0x", "#");
    }

    public String getId() {
        return id;
    }

    public abstract String toTeX();

    public abstract JSONObject toJSON();

    public static AppPaint parseFromJSON(JSONObject jsonObject) {
        return null;
    }

    public abstract String toSVG(int tabIndent);

    public abstract AppPaint copy();

    public String getType() {
        return type;
    }

}