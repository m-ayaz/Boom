package com.boom.structures.enums;

public enum AppDataSortingPolicy {

    NumericalXSort(0),
    LiteralXSort(1),
    NumericalYSort(2),
    LiteralYSort(3),
    NoSort(4);
    //    SortByX (0),
//    SortByY (1),
//    StringSortByX (2),
//    StringSortByY (3),
//    SortByDefault (4);
//    SortByNumericX (0),
//    SortByNumericY (1),
//    SortByLiteralX (2),
//    SortByLiteralY (3),
//    SortByDefault (4);
    public final int id;

    AppDataSortingPolicy(int id) {
        this.id = id;
    }

}
