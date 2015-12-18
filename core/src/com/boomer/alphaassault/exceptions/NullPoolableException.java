package com.boomer.alphaassault.exceptions;

/**
 * Created by Omer on 12/17/2015.
 */
public class NullPoolableException extends RuntimeException {
    private static final String message = "Poolable can not be null";

    public NullPoolableException(){
        super(message);
    }
}
