package objects;

import fileIO.SerializableImage;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 1/27/2016.
 */
public class ObjectBase implements Serializable {
    public List<SerializableImage> frames = new ArrayList<SerializableImage>();
    public Map<String,Object> attributes = new HashMap<String, Object>();
}
