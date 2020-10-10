public class Circle extends Shape2D {

    private static final String name = "circle";
    private static final double pi = 3.14159;
    private final double radius;

    public Circle(double radius){
        super();
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return pi * (this.radius * this.radius);
    }

    @Override
    public String getName() {
        return name;
    }
}
