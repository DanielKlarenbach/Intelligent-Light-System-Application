package IntelligentLightSystemApplication;

import IntelligentLightSystemApplication.Buttons.Buttons;
import IntelligentLightSystemApplication.Room.Room;
import IntelligentLightSystemApplication.LightSourceLIst.LightSourceList;
import IntelligentLightSystemApplication.Menu.MainMenu;
import IntelligentLightSystemApplication.SensorList.SensorList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    MainFrame(){
        // setting Main Frame attributes
        setTitle("Intelligent Light System Application");
        setPreferredSize(new Dimension(1500+10+10+10+10, 800+50+10+10+10)); //50px - menu, 3*10px spaces betweeen components
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(new BorderLayout(0,10));

        // menu
        MainMenu menu=new MainMenu();
        add(menu,BorderLayout.NORTH);

        // application panel
        JPanel applicationPanel=new JPanel();
        applicationPanel.setLayout(new BorderLayout(10,10));
        applicationPanel.setBorder(new EmptyBorder(0,10,10,10));

            // buttons
            Room room=new Room();
            applicationPanel.add(new Buttons(room),BorderLayout.WEST);

            // room
            applicationPanel.add(room,BorderLayout.CENTER);

            // list's panel
            JPanel lists=new JPanel();
            lists.setLayout(new BorderLayout(10,10));
            lists.setPreferredSize(new Dimension(500, 800));

                // sensor List
                SensorList sensorList=new SensorList();
                JScrollPane sensorListScroller = new JScrollPane();
                sensorListScroller.setViewportView(sensorList);
                lists.add(sensorListScroller);
                lists.add(sensorList,BorderLayout.NORTH);

                // lightSource List
                LightSourceList lightSourceList=new LightSourceList();
                JScrollPane lightSourceListScroller = new JScrollPane();
                lightSourceListScroller.setViewportView(lightSourceList);
                add( lightSourceListScroller );
                lists.add(lightSourceList,BorderLayout.SOUTH);

            applicationPanel.add(lists,BorderLayout.EAST);

        add(applicationPanel);

        pack();
    }

    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame();
    }
}
