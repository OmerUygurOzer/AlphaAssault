package com.boomer.alphaassault.memoryutils;

import com.boomer.alphaassault.exceptions.GameEngineException;

import java.util.Collection;
import java.util.Stack;

/**
 * Created by Omer on 12/17/2015.
 */
public abstract class Pool<T> {
    private Stack<T> memStack;

    public Pool(){
        memStack = new Stack<T>();
    }

    abstract protected T newObject();

    public void free(T object){
        if(object == null){throw new GameEngineException("Null Poolable Assignment.");}
        if(!(object instanceof  Poolable)) {throw new GameEngineException("Object Instance Not Poolable");}
        ((Poolable)object).reset();
        memStack.push(object);
    }

    public void freeAll(Collection<T> all){
        for(T t : all){
            free(t);
        }
    }

    public T acquire(){
       return memStack.isEmpty() ? newObject() : memStack.pop();
    }

}


