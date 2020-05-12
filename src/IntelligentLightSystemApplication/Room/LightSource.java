package IntelligentLightSystemApplication.Room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.*;
import java.util.ArrayList;

@Setter
@Getter
@ToString
public class LightSource{
    //
    private int x;
    private int y;
    private int z;
    private final int radius=5;
    private Color color=Color.yellow;
    private String name;

    private boolean placed=false;

    // optic axis coordinates
    private int axisX;
    private int axisY;
    private int axisZ;

    private double angle;
    private double energy;
    private String type;

    public LightSource(int x, int y, int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public void draw(Graphics2D g2d){
        if(isPlaced()==false){
            color=new Color(255,255,153);
            g2d.setColor(color);
            g2d.fillOval(x-radius,y-radius,2*radius,2*radius);
        }
        else{
            g2d.setColor(color);
            g2d.fillOval(x-radius,y-radius,2*radius,2*radius);
        }
    }

    static LightSource getAtLightSource(int x, int y, ArrayList<LightSource> lightSources) {
        for (int i = 0; i < lightSources.size(); i++) {
            if ((x - lightSources.get(i).getX()) * (x - lightSources.get(i).getX()) + (y - lightSources.get(i).getY()) * (y - lightSources.get(i).getY()) <= (lightSources.get(i).getRadius()+10) * (lightSources.get(i).getRadius()+10))
                return lightSources.get(i);
        }
        return null;
    }

    int countConeHeight(){
        return (int) Math.sqrt(Math.pow((getX()-getAxisX()),2)    +   Math.pow((getY()-getAxisY()),2)   +   Math.pow((getZ()-getAxisZ()),2));
    }

    int countConeRadius(){
        double radians= Math.toRadians(angle /2);
        return (int) (Math.tan(radians)*countConeHeight());
    }

    int countConeHeightWithGivenPoint(float[] point){
        return (int) Math.sqrt(Math.pow((getX()-point[0]),2)    +   Math.pow((getY()-point[1]),2)   +   Math.pow((getZ()-point[2]),2));
    }

    int countConeRadiusWithGivenPoint(float[] point){
        double radians= Math.toRadians(angle /2);
        double currentHeight=countConeHeightWithGivenPoint(point);
        System.out.println("currentHeight"+currentHeight);
        System.out.println("radians"+radians);
        System.out.println("tan"+Math.tan(radians));
        return  (int) (Math.tan(radians)*currentHeight);
    }
}
