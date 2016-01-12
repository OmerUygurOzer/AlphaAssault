package core.enjineutils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 1/12/2016.
 */
public class BrushSelector extends JScrollPane {
    private int x;
    private int y;

    private BufferedImage texture;
    private BufferedImage[] regions;

    private List<JButton> buttons = new ArrayList<JButton>();

    public BrushSelector(){
        super();
        setLayout(null);
    }

    public void setTexture(int _x,int _y,byte[] textureBytes) {
        x = _x;
        y = _y;
        regions = new BufferedImage[x * y];



        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(textureBytes);
        try {
            texture = ImageIO.read(byteArrayInputStream);
            int regionWidth = Math.round(texture.getWidth() / x);
            int regionHeight = Math.round(texture.getHeight() / y);

            for(int i = 0 ; i < y ; i++){
                for(int j = 0 ; j < x ; j++){
                    regions[j + i * x ] = texture.getSubimage(j * regionWidth,i * regionHeight , regionWidth , regionHeight);
                    JButton brush = new JButton();
                    brush.setBounds(j * regionWidth,i * regionHeight , regionWidth , regionHeight);
                    brush.setIcon(new ImageIcon(regions[j + i * x ]));
                    brush.setVisible(true);
                    add(brush);
                    buttons.add(brush);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearBrushes(){
        for(JButton button : buttons){
            remove(button);
        }
        buttons.clear();
        repaint();
    }



}
