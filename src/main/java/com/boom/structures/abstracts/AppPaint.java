package com.boom.structures.abstracts;

import com.boom.configuration.Configs;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Paint;
import org.json.JSONObject;

import static com.boom.tools.Tools.uuid;


public abstract class AppPaint extends  SimpleObjectProperty<Paint> {

    public final String type;
    public final String id=uuid(Configs.ID_LENGTH);

    protected AppPaint(Paint paint) {
        set(paint);
        type = paint.getClass().getName();
    }

    public String getFormatted() {
        return get().toString().replaceAll("0x", "#");
    }

    public abstract String toTeX();

    public abstract JSONObject toJSON();

    public static AppPaint parseFromJSON(JSONObject jsonObject) {
        return null;
    }

    public abstract String toSVG(int tabIndent);

    public abstract AppPaint copy();

//    public String getType() {
//        return type;
//    }

}