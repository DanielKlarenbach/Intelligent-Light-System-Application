package IntelligentLightSystemApplication.DragAndDrop;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.*;

public class ExternalLightSource extends JLabel implements DragGestureListener, DragSourceListener {
    public ExternalLightSource(){
        // sensor's image configuration
        super(new ImageIcon("src/resources/OutsideLightSource.png"));
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        setText("external light-source");
        setPreferredSize(new Dimension(100,100));

        DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this);
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent e) {
        e.startDrag(DragSource.DefaultCopyDrop, new StringSelection("ExternalLightSource;"), this);
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