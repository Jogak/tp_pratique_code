package org.sam.mines.examples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Disabled("This test is a non-thread safe example : sometimes it passes, sometimes not :)")
public class ThreadSafety {

    @Test
    public void shouldTestThreadUnsafe() throws InterruptedException {
        int threadPool = 100;
        int taskCount = 1000;

        final UnsafeThreadClass unsafeThreadClass = new UnsafeThreadClass();

        ExecutorService executorService = Executors.newFixedThreadPool(threadPool);

        List<Callable<Boolean>> collect = IntStream.range(0, taskCount)
                .mapToObj(value -> (Callable<Boolean>) () -> {
                    String threadValue = String.valueOf(new Random().nextInt());

                    unsafeThreadClass.setUnsafeValue(threadValue);

                    return threadValue.equals(unsafeThreadClass.getUnsafeValue());
                })
                .collect(Collectors.toList());


        executorService.invokeAll(collect).stream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                return null;
            }
        })
                .forEach(Assertions::assertTrue);

    }

    private class UnsafeThreadClass {


        private String unsafeValue;

        public String getUnsafeValue() {
            return unsafeValue;
        }

        public void setUnsafeValue(String unsafeValue) {
            this.unsafeValue = unsafeValue;
        }
    }

}
