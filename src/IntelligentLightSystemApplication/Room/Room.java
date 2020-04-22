package IntelligentLightSystemApplication.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import static IntelligentLightSystemApplication.Room.LightSource.getAtLightSource;

public class Room extends JPanel {
    //
    private int roomHeight=400;

    // action listeners
    private boolean paintWall=false;
    private boolean paintSensor=false;
    private boolean paintLightSource=false;
    private boolean paintExternalLightSource=false;
    private boolean paintXYAxis=false;
    private boolean paintXZAxis=false;
    private boolean delete=false;

    private boolean roomIsPainted=false;

    // objects in the room
    private ArrayList<Wall> walls=new ArrayList<>();
    private ArrayList<Sensor> sensors=new ArrayList<>();
    private ArrayList<LightSource> lightSources=new ArrayList<>();

    // currents
    private LightSource currentLightSource;
    private Sensor currentSensor;
    private Wall currentWall;

    public Room(){
        setPreferredSize(new Dimension(950, 800));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.WHITE);
        setLayout(null);

        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                if(!roomIsPainted) {
                    if (paintWall) {
                        currentWall = addWall(e);
                        if (walls.size() > 0 && currentWall.getX() == walls.get(0).getX() && currentWall.getY() == walls.get(0).getY()) {
                            roomIsPainted = true;
                            currentWall=null;
                            paintWall=false;
                        }
                        else walls.add(currentWall);
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
                if(paintWall && currentWall!=null){
                    currentWall=addWall(e);
                    repaint();
                }

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

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;

        // net
        g2d.setColor(Color.lightGray);
        for(int i=0;i<=950;i+=10) g2d.drawLine(i,0,i,820);
        for(int i=0;i<=800;i+=10) g2d.drawLine(0,i,950,i);
        g2d.setStroke(new BasicStroke(3));
        for(int i=0;i<=950;i+=100) g2d.drawLine(i,0,i,820);
        for(int i=0;i<=800;i+=100) g2d.drawLine(0,i,950,i);

        // walls, sensors, lightsources
        paintWall(g2d);
        for(int i=0;i<sensors.size();i++) sensors.get(i).draw(g2d);
        for(int i=0;i<lightSources.size();i++) lightSources.get(i).draw(g2d);

        if(paintWall && walls.size()>0 && currentWall!=null){
            Wall wall0=walls.get(walls.size()-1);
            g2d.draw(new Line2D.Float(wall0.getX(), wall0.getY(), currentWall.getX(), currentWall.getY()));
        }

        // axis
        if(paintXYAxis) drawAxisXY(currentLightSource.getAxisX(),currentLightSource.getAxisY(),g2d);
    }

    private void drawAxisXY(int x, int y, Graphics2D g2d){
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(currentLightSource.getX(),currentLightSource.getY(),x,y);
    }

    Wall addWall(MouseEvent e) {
        Wall wall=new Wall();
        wall.setX((e.getX()/10)*10+5); // center of clicked square
        wall.setY((e.getY()/10)*10+5);
        return wall;
    }

    private void paintWall(Graphics2D g2d){
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(11));
        if(walls.size()>1) {
            for(int i = 0; i< walls.size()-1; i++) {
                Wall wall1=walls.get(i);
                Wall wall2=walls.get(i+1);
                Wall wall0=walls.get(0);
                g2d.draw(new Line2D.Float(wall1.getX(), wall1.getY(), wall2.getX(), wall2.getY()));
                if(roomIsPainted==true && i==walls.size()-2) g2d.draw(new Line2D.Float(wall2.getX(), wall2.getY(), wall0.getX(), wall0.getY()));
            }
        }
    }

    // setters and getters

    public int getRoomHeight() {
        return roomHeight;
    }

    public LightSource getCurrentLightSource() {
        return currentLightSource;
    }

    public ArrayList<Sensor> getSensors() {
        return sensors;
    }

    public ArrayList<LightSource> getLightSources() {
        return lightSources;
    }

    public boolean isPaintXZAxis() { return paintXZAxis; }

    public boolean isPaintWall() { return paintWall; }

    public boolean isPaintSensor() { return paintSensor; }

    public boolean isPaintLightSource() { return paintLightSource; }

    public boolean isPaintExternalLightSource() { return paintExternalLightSource; }

    public boolean isPaintXYAxis() { return paintXYAxis; }

    public boolean isDelete() { return delete; }

    public void setPaintWall(boolean paintWall) { this.paintWall = paintWall; }

    public void setPaintSensor(boolean paintSensor) { this.paintSensor = paintSensor; }

    public void setPaintLightSource(boolean paintLightSource) { this.paintLightSource = paintLightSource; }

    public void setPaintExternalLightSource(boolean paintExternalLightSource) { this.paintExternalLightSource = paintExternalLightSource; }

    public void setPaintXYAxis(boolean paintXYAxis) { this.paintXYAxis = paintXYAxis; }

    public void setPaintXZAxis(boolean paintXZAxis) { this.paintXZAxis = paintXZAxis; }

    public void setDelete(boolean delete) { this.delete = delete; }
}

