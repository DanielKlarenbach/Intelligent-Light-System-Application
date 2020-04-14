package IntelligentLightSystemApplication.LightSourceLIst;

import javax.swing.*;
import java.awt.*;

public class LightSourceList extends JList{
    static Object[] data={"Anna","Maria","Jan","Tomasz", "Zenobia","Barbara","Teofil"};
    public LightSourceList(){
        super(data);
        setPreferredSize(new Dimension(500, 400));
    }
}
