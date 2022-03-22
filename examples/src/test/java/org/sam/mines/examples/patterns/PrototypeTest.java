package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrototypedShape implements Cloneable {

    private int intValue;

    private String stringValue;

    public int getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public PrototypedShape(int intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new PrototypedShape(intValue, stringValue);
    }
}

class PrototypeTest {

    @Test
    void shouldCloneShape() throws CloneNotSupportedException {

        PrototypedShape value = new PrototypedShape(1, "value");

        Object clone = value.clone();

        assertNotEquals(clone, value);

        assertTrue(clone instanceof PrototypedShape);
        assertEquals(value.getIntValue(), ((PrototypedShape) clone).getIntValue());
        assertEquals(value.getStringValue(), ((PrototypedShape) clone).getStringValue());
    }

}
