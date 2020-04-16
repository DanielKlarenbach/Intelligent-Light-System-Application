package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class WallButton extends JButton {
    WallButton(Room room){
        super(new ImageIcon("src/resources/WallButton.png"));
        setPreferredSize(new Dimension(50,50));
        addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                room.wall=true;
            } });
    }
}