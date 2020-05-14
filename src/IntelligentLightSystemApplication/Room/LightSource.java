package IntelligentLightSystemApplication.Room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.Color;
import java.awt.Graphics2D;

import java.util.ArrayList;

@Setter
@Getter
@ToString
public class LightSource{
    // lightSource's coordinates in the room in pixels
    private int x;
    private int y;
    private int z;

    // lightSource's icon properties
    private final int radius=5;
    private Color color=Color.yellow;
    private boolean placed=false;

    // optic axis coordinates in pixels
    private int axisX;
    private int axisY;
    private int axisZ;

    // popups
    private XZAxisPopup xyAxisPopup;
    private LightSourceConfigurationPopup lightSourceConfigurationPopup;

    // lightSource properties
    private String name;
    private double angle; // opening angle
    private double luminousFlux;
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
        LightSource current;
        for (int i = 0; i < lightSources.size(); i++) {
            current=lightSources.get(i);
            if (Math.pow((x - current.getX()),2) + Math.pow((y - current.getY()),2) <= Math.pow((current.getRadius()+10),2)) return current;
        }
        return null;
    }

    // counts the distance in pixels(1px-1cm) between the center of the lightSource and the given point located on the opiticAxis
    double countConeHeightWithGivenPoint(int[] point){
        return Math.sqrt(Math.pow((getX()-point[0]),2)    +   Math.pow((getY()-point[1]),2)   +   Math.pow((getZ()-point[2]),2));
    }

    // counts radius in pixels(1px-1cm) of the cone for given height
    double countConeRadiusWithGivenPoint(int[] point){
        double radians=Math.toRadians(angle/2);
        double currentHeight=countConeHeightWithGivenPoint(point);
        return Math.tan(radians)*currentHeight;
    }
}
