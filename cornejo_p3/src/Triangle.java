public class Triangle extends Shape2D {

    private static final String name = "triangle";
    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        super();
        this.base = base;
        this.height = height;
    }

    @Override
    public double getArea() {
        return (this.base * this.height) / 2;
    }

    @Override
    public String getName() {
        return name;
    }
}
