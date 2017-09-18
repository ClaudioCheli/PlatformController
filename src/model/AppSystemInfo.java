package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class AppSystemInfo {

    private static final FloatProperty fps = new SimpleFloatProperty(0);

    private AppSystemInfo() {}

    public static void reset() {
        setFPS(0);
    }

    public static void setFPS(float newFPS) {
        fps.set(newFPS);
    }

    public static float getFPS() {
        return fps.get();
    }

    public static FloatProperty fpsProperty() {
        return fps;
    }



}
