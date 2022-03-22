package org.sam.mines.examples;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class OptionalExample {


    @Test
    public void shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () ->  canBeNullable().substring(1));
    }

    @Test
    public void shouldNotThrowNullPointerException() {
        assertNotNull(cannotBeNullable().map(value -> value.substring(1)).orElse(""));
    }

    private String canBeNullable() {
        return null;
    }

    private Optional<String> cannotBeNullable() {

        return Optional.of("value");
    }

}
