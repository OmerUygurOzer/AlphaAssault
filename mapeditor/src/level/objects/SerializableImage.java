package level.objects;

import IOUtils.ByteIO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.Hashtable;

/**
 * Created by Omer on 1/30/2016.
 */
public class SerializableImage implements Serializable {

    public transient BufferedImage image;

    public SerializableImage(BufferedImage image){
        this.image = image;
    }

    public SerializableImage(int width, int height, int imageType) {
        this.image = new BufferedImage(width, height, imageType);

    }

    public SerializableImage(int width, int height, int imageType, IndexColorModel cm) {
        this.image = new BufferedImage(width, height, imageType, cm);

    }

    public SerializableImage(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied, Hashtable<?, ?> properties) {
        this.image = new BufferedImage(cm, raster, isRasterPremultiplied, properties);

    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(image,"png",out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        image = ImageIO.read(in);
    }

}
