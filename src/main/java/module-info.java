module com.example.Boom {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;
    requires java.desktop;
    requires javafx.swing;

    opens com.example.indicators to javafx.fxml;
    exports com.example.indicators;
    opens com.example.Boom to javafx.fxml;
    exports com.example.Boom;
    exports com.example.controllers;
    opens com.example.controllers to javafx.fxml;
    exports com.example.appshapes;
    opens com.example.appshapes to javafx.fxml;
    opens com.example.icons to javafx.fxml;
    exports com.example.icons;
    opens com.example.test to javafx.fxml;
    exports com.example.test;
    exports com.example.exceptions;
    opens com.example.exceptions to javafx.fxml;
    opens com.example.appcharts.number_number to javafx.fxml;
    exports com.example.appcharts.number_number;
    opens com.example.appcharts.string_number to javafx.fxml;
    exports com.example.appcharts.string_number;
    opens com.example.appcharts.number_string to javafx.fxml;
    exports com.example.appcharts.number_string;
    opens com.example.styles to javafx.fxml;
    exports com.example.styles;
    exports com.example.apppaints;
    opens com.example.apppaints to javafx.fxml;
    exports com.example.panels.paint;
    opens com.example.panels.paint to javafx.fxml;
    exports com.example.structures.abstracts;
    opens com.example.structures.abstracts to javafx.fxml;
    exports com.example.structures.enums;
    opens com.example.structures.enums to javafx.fxml;
    exports com.example.structures.interfaces;
    opens com.example.structures.interfaces to javafx.fxml;
    exports com.example;
    opens com.example to javafx.fxml;

}
 