package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;
import org.sam.mines.examples.patterns.model.Color;
import org.sam.mines.examples.patterns.model.Shape;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

interface Facade {
    String doSomeComplexStuff();
}

class FacadeImpl implements Facade {

    @Override
    public String doSomeComplexStuff() {
        Shape shape = () -> "rectangle";
        Color color = () -> "red";

        // A really complex code here...
        StringBuilder collect = new Random().ints(0, 40)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append);

        return collect.append(shape.getName()).append(".").append(color.getRgb()).toString();
    }
}

class FacadeTest {

    @Test
    void shouldProvideFacade() {
        String facadeResult = new FacadeImpl().doSomeComplexStuff();

        assertTrue(facadeResult.endsWith("rectangle.red"));
    }
}
