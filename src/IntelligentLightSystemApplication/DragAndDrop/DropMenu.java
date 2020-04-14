package IntelligentLightSystemApplication.DragAndDrop;

import javax.swing.*;
import java.awt.*;

public class DropMenu extends JPanel {
    public DropMenu(){
        //DropMenu panel configuration
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800,200));
        setBorder(BorderFactory.createLineBorder(Color.black));

        // Label
        add(new Label("Drag and drop objects to add them into your project"),BorderLayout.NORTH);

        // Drop icons
        JPanel iconPanel=new JPanel();
        iconPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));

        Sensor sensor=new Sensor(10,10,10);
        iconPanel.add(sensor);

        LightSource lightSource=new LightSource(10,10,10);
        iconPanel.add(lightSource);

        OutsideLightSource outsideLightSource=new OutsideLightSource(10,10,10);
        iconPanel.add(outsideLightSource);

        add(iconPanel,BorderLayout.CENTER);
    }
}
