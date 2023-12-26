package com.boom.apppaints;

import com.boom.structures.abstracts.AppPaint;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public final class AppColor extends AppPaint {

    public AppColor(Color color,String id){
        super(color,id);
    }

    @Override
    public String toTeX() {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("color",getFormatted());
        return jsonObject;
    }

    @Override
    public void parseFromJSON(JSONObject jsonObject) {

    }

    @Override
    public String toSVG(int tabIndent) {
        return "\n"+"\t".repeat(tabIndent)+"<linearGradient id=\"%s\">  <stop stop-color=\"%s\"/> </linearGradient>".formatted(id, getFormatted());
    }

    @Override
    public AppPaint copy(String id) {
        return new AppColor(Color.valueOf(getFormatted()),id);
    }
}

















