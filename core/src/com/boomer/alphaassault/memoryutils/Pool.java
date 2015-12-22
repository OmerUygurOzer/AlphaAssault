package com.boomer.alphaassault.memoryutils;



import com.boomer.alphaassault.exceptions.NullPoolableException;
import com.boomer.alphaassault.exceptions.ObjectNotPoolableException;
import com.boomer.alphaassault.gameworld.GameWorld;


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
        if(object == null){throw new NullPoolableException();}
        if(!(object instanceof  Poolable)) {throw new ObjectNotPoolableException();}
        ((Poolable)object).reset();
        memStack.push(object);
    }

    public void freeAll(Collection<T> all){
        for(T t : all){
            free(t);
        }
    }

    public T acquire(){
        if(!memStack.isEmpty()){
           return memStack.pop();

        }
        return newObject();
    }

}


