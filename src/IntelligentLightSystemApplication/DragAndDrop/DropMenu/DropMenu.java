package IntelligentLightSystemApplication.DragAndDrop.DropMenu;

import IntelligentLightSystemApplication.DragAndDrop.ExternalLightSource;
import IntelligentLightSystemApplication.DragAndDrop.LightSource;
import IntelligentLightSystemApplication.DragAndDrop.Sensor;

import javax.swing.*;
import java.awt.*;

public class DropMenu extends JPanel {
    public DropMenu(){
        // DropMenu panel configuration
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800,120));
        setBorder(BorderFactory.createLineBorder(Color.black));

        // label
        add(new Label("Drag and drop objects to add them into your project"),BorderLayout.NORTH);

        // drop icons
        JPanel iconPanel=new JPanel();
        iconPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));

        SensorDropIcon sensor=new SensorDropIcon();
        iconPanel.add(sensor);

        LightSource lightSource=new LightSource(10,10,10);
        iconPanel.add(lightSource);

        ExternalLightSource externalLightSource=new ExternalLightSource();
        iconPanel.add(externalLightSource);

        add(iconPanel,BorderLayout.CENTER);
    }
}
