package IntelligentLightSystemApplication.DragAndDrop;

import javax.swing.*;
import java.awt.*;

public class DropMenu extends JPanel {
    public DropMenu(){
        setLayout(new FlowLayout());
        add(new SensorDrop());
        setPreferredSize(new Dimension(800,200));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
