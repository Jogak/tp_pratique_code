package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class AbstractTemplate {
    protected abstract String templatedMethod();

    public String getTemplateResult() {
        return "Template result : " + templatedMethod();
    }
}

class ConcreteTemplate extends AbstractTemplate {
    @Override
    protected String templatedMethod() {
        return "templated method";
    }
}

class TemplateTest {
    @Test
    void shouldTemplateMethod() {
        assertEquals("Template result : templated method", new ConcreteTemplate().getTemplateResult());

    }
}
