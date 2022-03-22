package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;
import org.sam.mines.examples.patterns.model.Shape;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NiceShape implements Shape {
    @Override
    public String getName() {
        return "A nice rectangle";
    }
}

class NiceShapeDecorator extends NiceShape {
    @Override
    public String getName() {
        return super.getName() + " with a decoration";
    }
}

class DecoratorTest {

    @Test
    void shouldDecorate() {
        assertEquals("A nice rectangle with a decoration", new NiceShapeDecorator().getName());
    }
}
