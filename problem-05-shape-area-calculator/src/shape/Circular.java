package shape;

public class Circular implements Shape {

    private final float radius;

    public Circular(float radius) {
        this.radius = validateRadius(radius);
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    private float validateRadius(float val) {
        if (!Double.isFinite(val) || val <= 0) {
            throw new IllegalArgumentException("Radius should be positive or finite.");
        }

        return val;
    }
}
