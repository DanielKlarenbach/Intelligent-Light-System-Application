package IntelligentLightSystemApplication.Room;

public class Sun {
    double angle;

    public void setAngle(int LST, int day, double latitude) {
        double HRA = (15)*(LST-12); //ok
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
}
