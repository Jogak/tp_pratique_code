package org.sam.mines.examples;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by sam on 26/05/17.
 */
public class EqualsHashCodeExample {

    @Test
    @Disabled
    public void shouldGetSimpleObjectButFails() {
        Set<SimpleInterface> set = new HashSet<>();

        set.add(new SimpleObjectWithoutEqualsHashcode("a"));
        set.add(new SimpleObjectWithoutEqualsHashcode("a"));
        set.add(new SimpleObjectWithoutEqualsHashcode("a"));

        assertEquals(1, set.size());
    }

    @Test
    @Disabled
    public void shouldGetSimpleObjectButFailsOnlyEquals() {
        Set<SimpleInterface> set = new HashSet<>();

        set.add(new SimpleObjectWithOnlyEquals("a"));
        set.add(new SimpleObjectWithOnlyEquals("a"));
        set.add(new SimpleObjectWithOnlyEquals("a"));

        assertEquals(1, set.size());
    }

    @Test
    @Disabled
    public void shouldGetSimpleObjectButFailsOnlyHashCode() {
        Set<SimpleInterface> set = new HashSet<>();

        set.add(new SimpleObjectWithOnlyHashCode("a"));
        set.add(new SimpleObjectWithOnlyHashCode("a"));
        set.add(new SimpleObjectWithOnlyHashCode("a"));

        assertEquals(1, set.size());
    }

    @Test
    @Disabled
    public void shouldShowEqualsHashCode() {

        Set<SimpleInterface> set = new HashSet<>();

        set.add(new SimpleObject("a"));
        set.add(new SimpleObject("a"));
        set.add(new SimpleObject("a"));

        assertEquals(1, set.size());

    }

    private interface SimpleInterface {
        String getValue();
    }


    private class SimpleObjectWithoutEqualsHashcode implements SimpleInterface {
        private String value;

        public SimpleObjectWithoutEqualsHashcode(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    private class SimpleObjectWithOnlyEquals implements SimpleInterface {
        private String value;

        public SimpleObjectWithOnlyEquals(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SimpleObjectWithOnlyEquals)) return false;
            SimpleObjectWithOnlyEquals that = (SimpleObjectWithOnlyEquals) o;
            return Objects.equals(value, that.value);
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    private class SimpleObjectWithOnlyHashCode implements SimpleInterface {
        private String value;

        public SimpleObjectWithOnlyHashCode(String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    private class SimpleObject implements SimpleInterface {
        private String value;

        public SimpleObject(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SimpleObject)) return false;
            SimpleObject that = (SimpleObject) o;
            return Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String getValue() {
            return value;
        }
    }


}
