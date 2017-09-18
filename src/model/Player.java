package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Player{

    private static final FloatProperty xPos = new SimpleFloatProperty(0);
    private static final FloatProperty yPos = new SimpleFloatProperty(0);
    private static final FloatProperty speed = new SimpleFloatProperty(0);
    private static final FloatProperty jumpSpeed = new SimpleFloatProperty(0);
    private static final IntegerProperty fps = new SimpleIntegerProperty(0);

    private Player() {}

    public static void reset() {
        setxPos(0);
        setyPos(0);
        setSpeed(0);
        setJumpSpeed(0);
        setFPS(0);
    }

    public static int getFPS() {
        return fps.get();
    }

    public static void setFPS(int newFps) {
        fps.set(newFps);
    }

    public static IntegerProperty fpsProperty() {
        return fps;
    }

    public static float getXPos() {
        return xPos.get();
    }

    public static void setxPos(float newXPos){
        xPos.set(newXPos);
    }

    public static float getYPos() {
        return yPos.get();
    }

    public static void setyPos(float newYPos){
        yPos.set(newYPos);
    }

    public static float getSpeed() {
        return speed.get();
    }

    public static void setSpeed(float newSpeed) {
        speed.set(newSpeed);
    }

    public static float getJumpSpeed() {
        return jumpSpeed.get();
    }

    public static void setJumpSpeed(float newJumpSpeed) {
        jumpSpeed.set(newJumpSpeed);
    }

    public static FloatProperty xPosProperty() {
        return xPos;
    }

    public static FloatProperty yPosProperty() {
        return yPos;
    }

    public static FloatProperty speedProperty() {
        return speed;
    }

    public static FloatProperty jumpSpeedProperty() {
        return jumpSpeed;
    }
}
