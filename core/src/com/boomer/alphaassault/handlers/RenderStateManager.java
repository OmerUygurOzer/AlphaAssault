package com.boomer.alphaassault.handlers;

import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.settings.GameSettings;




/**
 * Created by Omer on 11/27/2015.
 */
public class RenderStateManager {


    private static int currentUpdatingStateIndex = -1;
    private static int lastUpdatedStateIndex = -1;
    private static int previousUpdatedStateIndex = -1;
    private static int currentRenderingStateIndex = -1;
    private static int lastUpdatedStateThatIsNotRendering = -1;

    public static RenderState renderingStatePointer;
    public static RenderState updatingStatePointer;
    public static RenderState lastUpdatedStatePointer;

    private static Object pointerSwitchLock;

    public static RenderState[] renderStates;

    public static int currentFrame;



    static{
        renderingStatePointer = null;
        updatingStatePointer = null;
        lastUpdatedStatePointer = null;

        renderStates = new RenderState[3];
        renderStates[0] = new RenderState(0);
        renderStates[1] = new RenderState(1);
        renderStates[2] = new RenderState(2);



        pointerSwitchLock = new Object();


        currentFrame = 0;

    }

    public static void beginUpdating(){
        updatingStatePointer = getUpdatingState();
        updatingStatePointer.currentFrame = currentFrame;
    }

    public static void swapUpdates(){
        lastUpdatedStatePointer = RenderStateManager.getLastUpdatedState();
        if(lastUpdatedStatePointer == null){
            updatingStatePointer.getUpdates(updatingStatePointer);
        }else{
            updatingStatePointer.getUpdates(lastUpdatedStatePointer);
        }
    }

    public static RenderState getUpdatingState(){
        synchronized (pointerSwitchLock){
            if(currentRenderingStateIndex == -1 || lastUpdatedStateIndex == currentRenderingStateIndex){
                switch (lastUpdatedStateIndex) {
                    case 0:
                        //System.out.println("case 0");
                        if (renderStates[1].currentFrame > renderStates[2].currentFrame) {
                            currentUpdatingStateIndex = 2;
                            return renderStates[2];
                        } else {
                            currentUpdatingStateIndex = 1;
                            return renderStates[1];
                        }
                    case 1:
                        //System.out.println("case 1");
                        if (renderStates[0].currentFrame > renderStates[2].currentFrame) {
                            currentUpdatingStateIndex = 2;
                            return renderStates[2];
                        } else {
                            currentUpdatingStateIndex = 0;
                            return renderStates[0];
                        }
                    case 2:
                        //System.out.println("case 2");
                        if (renderStates[0].currentFrame > renderStates[1].currentFrame) {
                            currentUpdatingStateIndex = 1;
                            return renderStates[1];
                        } else {
                            currentUpdatingStateIndex = 0;
                            return renderStates[0];
                        }
                    default:
                        // System.out.println("case default");
                        currentUpdatingStateIndex = 0;
                        return renderStates[0];
                }
            }else{
                //  System.out.println("case else");
                currentUpdatingStateIndex = 3 - (lastUpdatedStateIndex + currentRenderingStateIndex);
                return renderStates[currentUpdatingStateIndex];
            }

        }

    }

    public static void releaseUpdatingState(){
        synchronized (pointerSwitchLock){
            currentFrame++;
            previousUpdatedStateIndex = lastUpdatedStateIndex;
            lastUpdatedStateIndex = currentUpdatingStateIndex;
            lastUpdatedStateThatIsNotRendering = lastUpdatedStateIndex;
            currentUpdatingStateIndex = -1;
        }
    }

    public static RenderState getRenderingState(){
        synchronized (pointerSwitchLock){
            currentRenderingStateIndex = lastUpdatedStateIndex;
            lastUpdatedStateThatIsNotRendering = previousUpdatedStateIndex;
        }
        return renderStates[currentRenderingStateIndex];
    }

    public static void releaseRenderingState(){
        synchronized (pointerSwitchLock){
            currentRenderingStateIndex = -1;
        }
    }

    public static RenderState getLastUpdatedState(){
        if(lastUpdatedStateIndex == -1){
            return null;
        }
        return renderStates[lastUpdatedStateIndex];
    }

    public static void setGameRenderState(RenderState _renderState){
        GameSettings.GAME_RUNNING_STATE = GameSettings.RUNNING_STATE_INACTIVE;
        renderStates[0].set(_renderState);
        renderStates[1].set(_renderState);
        renderStates[2].set(_renderState);
        GameSettings.GAME_RUNNING_STATE = GameSettings.RUNNING_STATE_ACTIVE;

    }









    public static void dispose(){
        renderStates[0].dispose();
        renderStates[1].dispose();
        renderStates[2].dispose();
    }




}