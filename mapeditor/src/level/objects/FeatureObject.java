package level.objects;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 1/28/2016.
 */
public class FeatureObject extends ObjectBase implements Serializable {
    public Map<String,SerializableImage> frames = new HashMap<String, SerializableImage>();
}
