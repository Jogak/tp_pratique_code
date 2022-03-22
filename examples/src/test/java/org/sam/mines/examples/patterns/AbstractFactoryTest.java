package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;
import org.sam.mines.examples.patterns.model.Color;
import org.sam.mines.examples.patterns.model.Shape;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

interface AbstractFactory<T> {
    T create(String type);
}

class ConcreteColorFactory implements AbstractFactory<Color> {
    @Override
    public Color create(String type) {
        return switch (type) {
            case "RED" -> () -> "255-0-0";
            case "GREEN" -> () -> "0-255-0";
            case "BLUE" -> () -> "0-0-255";
            default -> () -> "0-0-0";
        };

    }
}

class ConcreteShapeFactory implements AbstractFactory<Shape> {
    @Override
    public Shape create(String type) {
        return switch (type) {
            case "CIRCLE" -> () -> "circle";
            case "ELLIPSIS" -> () -> "ellipsis";
            case "RECTANGLE" -> () -> "rectangle";
            default -> throw new IllegalArgumentException(String.format("%s is not a valid shape", type));
        };
    }
}
class FactoryProvider {

    public static AbstractFactory getFactory(String type){
        return switch (type) {
            case "COLOR" -> new ConcreteColorFactory();
            case "SHAPE" -> new ConcreteShapeFactory();
            default -> throw new IllegalArgumentException(String.format("%s is not a valid shape", type));
        };
    }
}

public class AbstractFactoryTest {

    @Test
    void shouldCreateObjects(){

        Object color = FactoryProvider.getFactory("COLOR").create("RED");

        assertTrue(color instanceof Color);
        assertEquals("255-0-0", ((Color) color).getRgb());

        Object shape = FactoryProvider.getFactory("SHAPE").create("RECTANGLE");

        assertTrue(shape instanceof Shape);
        assertEquals("rectangle", ((Shape)shape).getName());
    }
}
