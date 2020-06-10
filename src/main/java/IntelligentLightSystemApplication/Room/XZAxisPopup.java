package IntelligentLightSystemApplication.Room;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

@Setter
@Getter
public class XZAxisPopup extends JDialog {
    private JPanel jpanel;
    private Room room;
    private int tempX;

    XZAxisPopup(Room room){
        this.room=room;
        setSize(900,room.getRoomHeight());
        setVisible(true);
        setLayout(new BorderLayout());

        jpanel=new JPanel(){
            public void paint(Graphics g){
                ArrayList<Sensor> sensors=room.getSensors();
                ArrayList<LightSource> lightSources=room.getLightSources();
                super.paintComponent(g);
                Graphics2D g2d=(Graphics2D) g;
                g2d.translate(0,room.getRoomHeight());
                g2d.scale(1,-1);
                g2d.setColor(Color.lightGray);

                // net drawing
                for(int i=0;i<=900;i+=10) g2d.drawLine(i,0,i,room.getRoomHeight());
                for(int i=0;i<=room.getRoomHeight();i+=10) g2d.drawLine(0,i,900,i);
                g2d.setStroke(new BasicStroke(3));
                for(int i=0;i<=900;i+=100) g2d.drawLine(i,0,i,room.getRoomHeight());
                for(int i=0;i<=room.getRoomHeight();i+=100) g2d.drawLine(0,i,900,i);

                // drawing sensors from room
                for(int i=0;i<sensors.size();i++) new Sensor(evaluateX(sensors.get(i).getX(),sensors.get(i).getY()),sensors.get(i).getZ(),0).draw(g2d);

                // drawing lightsources from room
                for(int i=0;i<lightSources.size();i++) new LightSource(evaluateX(lightSources.get(i).getX(),lightSources.get(i).getY()),lightSources.get(i).getZ(),0).draw(g2d);

                if(room.isPaintXZAxis()) drawAxis(tempX,room.getCurrentLightSource().getAxisZ(),g2d);
            }
        };
        jpanel.setPreferredSize(new Dimension(900,room.getRoomHeight()));
        jpanel.setBackground(Color.WHITE);
        add(jpanel,BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                    LightSource current=room.getCurrentLightSource();
                    current.setAxisZ(room.getRoomHeight()-e.getY()+30);
                    current.setColor(Color.yellow);
                    room.setPaintXZAxis(false);
                    room.setCurrentLightSource(null);
                    setVisible(false);
                }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                    setTempX(e.getX());
                    room.getCurrentLightSource().setAxisZ(room.getRoomHeight()-e.getY()+30);
                    jpanel.repaint();
                }
        });

        pack();
    }

    private void drawAxis(int x, int y, Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(1));
        LightSource current=room.getCurrentLightSource();
        int newX=evaluateX(current.getX(),current.getY());

        g2d.drawLine(newX, current.getZ(), x, y);

        // optic axis and line perpendicular to optic axis and containing point (x,y)
        double a1, a2, b2;
        if (newX == x) {
            a2 = 1;
            b2 = -x;
        } else {
            a1 = (double) (current.getZ() - y) / (double) (newX - x);
            a2 = (-1) / a1;
            b2 = y - a2 * x;
        }

        // equation of circle with radius eqaul to r=tan(alfa/2)*distance between (x,y) and (currentLightSource.x,currentLightSOurcec.y)
        double height = Math.sqrt(Math.pow((newX - x), 2) + Math.pow((current.getZ() - y), 2));
        double radius = Math.tan(Math.toRadians(current.getAngle() / 2)) * height;

        double a, b, c;
        double delta;
        a = 1 + Math.pow(a2, 2);
        b = (-2) * x + 2 * a2 * b2 - 2 * a2 * y;
        c = Math.pow(x, 2) + Math.pow(b2, 2) - 2 * b2 * y + Math.pow(y, 2) - Math.pow(radius, 2);
        delta = Math.pow(b, 2) - 4 * a * c;

        double x1, x2, y1, y2;
        x1 = ((-1) * b - Math.sqrt(delta)) / (2 * a);
        x2 = ((-1) * b + Math.sqrt(delta)) / (2 * a);
        y1 = a2 * x1 + b2;
        y2 = a2 * x2 + b2;

        g2d.drawPolygon(new int[]{newX, (int) x1, (int) x2}, new int[]{current.getZ(), (int) y1, (int) y2}, 3);
    }

    // evaluating point's  x cooridante in new coordiante system - OX is now the optic axis of cone and OY is OZ
    private int evaluateX(int x,int y){
        int newX;
        LightSource current=room.getCurrentLightSource();
        double x1=current.getX();
        double x2=current.getAxisX();
        double y1=current.getY();
        double y2=current.getAxisY();
        double A=(-1)/((y1-y2)/(x1-x2));
        double B=1;
        double C=(-1)*(y1-A*x1);
        A=-A;
        newX= (int) (Math.abs(A*x+B*y+C)/Math.sqrt(Math.pow(A,2)+Math.pow(B,2)));
        return newX;
    }
}
