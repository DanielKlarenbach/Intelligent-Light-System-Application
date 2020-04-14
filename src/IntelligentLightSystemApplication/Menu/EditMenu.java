package IntelligentLightSystemApplication.Menu;

import IntelligentLightSystemApplication.DragAndDrop.ExternalLightSource;
import IntelligentLightSystemApplication.DragAndDrop.LightSource;
import IntelligentLightSystemApplication.DragAndDrop.Room;
import IntelligentLightSystemApplication.DragAndDrop.Sensor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMenu extends JMenu {
    private Room room;

    EditMenu(Room room) {
        super("Edit");
        this.room=room;

        JMenuItem tempMenuItem = new JMenuItem("Add sensor");
        tempMenuItem.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Sensor sensor = new Sensor(0,0,0);
                sensor.setBounds(200,200, 200, 200);
                room.add(sensor);
                room.revalidate();
                room.repaint();
            }});
        add(tempMenuItem);

        tempMenuItem = new JMenuItem("Add light-source");
        tempMenuItem.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                LightSource lightSource = new LightSource(0,0,0);
                lightSource.setBounds(200,200, 200, 200);
                room.add(lightSource);
                room.revalidate();
                room.repaint();
            }});
        add(tempMenuItem);

        tempMenuItem = new JMenuItem("Add external light-source");
        tempMenuItem.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ExternalLightSource externalLightSource = new ExternalLightSource();
                externalLightSource.setBounds(200,200, 200, 200);
                room.add(externalLightSource);
                room.revalidate();
                room.repaint();
            }});
        add(tempMenuItem);
    }
}
