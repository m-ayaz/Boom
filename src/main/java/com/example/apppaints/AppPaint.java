package com.example.apppaints;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Paint;

public abstract class AppPaint {




    SimpleObjectProperty<Paint> paintProperty=new SimpleObjectProperty<>();

    AppPaint(Paint paint){
        super();
        paintProperty.set(paint);
    }

    public String get(){
        return paintProperty.get().toString().replaceAll("0x","#");
    }

    public SimpleObjectProperty<Paint> getPaintProperty(){
        return paintProperty;
    }

}
