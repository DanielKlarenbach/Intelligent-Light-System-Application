package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class WallButton extends JButton {
    WallButton(Room room){
        super(new ImageIcon(WallButton.class.getResource("/WallButton.png")));
        setPreferredSize(new Dimension(50,50));
        addMouseListener( new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                room.setPaintWall(!room.isPaintWall());
            } });
    }
}