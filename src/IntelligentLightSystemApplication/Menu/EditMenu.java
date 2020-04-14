package IntelligentLightSystemApplication.Menu;

import javax.swing.*;

public class EditMenu extends JMenu {
    EditMenu() {
        super("Edit");
        JMenuItem tempMenuItem = new JMenuItem("Add sensor");
        /*tempMenu.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
            } });*/
        add(tempMenuItem);
        tempMenuItem = new JMenuItem("Add light-source");
        /*tempMenu.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
            } });*/
        add(tempMenuItem);
        tempMenuItem = new JMenuItem("Add external light-source");
        /*tempMenu.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
            } });*/
        add(tempMenuItem);
    }
}
