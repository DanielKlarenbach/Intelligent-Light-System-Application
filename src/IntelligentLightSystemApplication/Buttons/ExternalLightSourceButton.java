package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class ExternalLightSourceButton extends JButton{
    ExternalLightSourceButton(Room room){
        super(new ImageIcon("src/resources/ExternalLightSourceButton.png"));
        setPreferredSize(new Dimension(50,50));
        addMouseListener( new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                room.paintExternalLightSource=!room.paintExternalLightSource;
            } });
    }
}
