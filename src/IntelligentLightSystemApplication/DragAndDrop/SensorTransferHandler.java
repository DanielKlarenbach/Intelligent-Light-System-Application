package IntelligentLightSystemApplication.DragAndDrop;

import javax.swing.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class SensorTransferHandler extends TransferHandler {
    protected Transferable createTransferable(JComponent c) {
        Sensor sensor=(Sensor) c;
        return new StringSelection(sensor.getx()+";"+sensor.getz()+";"+sensor.gety());
    }

    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }
}