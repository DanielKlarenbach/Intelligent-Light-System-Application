package IntelligentLightSystemApplication.Menu;

import IntelligentLightSystemApplication.DragAndDrop.Room;

import javax.swing.*;

public class MainMenu extends JMenuBar {
    public MainMenu(Room room){
        add(new FileMenu());
        add(new EditMenu(room));
        add(new ViewMenu());
        add(new HelpMenu());
    }
}
