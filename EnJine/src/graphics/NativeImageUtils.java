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

    public static BufferedImage resizeImage(BufferedImage image,int width,int height){
        Image scaledInstance = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage imageBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = imageBuffer.createGraphics();
        g2d.drawImage(scaledInstance, 0, 0, null);
        g2d.dispose();
        return imageBuffer;
    }

    public static BufferedImage[] generateFrames(BufferedImage image,int width,int height) {
        int framesX = image.getWidth() % width > 0 ? Math.round(image.getWidth() / width) + 1 : Math.round(image.getWidth() / width);
        int framesY = image.getHeight() % height > 0 ? Math.round(image.getHeight() / height) + 1 : Math.round(image.getHeight() / height);
        BufferedImage roundedImage = new BufferedImage(framesX * width, framesY * height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = roundedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null, null);
        graphics2D.dispose();
        System.out.println(framesX + "  " + framesY);
        int totalFrames = framesX * framesY;
        BufferedImage[] frames = new BufferedImage[totalFrames];
        for (int i = 0; i < framesY; i++) {
            for (int j = 0; j < framesX; j++) {
                frames[j + i * framesX] = roundedImage.getSubimage(j * width, i * height, width, height);
            }
        }

        return frames;
    }
}
