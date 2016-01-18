package GUI.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Omer on 1/17/2016.
 */
public class ImageHolder extends JScrollPane {
    private BufferedImage image;
    private ImagePane imagePanel;


    private int x;
    private int y;
    private int width;
    private int heigth;

    public ImageHolder(BufferedImage image,int x,int y,int width,int heigth){
        super();
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        setLayout(null);
        setBounds(x,y,width,heigth);

        imagePanel = new ImagePane(image);
        add(imagePanel);

    }

   public class ImagePane extends JPanel{
       private BufferedImage image;
       public ImagePane(BufferedImage image){
            this.image = image;
            setBounds(0,0,image.getWidth(),image.getHeight());
       }

       @Override
       public void paint(Graphics g) {
           super.paintComponent(g);
           g.drawImage(image,0,0,null);
       }
   }
}
