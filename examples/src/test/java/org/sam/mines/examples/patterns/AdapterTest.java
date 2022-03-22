package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;
import org.sam.mines.examples.patterns.model.Color;
import org.sam.mines.examples.patterns.model.Shape;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeAdapter implements Shape {

    private Color color;

    public ShapeAdapter(Color color) {
        this.color = color;
    }

    @Override
    public String getName() {
        return "Some useful adaptation " + color.getRgb();
    }
}

class AdapterTest {
    @Test
    void shouldAdapt() {
        Color color = () -> "red";
        Shape shape = new ShapeAdapter(color);

        assertEquals("Some useful adaptation red", shape.getName());
    }
}
