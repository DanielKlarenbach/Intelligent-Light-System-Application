package IntelligentLightSystemApplication.Room;

import java.awt.*;
import java.util.ArrayList;

public class Sensor{
    // sensor's coordinates in the room
    private int x;
    private int y;
    private int z;

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    private final int radius=5;

    private boolean placed=false;

    public Sensor(int x, int y, int z){
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
            g2d.setColor(new Color(102,255,102));
            g2d.fillOval(x-radius,y-radius,2*radius,2*radius);
        }
        else{
            g2d.setColor(Color.green);
            g2d.fillOval(x-radius,y-radius,2*radius,2*radius);
        }
    }

    Sensor getAtSensor(int x, int y, ArrayList<Sensor> sensors) {
        for (int i = 0; i < sensors.size(); i++) {
            if ((x - sensors.get(i).getX()) * (x - sensors.get(i).getX()) + (y - sensors.get(i).getY()) * (y - sensors.get(i).getY()) <= (sensors.get(i).getRadius()+10) * (sensors.get(i).getRadius()+10))
                return sensors.get(i);
        }
        return null;
    }
}

