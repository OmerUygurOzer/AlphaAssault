package GUI.utils;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Created by Omer on 1/19/2016.
 */
public class ImageUtils {
    public static BufferedImage resizeImage(BufferedImage image,int width,int height){
        Image scaledInstance = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage imageBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = imageBuffer.createGraphics();
        g2d.drawImage(scaledInstance, 0, 0, null);
        g2d.dispose();
        return imageBuffer;
    }

    public static BufferedImage[] generateFrames(BufferedImage image,int width,int height){
        int framesX = Math.round(image.getWidth() / width);
        int framesY = Math.round(image.getHeight() / height);
        int totalFrames = framesX * framesY;
        BufferedImage[] frames = new BufferedImage[totalFrames];
        for(int i = 0; i <framesY ; i++){
            for(int j = 0; j <framesX ; j++){
                frames[j + i * framesX] = image.getSubimage(j*width,i*height,width,height);
            }
        }

        return frames;
    }
}
