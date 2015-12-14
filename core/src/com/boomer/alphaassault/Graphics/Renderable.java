package com.boomer.alphaassault.graphics;



/**
 * Created by Omer on 11/25/2015.
 */
public interface Renderable {
    public void addToRenderState();
    public void removeFromRenderState();
    public long getReferenceID();
    public void setReferenceID(long _referenceId);
    public void setViewType(int _viewType);
}
