package org.sam.mines.examples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LambdaExample {

    @Test
    void showSomeLambdas() {
        Map<String, List<Integer>> map = Map.of("key1", List.of(1, 2, 3), "key2", List.of(4, 5));

        List<String> collect = map.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .map(entry ->
                        Map.entry(entry.getKey(), entry.getValue().stream().max(Integer::compare)))
                .map(entry -> entry.getKey() + ", " + entry.getValue().orElse(0))
                .sorted().toList();


        assertEquals(List.of("key1, 3", "key2, 5"), collect);

    }
}
