package IntelligentLightSystemApplication.Room;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Sun {
    private double angle;
    private int day;
    private int illumination;
    private int time;


    public void setAngle(double latitude) {
        double HRA = (15)*(time-12); //ok
        double incos = (double)360*(day+10)/365; //ok
        double declinationAngle =(-23.35)*Math.cos(incos); // ok
        double sin = Math.sin(Math.toRadians(declinationAngle))*Math.sin(Math.toRadians(latitude)); //ok
        double cos = Math.cos(Math.toDegrees(declinationAngle))*Math.cos(Math.toDegrees(latitude))*Math.cos(Math.toRadians(HRA)); //ok
        double elevationAngle = Math.toDegrees(Math.asin(sin+cos));
        this.angle=elevationAngle;
    }
    public double getAngle(){
        return angle;
    }
    public int getDay(){ return  day;}
    public int getTime(){ return time;}
    public int getIllumination(){ return  illumination;}

    public void setDay(int day){
        this.day=day;
    }

    public void setIllumination(int illumination){
        this.illumination=illumination;
    }

    public void setTime(int time){
        this.time=time;
    }
}

