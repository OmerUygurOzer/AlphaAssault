package com.boomer.alphaassault.handlers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.graphics.elements.BAnimation;
import com.boomer.alphaassault.graphics.elements.BDrawable;
import com.boomer.alphaassault.utilities.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 12/22/2015.
 */
public class Update {
    public Type type;

    public Class<? extends BDrawable> drawable;
    public Map<ValueType,Value> values;

    public String text;
    public Texture texture;
    public TextureRegion[][] textureRegions;

    public BAnimation.Type animationType;

   public enum Type{
        ADD,
        REMOVE,
        UPDATE;
    }

    public enum ValueType{
        REFERENCE_ID,
        DEPTH,
        VIEW_TYPE,
        SIZE,
        CENTER,
        CURRENT_FRAME,
        ANGLE,
        INVISIBILITY,
        SPF,
    }

    public Update(Type _type){
        type = _type;
        values = new HashMap<ValueType, Value>();
    }

    public void setDrawable(Class<BDrawable> _drawable){
        drawable = _drawable;
    }

    public void setValue(ValueType _valueType, Vector2 _vector){
        values.put(_valueType,new Value(_vector));
    }

    public void setValue(ValueType _valueType,double _double){
        values.put(_valueType,new Value(_double));
    }

    public void setValue(ValueType _valueType,boolean _bool){
        values.put(_valueType,new Value(_bool));
    }

    public void setText(String _text){
        text = _text;
    }

    public void setTexture(Texture _texture){
        texture = _texture;
    }

    public void setTextureRegions(TextureRegion[][] _textureRegions){
        textureRegions = _textureRegions;
    }

    public void setAnimationType(BAnimation.Type _type){
        animationType = _type;
    }
}
