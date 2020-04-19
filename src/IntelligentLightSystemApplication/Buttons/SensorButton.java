package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.Room;
import IntelligentLightSystemApplication.Room.Sensor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class SensorButton extends JButton  {
    SensorButton(Room room){
        super(new ImageIcon("src/resources/SensorButton.png"));
        setPreferredSize(new Dimension(50,50));
        addMouseListener( new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                room.paintSensor=!room.paintSensor;
                room.sensors.add(new Sensor(200,200));
            } });
    }
}
