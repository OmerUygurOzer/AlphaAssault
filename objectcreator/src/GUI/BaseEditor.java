package GUI;


import GUI.utils.Animator;
import GUI.utils.ImageUtils;
import fileIO.SerializableImage;
import objects.TileObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Stack;


/**
 * Created by Omer on 1/17/2016.
 */
public class BaseEditor extends EditorBase{

    private Animator animator;

    private int[] frameX;
    private int[] frameY;
    private int frameCount = 0;
    private Stack<JLabel> frameLabels = new Stack<JLabel>();
    private Stack<BufferedImage> frames = new Stack<BufferedImage>();

    private JButton addFrame           = new JButton("Add Frame");
    private JButton addFrameSheet      = new JButton("Add Frame Sheet");
    private JButton removeFrame        = new JButton("Remove Last Frame");


    private JComboBox<String> tileType     = new JComboBox<String>();
    private JComboBox<Integer> frameWidth  = new JComboBox<Integer>();
    private JComboBox<Integer> frameHeigth = new JComboBox<Integer>();

    private int line = 0;
    private int lineSize = 20;


    public BaseEditor(){
        super();
        setLayout(null);
        setBounds(0,0,WIDTH,HEIGHT);


        object = new TileObject();

        frameX = new int[6 * 6];
        frameY = new int[6 * 6];

        for(int i = 0;i< 6;i++){
            for(int j = 0;j< 6;j++){
                frameX[j + i * 6] = j * 100;
                frameY[j + i * 6] = 200 + i * 100;
            }
        }

        for(int i = 20; i < 400; i++){
            frameWidth.addItem(i);
            frameHeigth.addItem(i);
        }


        JLabel tileTypeLabel = new JLabel("Tile Type:");
        tileTypeLabel.setBounds((WIDTH/2) - 60,lineSize*line,120,lineSize);
        add(tileTypeLabel);
        tileType.setBounds((WIDTH/2) + 60 ,lineSize*line,120,lineSize);line++;
        tileType.addItem("2D Tile");
        tileType.addItem("Isometric Tile");
        add(tileType);


        addFrame.setBounds((WIDTH/2) - 90,lineSize * line,180,lineSize); line++;
        addFrame.addActionListener(this);
        add(addFrame);

        removeFrame.setBounds((WIDTH/2) - 90,lineSize * line,180,lineSize);line++;
        removeFrame.addActionListener(this);
        add(removeFrame);

        addFrameSheet.setBounds((WIDTH/2) - 270,lineSize * line,180,lineSize);
        addFrameSheet.addActionListener(this);
        add(addFrameSheet);

        frameWidth.setBounds((WIDTH/2) - 60,lineSize * line,60,lineSize);
        JLabel byLabel = new JLabel(" by ");
        byLabel.setBounds((WIDTH/2) - 0,lineSize * line,60,lineSize);
        frameHeigth.setBounds((WIDTH/2) + 60,lineSize * line,60,lineSize);
        add(frameWidth);
        add(byLabel);
        add(frameHeigth);

        line++;
        animator = new Animator(20,20,100,100);
        animator.setSPF(1/6f);
        add(animator);

        attributesPanel.setAttributeMap(object.getAttributes());

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
                try {
                    BufferedImage image = ImageIO.read(chooser.getSelectedFile());
                    addFrame(image);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
                return;
        }

        if(e.getSource().equals(addFrameSheet)){
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Image Files","png", "jpg");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this.getParent());
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage image = ImageIO.read(chooser.getSelectedFile());
                    addFrames(image,(Integer)frameWidth.getSelectedItem(),(Integer)frameHeigth.getSelectedItem());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
                return;
        }

        if(e.getSource().equals(removeFrame)){
                removeFrame();
                return;
        }


        if(e.getSource().equals(save)){
            ((TileObject)object).tileType = tileType.getSelectedIndex();
            String path = saveName.getText().replaceAll("[^a-zA-Z]+", ""); path = path.trim();
            if(!path.equals("")) {
                path = "C:\\Users\\Omer\\Desktop\\Game Projects\\AlphaAssault\\objectcreator\\files\\" + path;
                save(path);
                popDialog("Object Saved!");
            }else{
                popDialog("Type-in a file name!");
            }
            return;
        }







    }



    private void addFrame(BufferedImage image) {
        if (frameCount < 36){
            BufferedImage scaledImage = ImageUtils.resizeImage(image, 100, 100);
            ImageIcon icon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel();
            imageLabel.setBackground(Color.MAGENTA);
            imageLabel.setBounds(frameX[frameCount], frameY[frameCount], 100, 100);
            imageLabel.setIcon(icon);
            add(imageLabel);
            animator.addFrame(scaledImage);
            frames.push(scaledImage);
            frameLabels.push(imageLabel);
            ((TileObject)object).frames.add(new SerializableImage(scaledImage));
            frameCount++;
            repaint();
        }
    }

    private void addFrames(BufferedImage image,int width,int height){
        BufferedImage[] frames = ImageUtils.generateFrames(image,width,height);
        for(int i = 0;i<frames.length;i++){
            addFrame(frames[i]);
        }
    }

    private void removeFrame(){
        if(frameLabels.size()==0)return;
        frames.pop();
        animator.removeFrame(animator.getFrames().size()-1);
        remove(frameLabels.pop());
        frameCount--;
        repaint();
    }




}
