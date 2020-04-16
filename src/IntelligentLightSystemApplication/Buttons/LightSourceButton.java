package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.Room;

import javax.swing.*;
import java.awt.*;

class LightSourceButton extends JButton{
    LightSourceButton(Room room){
        super(new ImageIcon("src/resources/LightSourceButton.png"));
        setPreferredSize(new Dimension(50,50));
    }
}
