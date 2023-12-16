package com.example.apppaints;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Paint;

public abstract class AppPaint {




    SimpleObjectProperty<Paint> paintProperty=new SimpleObjectProperty<>();

    public String getType() {
        return type;
    }

    String type;

    AppPaint(Paint paint){
        paintProperty.set(paint);
        type=paint.getClass().getName();
    }

    public String get(){
        return paintProperty.get().toString().replaceAll("0x","#");
    }

    public SimpleObjectProperty<Paint> getPaintProperty(){
        return paintProperty;
    }

}
