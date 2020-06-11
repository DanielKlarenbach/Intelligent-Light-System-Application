package IntelligentLightSystemApplication.Room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
class Window {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private double shadow;
    private double height=1.5;

    public double getShadow(double angle){
        this.shadow = 0.8/Math.tan(Math.toRadians(angle));
        return shadow;
    }
}