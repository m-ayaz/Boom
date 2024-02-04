package com.boom.structures.abstracts;

import com.boom.apppaints.AppColor;
import com.boom.apppaints.AppLinearGradient;
import com.boom.apppaints.AppRadialGradient;
import com.boom.exceptions.AppException;
import com.boom.structures.enums.AppExceptionEnum;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.*;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.boom.configuration.Configs.ID_LENGTH;
import static com.boom.tools.Tools.uuid;


public abstract class AppPaint extends  SimpleObjectProperty<Paint> {

    public final String type;
    public final String id = uuid(ID_LENGTH);

    protected AppPaint(Paint paint) {
        set(paint);
        type = paint.getClass().getName();
    }

    public String getFormatted() {
        return get().toString().replaceAll("0x", "#");
    }

    public abstract String toTeX();

    public abstract JSONObject toJSON();

//    public static AppPaint parseFromJSON(JSONObject jsonObject) {
//        return null;
//    }

    public abstract String toSVG(int tabIndent);

    public abstract AppPaint copy();

    protected abstract void update();

    public static AppPaint parseJSON(JSONObject jsonObject) {
        if (jsonObject.getString("type").equals(Color.class.getName())) {
            return new AppColor(Color.valueOf(jsonObject.getString("color")));
        } else if (jsonObject.getString("type").equals(LinearGradient.class.getName())) {
            JSONArray stopsProportions = jsonObject.getJSONArray("stopsProportions");
            JSONArray stopsColors = jsonObject.getJSONArray("stopsColors");
            Stop[] stops = new Stop[stopsProportions.length()];
            for (int i = 0; i < stops.length; i++) {
                stops[i] = new Stop(stopsProportions.getDouble(i), Color.valueOf(stopsColors.getString(i)));
            }
            return new AppLinearGradient(new LinearGradient(jsonObject.getDouble("startX"), jsonObject.getDouble("startY"), jsonObject.getDouble("endX"), jsonObject.getDouble("endY"), jsonObject.getBoolean("isProportional"), CycleMethod.valueOf(jsonObject.getString("cycleMethod")), stops));
        } else if (jsonObject.getString("type").equals(RadialGradient.class.getName())) {
            JSONArray stopsProportions = jsonObject.getJSONArray("stopsProportions");
            JSONArray stopsColors = jsonObject.getJSONArray("stopsColors");
            Stop[] stops = new Stop[stopsProportions.length()];
            for (int i = 0; i < stops.length; i++) {
                stops[i] = new Stop(stopsProportions.getDouble(i), Color.valueOf(stopsColors.getString(i)));
            }
            return new AppRadialGradient(new RadialGradient(jsonObject.getDouble("focusAngle"), jsonObject.getDouble("focusDistance"), jsonObject.getDouble("centerX"), jsonObject.getDouble("centerY"), jsonObject.getDouble("radius"), jsonObject.getBoolean("isProportional"), CycleMethod.valueOf(jsonObject.getString("cycleMethod")), stops));
        } else {
            throw new AppException(AppExceptionEnum.UnexpectedError);
        }
    }

}