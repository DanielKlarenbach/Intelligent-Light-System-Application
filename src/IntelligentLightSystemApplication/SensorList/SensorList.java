package IntelligentLightSystemApplication.SensorList;

import IntelligentLightSystemApplication.Room.LightSource;
import IntelligentLightSystemApplication.Room.Room;
import IntelligentLightSystemApplication.Room.Sensor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SensorList extends JList {
    static DefaultListModel data = new DefaultListModel();

    public SensorList(){
        super(data);
        setPreferredSize(new Dimension(500, 400));
    }

    public static void addItem(String Item) {
        if (Item.contains("null")) {data.addElement("Add Name"); }
        else {data.addElement(Item); }
    }

    public static void updateAllItems() {
        ArrayList<Sensor> sensors = Room.getSensors();
        for (int i=0; i<sensors.size(); i++) {
            Sensor sensor = sensors.get(i);
            if (sensor.getName() == null) {data.set(i, "Sensor " + i); }
            else {data.set(i, sensor.getName() + ": " + sensor.getIlluminance()); }
        }
    }
}
