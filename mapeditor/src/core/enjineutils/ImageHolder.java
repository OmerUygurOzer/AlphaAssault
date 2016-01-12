package core.enjineutils;

import javax.imageio.ImageIO;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Omer on 1/11/2016.
 */
public class ImageHolder extends JScrollPane {

    private BufferedImage bufferedImage = null;


    public ImageHolder(){
        super();
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


    }



    public void setImage(byte [] _image){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(_image);

        try {
            bufferedImage = ImageIO.read(byteArrayInputStream);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(bufferedImage!=null){
            g.drawImage(bufferedImage,0,0,this);

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
