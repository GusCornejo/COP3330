public class Sphere extends Shape3D {

    private static final String name = "sphere";
    private static final double pi = 3.14159;
    private final double radius;

    public Sphere(double radius){
        super();
        this.radius = radius;
    }

    @Override
    public double getVolume() {
        return (4 * pi * (this.radius * this.radius * this.radius)) / 3;
    }

    @Override
    public double getArea() {
        return 4 * pi * (this.radius * this.radius);
    }

    @Override
    public String getName() {
        return name;
    }
}
