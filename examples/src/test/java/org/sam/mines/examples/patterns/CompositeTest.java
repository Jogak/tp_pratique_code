package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;
import org.sam.mines.examples.patterns.model.Shape;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeComposite implements Shape {

    private List<Shape> children;

    public ShapeComposite(List<Shape> children) {
        this.children = children;
    }

    public void add(Shape shape) {
        children.add(shape);
    }

    public void remove(Shape shape) {
        children.remove(shape);
    }

    @Override
    public String getName() {
        return children.stream().map(Shape::getName).collect(Collectors.joining(","));
    }
}

class CompositeTest {

    @Test
    void shouldCompose() {
        Shape shape = new ShapeComposite(List.of(() -> "rectangle", () -> "circle"));

        assertEquals("rectangle,circle", shape.getName());
    }
}
