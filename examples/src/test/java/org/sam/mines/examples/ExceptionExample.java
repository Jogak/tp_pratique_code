package org.sam.mines.examples;

public class ExceptionExample {

    public static void main(String... args) {
        try {
            doSomeStuff(1);
        } catch (CheckedException e) {

            // Never hide an exception: it should be either logged or thrown
//            e.printStackTrace();
        }
    }

    private static void doSomeStuff(int i) throws CheckedException {

        if (i > 3) {
            throw new CheckedException("invalid");
        } else {
            throw new UncheckedException("somehow invalid");
        }

    }

}


class UncheckedException extends RuntimeException {

    public UncheckedException() {
        super();
    }

    public UncheckedException(String s) {
        super(s);
    }

    public UncheckedException(String s, Throwable throwable) {
        super(s, throwable);
    }
}

class CheckedException extends Exception {

    public CheckedException(String s) {
        super(s);
    }

    public CheckedException(String s, Throwable throwable) {
        super(s, throwable);
    }
}