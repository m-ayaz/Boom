package com.example.apppaints;

import com.example.structures.abstracts.AppPaint;
import javafx.scene.paint.Color;

public class AppColor extends AppPaint {

    public AppColor(Color color,String id){
        super(color,id);
    }

    @Override
    public String toTeX() {
        return null;
    }

    @Override
    public String toJSON() {
        return null;
    }

    @Override
    public String toSVG(int tabIndent) {
        return "\n"+"\t".repeat(tabIndent)+"<linearGradient id=\"%s\">  <stop stop-color=\"%s\"/> </linearGradient>".formatted(id, getFormatted());
    }
}

















