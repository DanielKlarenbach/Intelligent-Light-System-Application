package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.Room;
import IntelligentLightSystemApplication.Room.Sensor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class SensorButton extends JButton  {
    SensorButton(Room room){
        super(new ImageIcon(SensorButton.class.getResource("/SensorButton.png")));
        setPreferredSize(new Dimension(50,50));
        setBackground(Color.WHITE);
        addMouseListener( new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                room.setPaintSensor(!room.isPaintSensor());
                room.getSensors().add(new Sensor(200,200,0));
            } });
    }
}
