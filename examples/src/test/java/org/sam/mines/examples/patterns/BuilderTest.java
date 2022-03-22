package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BuilderTest {

    @Test
    void shouldBuildObject() {

        User user = UserBuilder.anUser().age(50).firstname("tom").lastname("araya").build();

        assertEquals(50, user.age());
    }
}
