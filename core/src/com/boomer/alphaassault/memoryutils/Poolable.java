package com.boomer.alphaassault.memoryutils;

/**
 * Created by Omer on 12/17/2015.
 */
public interface Poolable {
    public void free();
    public void reset();
}
