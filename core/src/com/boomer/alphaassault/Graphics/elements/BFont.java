package com.boomer.alphaassault.graphics.elements;

import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 12/13/2015.
 */
public class BFont implements BDrawable {

    private static final int ASCII_BASE = 32;

    public static final int SCALER = 2;

    private int fontSize;

    private Location location;

    private String text;
    private TextureRegion[] ascii;
    private List<Sprite> spriteText;


    public BFont(Location _location,String _text,int _fontSize){
        text = new String(_text);
        fontSize = _fontSize;
        location = _location;

        spriteText = new ArrayList<Sprite>();

        //INITIALIZE FONTS
        ascii = new TextureRegion[6 * 16];
        TextureRegion[][] textureRegions = TextureRegion.split(Resource.getTexture(Resource.TEXTURE_FONTS),32,31);
        int index = 0;
        for(int y = 0;y<6;y++){
                for(int x = 0;x<16;x++){
                    ascii[index] = textureRegions[y][x];
                    index++;
                }
            }



        generateFont();

    }

    private void generateFont(){
        spriteText.clear();
        for(int i = 0;i<text.length();i++){
            int charToAsc = (int)text.charAt(i);
            charToAsc = charToAsc - ASCII_BASE;
            Sprite charSprite = new Sprite(ascii[charToAsc]);
            charSprite.setSize(fontSize * SCALER, fontSize * SCALER);
            charSprite.setCenter(location.x + i*fontSize*SCALER,location.y);
            spriteText.add(charSprite);
        }

    }

    private String getText(){return text;}
    private void setText(String _text){
        text = _text;
        generateFont();
    }

    @Override
    public void set(BDrawable _bDrawable) {
        BFont bFont = (BFont)_bDrawable;
        setText(bFont.getText());
    }

    @Override
    public void draw(SpriteBatch _spriteBatch) {
        for(Sprite sprite: spriteText){
            sprite.draw(_spriteBatch);
        }
    }

    @Override
    public BDrawable copy() {
        BFont bFont  = new BFont(new Location(location.x,location.y),text,fontSize);
        return  bFont;
    }

    @Override
    public Location getCenter() {
        return location;
    }
}
