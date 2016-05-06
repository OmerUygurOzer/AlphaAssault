package ingame.logic;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Omer on 3/11/2016.
 */
public interface AttributeHolder extends Serializable {

    void addAttribute(String key, double attribute);

    void addAttribute(String key, boolean attribute);

    void addAttribute(String key, String attribute);

    void replaceAttribute(String key,String attribute);

    void replaceAttribute(String key,double attribute);

    void replaceAttribute(String key,boolean attribute);

    Object getAttribute(String key);

    double getNumeric(String key);

    boolean getBinary(String key);

    String getText(String key);

    boolean seekAttribute(String key);

    Attributes copy();

    void insert(AttributeHolder attributes);

    void merge(AttributeHolder... attributeHolders);

    Map<String,Double> getNumericAttributes();

    Map<String,Boolean> getBinaryAttributes();

    Map<String,String> getTextAttributes();
}
