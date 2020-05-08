package IntelligentLightSystemApplication.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class FileMenu extends JMenu {
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
        tempMenuItem.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            } });
        add(tempMenuItem);
    }
}
