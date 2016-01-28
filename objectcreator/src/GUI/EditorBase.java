package GUI;

import objects.ObjectBase;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Omer on 1/27/2016.
 */
public abstract class EditorBase extends JPanel implements ActionListener {
    protected static final int WIDTH = 1200;
    protected static final int HEIGHT = 800;

    private JButton save          = new JButton("Save");
    private JTextField saveName   = new JTextField();

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

    }

    public void setObject(ObjectBase object){
        this.object = object;
    }



}
