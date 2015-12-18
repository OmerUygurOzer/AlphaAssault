package com.boomer.alphaassault.exceptions;

/**
 * Created by Omer on 12/17/2015.
 */
public class ObjectNotPoolableException extends RuntimeException {
    private static final String message = "Object is not of Poolable type.";

    public ObjectNotPoolableException(){
        super(message);
    }
}
