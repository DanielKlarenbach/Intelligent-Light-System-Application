package IntelligentLightSystemApplication.DragAndDrop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Sensor extends JLabel implements MouseMotionListener {
    // sensor's coordinates in the room
    private double x;
    private double y;
    private double z;

    public Sensor(double x, double y,double z){
        // sensor's image configuration
        super(new ImageIcon("src/resources/sensor.png"));
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        setText("sensor");
        setPreferredSize(new Dimension(100,100));

        this.x=x;
        this.y=y;
        this.z=z;

        addMouseMotionListener(this);
    }

    public double getx(){
        return x;
    }

    public double getz(){
        return x;
    }

    public double gety(){
        return x;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setBounds(e.getX(),e.getY(), 100, 100);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}

