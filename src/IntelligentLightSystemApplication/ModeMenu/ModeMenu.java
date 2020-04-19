package IntelligentLightSystemApplication.ModeMenu;

import javax.swing.*;
import java.awt.*;

public class ModeMenu extends JPanel {
    public ModeMenu(){
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
        add(new Label("Mode"));
        JComboBox choice = new JComboBox();
        choice.addItem("Reading");
        choice.addItem("None");
        choice.addItem("Workout");
        choice.addItem("Sleep");
        add(choice);
    }
}
