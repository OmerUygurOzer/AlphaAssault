package com.boomer.alphaassault.graphics;



/**
 * Created by Omer on 11/25/2015.
 */
public interface Renderable {
    public void removeFromRenderState();
    public short getReferenceID();
    public void setViewType(int _viewType);
    public void addToRenderState();
}
