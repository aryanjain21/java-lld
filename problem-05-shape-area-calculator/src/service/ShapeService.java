package service;

import shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShapeService {
    private final List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        Objects.requireNonNull(shape, "shape cannot be null");

        shapes.add(shape);
    }

    public double calculateTotalArea() {
        double totalArea = 0.0;

        for (Shape shape: shapes) {
            double area = shape.calculateArea();
            if (!Double.isFinite(area) || area < 0) {
                throw new IllegalStateException(
                        "Shape returned an invalid area: " + area
                );
            }

            totalArea += area;

            if (!Double.isFinite(totalArea)) {
                throw new ArithmeticException(
                        "Total area overflowed."
                );
            }
        }

        return totalArea;
    }
}
