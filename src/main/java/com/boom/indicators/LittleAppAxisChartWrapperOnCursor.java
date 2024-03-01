package com.boom.indicators;

import com.boom.appcharts.baseclasses.AppAxisChart;
import com.boom.appcharts.baseclasses.AppSeries;
import com.boom.structures.interfaces.LittleIndicatorStructure;

import static com.boom.tools.Tools.print;

public  class LittleAppAxisChartWrapperOnCursor extends AppAxisChart implements LittleIndicatorStructure {
    public LittleAppAxisChartWrapperOnCursor() {
        super(100,100);

        AppSeries appSeries=new AppSeries();

//        AppRectangle appRectangle=new AppRectangle(0,0,0,0);

//        print(uuid(10));

//        appSeries.setMarkerShape(appRectangle);

//        print(uuid(50));

//        print(appSeries.getVisualLegend().get());

        addSeries(appSeries);

//        print(uuid(50));

        appSeries.addData(1,4);
        appSeries.addData(2,1);
        appSeries.addData(3,3);
        appSeries.addData(4,2);
//        add
//        setRadiusX(10);
//        setRadiusY(5);
//        setCenterX(-15);
//        setCenterY(15);
//        setStartAngle(45);
//        setLength(270);
//        setType(ArcType.ROUND);
//        setFill(new Color(0.3, 1, 0.3, 1));
        setVisible(false);
    }

    @Override
    public void show(double centerX, double centerY) {
//        print(uuid(20));
        setVisible(true);
        setTranslateX(centerX);
        setTranslateY(centerY);
    }

    @Override
    public void hide() {
        setVisible(false);
    }
}
