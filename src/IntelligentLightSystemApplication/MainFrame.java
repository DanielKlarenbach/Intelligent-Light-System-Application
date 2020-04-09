package IntelligentLightSystemApplication;

import IntelligentLightSystemApplication.Menu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    MainFrame(){
        // setting Main Frame attributes
        setTitle("Intelligent Light System Application");
        setPreferredSize(new Dimension(700, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        setLayout(new GridBagLayout());

        // IntelligentLightSystemApplication.Menu
        MainMenu menu=new MainMenu();
        setJMenuBar(menu);

        pack();
    }

    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame();
    }
}
