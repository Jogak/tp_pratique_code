package org.sam.mines.examples.sealed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public sealed interface Shape permits Rectangle, Circle {

}

record Rectangle(double length, double width) implements Shape {
    public String aRectangleMethod()  {
        return "This is a rectangle method";
    }
}
record Circle(double diameter) implements Shape {

    public String aCircleMethod () {
        return "This is a circle method";
    }
}

class ShapeUtil {

    @Test
    public void testShape(){
        Shape shape = new Rectangle(1, 2);

        assertEquals("This is a rectangle method", get(shape));
    }

    private String get(Shape shape) {
        return switch (shape) {
            case Rectangle r -> r.aRectangleMethod();
            case Circle c -> c.aCircleMethod();
        };
    }
}