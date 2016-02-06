package GUI;


import GUI.utils.Animator;
import GUI.utils.ImageUtils;
import utilities.ObjectIO;
import graphics.SerializableImage;
import ingame.objects.RawObject;

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


    private JButton addFrame           = new JButton("Add Frame");
    private JButton addFrameSheet      = new JButton("Add Frame Sheet");
    private JButton removeFrame        = new JButton("Remove Last Frame");

    private JComboBox<Integer> frameWidth  = new JComboBox<Integer>();
    private JComboBox<Integer> frameHeigth = new JComboBox<Integer>();

    private int line = 0;
    private int lineSize = 20;


    public BaseEditor(){
        super();
        setLayout(null);
        setBounds(0,0,WIDTH,HEIGHT);

        object = new RawObject();

        for(int i = 20; i < 400; i++){
            frameWidth.addItem(i);
            frameHeigth.addItem(i);
        }




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

        animator = new Animator(100,100,300,300);
        animator.setSPF(1/6f);
        add(animator);

        attributesPanel.setAttributeMap(object.attributes);

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

        if(e.getSource().equals(newObject)){
            object.frames.clear();
            animator.clear();
            attributesPanel.clearAttributes();
            return;
        }


        if(e.getSource().equals(save)){
            String path = "";
            String name = "";
            JFileChooser saver = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "EnJine2D Files","enjo");
            saver.setFileFilter(filter);
            int sVal = saver.showSaveDialog(this);
            if (sVal == JFileChooser.APPROVE_OPTION) {
                path = saver.getCurrentDirectory().toString();
                if(!path.endsWith("enjo"))name = saver.getSelectedFile().getName();
                ObjectIO.writeObject(path+"\\"+name,object);
                return;
            }
            if (sVal == JFileChooser.CANCEL_OPTION) {
                return;
            }
        }

        if(e.getSource().equals(load)){
            String path;
            String name;
            JFileChooser loader = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "EnJine2D Files","enjo");
            loader.setFileFilter(filter);
            int lVal = loader.showOpenDialog(this);
            if(lVal ==JFileChooser.APPROVE_OPTION){
                path = loader.getCurrentDirectory().toString();
                name = loader.getSelectedFile().getName();
                RawObject object = ObjectIO.readObject(path+"\\"+name);
                loadObject(object);
                return;
            }
            if(lVal == JFileChooser.CANCEL_OPTION){
                return;
            }

        }


    }



    private void addFrame(BufferedImage image) {
        BufferedImage scaledImage = ImageUtils.resizeImage(image, 100, 100);
        animator.addFrame(scaledImage);
        object.frames.add(new SerializableImage(scaledImage));
    }

    private void addFrames(BufferedImage image,int width,int height){
        BufferedImage[] frames = ImageUtils.generateFrames(image,width,height);
        for(int i = 0;i<frames.length;i++){
            addFrame(frames[i]);
        }
    }

    private void removeFrame(){
        animator.removeFrame(animator.getFrames().size()-1);
        object.frames.remove(object.frames.size()-1);
        repaint();
    }

    private void loadObject(RawObject rawObject){
        for(int i = 0 ; i < animator.getFrames().size(); i++){
            animator.removeFrame(i);
        }
        attributesPanel.clearAttributes();
        for(int i = 0; i < rawObject.frames.size(); i++){
            addFrame(rawObject.frames.get(i).image);
        }
        rawObject.attributes.clear();
        for(String key: rawObject.attributes.keySet()){
            attributesPanel.addAttribute(key, rawObject.attributes.get(key));
        }


    }




}
