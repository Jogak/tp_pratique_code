package org.sam.mines.examples;

import java.util.Collection;
import java.util.Collections;

public class ImmutableClassExample {

    private String value;

    private int intValue;

    private Collection<Double> collection;

    public ImmutableClassExample(String value, int intValue, Collection<Double> collection) {
        this.value = value;
        this.intValue = intValue;
        this.collection = Collections.unmodifiableCollection(collection);
    }

    public String getValue() {
        return value;
    }

    public int getIntValue() {
        return intValue;
    }

    public Collection<Double> getCollection() {
        return collection;
    }
}
