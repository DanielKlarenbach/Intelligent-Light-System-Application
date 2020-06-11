package IntelligentLightSystemApplication.Room;

import IntelligentLightSystemApplication.LightSourceList.LightSourceList;
import IntelligentLightSystemApplication.SensorList.SensorList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private double angle;
    private int illuminance;

    // action listeners
    private boolean paintWall = false;
    private boolean paintSensor = false;
    private boolean paintLightSource = false;
    private boolean paintExternalLightSource = false;
    private boolean paintXYAxis = false;
    private boolean paintXZAxis = false;
    private boolean delete = false;
    private boolean roomIsPainted = false;
    private boolean mode=false;
    private boolean paintWindow=false;
    private boolean windowEnd=false;

    // objects in the room
    private ArrayList<Wall> walls=new ArrayList<>();
    private static ArrayList<Sensor> sensors=new ArrayList<>(); //!!!!
    private static ArrayList<LightSource> lightSources=new ArrayList<>(); //!!!!!!!!!!!!!!!!
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
                    SensorList.addItem(sensors.get(sensors.size() - 1).getName() + ": " + sensors.get(sensors.size() - 1).getIlluminance());
                }

                if (paintLightSource) {
                    lightSources.get(lightSources.size() - 1).setPlaced(true);
                    lightSources.get(lightSources.size() - 1).setLightSourceConfigurationPopup(new LightSourceConfigurationPopup(lightSources.get(lightSources.size() - 1)));
                    paintLightSource = false;
                    repaint();
                    LightSourceList.addItem(lightSources.get(lightSources.size() - 1).getName() + ": " + lightSources.get(lightSources.size() - 1).getLuminousFlux());
                }

                if(mode){
                    // OutsideConfigurationPopup outsidePopup = new OutsideConfigurationPopup();
                    mode = false;
                    Sun sun = new Sun();
                    JFrame frame=new JFrame();
                    JButton button;
                    frame.setSize(300, 300);
                    frame.setVisible(true);
                    frame.setLayout(new GridLayout(4, 2));
                    JTextField  illumination=new JTextField();
                    frame.add(new JLabel("Lux outside: "));
                    frame.add(illumination);
                    frame.add(new JLabel("Day of year: "));
                    JTextField day = new JTextField();
                    frame.add(day);
                    frame.add(new JLabel("Time of day: "));
                    JTextField time=new JTextField();
                    frame.add(time);
                    button = new JButton("Apply");
                    frame.add(button);
                    button.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {

                            sun.setDay(Integer.parseInt(day.getText()));
                            sun.setTime(Integer.parseInt(time.getText()));
                            sun.setIllumination(Integer.parseInt(illumination.getText()));
                            sun.setAngle(50);
                            setAngle(sun.getAngle());
                            setIlluminance(sun.getIllumination());

                        }

                    });
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
                        LightSourceList.updateAllItems();
                        SensorList.updateAllItems();
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

                for(int i=0; i<windows.size()-1; i++){  //zacina się dla niektórych okien co jakiś czas, spr czy wina komputera
                    if(e.getX()<=windows.get(i).getX2() && e.getX()>=windows.get(i).getX1()
                            && e.getY()<=windows.get(i).getY2() && e.getY()>=windows.get(i).getY1()){
                        JPopupMenu pop =  showWindowMenu(i);
                        pop.show(e.getComponent(),e.getX(),e.getY());
                    }
                    else if(e.getX()>=windows.get(i).getX2() && e.getX()<=windows.get(i).getX1()
                            && e.getY()>=windows.get(i).getY2() && e.getY()<=windows.get(i).getY1()){
                        JPopupMenu pop =  showWindowMenu(i);
                        pop.show(e.getComponent(),e.getX(),e.getY());
                    }
                    else if(e.getX()>=windows.get(i).getX1() && e.getX()<=windows.get(i).getX2()
                            && e.getY()>=windows.get(i).getY2() && e.getY()<=windows.get(i).getY1()){
                        JPopupMenu pop =  showWindowMenu(i);
                        pop.show(e.getComponent(),e.getX(),e.getY());
                    }
                    else if(e.getX()<=windows.get(i).getX1() && e.getX()>=windows.get(i).getX2()
                            && e.getY()<=windows.get(i).getY2() && e.getY()>=windows.get(i).getY1()){
                        JPopupMenu pop =  showWindowMenu(i);
                        pop.show(e.getComponent(),e.getX(),e.getY());
                    }
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
        paintMap(g2d);
        for (int i = 0; i < sensors.size(); i++) sensors.get(i).draw(g2d);
        for (int i = 0; i < lightSources.size(); i++) lightSources.get(i).draw(g2d);
//      tu nie

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
//        tu nie
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

        // equation of circle with radius equal to r=tan(alfa/2)*distance between (x,y) and (currentLightSource.x,currentLightSOurcec.y)
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
//        tu nie
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
        g2d.setColor(Color.gray);
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
        if(roomIsPainted) findEdges();
    }
    boolean in = false;
    int minX;
    int maxX;
    int minY;
    int maxY;
    private void findEdges(){
        minX = walls.get(0).getX();
        maxX = walls.get(0).getX();
        minY = walls.get(0).getY();
        maxY = walls.get(0).getY();
        for(int i =0; i<walls.size()-1; i++){
            if(walls.get(i).getX()>maxX) maxX=walls.get(i).getX();
            else if(walls.get(i).getX()<minX) minX=walls.get(i).getX();
            if(walls.get(i).getY()>maxY) maxY=walls.get(i).getY();
            else if(walls.get(i).getY()<minY) minY=walls.get(i).getY();
        }
    }
    private void paintMap(Graphics2D g2d){     //heatmap
        double intensity;

        double a = getAngle();
        g2d.setColor(Color.lightGray);
        g2d.fillRect(minX+5,minY+5,maxX-minX-10,maxY-minY-10);
        for(int i = minX;i<=maxX-10;i+=1){
            for(int j = minY; j<=maxY-10;j+=1){
                intensity = 0;
                intensity+=countIlluminance(lightSources,i,j,0);
                if(intensity<50){
                    g2d.setColor(Color.blue);
                }
                else if(intensity>=50 && intensity<100){
                    g2d.setColor(Color.GREEN);
                }
                else if(intensity>=100 && intensity<200){
                    g2d.setColor(Color.yellow);
                }
                else if(intensity>=200 && intensity<300){
                    g2d.setColor(Color.orange);
                }
                else if(intensity>=300 && intensity<400){
                    g2d.setColor(Color.PINK);
                }
                else if(intensity>=400){
                    g2d.setColor(Color.red);
                }

                g2d.fillRect(i+5 , j+5 , 1, 1);
                for(int k=0; k<=windows.size()-1; k+=2){
                   // g2d.setColor(Color.darkGray);
                    if(windows.get(k).getX1()==windows.get(k).getX2() && windows.get(k).getX1()==minX) {
                        //  g2d.fillRect(windows.get(k).getX1() + 5, minY + 5, (int) (100 * windows.get(k).getShadow()), maxY - minY - 10);
                        if (i > (int) (windows.get(k).getShadow(a) * 100) + windows.get(k).getX1()) { //jeśli nie jest w cieniu ściany od oknem
                            if(j==maxY-10) continue;
                            intensity+=getPointIlluminance(i,j,getIlluminance(),windows.get(k));
                            if(intensity<50){
                                g2d.setColor(Color.blue);
                            }
                            else if(intensity>=50 && intensity<100){
                                g2d.setColor(Color.GREEN);
                            }
                            else if(intensity>=100 && intensity<200){
                                g2d.setColor(Color.yellow);
                            }
                            else if(intensity>=200 && intensity<300){
                                g2d.setColor(Color.orange);
                            }
                            else if(intensity>=300 && intensity<400){
                                g2d.setColor(Color.PINK);
                            }
                            else if(intensity>=400){
                                g2d.setColor(Color.red);
                            }

                            g2d.fillRect(i+5 , j+5 , 1, 1);
                        }
                    }
                    //swiatło od północy z jakiegoś powodu rysuje się tylko w cieniu pod innymi oknami,
                    // dlatego trzeba najpierw rysować pólnocne okna, a potem resztę
                    else if(windows.get(k).getY1()==windows.get(k).getY2() && windows.get(k).getY1()==minY) {
                        // g2d.fillRect(minX+5, minY + 5,maxX - minX - 10, (int) (100 * windows.get(k).getShadow()));
                        if (j > (int) (windows.get(k).getShadow(a) * 100) + windows.get(k).getY1()) { //jesli jest poniżej cienia
                            if(i==maxX-10) continue;
                            intensity+=getPointIlluminance(i,j,getIlluminance(),windows.get(k));
                            if(intensity<50){
                                g2d.setColor(Color.blue);
                            }
                            else if(intensity>=50 && intensity<100){
                                g2d.setColor(Color.GREEN);
                            }
                            else if(intensity>=100 && intensity<200){
                                g2d.setColor(Color.yellow);
                            }
                            else if(intensity>=200 && intensity<300){
                                g2d.setColor(Color.orange);
                            }
                            else if(intensity>=300 && intensity<400){
                                g2d.setColor(Color.PINK);
                            }
                            else if(intensity>=400){
                                g2d.setColor(Color.red);
                            }
                            g2d.fillRect(i + 5, j + 5, 1, 1);
                        }
                    }
                    else if(windows.get(k).getX1()==windows.get(k).getX2() && windows.get(k).getX1()==maxX) {
                        //   g2d.fillRect(windows.get(k).getX1()-(int)(100*windows.get(k).getShadow())-5, minY + 5,(int) (100 * windows.get(k).getShadow()), maxY - minY - 10 );
                        if (i <windows.get(k).getX1() - (int) (windows.get(k).getShadow(a) * 100)) {
                            if(i==minX || j==maxY-10) continue;
                            intensity+=getPointIlluminance(i,j,getIlluminance(),windows.get(k));

                            if(intensity<50){
                                g2d.setColor(Color.blue);
                            }
                            else if(intensity>=50 && intensity<100){
                                g2d.setColor(Color.GREEN);
                            }
                            else if(intensity>=100 && intensity<200){
                                g2d.setColor(Color.yellow);
                            }
                            else if(intensity>=200 && intensity<300){
                                g2d.setColor(Color.orange);
                            }
                            else if(intensity>=300 && intensity<400){
                                g2d.setColor(Color.PINK);
                            }
                            else if(intensity>=400){
                                g2d.setColor(Color.red);
                            }
                            g2d.fillRect(i + 5, j + 5, 1, 1);
                        }

                    }
                    else if(windows.get(k).getY1()==windows.get(k).getY2() && windows.get(k).getY1()==maxY) {
                        //  g2d.fillRect(minX+5,maxY-(int)(100*windows.get(k).getShadow()), maxX-minX-10, (int)(100*windows.get(k).getShadow())-5);
                        if (j <windows.get(k).getY2() - (int) (windows.get(k).getShadow(a) * 100)) {
                            if(i==minX || j==maxY ) continue;
                            intensity+=getPointIlluminance(i,j,getIlluminance(),windows.get(k));

                            if(intensity<50){
                                g2d.setColor(Color.blue);
                            }
                            else if(intensity>=50 && intensity<100){
                                g2d.setColor(Color.GREEN);
                            }
                            else if(intensity>=100 && intensity<200){
                                g2d.setColor(Color.yellow);
                            }
                            else if(intensity>=200 && intensity<300){
                                g2d.setColor(Color.orange);
                            }
                            else if(intensity>=300 && intensity<400){
                                g2d.setColor(Color.PINK);
                            }
                            else if(intensity>=400){
                                g2d.setColor(Color.red);
                            }
                            g2d.fillRect(i + 5, j + 5, 1, 1);
                        }
                    }


                }

            }

        }
    }
    private JPopupMenu showWindowMenu(int i){
        final JPopupMenu popupmenu = new JPopupMenu("Edit");
        JComboBox choice = new JComboBox();
        double a = getAngle();
        choice.addItem("Shadow length: " + windows.get(i).getShadow(a));
        choice.addItem("(X1,Y1): (" + windows.get(i).getX1() + "," + windows.get(i).getY1() +")");
        choice.addItem("(X2,Y2): (" + windows.get(i).getX2() + "," + windows.get(i).getY2() +")");
        choice.addItem("Width: " + Math.sqrt(Math.pow(Math.abs(windows.get(i).getX1()-windows.get(i).getX2()),2)+Math.pow(Math.abs(windows.get(i).getY1()-windows.get(i).getY2()),2))/100 + " m");
        add(choice);
        popupmenu.add(choice);
        add(popupmenu);
        setLayout(null);
        setVisible(true);
        return popupmenu;
    }
    public double getPointIlluminance(int x, int y, int outsideIlluminance, Window window){
        double area = window.getHeight()*0.01*Math.sqrt(Math.pow(Math.abs(window.getX1()-window.getX2()),2)+Math.pow(Math.abs(window.getY1()-window.getY2()),2));
        double stream = outsideIlluminance * area;
        double luminosity = stream/(4*Math.PI);
        double l = 0.01*Math.sqrt(Math.pow(x-(window.getX1()+window.getX2())/2,2)+Math.pow(y-(window.getY1()+window.getY2())/2,2));
        double r = Math.sqrt(window.getHeight()*window.getHeight()+l*l);
        double cosAngle = window.getHeight()/r;
        double E = luminosity*cosAngle/(r*r);
        return E;
    }
    public static ArrayList<Sensor> getSensors() {
        return sensors;
    }

    public static ArrayList<LightSource> getLightSources() {
        return lightSources;
    }

    public void setAngle(double ang){
        this.angle=Math.abs(ang);
    }

    boolean isInsideTheCone(LightSource lightSource,int x,int y,int z){
        int[] point=new int[]{x,y,z};
        int[] lineStart=new int[]{lightSource.getX(),lightSource.getY(),lightSource.getZ()};
        int[] lineEnd=new int[]{lightSource.getAxisX(),lightSource.getAxisY(),lightSource.getAxisZ()};
        double distance= distanceBetweenPointAndLine(point,lineStart,lineEnd);
        int[] pointOnLine=crossPoint(point,lineStart,lineEnd);
        double radius=lightSource.countConeRadiusWithGivenPoint(pointOnLine);
        //System.out.println("d r; "+distance+" "+radius);
        return (distance<=radius && distance>=0);
    }

    double countIlluminance(ArrayList<LightSource> lightSources,int x,int y,int z){
        double tempIlluminance=0;
        double r=0;
        double I=0;
        double cos=0;
        for(int i=0;i<lightSources.size();i++){
            LightSource temp=lightSources.get(i);
            if(isInsideTheCone(temp,x,y,z)) {
                // illumiance = (I/r^2)*cos(alfa)
                r = (Math.sqrt(Math.pow((x - temp.getX()), 2) + Math.pow((y - temp.getY()), 2) + Math.pow((z - temp.getZ()), 2))) / 100; // /100 to convert to meters
                I = (temp.getLuminousFlux() / (2 * Math.PI * (1 - Math.cos(temp.getAngle() / 2))));
                cos = Math.abs((temp.getZ() - z)) / r;
                tempIlluminance += (I / Math.pow(r, 2) * cos);
            }
        }
        return tempIlluminance;
    }

    public static double distanceBetweenPointAndLine(int[] point, int[] lineStart, int[] lineEnd){
        int[] vector1 = new int[3];
        int[] vector2 = new int[3];
        int[] TotalThing = new int[3];

        vector1[0] = lineEnd[0]-lineStart[0];
        vector1[1] = lineEnd[1]-lineStart[1];
        vector1[2] = lineEnd[2]-lineStart[2];

        vector2[0] = point[0]-lineStart[0];
        vector2[1] = point[1]-lineStart[1];
        vector2[2] = point[2]-lineStart[2];

        TotalThing[0] = (vector1[1]*vector2[2] - vector1[2]*vector2[1]);
        TotalThing[1] = (vector1[2]*vector2[0] - vector1[0]*vector2[2]);
        TotalThing[2] = (vector1[0]*vector2[1] - vector1[1]*vector2[0]);

        double distance = (double) ((Math.sqrt(Math.pow(TotalThing[0],2)+Math.pow(TotalThing[1],2)+Math.pow(TotalThing[2],2))) /
                Math.sqrt(Math.pow(vector1[0],2)+Math.pow(vector1[1],2)+Math.pow(vector1[2],2)));
        return distance;
    }

    // returns coordinates of a crosspoint between optic axis and the line perpendicular to the optic axis and containing given point (double[] point)
    public int[] crossPoint(int[] point,int [] lineStart,int [] lineEnd){
        int A,B,C;
        double x;
        A=lineEnd[0]-lineStart[0];
        B=lineEnd[1]-lineStart[1];
        C=lineEnd[2]-lineStart[2];
        x= (point[2]*C-lineStart[0]*A+A*point[0]-lineStart[1]*B+point[1]*B-lineStart[2]*C)/(Math.pow(A,2)+Math.pow(B,2)+Math.pow(C,2));
        return new int[]{(lineStart[0]+(int)(A*x)),(lineStart[1]+(int)(B*x)),(lineStart[2]+(int)(C*x))};
    }


}
