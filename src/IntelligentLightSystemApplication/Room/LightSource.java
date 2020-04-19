package IntelligentLightSystemApplication.Room;

import java.awt.*;

public class LightSource{
    private int x;
    private int y;

    private final int radius=5;

    private boolean placed=false;

    public LightSource(int x, int y){
        this.x=x;
        this.y=y;
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
            g2d.setColor(new Color(255,255,153));
            g2d.fillOval(x-radius,y-radius,2*radius,2*radius);
        }
        else{
            g2d.setColor(Color.yellow);
            g2d.fillOval(x-radius,y-radius,2*radius,2*radius);
        }
    }
}
