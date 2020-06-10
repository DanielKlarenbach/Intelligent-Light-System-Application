package IntelligentLightSystemApplication.Room;

import IntelligentLightSystemApplication.ModeMenu.ModeMenu;

import javax.swing.*;

public class Window {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private double shadow;
    private double height=1.5;

    // setters and getters
    public int getX1() { return x1; }
    public int getX2() { return x2; }

    public void setX1(int x1) { this.x1 = x1; }
    public void setX2(int x2) { this.x2 = x2; }

    public int getY1() { return y1; }
    public int getY2() { return y2; }

    public void setY1(int y1) { this.y1 = y1; }
    public void setY2(int y2) { this.y2 = y2; }

    public double getShadow(){
        this.shadow = 0.8/Math.tan(Math.toRadians(45));
        return shadow;
    }
    public double getHeight(){
        return height;
    }

}