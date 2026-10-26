package shape;

import java.util.Objects;

public class Rectangle implements Shape {

    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.width = validateDimension(width);
        this.height = validateDimension(height);
    }

    @Override
    public double calculateArea() {
        return (double) width * height;
    }

    private int validateDimension(int val) {

        if (val <= 0) {
            throw new IllegalArgumentException("Width or height should be positive.");
        }

        return val;
    }
}
