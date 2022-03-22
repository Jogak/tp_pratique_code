package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;
import org.sam.mines.examples.patterns.model.Shape;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class AbstractBridgeClient {

    private Shape shape;

    public AbstractBridgeClient(Shape shape) {
        this.shape = shape;
    }

    protected String getShapeName() {
        // Delegates to the shape interface
        return shape.getName();
    }
}

class ConcreteBridgeClient extends AbstractBridgeClient {
    public ConcreteBridgeClient(Shape shape) {
        super(shape);
    }

    @Override
    protected String getShapeName() {
        return "Concrete " + super.getShapeName();
    }
}

public class BridgeTest {

    @Test
    void shouldProvideABridge() {

        assertEquals("Concrete circle", new ConcreteBridgeClient(() -> "circle").getShapeName());
    }
}
