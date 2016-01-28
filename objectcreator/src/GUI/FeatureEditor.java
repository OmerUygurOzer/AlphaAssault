package GUI;

import GUI.utils.Animator;
import GUI.utils.ImageUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JLabel crossing_N  = new JLabel("N"); private JButton addFrame_N     = new JButton("Add Frame");
    private JLabel crossing_NE = new JLabel("NE");private JButton addFrame_NE    = new JButton("Add Frame");
    private JLabel crossing_E  = new JLabel("E");private JButton addFrame_E      = new JButton("Add Frame");
    private JLabel crossing_SE = new JLabel("SE");private JButton addFrame_SE    = new JButton("Add Frame");
    private JLabel crossing_S  = new JLabel("S");private JButton addFrame_S      = new JButton("Add Frame");
    private JLabel crossing_SW = new JLabel("SW");private JButton addFrame_SW    = new JButton("Add Frame");
    private JLabel crossing_W  = new JLabel("W");private JButton addFrame_W      = new JButton("Add Frame");
    private JLabel crossing_NW = new JLabel("NW");private JButton addFrame_NW    = new JButton("Add Frame");
    private JLabel crossing_C  = new JLabel("Center");private JButton addFrame_C = new JButton("Add Frame");

    private JLabel image_N = new JLabel();
    private JLabel image_NE = new JLabel();
    private JLabel image_E = new JLabel();
    private JLabel image_SE = new JLabel();
    private JLabel image_S = new JLabel();
    private JLabel image_SW = new JLabel();
    private JLabel image_W = new JLabel();
    private JLabel image_NW = new JLabel();
    private JLabel image_C = new JLabel();





    public FeatureEditor(){
        setLayout(null);
        setBounds(0,0,WIDTH,HEIGHT);

        crossing_NW.setBounds(0,Y_OFFSET-LABEL_HEIGHT,LABEL_WIDTH,LABEL_HEIGHT);
        crossing_N.setBounds(200+PADDING,Y_OFFSET-LABEL_HEIGHT,LABEL_WIDTH,LABEL_HEIGHT);
        crossing_NE.setBounds(400+PADDING*2,Y_OFFSET-LABEL_HEIGHT,LABEL_WIDTH,LABEL_HEIGHT);
        crossing_W.setBounds(0,Y_OFFSET+200+PADDING-LABEL_HEIGHT,LABEL_WIDTH,LABEL_HEIGHT);
        crossing_C.setBounds(200+PADDING,Y_OFFSET+200+PADDING-LABEL_HEIGHT,LABEL_WIDTH,LABEL_HEIGHT);
        crossing_E.setBounds(400+PADDING*2,Y_OFFSET+200+PADDING-LABEL_HEIGHT,LABEL_WIDTH,LABEL_HEIGHT);
        crossing_SW.setBounds(0,Y_OFFSET+400-LABEL_HEIGHT+PADDING*2,LABEL_WIDTH,LABEL_HEIGHT);
        crossing_S.setBounds(200+PADDING,Y_OFFSET+400-LABEL_HEIGHT+PADDING*2,LABEL_WIDTH,LABEL_HEIGHT);
        crossing_SE.setBounds(400+PADDING*2,Y_OFFSET+400-LABEL_HEIGHT+PADDING*2,LABEL_WIDTH,LABEL_HEIGHT);

        addFrame_NW.setBounds(crossing_NW.getX()+LABEL_WIDTH,Y_OFFSET-LABEL_HEIGHT,BUTTON_WIDTH,LABEL_HEIGHT);
        addFrame_N.setBounds(crossing_N.getX()+LABEL_WIDTH,Y_OFFSET-LABEL_HEIGHT,BUTTON_WIDTH,LABEL_HEIGHT);
        addFrame_NE.setBounds(crossing_NE.getX()+LABEL_WIDTH,Y_OFFSET-LABEL_HEIGHT,BUTTON_WIDTH,LABEL_HEIGHT);
        addFrame_W.setBounds(crossing_W.getX()+LABEL_WIDTH,Y_OFFSET+200+PADDING-LABEL_HEIGHT,BUTTON_WIDTH,LABEL_HEIGHT);
        addFrame_C.setBounds(crossing_C.getX()+LABEL_WIDTH,Y_OFFSET+200+PADDING-LABEL_HEIGHT,BUTTON_WIDTH,LABEL_HEIGHT);
        addFrame_E.setBounds(crossing_E.getX()+LABEL_WIDTH,Y_OFFSET+200+PADDING-LABEL_HEIGHT,BUTTON_WIDTH,LABEL_HEIGHT);
        addFrame_SW.setBounds(crossing_SW.getX()+LABEL_WIDTH,Y_OFFSET+400-LABEL_HEIGHT+PADDING*2,BUTTON_WIDTH,LABEL_HEIGHT);
        addFrame_S.setBounds(crossing_S.getX()+LABEL_WIDTH,Y_OFFSET+400-LABEL_HEIGHT+PADDING*2,BUTTON_WIDTH,LABEL_HEIGHT);
        addFrame_SE.setBounds(crossing_SE.getX()+LABEL_WIDTH,Y_OFFSET+400-LABEL_HEIGHT+PADDING*2,BUTTON_WIDTH,LABEL_HEIGHT);

        image_NW.setBounds(crossing_NW.getX(),crossing_NW.getY()+LABEL_HEIGHT,200,200);
        image_N.setBounds(crossing_N.getX(),crossing_N.getY()+LABEL_HEIGHT,200,200);
        image_NE.setBounds(crossing_NE.getX(),crossing_NE.getY()+LABEL_HEIGHT,200,200);
        image_W.setBounds(crossing_W.getX(),crossing_W.getY()+LABEL_HEIGHT,200,200);
        image_C.setBounds(crossing_C.getX(),crossing_C.getY()+LABEL_HEIGHT,200,200);
        image_E.setBounds(crossing_E.getX(),crossing_E.getY()+LABEL_HEIGHT,200,200);
        image_SW.setBounds(crossing_SW.getX(),crossing_SW.getY()+LABEL_HEIGHT,200,200);
        image_S.setBounds(crossing_S.getX(),crossing_S.getY()+LABEL_HEIGHT,200,200);
        image_SE.setBounds(crossing_SE.getX(),crossing_SE.getY()+LABEL_HEIGHT,200,200);

        add(image_NW);
        add(image_N);
        add(image_NE);
        add(image_W);
        add(image_C);
        add(image_E);
        add(image_SW);
        add(image_S);
        add(image_SE);

        add(crossing_NW);
        add(crossing_N);
        add(crossing_NE);
        add(crossing_W);
        add(crossing_C);
        add(crossing_E);
        add(crossing_SW);
        add(crossing_S);
        add(crossing_SE);

        addFrame_NW.addActionListener(this);
        addFrame_N.addActionListener(this);
        addFrame_NE.addActionListener(this);
        addFrame_E.addActionListener(this);
        addFrame_C.addActionListener(this);
        addFrame_W.addActionListener(this);
        addFrame_SW.addActionListener(this);
        addFrame_S.addActionListener(this);
        addFrame_SE.addActionListener(this);

        addFrameSheet.setBounds(0,40,160,20);
        addFrameSheet.addActionListener(this);

        add(addFrameSheet);
        add(addFrame_NW);
        add(addFrame_N);
        add(addFrame_NE);
        add(addFrame_W);
        add(addFrame_C);
        add(addFrame_E);
        add(addFrame_SW);
        add(addFrame_S);
        add(addFrame_SE);



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
    }

    private void createCrossings(BufferedImage readImage){
        BufferedImage[] frames= ImageUtils.generateFrames(readImage,readImage.getWidth()/3,readImage.getHeight()/3);
        image_NW.setIcon(new ImageIcon(frames[0]));
        image_N.setIcon(new ImageIcon(frames[1]));
        image_NE.setIcon(new ImageIcon(frames[2]));
        image_W.setIcon(new ImageIcon(frames[3]));
        image_C.setIcon(new ImageIcon(frames[4]));
        image_E.setIcon(new ImageIcon(frames[5]));
        image_SW.setIcon(new ImageIcon(frames[6]));
        image_S.setIcon(new ImageIcon(frames[7]));
        image_SE.setIcon(new ImageIcon(frames[8]));
        repaint();
    }
}
