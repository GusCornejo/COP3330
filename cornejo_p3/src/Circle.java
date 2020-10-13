public class Circle extends Shape2D {

    private static final String name = "circle";
    private final double radius;

    public Circle(double radius){
        super();
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return super.pi * (this.radius * this.radius);
    }

    @Override
    public String getName() {
        return name;
    }
}
