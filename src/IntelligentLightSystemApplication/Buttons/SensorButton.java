package IntelligentLightSystemApplication.Buttons;

import javax.swing.*;
import java.awt.*;

class SensorButton extends JButton  {
    SensorButton(){
        super(new ImageIcon("src/resources/SensorButton.png"));
        setPreferredSize(new Dimension(50,50));
    }
}
