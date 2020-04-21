package IntelligentLightSystemApplication.Room;

import java.awt.*;
import java.util.ArrayList;

public class LightSource{
    //
    private int x;
    private int y;
    private int z;
    private final int radius=5;
    private Color color=Color.yellow;

    private boolean placed=false;

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getAxisZ() {
        return axisZ;
    }

    public void setAxisZ(int axisZ) {
        this.axisZ = axisZ;
    }

    // optic axis coordinates
    private int axisX;
    private int axisY;
    private int axisZ;

    private double solidAngle;

    public int getAxisX() {
        return axisX;
    }

    public void setAxisX(int axisX) {
        this.axisX = axisX;
    }

    public int getAxisY() {
        return axisY;
    }

    public void setAxisY(int axisY) {
        this.axisY = axisY;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public LightSource(int x, int y, int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public void draw(Graphics2D g2d){
        if(isPlaced()==false){
            color=new Color(255,255,153);
            g2d.setColor(color);
            g2d.fillOval(x-radius,y-radius,2*radius,2*radius);
        }
        else{
            g2d.setColor(color);
            g2d.fillOval(x-radius,y-radius,2*radius,2*radius);
        }
    }

    static LightSource getAtLightSource(int x, int y, ArrayList<LightSource> lightSources) {
        for (int i = 0; i < lightSources.size(); i++) {
            if ((x - lightSources.get(i).getX()) * (x - lightSources.get(i).getX()) + (y - lightSources.get(i).getY()) * (y - lightSources.get(i).getY()) <= (lightSources.get(i).getRadius()+10) * (lightSources.get(i).getRadius()+10))
                return lightSources.get(i);
        }
        return null;
    }
}
