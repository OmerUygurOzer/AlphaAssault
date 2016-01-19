package GUI;

import GUI.utils.ImageHolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Omer on 1/17/2016.
 */
public class BaseEditor extends JPanel implements ActionListener{
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;


    private JButton addFrame = new JButton("Add Frame");
    private JComboBox<String> tileType = new JComboBox<String>();

    private int line = 0;
    private int lineSize = 20;

    public BaseEditor(){

        setLayout(null);
        setBounds(800,0,WIDTH,HEIGHT);


        JLabel tileTypeLabel = new JLabel("Tile Type:");
        tileTypeLabel.setBounds((WIDTH/2) - 60,lineSize*line,120,lineSize);line++;
        add(tileTypeLabel);
        tileType.setBounds((WIDTH/2) - 60,lineSize*line,120,lineSize);line++;
        tileType.addItem("2D Tile");
        tileType.addItem("Isometric Tile");
        add(tileType);
        addFrame.setBounds((WIDTH/2) - 60,lineSize * line,120,lineSize); line++;
        addFrame.addActionListener(this);
        add(addFrame);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(addFrame)){
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Image Files","png", "jpg");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this.getParent());
            if(returnVal == JFileChooser.APPROVE_OPTION) {
              //DO STUFF
            }
        }
    }
}
