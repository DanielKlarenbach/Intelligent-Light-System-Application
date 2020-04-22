package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.Room;

import javax.swing.*;
import java.awt.*;

public class Buttons extends JPanel {
    public Buttons(Room room){
        setLayout(new FlowLayout(0,0,0));
        setPreferredSize(new Dimension(50,800));

        SensorButton sensorButton=new SensorButton(room);
        add(sensorButton);

        LightSourceButton lightSourceButton=new LightSourceButton(room);
        add(lightSourceButton);

        ExternalLightSourceButton externalLightSourceButton=new ExternalLightSourceButton(room);
        add(externalLightSourceButton);

        WallButton wallButton=new WallButton(room);
        add(wallButton);

        XYAxisButton xyAxisButton=new XYAxisButton(room);
        add(xyAxisButton);

        XZAxisButton xzAxisButton=new XZAxisButton(room);
        add(xzAxisButton);

        DeleteButton deleteButton=new DeleteButton(room);
        add(deleteButton);
    }
}
