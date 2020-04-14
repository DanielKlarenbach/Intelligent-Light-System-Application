package IntelligentLightSystemApplication.DragAndDrop;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Sensor extends JLabel implements DragGestureListener, DragSourceListener {
    private double x;
    private double y;
    private double z;


    public Sensor(double x, double y,double z){
        // Sensor's image configuration
        super(new ImageIcon("src/resources/sensor.png"));
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        setText("sensor");
        setPreferredSize(new Dimension(100,100));

        // Sensor's coordinates in the room
        this.x=x;
        this.y=y;
        this.z=z;

        DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this);
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
    public void dragGestureRecognized(DragGestureEvent e) {
        // drag anything ...
        e.startDrag(DragSource.DefaultCopyDrop, new StringSelection(getx()+";"+getz()+";"+gety()), this); // drag source listener
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {

    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {

    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {

    }

    @Override
    public void dragExit(DragSourceEvent dse) {

    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {

    }
}
