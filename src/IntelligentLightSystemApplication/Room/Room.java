package IntelligentLightSystemApplication.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Room extends JPanel {
    // action listeners
    public boolean wall=false;
    public boolean sensor=false;
    public boolean lightSource=false;
    public boolean externalLightSource=false;

    private CrossPoint[] crossPoints=new CrossPoint[340];
    private int count=0;

    private ArrayList<CrossPoint> lines=new ArrayList<>();

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
                if(wall) {
                    CrossPoint crossPoint = getAt(e.getX(), e.getY());
                    crossPoint.selected=true;
                    lines.add(crossPoint);
                    repaint();
                }
            }
        });
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D)g;
        g2d.setColor(new Color(204, 255, 255));
        for(int i=0;i<=950;i+=50) g2d.drawLine(i,0,i,820);
        for(int i=0;i<=800;i+=50) g2d.drawLine(0,i,950,i);
        for(int i=0;i<crossPoints.length;i++) crossPoints[i].draw(g);
        g2d.setColor(Color.black);
        if(lines.size()>1) {
            for(int i=0;i<lines.size()-1;i++) {
                g2d.drawLine(lines.get(i).x, lines.get(i).y, lines.get(i + 1).x, lines.get(i + 1).y);
                lines.get(i).selected = false;
                lines.get(i + 1).selected = false;
            }
        }
    }

    private class CrossPoint{
        int x,y;
        int radius=5; // 1px for line 2px to the left and 1px to the right
        boolean selected=false;
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
        crossPoints[count].x=x;
        crossPoints[count].y=y;
        count++;
    }

    CrossPoint getAt(int x,int y) {
        for (int i = 0; i < count; i++) {
            if ((x - crossPoints[i].x) * (x - crossPoints[i].x) + (y - crossPoints[i].y) * (y - crossPoints[i].y) <= (crossPoints[i].radius+10) * (crossPoints[i].radius+10)) // radius+10 so thaht it would be easier to tag a point
                return crossPoints[i];
        }
        return null;
    }
}

