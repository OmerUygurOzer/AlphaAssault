package level.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 1/19/2016.
 */
public class TileObject extends ObjectBase implements Serializable {
    public int tileType;
    public List<BufferedImage> frames = new ArrayList<BufferedImage>();
}
