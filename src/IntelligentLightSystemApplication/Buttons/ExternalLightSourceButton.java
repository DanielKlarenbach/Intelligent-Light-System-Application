package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.Room;

import javax.swing.*;
import java.awt.*;

class ExternalLightSourceButton extends JButton{
    ExternalLightSourceButton(Room room){
        super(new ImageIcon("src/resources/ExternalLightSourceButton.png"));
        setPreferredSize(new Dimension(50,50));
    }
}
