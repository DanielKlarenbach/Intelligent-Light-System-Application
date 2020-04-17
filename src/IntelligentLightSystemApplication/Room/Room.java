package IntelligentLightSystemApplication.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Room extends JPanel {
    // action listeners
    public boolean paintWall=false;
    public boolean paintSensor=false;
    public boolean paintLightSource=false;
    public boolean paintExternalLightSource=false;

    private boolean roomIsPainted=false;

    private CrossPoint[] crossPoints=new CrossPoint[340];
    private int count=0;

    private ArrayList<CrossPoint> walls=new ArrayList<>();
    public ArrayList<Sensor> sensors=new ArrayList<>();
    public ArrayList<LightSource> lightSources=new ArrayList<>();

    public Room(){
        setPreferredSize(new Dimension(950, 800));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.WHITE);
        setLayout(null);
        for(int i=0;i<=950;i+=50) {
            for (int j = 0; j <= 800; j += 50) addCrossPoint(i, j);
        }

        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                if(!roomIsPainted) {
                    if (paintWall) {
                        if (walls.size() > 0) walls.get(walls.size() - 1).setSelected(false);
                        CrossPoint crossPoint = getAt(e.getX(), e.getY());
                        crossPoint.setSelected(true);
                        if (walls.size() > 0 && crossPoint.getX() == walls.get(0).getX() && crossPoint.getY() == walls.get(0).getY()) {
                            crossPoint.setSelected(false);
                            roomIsPainted = true;
                            paintWall=false;
                        }
                        else walls.add(crossPoint);
                        repaint();
                    }
                }

                if(paintSensor){
                    sensors.get(sensors.size()-1).setPlaced(true);
                    paintSensor=false;
                    repaint();
                }

                if(paintLightSource){
                    lightSources.get(lightSources.size()-1).setPlaced(true);
                    paintLightSource=false;
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                    if(paintSensor){
                        sensors.get(sensors.size()-1).setX(e.getX()-5); // -5 so that sensor would be visible
                        sensors.get(sensors.size()-1).setY(e.getY()-5);
                        repaint();
                    }

                    if(paintLightSource){
                        lightSources.get(lightSources.size()-1).setX(e.getX()-5);
                        lightSources.get(lightSources.size()-1).setY(e.getY()-5);
                        repaint();
                    }
                }
        });
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;

        // net
        g2d.setColor(new Color(204, 255, 255));
        for(int i=0;i<=950;i+=50) g2d.drawLine(i,0,i,820);
        for(int i=0;i<=800;i+=50) g2d.drawLine(0,i,950,i);
        for(int i=0;i<crossPoints.length;i++) crossPoints[i].draw(g);

        paintWall(g2d);
        for(int i=0;i<sensors.size();i++) sensors.get(i).draw(g2d);
        for(int i=0;i<lightSources.size();i++) lightSources.get(i).draw(g2d);
    }

    private class CrossPoint{
        private int x;
        private int y;
        private final int radius=5; // 1px for line 2px to the left and 1px to the right
        private boolean selected=false;

        public int getX() { return x; }

        public void setX(int x) { this.x = x; }

        public int getY() { return y; }

        public void setY(int y) { this.y = y; }

        public int getRadius() { return radius; }

        public boolean isSelected() { return selected; }

        public void setSelected(boolean selected) { this.selected = selected; }

        void draw(Graphics g){
            Graphics2D g2d= (Graphics2D)g;
            if(selected)g2d.setColor(new Color(255, 51, 0));
            else g2d.setColor(new Color(179, 255, 255));
            g2d.fillOval(x-radius,y-radius,2*radius,2*radius);
        }
    }

    void addCrossPoint(int x, int y){
        if(count==crossPoints.length)return;
        crossPoints[count]=new CrossPoint();
        crossPoints[count].setX(x);
        crossPoints[count].setY(y);
        count++;
    }

    CrossPoint getAt(int x,int y) {
        for (int i = 0; i < count; i++) {
            if ((x - crossPoints[i].getX()) * (x - crossPoints[i].getX()) + (y - crossPoints[i].getY()) * (y - crossPoints[i].getY()) <= (crossPoints[i].getRadius()+10) * (crossPoints[i].getRadius()+10)) // radius+10 so thaht it would be easier to tag a point
                return crossPoints[i];
        }
        return null;
    }

    private void paintWall(Graphics2D g2d){
        g2d.setColor(Color.black);
        if(walls.size()>1) {
            for(int i = 0; i< walls.size()-1; i++) {
                g2d.drawLine(walls.get(i).getX(), walls.get(i).getY(), walls.get(i + 1).getX(), walls.get(i + 1).getY());
                walls.get(i).setSelected(false);
                walls.get(i + 1).setSelected(false);
                if(roomIsPainted==true && i==walls.size()-2) g2d.drawLine(walls.get(i+1).getX(), walls.get(i+1).getY(), walls.get(0).getX(), walls.get(0).getY());
            }
        }
    }
}

