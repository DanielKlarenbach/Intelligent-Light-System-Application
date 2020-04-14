package IntelligentLightSystemApplication;

import IntelligentLightSystemApplication.DragAndDrop.DropMenu;
import IntelligentLightSystemApplication.ModeMenu.ModeMenu;
import IntelligentLightSystemApplication.DragAndDrop.Room;
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
        setPreferredSize(new Dimension(1500+100, 800+80)); //25px - menu, 3*10px spaces betweeen components
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(new BorderLayout(10,10));

        //Menu
        MainMenu menu=new MainMenu();
        add(menu,BorderLayout.NORTH);

        //Lists panel
        JPanel lists=new JPanel();
        lists.setLayout(new BorderLayout(10,10));
        lists.setPreferredSize(new Dimension(500, 800));
        lists.setBorder(new EmptyBorder(0,0,10,10));

        //Sensor List
        SensorList sensorList=new SensorList();
        JScrollPane sensorListScroller = new JScrollPane();
        sensorListScroller.setViewportView(sensorList);
        //lists.add(sensorListScroller);
        lists.add(sensorList,BorderLayout.NORTH);

        //LightSource List
        LightSourceList lightSourceList=new LightSourceList();
        JScrollPane lightSourceListScroller = new JScrollPane();
        lightSourceListScroller.setViewportView(lightSourceList);
        //add( lightSourceListScroller );
        lists.add(lightSourceList,BorderLayout.SOUTH);

        add(lists,BorderLayout.EAST);

        //DragDrop and Mode panel
        JPanel dragAndDropPanel=new JPanel();
        dragAndDropPanel.setLayout(new BorderLayout(10,10));
        dragAndDropPanel.setPreferredSize(new Dimension(1000, 800));
        dragAndDropPanel.setBorder(new EmptyBorder(0,10,10,0));

        dragAndDropPanel.add(new DropMenu(),BorderLayout.WEST);
        dragAndDropPanel.add(new ModeMenu(), BorderLayout.EAST);
        dragAndDropPanel.add(new Room(),BorderLayout.SOUTH);

        add(dragAndDropPanel,BorderLayout.WEST);

        pack();
    }

    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame();
    }
}
