module com.boom.Boom {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires javafx.swing;

    opens com.boom.indicators to javafx.fxml;
    exports com.boom.indicators;
    opens com.boom.Boom to javafx.fxml;
    exports com.boom.Boom;
    exports com.boom.controllers;
    opens com.boom.controllers to javafx.fxml;
    exports com.boom.appshapes;
    opens com.boom.appshapes to javafx.fxml;
    opens com.boom.icons to javafx.fxml;
    exports com.boom.icons;
    opens com.boom.test to javafx.fxml;
    exports com.boom.test;
    exports com.boom.exceptions;
    opens com.boom.exceptions to javafx.fxml;
    opens com.boom.appcharts.number_number to javafx.fxml;
    exports com.boom.appcharts.number_number;
    opens com.boom.appcharts.string_number to javafx.fxml;
    exports com.boom.appcharts.string_number;
    opens com.boom.appcharts.number_string to javafx.fxml;
    exports com.boom.appcharts.number_string;
    opens com.boom.styles to javafx.fxml;
    exports com.boom.styles;
    exports com.boom.apppaints;
    opens com.boom.apppaints to javafx.fxml;
    exports com.boom.panels.paint;
    opens com.boom.panels.paint to javafx.fxml;
    exports com.boom.structures.abstracts;
    opens com.boom.structures.abstracts to javafx.fxml;
    exports com.boom.structures.enums;
    opens com.boom.structures.enums to javafx.fxml;
    exports com.boom.structures.interfaces;
    opens com.boom.structures.interfaces to javafx.fxml;
    exports com.boom;
    opens com.boom to javafx.fxml;

}
 