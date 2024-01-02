package com.boom.structures.enums;

public enum AppExceptionEnum {

    AppNodeNotRegistered(0),
    UnexpectedError(1),
    InvalidXYAxisType(2),
    UnknownFileExtension(3),
    ChartTypeNotRegistered(4),
    SoftwareArchNeedsUpdate(5)
    ;



    AppExceptionEnum(int code) {
        this.code = code;
    }

    public static AppExceptionEnum getEnum(String name){
        return valueOf(name);


    }

    public final int code;



}
