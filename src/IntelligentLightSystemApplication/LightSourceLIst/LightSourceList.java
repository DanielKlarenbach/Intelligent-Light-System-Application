package IntelligentLightSystemApplication.LightSourceLIst;

import IntelligentLightSystemApplication.Room.LightSource;
import IntelligentLightSystemApplication.Room.Room;
import IntelligentLightSystemApplication.Room.Sensor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LightSourceList extends JList{
    static DefaultListModel data = new DefaultListModel();

    public LightSourceList(){
        super(data);
        setPreferredSize(new Dimension(500, 400));
    }

    public static void addItem(String Item) {
        if (Item.contains("null")) {data.addElement("Incomplete Configuration"); }
        else {data.addElement(Item); }
    }


    public static void updateAllItems() {
        ArrayList<LightSource> sources = Room.getLightSources();
        for (int i=0; i<sources.size(); i++) {
            LightSource source = sources.get(i);
            if (source.getName() == null) {data.set(i, "LightSource " + i); }
            else {data.set(i, source.getName() + ": " + source.getLuminousFlux()); }
        }

    }

}
