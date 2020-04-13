package IntelligentLightSystemApplication.DragAndDrop;

import javax.swing.*;

public class Sensor extends JTextField {
    private double x;
    private double y;
    private double z;

    public Sensor(double x, double y,double z){
        super("dss");

        this.x=x;
        this.y=y;
        this.z=z;

        setDragEnabled(true);
        setTransferHandler(new SensorTransferHandler());
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
}