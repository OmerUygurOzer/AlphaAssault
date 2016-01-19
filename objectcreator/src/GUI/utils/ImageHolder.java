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

    public ImageHolder(int x,int y,int width,int heigth){
        super();
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        //setLayout(null);
        setBounds(x,y,width,heigth);

        imagePanel = new ImagePane(width,heigth);
        add(imagePanel);

    }

    public void setImage(BufferedImage image){
        imagePanel.setImage(image);
    }

   public class ImagePane extends JPanel{
       private BufferedImage image;
       private int width;
       private int heigth;
       public ImagePane(int width,int heigth){
            this.image = null;
            this.width = width;
            this.heigth = heigth;
            setBackground(Color.RED);
       }

       public void setImage(BufferedImage image){
           this.image = image;
           setBounds(0,0,image.getWidth(),image.getHeight());
           repaint();
       }

       @Override
       public void paintComponent(Graphics g) {
           super.paintComponent(g);
           if(image!=null)
                g.drawImage(image,0,0,width,heigth,null);

       }


   }
}
