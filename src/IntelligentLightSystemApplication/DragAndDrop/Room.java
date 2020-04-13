package IntelligentLightSystemApplication.DragAndDrop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Room extends JPanel implements MouseMotionListener {
    //private int mouseX = 200;
    //private int mouseY = 200;
    //private JLabel dragLabel = new JLabel("drag test");

    public Room(){
        setPreferredSize(new Dimension(1000, 600));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(null);
        setTransferHandler(new RoomTransferHandler());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        /*mouseX = e.getX();
        mouseY = e.getY();
        dragLabel.setBounds(mouseX, mouseY, 100, 50);*/
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}

