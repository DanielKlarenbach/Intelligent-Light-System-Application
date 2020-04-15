package IntelligentLightSystemApplication.Buttons;

import javax.swing.*;
import java.awt.*;

class ExternalLightSourceButton extends JButton{
    ExternalLightSourceButton(){
        super(new ImageIcon("src/resources/ExternalLightSourceButton.png"));
        setPreferredSize(new Dimension(50,50));
    }
}
