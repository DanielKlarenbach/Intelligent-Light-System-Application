package IntelligentLightSystemApplication.DragAndDrop;

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

        Sensor sensor=new Sensor(10,10,10);
        iconPanel.add(sensor);

        LightSource lightSource=new LightSource(10,10,10);
        iconPanel.add(lightSource);

        ExternalLightSource externalLightSource=new ExternalLightSource();
        iconPanel.add(externalLightSource);

        add(iconPanel,BorderLayout.CENTER);
    }
}
