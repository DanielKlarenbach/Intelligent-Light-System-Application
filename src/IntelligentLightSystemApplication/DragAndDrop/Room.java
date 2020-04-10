package IntelligentLightSystemApplication.DragAndDrop;

import javax.swing.*;
import java.awt.*;

public class Room extends JButton {
    public Room(){
        setTransferHandler(new TransferHandler("text"));
        setPreferredSize(new Dimension(1000, 600));
    }
}
