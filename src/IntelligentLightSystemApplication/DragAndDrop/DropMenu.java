package IntelligentLightSystemApplication.DragAndDrop;

import javax.swing.*;
import java.awt.*;

public class DropMenu extends JPanel {
    public DropMenu(){
        setLayout(new FlowLayout());
        add(new Sensor(10,10,10));
        setPreferredSize(new Dimension(800,200));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
