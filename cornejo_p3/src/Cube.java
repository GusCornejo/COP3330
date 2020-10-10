public class Cube extends Shape3D {

    private final static String name = "cube";
    private final double side;

    public Cube(double side) {
        super();
        this.side = side;
    }

    @Override
    public double getVolume() {
        return this.side * this.side * this.side;
    }

    @Override
    public double getArea() {
        return 6 * (this.side * this.side);
    }

    @Override
    public String getName() {
        return name;
    }
}
