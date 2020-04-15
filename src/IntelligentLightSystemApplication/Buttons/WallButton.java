package IntelligentLightSystemApplication.Buttons;

import javax.swing.*;
import java.awt.*;

class WallButton extends JButton {
    WallButton(){
        super(new ImageIcon("src/resources/WallButton.png"));
        setPreferredSize(new Dimension(50,50));
    }
}