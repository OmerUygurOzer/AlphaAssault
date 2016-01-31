package GUI;

import fileIO.ObjectIO;
import objects.ObjectBase;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Omer on 1/27/2016.
 */
public abstract class EditorBase extends JPanel implements ActionListener {
    protected static final int WIDTH = 1200;
    protected static final int HEIGHT = 800;

    protected JButton save        = new JButton("Save");
    protected JTextField saveName   = new JTextField();

    protected ObjectBase object;

    protected AttributesPanel attributesPanel;

    protected EditorBase(){
        setLayout(null);
        setBounds(0,0,WIDTH,HEIGHT);

        saveName.setBounds(WIDTH - 100,HEIGHT - 120,80,20);
        add(saveName);

        save.setBounds(WIDTH - 100,HEIGHT - 100,80,40);
        save.addActionListener(this);
        add(save);

        attributesPanel = new AttributesPanel(800,0,400,800);
        add(attributesPanel);


        repaint();

    }

    public void setObject(ObjectBase object){
        this.object = object;
    }

    protected void save(String path){ObjectIO.writeObject(path,object);}

    protected void popDialog(String message){JOptionPane.showMessageDialog(this, message);}



}
