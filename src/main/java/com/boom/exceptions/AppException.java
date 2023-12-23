package com.boom.exceptions;

import com.boom.structures.enums.AppExceptionEnum;

public class AppException extends RuntimeException{

    AppExceptionEnum code;

    public AppException(AppExceptionEnum code){
        super();
        this.code=code;
        printStackTrace();
    }

    void informViaEmail(){

    }

}
