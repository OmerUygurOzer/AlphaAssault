package com.boomer.alphaassault.handlers;

import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BAnimation;
import com.boomer.alphaassault.graphics.elements.BDrawable;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Omer on 11/27/2015.
 */
public class RenderStateManager {

    private static RenderState updateBuffer;
    private static RenderState renderBuffer;

    private static List<Update> incomingUpdates;
    private static List<Update> processingUpdates;

    private static final Object incomingUpdateLock;



    static{
        updateBuffer = new RenderState(0);
        renderBuffer = new RenderState(1);

        incomingUpdates = new ArrayList<Update>();
        processingUpdates = new ArrayList<Update>();

        incomingUpdateLock = new Object();
    }

    public static void addUpdate(Update _update){
        synchronized (incomingUpdateLock) {
            incomingUpdates.add(_update);
        }
    }

    public static void processUpdates(){
        synchronized (incomingUpdateLock){
            processingUpdates.addAll(incomingUpdates);
            incomingUpdates.clear();
        }
        for(Update update : processingUpdates){
            int viewType;
            int referenceId;
            int depth;
            switch (update.type){
                case ADD:
                    BDrawable bDrawable = createInstance(update);
                    viewType = (int)update.values.get(Update.ValueType.VIEW_TYPE).valueDouble;
                    referenceId = (int)update.values.get(Update.ValueType.REFERENCE_ID).valueDouble;
                    depth = (int)update.values.get(Update.ValueType.DEPTH).valueDouble;
                    renderBuffer.addElement(viewType,referenceId,depth,bDrawable);
                    break;
                case REMOVE:
                    referenceId = (int)update.values.get(Update.ValueType.REFERENCE_ID).valueDouble;
                    depth = (int)update.values.get(Update.ValueType.DEPTH).valueDouble;
                    renderBuffer.removeElement(referenceId,depth);
                    break;
                case UPDATE:
                    referenceId = (int)update.values.get(Update.ValueType.REFERENCE_ID).valueDouble;
                    depth = (int)update.values.get(Update.ValueType.DEPTH).valueDouble;
                    renderBuffer.updateElement(referenceId,depth,update);
                    break;
            }
        }
    }

    public static void render(){
            renderBuffer.render();
    }

    public static void dispose(){
            renderBuffer.dispose();
            updateBuffer.dispose();
    }


    private static BDrawable createInstance(Update _update){
            Class inc = _update.drawable;

           if(inc.equals(BAnimation.class)){
               BAnimation bAnimation = new BAnimation(_update.textureRegions,_update.animationType);
               float x = _update.values.get(Update.ValueType.CENTER).valueVector2.x;
               float y = _update.values.get(Update.ValueType.CENTER).valueVector2.y;
               int size = (int) _update.values.get(Update.ValueType.SIZE).valueDouble;
               bAnimation.setCenter();
           }
            return null;
    }



}
