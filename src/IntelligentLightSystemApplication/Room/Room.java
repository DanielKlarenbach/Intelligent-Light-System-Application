package IntelligentLightSystemApplication.Room;

import IntelligentLightSystemApplication.MainFrame;
import IntelligentLightSystemApplication.ModeMenu.ModeMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    private boolean paintWindow=false;
    private boolean paintXYAxis=false;
    private boolean paintXZAxis=false;
    private boolean delete=false;
    // private boolean flag = false;
    private boolean roomIsPainted=false;

    private boolean windowEnd=false;

    // objects in the room
    private ArrayList<Wall> walls=new ArrayList<>();
    private ArrayList<Sensor> sensors=new ArrayList<>();
    private ArrayList<LightSource> lightSources=new ArrayList<>();
    private  ArrayList<Window> windows =new ArrayList<>();

    // currents
    private LightSource currentLightSource;
    private Sensor currentSensor;
    private Wall currentWall;
    private Window currentWindow;

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
                    repaint();

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
                if(paintWall && currentWall!=null){
                    currentWall=addWall(e);
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
        paintMap(g2d);
        for(int i=0;i<sensors.size();i++) sensors.get(i).draw(g2d);
        for(int i=0;i<lightSources.size();i++) lightSources.get(i).draw(g2d);

        if(paintWall && walls.size()>0 && currentWall!=null){
            Wall wall0=walls.get(walls.size()-1);
            g2d.draw(new Line2D.Float(wall0.getX(), wall0.getY(), currentWall.getX(), currentWall.getY()));
        }
        paintWindow(g2d);
        if(paintWindow && windows.size()>0 && currentWindow!=null){      //rysowanie ciągnięcia linii
            Window window=windows.get(windows.size()-1);
            if(currentWindow.getX2()!=0 && currentWindow.getY2()!=0) {
                g2d.draw(new Line2D.Float(window.getX1(), window.getY1(), currentWindow.getX2() , currentWindow.getY2() ));
            }
        }
        ModeMenu mode = new ModeMenu();
        mode.getMode();
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
        g2d.setColor(Color.lightGray);
        g2d.fillRect(minX+5,minY+5,maxX-minX-10,maxY-minY-10);
        for(int i = minX;i<=maxX-10;i+=10){
            for(int j = minY; j<=maxY-10;j+=10){
                intensity = 0;
                for(int k=0; k<=windows.size()-1; k+=2){
                    g2d.setColor(Color.darkGray);
                    if(windows.get(k).getX1()==windows.get(k).getX2() && windows.get(k).getX1()==minX) {
                          //  g2d.fillRect(windows.get(k).getX1() + 5, minY + 5, (int) (100 * windows.get(k).getShadow()), maxY - minY - 10);
                            if (i > (int) (windows.get(k).getShadow() * 100) + windows.get(k).getX1()) { //jeśli nie jest w cieniu ściany od oknem
                                if(j==maxY-10) continue;
                                intensity+=getPointIlluminance(i,j,20000,windows.get(k));
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
                                g2d.fillRect(i - 5, j + 5, 10, 10);
                            }
                        }
                    //swiatło od północy z jakiegoś powodu rysuje się tylko w cieniu pod innymi oknami,
                    // dlatego trzeba najpierw rysować pólnocne okna, a potem resztę
                    else if(windows.get(k).getY1()==windows.get(k).getY2() && windows.get(k).getY1()==minY) {
                       // g2d.fillRect(minX+5, minY + 5,maxX - minX - 10, (int) (100 * windows.get(k).getShadow()));
                       if (j > (int) (windows.get(k).getShadow() * 100) + windows.get(k).getY1()) { //jesli jest poniżej cienia
                            if(i==maxX-10) continue;
                            intensity+=getPointIlluminance(i,j,20000,windows.get(k));
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
                            g2d.fillRect(i + 5, j - 5, 10, 10);
                      }
                    }
                    else if(windows.get(k).getX1()==windows.get(k).getX2() && windows.get(k).getX1()==maxX) {
                     //   g2d.fillRect(windows.get(k).getX1()-(int)(100*windows.get(k).getShadow())-5, minY + 5,(int) (100 * windows.get(k).getShadow()), maxY - minY - 10 );
                        if (i <windows.get(k).getX1() - (int) (windows.get(k).getShadow() * 100)) {
                            if(i==minX || j==maxY-10) continue;
                            intensity+=getPointIlluminance(i,j,20000,windows.get(k));
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
                            g2d.fillRect(i - 5, j + 5, 10, 10);
                        }

                    }
                    else if(windows.get(k).getY1()==windows.get(k).getY2() && windows.get(k).getY1()==maxY) {
                      //  g2d.fillRect(minX+5,maxY-(int)(100*windows.get(k).getShadow()), maxX-minX-10, (int)(100*windows.get(k).getShadow())-5);
                        if (j <windows.get(k).getY2() - (int) (windows.get(k).getShadow() * 100)) {
                            if(i==minX || j==maxY ) continue;
                            intensity+=getPointIlluminance(i,j,20000,windows.get(k));
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
                            g2d.fillRect(i -5, j + 5, 10, 10);
                        }
                    }

                    }
                }

            }
    }
    private JPopupMenu showWindowMenu(int i){
        final JPopupMenu popupmenu = new JPopupMenu("Edit");
        JComboBox choice = new JComboBox();
        choice.addItem("Shadow length: " + windows.get(i).getShadow());
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

    public boolean isPaintWindow() { return paintWindow; }

    public boolean isPaintXYAxis() { return paintXYAxis; }

    public boolean isDelete() { return delete; }

    public void setPaintWall(boolean paintWall) { this.paintWall = paintWall; }

    public void setPaintSensor(boolean paintSensor) { this.paintSensor = paintSensor; }

    public void setPaintLightSource(boolean paintLightSource) { this.paintLightSource = paintLightSource; }

    public void setPaintWindow(boolean paintWindow) { this.paintWindow = paintWindow; }

    public void setPaintXYAxis(boolean paintXYAxis) { this.paintXYAxis = paintXYAxis; }

    public void setPaintXZAxis(boolean paintXZAxis) { this.paintXZAxis = paintXZAxis; }

    public void setDelete(boolean delete) { this.delete = delete; }
}

