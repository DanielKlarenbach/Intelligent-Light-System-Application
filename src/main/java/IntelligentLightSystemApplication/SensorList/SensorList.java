package IntelligentLightSystemApplication.SensorList;

import javax.swing.*;
import java.awt.*;

public class SensorList extends JList {
    static DefaultListModel data = new DefaultListModel();

    public SensorList(){
        super(data);
        setPreferredSize(new Dimension(500, 400));
    }

    public static void addItem(Object Item) {
        data.addElement(Item);
    }

     /*public static void addAllItems() {
         ArrayList<Sensor> sensors = Room.getSensors();
          for (int i=0; i<sensors.size(); i++) {
              if (sensors.get(i).getName() == null) {addItem("Sensor " + i); }
              else {addItem(sensors.get(i).getName()); }
          }

     }*/
}

