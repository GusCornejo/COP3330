import java.lang.Math;
public class Pyramid extends Shape3D {

    private final static String name = "pyramid";
    private final double baseLength;
    private final double baseWidth;
    private final double height;

    public Pyramid(double baseLength, double baseWidth, double height){
        super();
        this.baseLength = baseLength;
        this.baseWidth = baseWidth;
        this.height = height;
    }

    @Override
    public double getVolume() {
        return (this.baseLength * this.baseWidth * this.height) / 3;
    }

    @Override
    public double getArea() {
        return this.baseLength * this.baseWidth + this.baseLength * Math.sqrt(Math.pow((this.baseWidth/2), 2) + Math.pow(this.height, 2)) + this.baseWidth * Math.sqrt(Math.pow((this.baseLength/2), 2) + Math.pow(this.height, 2));
    }

    @Override
    public String getName() {
        return name;
    }
}
