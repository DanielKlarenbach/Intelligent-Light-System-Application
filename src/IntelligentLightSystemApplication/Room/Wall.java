package IntelligentLightSystemApplication.Room;

import java.awt.*;

public class Wall {
    private int x;
    private int y;
    private final int radius=5; // 1px for line 2px to the left and 1px to the right

    private boolean selected=false;

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public int getRadius() { return radius; }

    public void setSelected(boolean selected) { this.selected = selected; }

    void draw(Graphics g){
        Graphics2D g2d= (Graphics2D)g;
        if(selected)g2d.setColor(new Color(255, 51, 0));
        else g2d.setColor(new Color(179, 255, 255));
        g2d.fillOval(x-radius,y-radius,2*radius,2*radius);
    }
}
