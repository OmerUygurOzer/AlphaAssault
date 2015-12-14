package com.boomer.alphaassault.GUI;

import com.boomer.alphaassault.gameworld.gamelogic.Player;
import com.boomer.alphaassault.gameworld.gamelogic.buffs.Buff;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.graphics.elements.BFont;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Updateable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 12/13/2015.
 */
public class Hud implements Renderable,Updateable {

    private static final int PLAYER_NAME_MAX_LENGTH = 10;

    private static final int PLAYER_NAME_FONT_BASE = 10;

    private static final int PLAYER_NAME_X = 110;
    private static final int PLAYER_NAME_Y = GameGraphics.VIRTUAL_HEIGHT - 5*BFont.SCALER;

    private long referenceId;
    private int viewType;


    private Player player;

    //ICONS , FONTS
    private BSprite playerIcon;
    private BFont playerName;


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

            int scaledFont = PLAYER_NAME_FONT_BASE - Math.round(5*_playerName.length()/PLAYER_NAME_MAX_LENGTH);
            playerName = new BFont(new Location(PLAYER_NAME_X,PLAYER_NAME_Y),_playerName,scaledFont);



    }

    public void setPlayer(Player _player){
        player = _player;
    }

    public void showBuff(Buff _buff){
        _buff.getIcon().setCenter(buffBaseX,buffBaseY + buffsShown.size()* Buff.WIDTH + 10);
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
        RenderStateManager.addElement(viewType,referenceId+10, RenderState.DEPTH_GAME_SCREEN,playerName);
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
