package com.example.structures.enums;

public enum AppExceptionEnum {

    AppNodeNotRegistered(0),
    UnexpectedError(1),
    InvalidXYAxisType(2),
    UnknownFileExtension(3),
    ChartTypeNotRegistered(4)
    ;



    AppExceptionEnum(int code) {
        this.code = code;
    }

//    public int getCode() {
//        return code;
//    }

    public final int code;



}
