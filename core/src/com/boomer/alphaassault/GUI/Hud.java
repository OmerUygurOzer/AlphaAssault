package com.boomer.alphaassault.GUI;

import com.boomer.alphaassault.gameworld.gamelogic.Player;
import com.boomer.alphaassault.gameworld.gamelogic.buffs.Buff;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.utilities.Updateable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 12/13/2015.
 */
public class Hud implements Renderable,Updateable {

    private long referenceId;
    private int viewType;


    private Player player;

    //ICONS , FONTS
    private BSprite playerIcon;


    //PLAYER VARIABLES
    //BUFFS:
    private List<Buff> buffsShown;
    private float buffBaseX;
    private float buffBaseY;

    public Hud(String _playerName, BSprite _playerIcon){
            playerIcon = _playerIcon;
            buffsShown = new ArrayList<Buff>();
            buffBaseX =  Buff.WIDTH/2 + 10f;
            buffBaseY = GameGraphics.VIRTUAL_HEIGHT - 130f;
    }

    public void setPlayer(Player _player){
        player = _player;
    }

    public void showBuff(Buff _buff){
        _buff.getIcon().setCenter(buffBaseX,buffBaseY + Buff.WIDTH + 10);
        _buff.setViewType(viewType);
        _buff.addToRenderState();
        buffsShown.add(_buff);
    }
    public void removeBuff(Buff _buff) {
        buffsShown.remove(_buff);
        _buff.removeFromRenderState();
    }


    @Override
    public void addToRenderState() {

    }

    @Override
    public void removeFromRenderState() {

    }

    @Override
    public long getReferenceID() {
        return referenceId;
    }

    @Override
    public void setReferenceID(long _referenceId) {
        referenceId = _referenceId;
    }

    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
    }

    @Override
    public void update(float _deltaTime) {
         //   for()
    }
}
