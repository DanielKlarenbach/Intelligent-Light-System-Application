package IntelligentLightSystemApplication.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class XZAxisPopup extends JDialog {
    private JPanel jpanel;
    private LightSource lightSource;
    private Room room;

    XZAxisPopup(Room room){
        this.room=room;
        lightSource=new LightSource(100,50, room.getRoomHeight());
        setSize(400,room.getRoomHeight());
        setVisible(true);
        setLayout(new BorderLayout());

        lightSource.setPlaced(true);

        jpanel=new JPanel(){
            public void paint(Graphics g){
                ArrayList<Sensor> sensors=room.getSensors();
                ArrayList<LightSource> lightSources=room.getLightSources();
                super.paintComponent(g);
                Graphics2D g2d=(Graphics2D) g;
                g2d.translate(0,400);
                g2d.scale(1,-1);
                g2d.setColor(new Color(204, 255, 255));
                for(int i=0;i<=400;i+=50) g2d.drawLine(i,0,i,400);
                for(int i=0;i<=400;i+=50) g2d.drawLine(0,i,400,i);
                for(int i=0;i<sensors.size();i++) {
                    new Sensor(evaluateX(sensors.get(i).getX(),sensors.get(i).getY()),sensors.get(i).getZ(),0).draw(g2d);
                }
                for(int i=0;i<lightSources.size();i++) new LightSource(evaluateX(lightSources.get(i).getX(),lightSources.get(i).getY()),lightSources.get(i).getZ(),0).draw(g2d);
            }
        };
        jpanel.setPreferredSize(new Dimension(400,room.getRoomHeight()));
        jpanel.setBackground(Color.WHITE);
        add(jpanel,BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (room.isPaintXZAxis()){

                }
            }
        });

        pack();
    }

    private int evaluateX(int x,int y){
        int newX;
        double x1=room.getCurrentLightSource().getX();
        double x2=room.getCurrentLightSource().getAxisX();
        double y1=room.getCurrentLightSource().getY();
        double y2=room.getCurrentLightSource().getAxisY();
        double A=(-1)/((y1-y2)/(x1-x2));
        double B=1;
        double C=(-1)*(y1-A*x1);
        A=-A;
        newX= (int) (Math.abs(A*x+B*y+C)/Math.sqrt(Math.pow(A,2)+Math.pow(B,2)));
        return newX;
    }
}
