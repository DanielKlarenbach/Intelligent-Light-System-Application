package IntelligentLightSystemApplication.Buttons;

import IntelligentLightSystemApplication.Room.LightSource;
import IntelligentLightSystemApplication.Room.Room;
import IntelligentLightSystemApplication.Room.Sensor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class LightSourceButton extends JButton{
    LightSourceButton(Room room){
        super(new ImageIcon("src/resources/LightSourceButton.png"));
        setPreferredSize(new Dimension(50,50));
        setBackground(Color.WHITE);
        addMouseListener( new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                room.setPaintLightSource(!room.isPaintLightSource());
                room.getLightSources().add(new LightSource(200,200, room.getRoomHeight()-50));
            } });
    }
}
