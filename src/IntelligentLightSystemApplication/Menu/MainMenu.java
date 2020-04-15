package IntelligentLightSystemApplication.Menu;

import javax.swing.*;

public class MainMenu extends JMenuBar {
    public MainMenu(){
        add(new FileMenu());
        add(new HelpMenu());
    }
}
