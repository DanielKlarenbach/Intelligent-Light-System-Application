package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class XZAxisButton extends JButton {
    XZAxisButton(Room room){
        super(new ImageIcon("src/resources/XZAxisButton.png"));
        setPreferredSize(new Dimension(50,50));
        setBackground(Color.WHITE);
        addMouseListener( new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                room.paintXZAxis=!room.paintXZAxis;
            } });
    }
}
