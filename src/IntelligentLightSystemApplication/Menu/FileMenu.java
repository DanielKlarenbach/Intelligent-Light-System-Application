package IntelligentLightSystemApplication.Menu;

import javax.swing.*;

public class FileMenu extends JMenu {
    FileMenu() {
        super("File");
        JMenuItem tempMenuItem = new JMenuItem("Save");
        /*tempMenu.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
            } });*/
        add(tempMenuItem);
        tempMenuItem = new JMenuItem("Open");
        /*tempMenu.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
            } });*/
        add(tempMenuItem);
        tempMenuItem = new JMenuItem("Open recent");
        /*tempMenu.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
            } });*/
        add(tempMenuItem);
        tempMenuItem = new JMenuItem("Import");
        /*tempMenu.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
            } });*/
        add(tempMenuItem);
        tempMenuItem = new JMenuItem("Exit");
        /*tempMenu.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
            } });*/
        add(tempMenuItem);
    }
}
