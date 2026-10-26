
import shape.Shape;
import shape.Rectangle;
import shape.Circular;
import shape.Triangle;
import service.ShapeService;

public class Main {

    public static void main(String[] args) {

        ShapeService shapeService = new ShapeService();

        Shape rectangle = new Rectangle(4, 5);
        Shape circle = new Circular(3);
        Shape triangle = new Triangle(3, 4, 5);

        shapeService.addShape(rectangle);
        shapeService.addShape(circle);
        shapeService.addShape(triangle);

        System.out.println("Rectangle area: " + rectangle.calculateArea());
        System.out.println("Circle area: " + circle.calculateArea());
        System.out.println("Triangle area: " + triangle.calculateArea());

        System.out.println(
                "Total area: " + shapeService.calculateTotalArea()
        );
    }
}
