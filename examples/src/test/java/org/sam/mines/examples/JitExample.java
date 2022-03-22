package org.sam.mines.examples;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

/**
 * Example found here : https://stackoverflow.com/questions/15756075/is-it-true-that-having-lots-of-small-methods-helps-the-jit-compiler-optimize
 *
 * run with those options :
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintCompilation -XX:FreqInlineSize=50 -XX:MaxInlineSize=50 -XX:+PrintInlining
 */
public class JitExample {

    private int count = 0;

    @Test
    public void longMethodTest() {
        int sum1 = IntStream.range(0, 1000000)
                .map(i -> longMethod())
                .sum();
        System.out.println(sum1);
    }

    @Test
    public void shortMethodTest() {
        int sum1 = IntStream.range(0, 1000000)
                .map(i -> shortMethod())
                .sum();
        System.out.println(sum1);
    }

    /**
     * Non JITed
     */
    private int longMethod() {
        int i = count;
        if (i % 10 == 0) {
            i += 1;
        } else if (i % 10 == 1) {
            i += 2;
        } else if (i % 10 == 2) {
            i += 3;
        }
        i += count;
        i *= count;
        i++;
        return i;
    }

    /**
     * JITed method
     */
    private int shortMethod() {
        int i = count;
        i = shortMethod2(i);
        i += count;
        i *= count;
        i++;
        return i;
    }

    private int shortMethod2(int i) {
        if (i % 10 == 0) {
            i += 1;
        } else if (i % 10 == 1) {
            i += 2;
        } else if (i % 10 == 2) {
            i += 3;
        }
        return i;
    }

}
