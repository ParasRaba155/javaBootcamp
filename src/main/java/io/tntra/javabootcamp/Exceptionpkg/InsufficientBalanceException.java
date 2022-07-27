package io.tntra.javabootcamp.Exceptionpkg;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
