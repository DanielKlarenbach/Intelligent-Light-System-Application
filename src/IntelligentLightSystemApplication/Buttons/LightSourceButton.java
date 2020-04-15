package IntelligentLightSystemApplication.Buttons;

import javax.swing.*;
import java.awt.*;

class LightSourceButton extends JButton{
    LightSourceButton(){
        super(new ImageIcon("src/resources/LightSourceButton.png"));
        setPreferredSize(new Dimension(50,50));
    }
}
