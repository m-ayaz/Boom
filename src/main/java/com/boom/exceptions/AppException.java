package com.boom.exceptions;

import com.boom.structures.enums.AppExceptionEnum;

import static com.boom.tools.Tools.print;

public class AppException extends RuntimeException{

    AppExceptionEnum code;

    public AppException(AppExceptionEnum code){
        super();
        this.code=code;
        for(int i=0;i< getStackTrace().length;i++){
//            print("____________________________________________________________________");
            print("code = "+AppExceptionEnum.getEnum(code.toString()));
            print(getStackTrace()[i].toString());
//            MimeType mimeType=new MimeType();
//            mimeType.set
        }

//        Messa
//
//        setStackTrace(new StackTraceElement[]{new StackTraceElement("asdasd","sklad","asa",3)});
//        printStackTrace();
    }

    void informViaEmail(){

    }

}
