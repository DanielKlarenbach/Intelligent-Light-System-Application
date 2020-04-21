package IntelligentLightSystemApplication.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static IntelligentLightSystemApplication.Room.LightSource.getAtLightSource;

public class Room extends JPanel {
    //
    private int roomHeight=400;

    // action listeners
    public boolean paintWall=false;
    public boolean paintSensor=false;
    public boolean paintLightSource=false;
    public boolean paintExternalLightSource=false;
    public boolean paintXYAxis=false;
    public boolean paintXZAxis=false;
    public boolean delete=false;


    private boolean roomIsPainted=false;

    private Wall[] crossPoints=new Wall[340];
    private int count=0;

    private ArrayList<Wall> walls=new ArrayList<>();
    public ArrayList<Sensor> sensors=new ArrayList<>();
    public ArrayList<LightSource> lightSources=new ArrayList<>();

    // currents
    private LightSource currentLightSource;
    private Sensor currentSensor;
    private Wall currentWall;

    public LightSource getCurrentLightSource() {
        return currentLightSource;
    }

    public Room(){
        setPreferredSize(new Dimension(950, 800));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.WHITE);
        setLayout(null);
        for(int i=0;i<=950;i+=50) {
            for (int j = 0; j <= 800; j += 50) addWall(i, j);
        }

        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                if(!roomIsPainted) {
                    if (paintWall) {
                        if (walls.size() > 0) walls.get(walls.size() - 1).setSelected(false);
                        Wall crossPoint = getAtWall(e.getX(), e.getY());
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

                if(paintXYAxis){
                    if(currentLightSource==null) {
                        currentLightSource = getAtLightSource(e.getX(), e.getY(), lightSources);
                        currentLightSource.setColor(Color.RED);
                    }
                    else{
                        currentLightSource.setAxisX(e.getX());
                        currentLightSource.setAxisY(e.getY());
                        currentLightSource.setColor(Color.yellow);
                        paintXYAxis=false;
                        currentLightSource=null;
                    }
                    repaint();
                }

                if(paintXZAxis){
                    currentLightSource = getAtLightSource(e.getX(), e.getY(), lightSources);
                    if(currentLightSource!=null) {
                        currentLightSource.setColor(Color.RED);
                        XZAxisPopup popup=new XZAxisPopup(Room.this);
                        repaint();
                    }
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                if (paintSensor) {
                    sensors.get(sensors.size() - 1).setX(e.getX() - 5); // -5 so that sensor would be visible
                    sensors.get(sensors.size() - 1).setY(e.getY() - 5);
                    repaint();
                }

                if (paintLightSource) {
                    lightSources.get(lightSources.size() - 1).setX(e.getX() - 5);
                    lightSources.get(lightSources.size() - 1).setY(e.getY() - 5);
                    repaint();
                }

                if (paintXYAxis && currentLightSource!=null) {
                    currentLightSource.setAxisX(e.getX());
                    currentLightSource.setAxisY(e.getY());
                    repaint();
                }
            }
        });
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<Sensor> getSensors() {
        return sensors;
    }

    public ArrayList<LightSource> getLightSources() {
        return lightSources;
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

        // axis
        if(paintXYAxis) drawAxisXY(currentLightSource.getAxisX(),currentLightSource.getAxisY(),g2d);
    }

    private void drawAxisXY(int x, int y, Graphics2D g2d){
        g2d.setColor(Color.black);
        g2d.drawLine(currentLightSource.getX(),currentLightSource.getY(),x,y);
    }

    void addWall(int x, int y){
        if(count==crossPoints.length)return;
        crossPoints[count]=new Wall();
        crossPoints[count].setX(x);
        crossPoints[count].setY(y);
        count++;
    }

    Wall getAtWall(int x, int y) {
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

    public int getRoomHeight() {
        return roomHeight;
    }
}

