package IntelligentLightSystemApplication.Room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.*;
import java.util.ArrayList;

@Setter
@Getter
@ToString
public class Sensor{
    // sensor's coordinates in the room in pixels
    private int x;
    private int y;
    private int z;

    // sensor's icon properties
    private boolean placed=false;
    private final int radius=5;

    //popups
    private SensorConfigurationPopup sensorConfigurationPopup;

    // sensor's properties
    double illuminance;
    private String name;

    public Sensor(int x, int y, int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public void draw(Graphics2D g2d){
        if(isPlaced()==false){
            g2d.setColor(new Color(102,255,102));
            g2d.fillOval(x-radius,y-radius,2*radius,2*radius);
        }
        else{
            g2d.setColor(Color.green);
            g2d.fillOval(x-radius,y-radius,2*radius,2*radius);
        }
    }

    static Sensor getAtSensor(int x, int y, ArrayList<Sensor> sensors) {
        Sensor current;
        for (int i = 0; i < sensors.size(); i++) {
            current=sensors.get(i);
            if (Math.pow((x - current.getX()),2) + Math.pow((y - current.getY()),2) <= Math.pow((current.getRadius()+10),2)) return sensors.get(i);
        }
        return null;
    }

    boolean isInsideTheCone(LightSource lightSource){
        int[] point=new int[]{getX(),getY(),getZ()};
        int[] lineStart=new int[]{lightSource.getX(),lightSource.getY(),lightSource.getZ()};
        int[] lineEnd=new int[]{lightSource.getAxisX(),lightSource.getAxisY(),lightSource.getAxisZ()};
        double distance= distanceBetweenPointAndLine(point,lineStart,lineEnd);
        int[] pointOnLine=crossPoint(point,lineStart,lineEnd);
        double radius=lightSource.countConeRadiusWithGivenPoint(pointOnLine);
        //System.out.println("d r; "+distance+" "+radius);
        return (distance<=radius && distance>=0);
    }

    void countIlluminance(ArrayList<LightSource> lightSources){
        double tempIlluminance=0;
        double r=0;
        double I=0;
        double cos=0;
        for(int i=0;i<lightSources.size();i++){
            LightSource temp=lightSources.get(i);
            if(isInsideTheCone(temp)) {
                System.out.println(getName() + " jest");
                // illumiance = (I/r^2)*cos(alfa)
                r = (Math.sqrt(Math.pow((getX() - temp.getX()), 2) + Math.pow((getY() - temp.getY()), 2) + Math.pow((getZ() - temp.getZ()), 2))) / 100; // /100 to convert to meters
                I = (temp.getLuminousFlux() / (2 * Math.PI * (1 - Math.cos(temp.getAngle() / 2))));
                cos = Math.abs((temp.getZ() - getZ())) / r;
                tempIlluminance += (I / Math.pow(r, 2) * cos);
                double temp1=(I / Math.pow(r, 2) * cos);
                System.out.println("r I cos illuminance: " + r + " " + I + " " + cos + " " + temp1);
            }
        }
        setIlluminance(tempIlluminance);
        sensorConfigurationPopup.getIlluminance().setText(String.valueOf(illuminance));
        System.out.println(illuminance);
        System.out.println("===================");
    }

    public static double distanceBetweenPointAndLine(int[] point, int[] lineStart, int[] lineEnd){
        int[] vector1 = new int[3];
        int[] vector2 = new int[3];
        int[] TotalThing = new int[3];

        vector1[0] = lineEnd[0]-lineStart[0];
        vector1[1] = lineEnd[1]-lineStart[1];
        vector1[2] = lineEnd[2]-lineStart[2];

        vector2[0] = point[0]-lineStart[0];
        vector2[1] = point[1]-lineStart[1];
        vector2[2] = point[2]-lineStart[2];

        TotalThing[0] = (vector1[1]*vector2[2] - vector1[2]*vector2[1]);
        TotalThing[1] = (vector1[2]*vector2[0] - vector1[0]*vector2[2]);
        TotalThing[2] = (vector1[0]*vector2[1] - vector1[1]*vector2[0]);

        double distance = (double) ((Math.sqrt(Math.pow(TotalThing[0],2)+Math.pow(TotalThing[1],2)+Math.pow(TotalThing[2],2))) /
                Math.sqrt(Math.pow(vector1[0],2)+Math.pow(vector1[1],2)+Math.pow(vector1[2],2)));
        return distance;
    }

    // returns coordinates of a crosspoint between optic axis and the line perpendicular to the optic axis and containing given point (double[] point)
    public int[] crossPoint(int[] point,int [] lineStart,int [] lineEnd){
        int A,B,C;
        double x;
        A=lineEnd[0]-lineStart[0];
        B=lineEnd[1]-lineStart[1];
        C=lineEnd[2]-lineStart[2];
        x= (point[2]*C-lineStart[0]*A+A*point[0]-lineStart[1]*B+point[1]*B-lineStart[2]*C)/(Math.pow(A,2)+Math.pow(B,2)+Math.pow(C,2));
        return new int[]{(lineStart[0]+(int)(A*x)),(lineStart[1]+(int)(B*x)),(lineStart[2]+(int)(C*x))};
    }
}

