package IntelligentLightSystemApplication.ModeMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.HashMap;
import java.util.Map;

public class ModeMenu extends JPanel {
    private String mode;
    private String lightning;
    private int day;
    private int time;

    public Map<String,Double> illuminationModes = new HashMap<>();
    public  ModeMenu(){
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
        add(new Label("Mode:"));
        JComboBox choice = new JComboBox();
        choice.addItem("Reading");
        choice.addItem("None");
        choice.addItem("Workout");
        choice.addItem("Sleep");
        add(choice);

        illuminationModes.put("Sunlight",107527.0);
        illuminationModes.put("Full Day Light",10752.0);
        illuminationModes.put("Overcast Day",1075.0);
        illuminationModes.put("Very Dark Day",107.0);


        String light[] = new String[illuminationModes.size()];
        light[0] = "Sunlight";
        light[1] = "Full Day Light";
        light[2] = "Overcast Day";
        light[3] = "Very Dark Day";

        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
        add(new Label("Lightning:"));
        JComboBox choice1 = new JComboBox(light);
        add(choice1);

        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
        add(new Label("Day of the year:"));
        JComboBox choice2 = new JComboBox();
        choice2.addItem(1);
        choice2.addItem(100);
        choice2.addItem(200);
        add(choice2);


        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
        add(new Label("Local Standard Time:"));
        JComboBox choice3 = new JComboBox();
        choice3.addItem(9);
        choice3.addItem(12);
        choice3.addItem(15);
        choice3.addItem(18);
        add(choice3);

        ActionListener modeActionListener = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) choice.getSelectedItem();//get the selected item
                switch (s) {//check for a match
                    case "Reading":
                        setMode("Reading");

                        break;
                    case "None":
                        setMode("None");
                        break;
                    case "Workout":
                       setMode("Workout");
                        break;
                    case "Sleep":
                       setMode("Sleep");
                        break;
                }
            }
        };
        choice.addActionListener(modeActionListener);
        ActionListener lightActionListener = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) choice1.getSelectedItem();//get the selected item
                switch (s) {//check for a match
                    case "Sunlight":
                        setLightning("Sunlight");
                        break;
                    case "Full Day Light":
                        setLightning("Full Day Light");
                        break;
                    case "Overcast Day":
                        setLightning("Overcast Day");
                        break;
                    case "Very Dark Day":
                        setLightning("Very Dark Day");
                        break;
                }
            }
        };
        choice1.addActionListener(lightActionListener);

        ActionListener dayActionListener = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {
                int s = (int) choice2.getSelectedItem();//get the selected item
                switch (s) {//check for a match
                    case 1:
                        setDay(1);
                        break;
                    case 100:
                        setDay(100);
                        break;
                    case 200:
                        setDay(200);
                        break;

                }
            }
        };
        choice2.addActionListener(dayActionListener);
        ActionListener timeActionListener = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {
                int s = (int) choice3.getSelectedItem();//get the selected item
                switch (s) {//check for a match
                    case 9:
                        setTime(9);
                        break;
                    case 12:
                        setTime(12);
                        break;
                    case 15:
                        setTime(15);
                        break;
                    case 18:
                        setTime(18);
                        break;

                }
            }
        };
        choice3.addActionListener(timeActionListener);
    }
   public void setMode(String mode){
        System.out.println("Set" + this.mode);
        this.mode=mode;
    }
    public String getMode(){
        System.out.println("Get: "+this.mode);
        return this.mode;
    }

    public void setLightning(String lightning){
        this.lightning=lightning;
    }
    public  String getLightning(){
        return this.lightning;
    }
    public void setDay(int day){
        this.day=day;
    }
    public int getDay(){
        return this.day;
    }
    public void setTime(int time){
        this.time=time;
    }
    public int getTime(){
        return this.time;
    }

}
