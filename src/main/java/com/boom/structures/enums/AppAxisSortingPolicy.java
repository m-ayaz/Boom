package com.boom.structures.enums;

public enum AppAxisSortingPolicy {
    SortByX (0),
    SortByY (1),
    SortByDefault (2);
//    SortByNumericX (0),
//    SortByNumericY (1),
//    SortByLiteralX (2),
//    SortByLiteralY (3),
//    SortByDefault (4);
        public final int id;
    AppAxisSortingPolicy(int id){
            this.id=id;
        }

}
