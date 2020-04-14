package IntelligentLightSystemApplication.DragAndDrop;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.IOException;

public class Room extends JPanel implements DropTargetListener {
    public Room(){
        new DropTarget(this, DnDConstants.ACTION_COPY, this);
        setPreferredSize(new Dimension(1000, 600));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(null);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    public void drop(DropTargetDropEvent e) {
        try {
            DataFlavor stringFlavor = DataFlavor.stringFlavor;
            Transferable tr = e.getTransferable();
            if(e.isDataFlavorSupported(stringFlavor)) {
                String data = (String)tr.getTransferData(DataFlavor.stringFlavor);

                String[] values = data.split(";");
                e.acceptDrop(DnDConstants.ACTION_COPY);
                Sensor sensor=new Sensor(Double.parseDouble(values[0]),Double.parseDouble(values[1]),Double.parseDouble(values[2]));
                sensor.setBounds(e.getLocation().x,e.getLocation().y,200,200);
                add(sensor);
                revalidate();
                repaint();
                e.dropComplete(true);
            }
            else {
                e.rejectDrop();
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        catch(UnsupportedFlavorException ufe) {
            ufe.printStackTrace();
        }
    }
}

