package IntelligentLightSystemApplication.Room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import static IntelligentLightSystemApplication.Room.LightSource.getAtLightSource;
import static IntelligentLightSystemApplication.Room.Sensor.getAtSensor;

@Setter
@Getter
@ToString
public class Room extends JPanel {
    // room's properties
    private int roomHeight = 1000; // in pixels(1px-1cm)

    // action listeners
    private boolean paintWall = false;
    private boolean paintSensor = false;
    private boolean paintLightSource = false;
    private boolean paintExternalLightSource = false;
    private boolean paintXYAxis = false;
    private boolean paintXZAxis = false;
    private boolean delete = false;
    private boolean roomIsPainted = false;
    private boolean paintWindow=false;
    private boolean windowEnd=false;

    // objects in the room
    private ArrayList<Wall> walls=new ArrayList<>();
    private ArrayList<Sensor> sensors=new ArrayList<>();
    private ArrayList<LightSource> lightSources=new ArrayList<>();
    private ArrayList<Window> windows =new ArrayList<>();

    // currents
    private LightSource currentLightSource;
    private Sensor currentSensor;
    private Wall currentWall;
    private Window currentWindow;

    public Room() {
        setPreferredSize(new Dimension(950, 800));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.WHITE);
        setLayout(null);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!roomIsPainted) {
                    if (paintWall) {
                        currentWall = addWall(e);
                        if (walls.size() > 0 && currentWall.getX() == walls.get(0).getX() && currentWall.getY() == walls.get(0).getY()) {
                            roomIsPainted = true;
                            currentWall = null;
                            paintWall = false;
                        } else walls.add(currentWall);
                        repaint();
                    }
                }
                if(paintWindow){             // create new or take the last one from table
                    if(windowEnd==false) {
                        Window window = new Window();
                        currentWindow = setWindow(e,window, false);
                        windowEnd = true;
                        windows.add(currentWindow);
                    }
                    else{
                        currentWindow = setWindow(e,windows.get(windows.size()-1),true);
                        windowEnd = false;
                        paintWindow=false;
                        windows.add(currentWindow);
                    }
                    //currentWindow = null;
                    repaint();
                }

                if (paintSensor) {
                    sensors.get(sensors.size() - 1).setPlaced(true);
                    sensors.get(sensors.size()-1).setSensorConfigurationPopup(new SensorConfigurationPopup(sensors.get(sensors.size()-1)));
                    paintSensor = false;
                    sensors.get(sensors.size() - 1).countIlluminance(lightSources);
                    repaint();
                }

                if (paintLightSource) {
                    lightSources.get(lightSources.size() - 1).setPlaced(true);
                    lightSources.get(lightSources.size() - 1).setLightSourceConfigurationPopup(new LightSourceConfigurationPopup(lightSources.get(lightSources.size() - 1)));
                    paintLightSource = false;
                    repaint();
                }

                if (paintXYAxis) {
                    if (currentLightSource == null) {
                        currentLightSource = getAtLightSource(e.getX(), e.getY(), lightSources);
                        if (currentLightSource != null) currentLightSource.setColor(Color.RED);
                        else paintXYAxis = false; // no lightSOurce detected int the area
                    } else {
                        currentLightSource.setAxisX(e.getX());
                        currentLightSource.setAxisY(e.getY());
                        currentLightSource.setColor(Color.yellow);
                        paintXYAxis = false;
                        currentLightSource = null;
                    }
                    repaint();
                }

                if (paintXZAxis) {
                    currentLightSource = getAtLightSource(e.getX(), e.getY(), lightSources);
                    if (currentLightSource != null) {
                        currentLightSource.setColor(Color.RED);
                        XZAxisPopup popup = new XZAxisPopup(Room.this);
                        repaint();
                    }
                }

                if (e.getClickCount() == 2) {
                    currentLightSource = getAtLightSource(e.getX(), e.getY(), lightSources);
                    if (currentLightSource != null) {
                        currentLightSource.getLightSourceConfigurationPopup().setVisible(true);
                        currentLightSource = null;
                    }else{
                        currentSensor=getAtSensor(e.getX(),e.getY(),sensors);
                        if (currentSensor != null) {
                            currentSensor.getSensorConfigurationPopup().setVisible(true);
                            currentSensor = null;
                        }
                    }
                }

                if(e.getClickCount()==1){
                    for (int i = 0; i < sensors.size(); i++) sensors.get(i).countIlluminance(lightSources);
                    System.out.println("*************************************");
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                if (paintWall && currentWall != null) {
                    currentWall = addWall(e);
                    repaint();
                }
                if(paintWindow && currentWindow!=null){
                    if(windowEnd==true) {
                        currentWindow = addWindow(e, windowEnd);        //      do rysowania na bieżąco
                    }
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

                if (paintXYAxis && currentLightSource != null) {
                    currentLightSource.setAxisX(e.getX());
                    currentLightSource.setAxisY(e.getY());
                    repaint();
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // net
        g2d.setColor(Color.lightGray);
        for (int i = 0; i <= 950; i += 10) g2d.drawLine(i, 0, i, 820);
        for (int i = 0; i <= 800; i += 10) g2d.drawLine(0, i, 950, i);
        g2d.setStroke(new BasicStroke(3));
        for (int i = 0; i <= 950; i += 100) g2d.drawLine(i, 0, i, 820);
        for (int i = 0; i <= 800; i += 100) g2d.drawLine(0, i, 950, i);

        // walls, sensors, lightsources
        paintWall(g2d);
        for (int i = 0; i < sensors.size(); i++) sensors.get(i).draw(g2d);
        for (int i = 0; i < lightSources.size(); i++) lightSources.get(i).draw(g2d);

        if (paintWall && walls.size() > 0 && currentWall != null) {
            Wall wall0 = walls.get(walls.size() - 1);
            g2d.draw(new Line2D.Float(wall0.getX(), wall0.getY(), currentWall.getX(), currentWall.getY()));
        }

        paintWindow(g2d);
        if(paintWindow && windows.size()>0 && currentWindow!=null){      //rysowanie ciągnięcia linii                                               //////////
            Window window=windows.get(windows.size()-1);
            if(currentWindow.getX2()!=0 && currentWindow.getY2()!=0) {
                g2d.draw(new Line2D.Float(window.getX1(), window.getY1(), currentWindow.getX2() , currentWindow.getY2() ));
            }
        }

        // axis
        if (paintXYAxis) drawAxis(currentLightSource.getAxisX(), currentLightSource.getAxisY(), g2d);
    }

    private void drawAxis(int x, int y, Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(currentLightSource.getX(), currentLightSource.getY(), x, y);

        // optic axis and line perpendicular to optic axis and containing point (x,y)
        double a1, a2, b2;
        if (currentLightSource.getX() == x) {
            a2 = 1;
            b2 = -x;
        } else {
            a1 = (double) (currentLightSource.getY() - y) / (double) (currentLightSource.getX() - x);
            a2 = (-1) / a1;
            b2 = y - a2 * x;
        }

        // equation of circle with radius eqaul to r=tan(alfa/2)*distance between (x,y) and (currentLightSource.x,currentLightSOurcec.y)
        double height = Math.sqrt(Math.pow((currentLightSource.getX() - x), 2) + Math.pow((currentLightSource.getY() - y), 2));
        double radius = Math.tan(Math.toRadians(currentLightSource.getAngle() / 2)) * height;

        double a, b, c;
        double delta;
        a = 1 + Math.pow(a2, 2);
        b = (-2) * x + 2 * a2 * b2 - 2 * a2 * y;
        c = Math.pow(x, 2) + Math.pow(b2, 2) - 2 * b2 * y + Math.pow(y, 2) - Math.pow(radius, 2);
        delta = Math.pow(b, 2) - 4 * a * c;

        double x1, x2, y1, y2;
        x1 = ((-1) * b - Math.sqrt(delta)) / (2 * a);
        x2 = ((-1) * b + Math.sqrt(delta)) / (2 * a);
        y1 = a2 * x1 + b2;
        y2 = a2 * x2 + b2;

        g2d.drawPolygon(new int[]{currentLightSource.getX(), (int) x1, (int) x2}, new int[]{currentLightSource.getY(), (int) y1, (int) y2}, 3);
    }

    Wall addWall(MouseEvent e) {
        Wall wall = new Wall();
        wall.setX((e.getX() / 10) * 10 + 5); // center of clicked square
        wall.setY((e.getY() / 10) * 10 + 5);
        return wall;
    }
    Window addWindow(MouseEvent e, boolean windowEnd) {
        Window window=new Window();
        if(windowEnd==false) {
            window.setX1((e.getX() / 10) * 10 + 5); // center of clicked square
            window.setY1((e.getY() / 10) * 10 + 5);
            //currentWindow=null;
        }else{
            window.setX2((e.getX() / 10) * 10 + 5); // center of clicked square
            window.setY2((e.getY() / 10) * 10 + 5);

        }
        return window;
    }
    //w zależności od kliknięcia mamy albo współrzędne początku albo końca
    Window setWindow(MouseEvent e,Window window, boolean windowEnd) {
       // Window window=new Window();
        if(windowEnd==false) {
            window.setX1((e.getX() / 10) * 10 + 5); // center of clicked square
            window.setY1((e.getY() / 10) * 10 + 5);
        }else{
            window.setX2((e.getX() / 10) * 10 + 5); // center of clicked square
            window.setY2((e.getY() / 10) * 10 + 5);
        }
        return window;
    }


    private void paintWindow(Graphics2D g2d){
        g2d.setColor(Color.cyan);
        g2d.setStroke(new BasicStroke(11));
        if(windows.size()>1) {
            for (int i = 0; i < windows.size() - 1; i++) {
                Window window = windows.get(i);
                g2d.draw(new Line2D.Float(window.getX1(), window.getY1(), window.getX2(), window.getY2()));
            }
        }
    }

    private void paintWall(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(11));
        if (walls.size() > 1) {
            for (int i = 0; i < walls.size() - 1; i++) {
                Wall wall1 = walls.get(i);
                Wall wall2 = walls.get(i + 1);
                Wall wall0 = walls.get(0);
                g2d.draw(new Line2D.Float(wall1.getX(), wall1.getY(), wall2.getX(), wall2.getY()));
                if (roomIsPainted == true && i == walls.size() - 2)
                    g2d.draw(new Line2D.Float(wall2.getX(), wall2.getY(), wall0.getX(), wall0.getY()));
            }
        }
    }

}
