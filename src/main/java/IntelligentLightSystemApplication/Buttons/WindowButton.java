package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class WindowButton extends JButton{
    WindowButton(Room room){
        super(new ImageIcon(WindowButton.class.getResource("/ExternalLightSourceButton.png")));
        setPreferredSize(new Dimension(50,50));
        addMouseListener( new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                room.setPaintWindow(!room.isPaintWindow());
            } });
    }
}
