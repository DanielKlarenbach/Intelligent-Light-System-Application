package IntelligentLightSystemApplication.LightSourceList;

import javax.swing.*;
import java.awt.*;

public class LightSourceList extends JList{
    static DefaultListModel data = new DefaultListModel();

    public LightSourceList(){
        super(data);
        setPreferredSize(new Dimension(500, 400));
    }

    public static void addItem(Object Item) {
        data.addElement(Item);
    }


//    public static void addAllItems() {
//        ArrayList<LightSource> sources = Room.getLightSources();
//        for (int i=0; i<sources.size(); i++) {
//            if (sources.get(i).getName() == null) {data.addElement("LightSource " + i); }
//            else {data.addElement(sources.get(i).getName()); }
//        }
//
//    }

}
