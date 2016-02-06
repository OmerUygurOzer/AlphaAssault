package GUI;

import utilities.ObjectIO;
import ingame.objects.RawObject;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Omer on 1/27/2016.
 */
public abstract class EditorBase extends JPanel implements ActionListener {
    protected static final int WIDTH = 1200;
    protected static final int HEIGHT = 800;

    protected JButton newObject= new JButton("New");
    protected JButton save     = new JButton("Save");
    protected JButton load     = new JButton("Load");

    protected RawObject object;

    protected AttributesPanel attributesPanel;

    protected EditorBase(){
        setLayout(null);
        setBounds(0,0,WIDTH,HEIGHT);

        newObject.setBounds(WIDTH-100,HEIGHT-180,80,40);
        newObject.addActionListener(this);
        add(newObject);

        load.setBounds(WIDTH-100,HEIGHT-140,80,40);
        load.addActionListener(this);
        add(load);

        save.setBounds(WIDTH - 100,HEIGHT - 100,80,40);
        save.addActionListener(this);
        add(save);

        attributesPanel = new AttributesPanel(800,0,400,600);
        add(attributesPanel);


        repaint();

    }

    public void setObject(RawObject object){
        this.object = object;
    }

    protected void save(String path){
        ObjectIO.writeObject(path,object);}

    protected void popDialog(String message){JOptionPane.showMessageDialog(this, message);}



}
