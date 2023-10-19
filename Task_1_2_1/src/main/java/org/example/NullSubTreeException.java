package org.example;

/**
 * custom exception.
 */
public class NullSubTreeException extends Exception {
    public NullSubTreeException(String errorMessage) {
        super(errorMessage);
    }
}