package IntelligentLightSystemApplication.Room;

import IntelligentLightSystemApplication.SensorList.SensorList;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Getter
public class SensorConfigurationPopup extends JDialog {
    private JTextField illuminance;

    SensorConfigurationPopup(Sensor sensor) {
        setSize(400, 400);
        setVisible(true);
        setLayout(new GridLayout(4, 2));
        JTextField name = new JTextField();
        add(new JLabel("Name"));
        add(name);
        add(new JLabel("Illuminance"));
        illuminance = new JTextField();
        illuminance.setText(String.valueOf(sensor.getIlluminance()));
        add(illuminance);
        String prevname = name.getText();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                sensor.setName(name.getText());
                SensorList.updateAllItems();
            }
        });
        pack();
    }
}
