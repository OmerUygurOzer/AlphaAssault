package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Omer on 2/5/2016.
 */
public class NativeImageUtils {
    public static BufferedImage packFrames(BufferedImage... frames){
        int width  = frames[0].getWidth();
        int height = frames[0].getHeight();
        for(BufferedImage image: frames){
            if(image.getWidth()!=width || image.getHeight()!=height){throw new IllegalArgumentException("Frames are of different size");}
        }
        int len = Math.round(frames.length / 8) + 1;
        int packedHeight = len*height;
        int packedWidth = frames.length > 8 ? width*8 : width*frames.length;
        BufferedImage image = new BufferedImage(packedWidth,packedHeight,BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = image.createGraphics();
        int line = 0;
        int col = 0;
        for(int i = 0; i< frames.length; i++){
            graphics2D.drawImage(frames[i],col*width,line*height,width,height,null,null);
            line = i%7==0 &&i>0 ? line+1 : line;
            col = i%7==0 ? 0:col+1;
        }
        graphics2D.dispose();
        return image;
    }
}
