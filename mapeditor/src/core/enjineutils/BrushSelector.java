package core.enjineutils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 1/12/2016.
 */
public class BrushSelector extends JPanel {
    private int x;
    private int y;

    private BufferedImage texture;
    private BufferedImage[] regions;


    private DefaultListModel<String> varieties = new DefaultListModel<String>();
    private JList varietyList;
    private final JButton brush = new JButton();

    private JScrollPane varietyPane;

    public BrushSelector(){
        super();
        setLayout(null);



        varietyList = new JList(varieties);
        varietyPane = new JScrollPane();
        varietyPane.setBounds(100,0,290,375);
        varietyPane.setViewportView(varietyList);

        brush.setBounds(0,0,100,100);

        brush.setVisible(true);
        varietyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        varietyList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    JList listPointer = (JList) e.getSource();
                    int selection = listPointer.getSelectedIndex();
                    if(selection!=-1)
                        brush.setIcon(new ImageIcon(regions[selection]));
                }
            }
        });


        add(brush);
        add(varietyPane);
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
                    int linear = j + i * x;
                    varieties.addElement("variety : "+ linear);
                    regions[linear] = texture.getSubimage(j * regionWidth,i * regionHeight , regionWidth , regionHeight);


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearBrushes(){
        varietyList.clearSelection();
        varieties.clear();
        regions = null;


    }



}
