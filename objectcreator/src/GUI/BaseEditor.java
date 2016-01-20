package GUI;


import GUI.utils.Animator;
import GUI.utils.ImageUtils;
import objects.BaseTile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * Created by Omer on 1/17/2016.
 */
public class BaseEditor extends JPanel implements ActionListener{
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private Animator animator;

    private int[] frameX;
    private int[] frameY;
    private int frameCount = 0;
    private Stack<JLabel> frameLabels = new Stack<JLabel>();
    private Stack<BufferedImage> frames = new Stack<BufferedImage>();

    private JButton addFrame           = new JButton("Add Frame");
    private JButton addFrameSheet      = new JButton("Add Frame Sheet");
    private JButton removeFrame        = new JButton("Remove Last Frame");
    private JButton addCrossing        = new JButton("Add");
    private JButton removeCrossing     = new JButton("Remove");
    private JButton removeAllCrossings = new JButton("Remove All");
    private JButton save               = new JButton("Save");

    private List<String> crossingsAll  = new ArrayList<String>();
    private DefaultListModel<String> crossingListModel = new DefaultListModel<String>();
    private JList<String> crossingsAllUI = new JList<String>(crossingListModel);

    private JComboBox<String> tileType     = new JComboBox<String>();
    private JComboBox<String> crossings    = new JComboBox<String>();
    private JComboBox<Integer> frameWidth  = new JComboBox<Integer>();
    private JComboBox<Integer> frameHeigth = new JComboBox<Integer>();

    private JTextField saveName = new JTextField();




    private int line = 0;
    private int lineSize = 20;

    private BaseTile baseTile;

    public BaseEditor(){
        setLayout(null);
        setBounds(800,0,WIDTH,HEIGHT);

        baseTile = new BaseTile();

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

        JLabel crossingsLabel = new JLabel("Crossings:");
        crossingsLabel.setBounds((WIDTH/2) - 60,lineSize*line,120,lineSize);
        add(crossingsLabel);
        crossings.setBounds((WIDTH/2) + 60 ,lineSize*line,120,lineSize);
        crossings.addItem("N");
        crossings.addItem("NE");
        crossings.addItem("E");
        crossings.addItem("SE");
        crossings.addItem("S");
        crossings.addItem("SW");
        crossings.addItem("W");
        crossings.addItem("NW");

        addCrossing.setBounds((WIDTH/2) + 180 ,lineSize*line,120,lineSize); addCrossing.addActionListener(this);
        removeCrossing.setBounds((WIDTH/2) + 300 ,lineSize*line,120,lineSize);removeCrossing.addActionListener(this);
        removeAllCrossings.setBounds((WIDTH/2) + 300 ,lineSize*(line+1),120,lineSize);removeAllCrossings.addActionListener(this);

        JScrollPane listHolder = new JScrollPane();
        listHolder.setBounds((WIDTH/2) + 420 ,lineSize*line,40,lineSize*crossings.getItemCount());
        listHolder.setViewportView(crossingsAllUI);

        add(crossings);
        add(addCrossing);
        add(removeCrossing);
        add(removeAllCrossings);
        add(listHolder);
        line++;

        addFrame.setBounds((WIDTH/2) - 60,lineSize * line,120,lineSize); line++;
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
        animator = new Animator(800,400,100,100);
        animator.setSPF(1/5f);
        add(animator);

        saveName.setBounds(WIDTH - 100,HEIGHT - 120,80,20);
        add(saveName);

        save.setBounds(WIDTH - 100,HEIGHT - 100,80,40);
        save.addActionListener(this);
        add(save);


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

        if(e.getSource().equals(addCrossing)){
            addCrossing();
            return;
        }

        if(e.getSource().equals(removeCrossing)){
            removeCrossing();
            return;
        }

        if(e.getSource().equals(removeAllCrossings)){
            removeAllCrossings();
            return;
        }

        if(e.getSource().equals(save)){
            String path = saveName.getText().replaceAll("[^a-zA-Z]+", ""); path = path.trim();
            path = "C:\\Users\\Omer\\Desktop\\Game Projects\\AlphaAssault\\objectcreator\\files\\" + path;
            save(path);
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
            frameCount++;
            baseTile.frames.add(scaledImage);
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
        animator.removeFrame(frames.pop());
        remove(frameLabels.pop());
        frameCount--;
        repaint();
    }

    private void addCrossing(){
        if(crossingsAll.contains((String)crossings.getSelectedItem())){
            return;
        }
        crossingsAll.add((String)crossings.getSelectedItem());
        crossingListModel.addElement((String)crossings.getSelectedItem());
        baseTile.crossings.add(crossings.getSelectedIndex());
        return;
    }

    private void removeCrossing(){
        if(!crossingsAll.contains(crossings.getSelectedItem())){
            return;
        }
        crossingsAll.remove(crossings.getSelectedItem());
        crossingListModel.removeElement(crossings.getSelectedItem());
        baseTile.crossings.remove(crossings.getSelectedIndex());
        return;
    }

    private void removeAllCrossings(){
        crossingsAll.clear();
        crossingListModel.clear();
        baseTile.crossings.clear();
        return;
    }

    private void save(String path){
        baseTile.toFile(path);
    }
}
