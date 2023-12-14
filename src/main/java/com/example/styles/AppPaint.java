package com.example.styles;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class AppPaint {

    Paint paint;

    AppPaint(Paint paint){
        this.paint=paint;
    }

    public String get(){
        return paint.toString().replaceAll("0x","#");
    }

}
