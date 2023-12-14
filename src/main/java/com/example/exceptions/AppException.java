package com.example.exceptions;

import com.example.structures.AppExceptionEnum;

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
