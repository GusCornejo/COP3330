public class Square extends Shape2D {

    private static final String name = "square";
    private final double side;

    public Square(double side) {
        super();
        this.side = side;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public double getArea() {
        return this.side * this.side;
    }
}
