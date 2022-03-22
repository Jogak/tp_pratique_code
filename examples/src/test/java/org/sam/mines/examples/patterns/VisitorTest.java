package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

interface VisitedElement {
    void accept(Visitor visitor);
}

class ConcreteVisitor1 implements VisitedElement {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class ConcreteVisitor2 implements VisitedElement {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class ConcreteListVisitor implements VisitedElement {
    private List<VisitedElement> visitedElements;

    public ConcreteListVisitor(List<VisitedElement> visitedElements) {
        this.visitedElements = visitedElements;
    }

    @Override
    public void accept(Visitor visitor) {
        visitedElements.forEach(element -> element.accept(visitor));

        visitor.visit(this);
    }
}

interface Visitor {
    void visit(ConcreteVisitor1 concreteVisitor1);

    void visit(ConcreteVisitor2 concreteVisitor2);

    void visit(ConcreteListVisitor concreteListVisitor);
}

class VisitorTest {

    @Test
    void shouldVisit() {

        ConcreteListVisitor list = new ConcreteListVisitor(List.of(new ConcreteVisitor1(), new ConcreteVisitor2()));

        AtomicReference<String> sideEffect = new AtomicReference<>("");
        list.accept(new Visitor() {
            @Override
            public void visit(ConcreteVisitor1 concreteVisitor1) {
                sideEffect.accumulateAndGet("concreteVisitor1", (s, s2) -> s + "," + s2);
            }

            @Override
            public void visit(ConcreteVisitor2 concreteVisitor2) {
                sideEffect.accumulateAndGet("concreteVisitor2", (s, s2) -> s + "," + s2);
            }

            @Override
            public void visit(ConcreteListVisitor concreteListVisitor) {
                sideEffect.accumulateAndGet("concreteVisitorList", (s, s2) -> s + "," + s2);
            }
        });

        assertEquals(",concreteVisitor1,concreteVisitor2,concreteVisitorList", sideEffect.get());

    }
}
