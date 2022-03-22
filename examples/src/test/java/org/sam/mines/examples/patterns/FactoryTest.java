package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;
import org.sam.mines.examples.patterns.model.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FunctionalInterface
interface ColorFactory {
    Color createColor(String value);
}

class ConcreteColorColorFactory implements ColorFactory {
    @Override
    public Color createColor(String value) {
        return switch (value) {
            case "RED" -> () -> "255-0-0";
            case "GREEN" -> () -> "0-255-0";
            case "BLUE" -> () -> "0-0-255";
            default -> () -> "0-0-0";
        };
    }
}

class FactoryTest {
    @Test
    void shouldReturnColors(){
        ConcreteColorColorFactory concreteColorFactory = new ConcreteColorColorFactory();

        assertEquals("255-0-0", concreteColorFactory.createColor("RED").getRgb());
        assertEquals("0-255-0", concreteColorFactory.createColor("GREEN").getRgb());
        assertEquals("0-0-255", concreteColorFactory.createColor("BLUE").getRgb());
        assertEquals("0-0-0", concreteColorFactory.createColor("some other value").getRgb());
    }
}
