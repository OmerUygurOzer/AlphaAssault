package core;



import core.fileIO.FileIO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Omer on 1/7/2016.
 */
public class EditPanel extends JPanel {
    private static final int X = 824;
    private static final int Y = 0;

    private static final int WIDTH = 400;
    private static final int HEIGHT = 768;

    private static final String NAME = "Name:";
    private static final String DETAILS = "DETAILS";

    private JTabbedPane objectPane;

    public EditPanel(){
        setBounds(X,Y,WIDTH,HEIGHT);
        setLayout(null);
        objectPane = new JTabbedPane();
        objectPane.setBounds(0,0,400,HEIGHT);

        JComponent mapFeature = createTab();
        JComponent units = createTab();
        JComponent items = createTab();
        JComponent doodads = createTab();
        JComponent spawners = createTab();
        JComponent base = createTab();
        JComponent triggers = createTab();
        JComponent effects = createTab();



        objectPane.addTab("MF*",mapFeature);
        objectPane.addTab("Units",units);
        objectPane.addTab("Item",items);
        objectPane.addTab("Doodads",doodads);
        objectPane.addTab("Spawners",spawners);
        objectPane.addTab("Base",base);
        objectPane.addTab("Triggers",triggers);
        objectPane.addTab("Effects",effects);


        add(objectPane);

    }

    private static JComponent createTab(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,40,WIDTH,HEIGHT - 40);
        JLabel name = new JLabel(NAME);
        JLabel details = new JLabel(DETAILS);
        String[] test = {"TEST1" , "TEST2", "TEST3", "TEST4", "TEST5", "TEST6", "TEST7", "TEST8", "TEST9", "TEST10", "TEST11", "TEST12", "TEST13", "TEST14", "TEST15"};
        JList filesList = new JList(test);
        filesList.setBounds(0,148,WIDTH,200);
        panel.add(filesList);
        byte [] bytes = FileIO.readFromFile("C:\\Users\\Omer\\Desktop\\Game Projects\\AlphaAssault\\mapeditor\\mapfiles\\objects\\mapfeatures\\bush.enj");


        return panel;
    }


}
