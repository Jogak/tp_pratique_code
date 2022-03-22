package org.sam.mines.examples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by sam on 25/05/17.
 */
@Disabled
public class IterationExit {

    private static final int LIMIT = 10000;

    private int end;

    private final List<Integer> values;

    public IterationExit() {
        int count = 10000000;
        values = IntStream.range(0, count).boxed().collect(Collectors.toList());
    }

    @BeforeEach
    public void setUp() {
        end = -1;
    }

    private double getComputedValue(int entry) {
        return (entry + 1000 / 50) % 4 + LIMIT;
    }

    @Test
    @RepeatedTest(10)
    public void shouldDemonstrateIteration() {

        long start = System.currentTimeMillis();

        for (Integer integer : values) {
            // Some kind of useless code
            if (end < 0 && getComputedValue(integer) > LIMIT) {
                end = integer;
            }
        }

        assertTrue(end > 0);

        System.out.println("shouldDemonstrateIteration executed in " + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    @RepeatedTest(10)
    public void shouldDemonstrateIterationExitBenefits() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < values.size() && end < 0; i++) {
            Integer integer = values.get(i);

            // Some kind of useless code
            if (getComputedValue(integer) > LIMIT) {
                end = integer;
            }
        }

        assertTrue(end > 0);
        System.out.println("shouldDemonstrateIterationExitBenefits executed in " + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    @RepeatedTest(10)
    public void shouldDoWithStream() {
        long start = System.currentTimeMillis();

        assertTrue(values.stream()
                .filter(entry -> getComputedValue(entry) > LIMIT)
                .findFirst()
                .filter(e -> e > 0)
                .isPresent());

        System.out.println("shouldDoWithStream executed in " + (System.currentTimeMillis() - start) + "ms");
    }


}
