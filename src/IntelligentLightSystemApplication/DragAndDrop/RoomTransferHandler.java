package IntelligentLightSystemApplication.DragAndDrop;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class RoomTransferHandler extends TransferHandler {
    public boolean canImport(TransferHandler.TransferSupport info) {
        // Check for String flavor
        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        return true;
    }

    public boolean importData(TransferHandler.TransferSupport info) {
        if (!info.isDrop()) {
            return false;
        }

        Room room = (Room)info.getComponent();

        // Get the string that is being dropped.
        Transferable t = info.getTransferable();
        String data;
        try {
            data = (String)t.getTransferData(DataFlavor.stringFlavor);
        }
        catch (Exception e) { return false; }
        String[] values = data.split(";");

        Sensor sensor=new Sensor(Double.parseDouble(values[0]),Double.parseDouble(values[1]),Double.parseDouble(values[2]));
        sensor.setBounds(info.getDropLocation().getDropPoint().x,info.getDropLocation().getDropPoint().y,20,20);
        room.add(sensor);
        room.revalidate();

        return true;
    }
}
