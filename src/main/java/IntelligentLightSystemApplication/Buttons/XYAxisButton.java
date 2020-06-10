package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class XYAxisButton extends JButton {
    XYAxisButton(Room room){
        super(new ImageIcon(XYAxisButton.class.getResource("/XYAxisButton.png")));
        setPreferredSize(new Dimension(50,50));
        setBackground(Color.WHITE);
        addMouseListener( new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                room.setPaintXYAxis(!room.isPaintXYAxis());
            } });
    }
}
