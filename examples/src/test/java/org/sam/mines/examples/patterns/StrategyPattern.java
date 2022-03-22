package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;
import org.sam.mines.examples.patterns.model.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

interface ChangeColorStrategy {

    Color getChangedColor(Color color);

    static ChangeColorStrategy noChange() {
        return (color) -> color;
    }

    static ChangeColorStrategy invert() {
        return (color) -> () -> new StringBuilder(color.getRgb()).reverse().toString();
    }
}

class ChangeColor {
    private List<Color> colors = new ArrayList<>();
    private ChangeColorStrategy changeColorStrategy;

    public ChangeColor(ChangeColorStrategy changeColorStrategy) {
        this.changeColorStrategy = changeColorStrategy;
    }

    public void add(Color color) {
        this.colors.add(color);
    }

    public String getColors() {
        return colors.stream().map(changeColorStrategy::getChangedColor).map(Color::getRgb).collect(Collectors.joining(","));
    }


    public void setChangeColorStrategy(ChangeColorStrategy changeColorStrategy) {
        this.changeColorStrategy = changeColorStrategy;
    }
}

class StrategyPattern {

    @Test
    void shouldDoStrategy() {

        ChangeColor changeColor = new ChangeColor(ChangeColorStrategy.noChange());
        changeColor.add(() -> "CC0033");
        changeColor.add(() -> "AABBCC");
        changeColor.add(() -> "001122");

        assertEquals("CC0033,AABBCC,001122", changeColor.getColors());

        changeColor.setChangeColorStrategy(ChangeColorStrategy.invert());
        assertEquals("3300CC,CCBBAA,221100", changeColor.getColors());

        changeColor.setChangeColorStrategy(color -> () -> "AA0066");
        assertEquals("AA0066,AA0066,AA0066", changeColor.getColors());
    }
}
