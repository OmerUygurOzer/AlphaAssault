package com.boomer.alphaassault.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.gamelogic.Player;
import com.boomer.alphaassault.gameworld.gamelogic.buffs.Buff;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.graphics.elements.BFont;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.gameworld.gamelogic.Updateable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 12/13/2015.
 */
public class Hud implements Renderable,Updateable {

    private static final int PLAYER_ICON_WIDTH = 100;
    private static final int PLAYER_ICON_HEIGHT = 100;

    private static final int PLAYER_ICON_X = 50;
    private static final int PLAYER_ICON_Y = GameGraphics.VIRTUAL_HEIGHT - 50;

    private static final int MAX_FONT_LENGTH = 10;
    private static final int FONT_BASE = 10;

    private static final int PLAYER_NAME_X = 110;
    private static final int PLAYER_NAME_Y = GameGraphics.VIRTUAL_HEIGHT - 5*BFont.SCALER;

    private static final int SUPPLY_FONT_BASE = 10;
    private static final int SUPPLY_X = PLAYER_NAME_X;
    private static final int SUPPLY_Y = PLAYER_NAME_Y - FONT_BASE;
    private static final int SUPPLY_ICON_SIZE = 20;

    private static final int PADDING = 10;

    private long referenceId;
    private int viewType;


    private Player player;

    //ICONS , FONTS
    private Texture playerIconTexture;
    private BSprite playerIcon;
    private BFont playerName;



    //PLAYER VARIABLES
    //BUFFS:
    private List<Buff> buffsShown;
    private List<Buff> buffsExpired;
    private float buffBaseX;
    private float buffBaseY;

    //SUPPLIES:
    private List<BFont> supplyFonts;
    private List<BSprite> supplyIcons;

    public Hud(){
            buffsShown = new ArrayList<Buff>();
            buffsExpired = new ArrayList<Buff>();
            buffBaseX =  Buff.WIDTH/2 + 10f;
            buffBaseY = GameGraphics.VIRTUAL_HEIGHT - 130f;

            supplyFonts = new ArrayList<BFont>();
            supplyIcons = new ArrayList<BSprite>();

    }

    public void setPlayer(Player _player){
        player = _player;

        playerIconTexture = player.getIcon();
        playerIcon = new BSprite(playerIconTexture);
        playerIcon.setSize(PLAYER_ICON_WIDTH,PLAYER_ICON_HEIGHT);
        playerIcon.setCenter(PLAYER_ICON_X,PLAYER_ICON_Y);

        //PRINT PLAYERNAME
        String playerNameString = new String(player.getName());
        int scaledFont = FONT_BASE - Math.round(5*playerNameString.length()/ MAX_FONT_LENGTH);
        playerName = new BFont(new Vector2(PLAYER_NAME_X,PLAYER_NAME_Y),playerNameString,scaledFont);

        //PRINT AMMO FONTS AND ICONS
        int supplyIndex = 0;
        for(int supplyKey : player.getPlayerUnit().getSupplies().keySet()){
            String ammoString =  " x " + player.getPlayerUnit().getSupplies().get(supplyKey).count;
            int scaledAmmoFont = FONT_BASE - Math.round(6*ammoString.length()/ MAX_FONT_LENGTH);
            BFont bFont = new BFont(new Vector2(SUPPLY_X + SUPPLY_ICON_SIZE, SUPPLY_Y - SUPPLY_FONT_BASE *supplyIndex),ammoString,scaledAmmoFont);
            supplyFonts.add(bFont);

            BSprite bSprite = new BSprite(player.getPlayerUnit().getSupplies().get(supplyKey).icon);
            bSprite.setSize(SUPPLY_ICON_SIZE,SUPPLY_ICON_SIZE);
            bSprite.setCenter(SUPPLY_X, SUPPLY_Y - SUPPLY_FONT_BASE *supplyIndex );
            supplyIcons.add(bSprite);
            supplyIndex++;
        }


      addToRenderState();
    }

    public void showBuff(Buff _buff){
        _buff.getIcon().setCenter(buffBaseX + buffsShown.size()* Buff.WIDTH,buffBaseY);
        _buff.setViewType(viewType);
        _buff.addToRenderState();
        buffsShown.add(_buff);
    }
    public void removeBuff(Buff _buff) {
        buffsShown.remove(_buff);
        _buff.removeFromRenderState();
    }

    private void updateAmmos(){

    }


    @Override
    public void addToRenderState() {
        RenderStateManager.addElement(viewType,referenceId+1,RenderState.DEPTH_GAME_SCREEN_BASE,playerIcon);
        RenderStateManager.addElement(viewType,referenceId+10, RenderState.DEPTH_GAME_SCREEN_BASE,playerName);
        int index = 1;
        for(BFont bFont : supplyFonts){
            RenderStateManager.addElement(viewType,referenceId + 10 + index,RenderState.DEPTH_GAME_SCREEN_BASE,bFont);
            index++;
        }
        for(BSprite bSprite : supplyIcons){
            RenderStateManager.addElement(viewType,referenceId + 10 + index,RenderState.DEPTH_GAME_SCREEN_BASE,bSprite);
            index++;
        }

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
        //UPDATE BUFFS
            for(Buff buff : player.getPlayerUnit().getActiveBuffs()){
                if(!buffsShown.contains(buff)){
                    showBuff(buff);
                }
            }
            for(Buff buff : buffsShown){
                if(!player.getPlayerUnit().getActiveBuffs().contains(buff)){
                    buffsExpired.add(buff);
                }
            }

            for(Buff buff : buffsExpired){
               removeBuff(buff);

            }
            buffsExpired.clear();

        //UPDATE AMMOS
        updateAmmos();

    }
}
