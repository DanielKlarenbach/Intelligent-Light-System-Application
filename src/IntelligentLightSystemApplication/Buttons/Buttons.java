package IntelligentLightSystemApplication.Buttons;

import javax.swing.*;
import java.awt.*;

public class Buttons extends JPanel {
    public Buttons(){
        setLayout(new FlowLayout(0,0,0));
        setPreferredSize(new Dimension(50,800));

        SensorButton sensorButton=new SensorButton();
        add(sensorButton);

        LightSourceButton lightSourceButton=new LightSourceButton();
        add(lightSourceButton);

        ExternalLightSourceButton externalLightSourceButton=new ExternalLightSourceButton();
        add(externalLightSourceButton);

        WallButton wallButton=new WallButton();
        add(wallButton);
    }
}
