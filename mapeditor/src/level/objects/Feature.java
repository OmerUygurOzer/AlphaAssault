package level.objects;

import com.badlogic.gdx.math.Vector2;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 1/30/2016.
 */
public class Feature extends ObjectBase{
    public Map<String,BufferedImage> frames = new HashMap<String, BufferedImage>();

    public Vector2 position;

    public Feature(){
        position = new Vector2();
    }

    public void setPosition(Vector2 position){this.position = position;}

    public void setPosition(float x, float y){this.setPosition(new Vector2(x,y));}

    public static Feature readFeature(String path){
        File file = new File(path);
        FeatureObject featureObject = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            featureObject = (FeatureObject)object;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Feature feature = new Feature();
        for(String key:featureObject.frames.keySet()){
            feature.frames.put(key,featureObject.frames.get(key).image);
        }
        feature.attributes = featureObject.attributes;
        return feature;
    }

}
