package IntelligentLightSystemApplication.Room;

import lombok.Data;

import java.awt.*;
import java.util.ArrayList;

@Data
public class Sensor{
    //
    private int x;
    private int y;
    private int z;
    private final int radius=5;

    private boolean placed=false;

    double illuminance;

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
        for (int i = 0; i < sensors.size(); i++) {
            if ((x - sensors.get(i).getX()) * (x - sensors.get(i).getX()) + (y - sensors.get(i).getY()) * (y - sensors.get(i).getY()) <= (sensors.get(i).getRadius()+10) * (sensors.get(i).getRadius()+10))
                return sensors.get(i);
        }
        return null;
    }

    boolean isInsideTheCone(LightSource lightSource){
        float distance= betweenPointAndLine(new float[]{getX(),getY(),getZ()},new float[]{lightSource.getX(),lightSource.getY(),lightSource.getZ()},new float[]{lightSource.getAxisX(),lightSource.getAxisY(),lightSource.getAxisZ()});
        float[] pointOnLine=crossPoint(new float[]{getX(),getY(),getZ()},new float[]{lightSource.getX(),lightSource.getY(),lightSource.getZ()},new float[]{lightSource.getAxisX(),lightSource.getAxisY(),lightSource.getAxisZ()});
        float radius=lightSource.countConeRadiusWithGivenPoint(pointOnLine);
        System.out.println(pointOnLine[0]+" "+pointOnLine[1]+" "+pointOnLine[2]);
        System.out.println("distance: "+distance);
        System.out.println("radius: "+radius);
        return (distance<=radius && distance>=0);
    }

    void countIlluminance(ArrayList<LightSource> lightSources){
        illuminance=0;
        for(int i=0;i<lightSources.size();i++){
            LightSource temp=lightSources.get(i);
            System.out.println(i);
            if(isInsideTheCone(temp)){
                System.out.println("isInside");
                illuminance+=((temp.getEnergy()/(2*Math.PI*(1-Math.cos(temp.getAngle()/2))))/Math.pow(Math.sqrt(Math.pow((getX()-temp.getX()),2)+Math.pow((getY()-temp.getY()),2)),2))*Math.cos(Math.atan(Math.abs(((temp.getZ()-getZ()/(temp.getY()-getY()))-getZ())/(1+(temp.getZ()-getZ()/(temp.getY()-getY()))*getZ()))));
            }
        }
    }

    public static float betweenPointAndLine(float[] point, float[] lineStart, float[] lineEnd){
        float[] PointThing = new float[3];
        float[] TotalThing = new float[3];
        PointThing[0] = lineStart[0] - point[0];
        PointThing[1] = lineStart[1] - point[1];
        PointThing[2] = lineStart[2] - point[2];

        TotalThing[0] = (PointThing[1]*lineEnd[2] - PointThing[2]*lineEnd[1]);
        TotalThing[1] = -(PointThing[0]*lineEnd[2] - PointThing[2]*lineEnd[0]);
        TotalThing[2] = (PointThing[0]*lineEnd[1] - PointThing[1]*lineEnd[0]);

        float distance = (float) (Math.sqrt(TotalThing[0]*TotalThing[0] + TotalThing[1]*TotalThing[1] + TotalThing[2]*TotalThing[2]) /
                Math.sqrt(lineEnd[0] * lineEnd[0] + lineEnd[1] * lineEnd[1] + lineEnd[2] * lineEnd[2] ));
        return distance;
    }

    public float[] crossPoint(float[] point,float [] lineStart,float [] lineEnd){
        float A,B,C;
        float x;
        A=lineEnd[0]-lineStart[0];  //(lineStart[0]+A*x)
        B=lineEnd[1]-lineStart[1];  //(lineStart[1]+B*x)
        C=lineEnd[2]-lineStart[2];  //(lineStart[2]+C*x)
        x= (float) ((point[2]*C-lineStart[0]*A+A*point[0]-lineStart[1]*B+point[1]*B-lineStart[2]*C)/(Math.pow(A,2)+Math.pow(B,2)+Math.pow(C,2)));
        System.out.println("x: "+x);
        return new float[]{(lineStart[0]+A*x),(lineStart[1]+B*x),(lineStart[2]+C*x)};
    }
}

