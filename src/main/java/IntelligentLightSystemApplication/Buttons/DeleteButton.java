package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeleteButton extends JButton {
    DeleteButton(Room room){
        super(new ImageIcon(DeleteButton.class.getResource("/DeleteButton.png")));
        setPreferredSize(new Dimension(50,50));
        setBackground(Color.WHITE);
        addMouseListener( new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                room.setDelete(!room.isDelete());
            } });
    }
}
