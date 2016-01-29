package GUI;

import GUI.utils.ImageUtils;
import objects.Feature;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Omer on 1/20/2016.
 */
public class FeatureEditor extends EditorBase {

    private static final int PADDING = 10;
    private static final int Y_OFFSET = 100;

    private static final int LABEL_WIDTH = 40;
    private static final int LABEL_HEIGHT = 20;

    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGTH = 20;

    private JButton addFrameSheet = new JButton("Add Frame Sheet");

    private JLabel crossing_N  = new JLabel("N");
    private JLabel crossing_E  = new JLabel("E");
    private JLabel crossing_S  = new JLabel("S");
    private JLabel crossing_W  = new JLabel("W");
    private JLabel crossing_C  = new JLabel("Center");

    private JLabel image_N = new JLabel();
    private JLabel image_E = new JLabel();
    private JLabel image_S = new JLabel();
    private JLabel image_W = new JLabel();
    private JLabel image_C = new JLabel();

    private JButton clear = new JButton("CLEAR");

    public FeatureEditor(){
        super();
        setLayout(null);
        setBounds(0,0,WIDTH,HEIGHT);

        object = new Feature();

        clear.setBounds(20,20,BUTTON_WIDTH,BUTTON_HEIGTH);
        clear.addActionListener(this);


        crossing_N.setBounds(200+PADDING,Y_OFFSET-LABEL_HEIGHT,LABEL_WIDTH,LABEL_HEIGHT);
        crossing_W.setBounds(0,Y_OFFSET+200+PADDING-LABEL_HEIGHT,LABEL_WIDTH,LABEL_HEIGHT);
        crossing_C.setBounds(200+PADDING,Y_OFFSET+200+PADDING-LABEL_HEIGHT,LABEL_WIDTH,LABEL_HEIGHT);
        crossing_E.setBounds(400+PADDING*2,Y_OFFSET+200+PADDING-LABEL_HEIGHT,LABEL_WIDTH,LABEL_HEIGHT);
        crossing_S.setBounds(200+PADDING,Y_OFFSET+400-LABEL_HEIGHT+PADDING*2,LABEL_WIDTH,LABEL_HEIGHT);



        image_N.setBounds(crossing_N.getX(),crossing_N.getY()+LABEL_HEIGHT,200,200);
        image_W.setBounds(crossing_W.getX(),crossing_W.getY()+LABEL_HEIGHT,200,200);
        image_C.setBounds(crossing_C.getX(),crossing_C.getY()+LABEL_HEIGHT,200,200);
        image_E.setBounds(crossing_E.getX(),crossing_E.getY()+LABEL_HEIGHT,200,200);
        image_S.setBounds(crossing_S.getX(),crossing_S.getY()+LABEL_HEIGHT,200,200);



        add(crossing_N);
        add(crossing_W);
        add(crossing_C);
        add(crossing_E);
        add(crossing_S);


        addFrameSheet.setBounds(0,40,160,20);
        addFrameSheet.addActionListener(this);

        add(addFrameSheet);
        add(clear);




    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(addFrameSheet)){
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Image Files","png", "jpg");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this.getParent());
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage image = ImageIO.read(chooser.getSelectedFile());
                    createCrossings(image);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return;
        }

        if (e.getSource().equals(clear)){
            remove(image_N);
            remove(image_W);
            remove(image_C);
            remove(image_E);
            remove(image_S);

            ((Feature)object).frames.clear();
            repaint();

        }
    }

    private void createCrossings(BufferedImage readImage){
        BufferedImage[] frames= ImageUtils.generateFrames(readImage,readImage.getWidth()/3,readImage.getHeight()/3);

        image_N.setIcon(new ImageIcon(frames[1]));
        image_W.setIcon(new ImageIcon(frames[3]));
        image_C.setIcon(new ImageIcon(frames[4]));
        image_E.setIcon(new ImageIcon(frames[5]));
        image_S.setIcon(new ImageIcon(frames[7]));

        ((Feature)object).frames.add(frames[1]);
        ((Feature)object).frames.add(frames[3]);
        ((Feature)object).frames.add(frames[4]);
        ((Feature)object).frames.add(frames[5]);
        ((Feature)object).frames.add(frames[7]);

        add(image_N);
        add(image_W);
        add(image_C);
        add(image_E);
        add(image_S);

        repaint();
    }
}
