package IntelligentLightSystemApplication.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LightSourceConfigurationPopup extends JDialog {
    LightSourceConfigurationPopup(LightSource lightSource) {
        setSize(400, 400);
        setVisible(true);
        setLayout(new GridLayout(4, 2));
        JTextField name=new JTextField();
        add(new JLabel("Name"));
        add(name);
        add(new JLabel("Angle"));
        JTextField angle=new JTextField();
        add(angle);
        add(new JLabel("Energy"));
        JTextField energy=new JTextField();
        add(energy);
        add(new JLabel("Type"));
        JTextField type=new JTextField();
        add(type);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                lightSource.setName(name.getText());
                lightSource.setAngle(Double.parseDouble(angle.getText()));
                lightSource.setEnergy(Double.parseDouble(energy.getText()));
                lightSource.setType(type.getText());
                System.out.println(lightSource);
            }
        });
        pack();
    }
}
