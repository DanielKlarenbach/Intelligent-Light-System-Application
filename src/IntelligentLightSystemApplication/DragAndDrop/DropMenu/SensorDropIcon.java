package IntelligentLightSystemApplication.DragAndDrop.DropMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.*;

public class SensorDropIcon extends JLabel implements DragGestureListener, DragSourceListener {
    public SensorDropIcon(){
        // sensor's image configuration
        super(new ImageIcon("src/resources/sensor.png"));
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        setText("sensor");
        setPreferredSize(new Dimension(100,100));

        DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this);
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent e) {
        e.startDrag(DragSource.DefaultCopyDrop, new StringSelection("Sensor;"), this);
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragExit(DragSourceEvent dse) {
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
    }
}
