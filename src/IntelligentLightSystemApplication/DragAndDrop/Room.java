package IntelligentLightSystemApplication.DragAndDrop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Room extends JPanel implements MouseMotionListener {
    private int mouseX = 200;
    private int mouseY = 200;
    private JLabel dragLabel = new JLabel("drag test");

    public Room(){
        setPreferredSize(new Dimension(1000, 600));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setTransferHandler(new RoomHandler());
    }

    private class RoomHandler extends TransferHandler {
        public RoomHandler() {
            Room.this.add(dragLabel);
            dragLabel.setForeground(Color.RED);
            dragLabel.setBounds(mouseX, mouseY, 100, 50);
            Room.this.addMouseMotionListener(Room.this);
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        dragLabel.setBounds(mouseX, mouseY, 100, 50);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}

