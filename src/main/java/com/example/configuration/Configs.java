package com.example.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.example.tools.Tools.print;

public final class Configs {

    public static HashMap<String, Number> configs = new HashMap<>();

    public static HashMap<String, List<String>> colorConfigs = new HashMap<>();

    public static void setDefaultConfig() {
        configs.put("ADD_SERIES_BUTTON_WIDTH", 40);
        configs.put("ADD_SERIES_BUTTON_HEIGHT", 40);
        configs.put("REMOVE_SERIES_BUTTON_WIDTH", 40);
        configs.put("REMOVE_SERIES_BUTTON_HEIGHT", 40);
        configs.put("ADD_DATA_BUTTON_WIDTH", 40);
        configs.put("ADD_DATA_BUTTON_HEIGHT", 40);
        configs.put("REMOVE_DATA_BUTTON_WIDTH", 40);
        configs.put("REMOVE_DATA_BUTTON_HEIGHT", 40);
        configs.put("ELLIPSE_BUTTON_ICON_WIDTH",25);
        configs.put("ELLIPSE_BUTTON_ICON_HEIGHT",13);
        configs.put("RECTANGLE_BUTTON_ICON_WIDTH",50);
        configs.put("RECTANGLE_BUTTON_ICON_HEIGHT",26);

        colorConfigs.put("SERIES_AREA_COLOR", Arrays.asList("0xfba71b33", "0x57b75733", "0x41a9c933", "0x4258c933", "0x9a42c833", "0xc8416433", "0x88888833", "0xf3622d33"));
        colorConfigs.put("SERIES_LINE_COLOR", Arrays.asList("0xfba71bff", "0x57b757ff", "0x41a9c9ff", "0x4258c9ff", "0x9a42c8ff", "0xc84164ff", "0x888888ff", "0xf3622dff"));
//        colorConfigs.put("SERIES_COLOR", Arrays.asList("0xff0000", "0x00ff00", "0x0000ff"));
    }

    public static void setConfigFromFile() {
        configs.clear();
    }

}