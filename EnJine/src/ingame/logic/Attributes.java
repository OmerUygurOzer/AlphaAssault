package ingame.logic;

import exceptions.GameEngineException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 3/1/2016.
 */
public class Attributes implements Serializable{

    private Map<String,Double> numericAttributes = new HashMap<String, Double>();
    private Map<String,Boolean> binaryAttributes = new HashMap<String, Boolean>();
    private Map<String,String> textAttributes    = new HashMap<String, String>();

    public void addAttribute(String key, double attribute){
        boolean inBinary = binaryAttributes.containsKey(key);
        boolean inText = textAttributes.containsKey(key);
        if(inBinary || inText){
            throw new GameEngineException("Attribute Addition: Two attributes can not have the same key.");
        }
        numericAttributes.put(key,attribute);
    }

    public void addAttribute(String key, boolean attribute){
        boolean inNumeric = numericAttributes.containsKey(key);
        boolean inText = textAttributes.containsKey(key);
        if(inNumeric || inText){
            throw new GameEngineException("Attribute Addition: Two attributes can not have the same key.");
        }
        binaryAttributes.put(key,attribute);
    }

    public void addAttribute(String key, String attribute){
        boolean inBinary = binaryAttributes.containsKey(key);
        boolean inNumeric = numericAttributes.containsKey(key);
        if(inBinary || inNumeric){
            throw new GameEngineException("Attribute Addition: Two attributes can not have the same key.");
        }
        textAttributes.put(key,attribute);
    }

    public void replaceAttribute(String key,String attribute){
        boolean inBinary  = binaryAttributes.containsKey(key);
        boolean inNumeric = numericAttributes.containsKey(key);
        if(inBinary || inNumeric){
            throw new GameEngineException("Attribute Replacement: Type mismatch.");
        }
        textAttributes.put(key,attribute);
    }

    public void replaceAttribute(String key,double attribute){
        boolean inBinary = binaryAttributes.containsKey(key);
        boolean inText   = textAttributes.containsKey(key);
        if(inBinary || inText){
            throw new GameEngineException("Attribute Replacement: Type mismatch.");
        }
        numericAttributes.put(key,attribute);
    }

    public void replaceAttribute(String key,boolean attribute){
        boolean inNumeric = numericAttributes.containsKey(key);
        boolean inText   = textAttributes.containsKey(key);
        if(inNumeric || inText){
            throw new GameEngineException("Attribute Replacement: Type mismatch.");
        }

        binaryAttributes.put(key,attribute);
    }

    public Object getAttribute(String key){
        if(seekAttribute(key)){
            if(numericAttributes.containsKey(key))return numericAttributes.get(key);
            if(binaryAttributes.containsKey(key))return binaryAttributes.get(key);
            if(textAttributes.containsKey(key))return textAttributes.get(key);
        }
        return null;
    }

    public double getNumeric(String key){
        double val = 0d;
        if(numericAttributes.containsKey(key)){
             val = numericAttributes.get(key);
        }
        return val;
    }

    public boolean getBinary(String key){
        boolean val = false;
        if(binaryAttributes.containsKey(key)){
            val = binaryAttributes.get(key);
        }
        return val;
    }

    public String getText(String key){
        String val = "";
        if(textAttributes.containsKey(key)){
            val = textAttributes.get(key);
        }
        return val;
    }

    public boolean seekAttribute(String key){
        boolean inDouble = numericAttributes.containsKey(key);
        boolean inBinary = binaryAttributes.containsKey(key);
        boolean inText = textAttributes.containsKey(key);
        return inBinary || inDouble || inText;
    }

    public Attributes copy(){
        Attributes newAttribs = new Attributes();
        for(String key : numericAttributes.keySet()){
            newAttribs.addAttribute(key,new Double(numericAttributes.get(key)));
        }
        for(String key : binaryAttributes.keySet()){
            newAttribs.addAttribute(key,new Boolean(binaryAttributes.get(key)));
        }
        for(String key: textAttributes.keySet()){
            newAttribs.addAttribute(key,new String(textAttributes.get(key)));
        }
        return newAttribs;
    }

    public void insert(Attributes attributes){
        for(String key : attributes.numericAttributes.keySet()){
            this.numericAttributes.put(key,attributes.getNumeric(key));
        }
        for(String key : attributes.binaryAttributes.keySet()){
            this.binaryAttributes.put(key,attributes.getBinary(key));
        }
        for(String key : attributes.textAttributes.keySet()){
            this.textAttributes.put(key,attributes.getText(key));
        }

    }
}
