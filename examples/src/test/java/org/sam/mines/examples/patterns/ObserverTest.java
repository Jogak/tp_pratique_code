package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

interface Subscriber {
    void update();
}

class Publisher {

    private List<Subscriber> subscribers = new ArrayList<>();

    public void attach(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void executeSomeStuff() {
        System.out.println("Do something useful");

        this.subscribers.stream().forEach(Subscriber::update);
    }
}


public class ObserverTest {
    @Test
    void shouldObserve() {

        AtomicReference<String> sideEffectReturn1 = new AtomicReference<>();
        AtomicReference<String> sideEffectReturn2 = new AtomicReference<>();

        Publisher publisher = new Publisher();
        publisher.attach(() -> sideEffectReturn1.set("Concrete subscriber"));
        publisher.attach(() -> sideEffectReturn2.set("Another concrete subscriber"));

        publisher.executeSomeStuff();

        assertEquals("Concrete subscriber", sideEffectReturn1.get());
        assertEquals("Another concrete subscriber", sideEffectReturn2.get());
    }
}
