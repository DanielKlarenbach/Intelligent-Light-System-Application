package IntelligentLightSystemApplication.SensorList;

import javax.swing.*;
import java.awt.*;

public class SensorList extends JList {
    static Object[] data={"Anna","Maria","Jan","Tomasz",
            "Zenobia","Barbara","Teofil"};
    public SensorList(){
        super(data);
        setPreferredSize(new Dimension(500, 400));
    }
}
