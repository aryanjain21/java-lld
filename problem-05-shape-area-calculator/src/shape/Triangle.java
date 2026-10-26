package shape;

public class Triangle implements Shape {

    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b,double c) {
        if (!((a + b > c) && (b + c > a) && (c + a > b))) {
            throw new IllegalArgumentException("The given sides do not form a valid triangle.");
        }
        this.a = validateDimension(a);
        this.b = validateDimension(b);
        this.c = validateDimension(c);
    }

    @Override
    public double calculateArea() {
        double s = (a + b +c)/2;
        return Math.sqrt(s * (s-a) * (s-b) * (s-c));
    }

    private double validateDimension(double val) {
        if (!Double.isFinite(val) || val <= 0) {
            throw new IllegalArgumentException("Triangle sides must be positive and finite.");
        }

        return val;
    }
}
