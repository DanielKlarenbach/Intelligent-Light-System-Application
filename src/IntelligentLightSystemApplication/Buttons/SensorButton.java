package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.Room;

import javax.swing.*;
import java.awt.*;

class SensorButton extends JButton  {
    SensorButton(Room room){
        super(new ImageIcon("src/resources/SensorButton.png"));
        setPreferredSize(new Dimension(50,50));
    }
}
