package ingame.logic;

import exceptions.GameEngineException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 3/1/2016.
 */
public class Attributes implements AttributeHolder{

    private Map<String,Double> numericAttributes = new HashMap<String, Double>();
    private Map<String,Boolean> binaryAttributes = new HashMap<String, Boolean>();
    private Map<String,String> textAttributes    = new HashMap<String, String>();

    @Override
    public void addAttribute(String key, double attribute){
        boolean inBinary = binaryAttributes.containsKey(key);
        boolean inText = textAttributes.containsKey(key);
        if(inBinary || inText){
            throw new GameEngineException("Attribute Addition: Two attributes can not have the same key.");
        }
        numericAttributes.put(key,attribute);
    }

    @Override
    public void addAttribute(String key, boolean attribute){
        boolean inNumeric = numericAttributes.containsKey(key);
        boolean inText = textAttributes.containsKey(key);
        if(inNumeric || inText){
            throw new GameEngineException("Attribute Addition: Two attributes can not have the same key.");
        }
        binaryAttributes.put(key,attribute);
    }

    @Override
    public void addAttribute(String key, String attribute){
        boolean inBinary = binaryAttributes.containsKey(key);
        boolean inNumeric = numericAttributes.containsKey(key);
        if(inBinary || inNumeric){
            throw new GameEngineException("Attribute Addition: Two attributes can not have the same key.");
        }
        textAttributes.put(key,attribute);
    }

    @Override
    public void replaceAttribute(String key,String attribute){
        boolean inBinary  = binaryAttributes.containsKey(key);
        boolean inNumeric = numericAttributes.containsKey(key);
        if(inBinary || inNumeric){
            throw new GameEngineException("Attribute Replacement: Type mismatch.");
        }
        textAttributes.put(key,attribute);
    }

    @Override
    public void replaceAttribute(String key,double attribute){
        boolean inBinary = binaryAttributes.containsKey(key);
        boolean inText   = textAttributes.containsKey(key);
        if(inBinary || inText){
            throw new GameEngineException("Attribute Replacement: Type mismatch.");
        }
        numericAttributes.put(key,attribute);
    }

    @Override
    public void replaceAttribute(String key,boolean attribute){
        boolean inNumeric = numericAttributes.containsKey(key);
        boolean inText   = textAttributes.containsKey(key);
        if(inNumeric || inText){
            throw new GameEngineException("Attribute Replacement: Type mismatch.");
        }

        binaryAttributes.put(key,attribute);
    }

    @Override
    public Object getAttribute(String key){
        if(seekAttribute(key)){
            if(numericAttributes.containsKey(key))return numericAttributes.get(key);
            if(binaryAttributes.containsKey(key))return binaryAttributes.get(key);
            if(textAttributes.containsKey(key))return textAttributes.get(key);
        }
        return null;
    }

    @Override
    public double getNumeric(String key){
        double val = 0d;
        if(numericAttributes.containsKey(key)){
             val = numericAttributes.get(key);
        }
        return val;
    }

    @Override
    public boolean getBinary(String key){
        boolean val = false;
        if(binaryAttributes.containsKey(key)){
            val = binaryAttributes.get(key);
        }
        return val;
    }

    @Override
    public String getText(String key){
        String val = "";
        if(textAttributes.containsKey(key)){
            val = textAttributes.get(key);
        }
        return val;
    }

    @Override
    public boolean seekAttribute(String key){
        boolean inDouble = numericAttributes.containsKey(key);
        boolean inBinary = binaryAttributes.containsKey(key);
        boolean inText = textAttributes.containsKey(key);
        return inBinary || inDouble || inText;
    }

    @Override
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

    @Override
    public void insert(AttributeHolder attributes) {
        for(String key : attributes.getNumericAttributes().keySet()){
            this.numericAttributes.put(key,attributes.getNumeric(key));
        }
        for(String key : attributes.getBinaryAttributes().keySet()){
            this.binaryAttributes.put(key,attributes.getBinary(key));
        }
        for(String key : attributes.getTextAttributes().keySet()){
            this.textAttributes.put(key,attributes.getText(key));
        }
    }

    @Override
    public void merge(AttributeHolder... attributeHolders) {
        AttributeHolder[] ah = attributeHolders;
        for(AttributeHolder attributeHolder: ah){
            insert(attributeHolder);
        }
    }

    @Override
    public Map<String, Double> getNumericAttributes() {
        return numericAttributes;
    }

    @Override
    public Map<String, Boolean> getBinaryAttributes() {
        return binaryAttributes;
    }

    @Override
    public Map<String, String> getTextAttributes() {
        return textAttributes;
    }
}
