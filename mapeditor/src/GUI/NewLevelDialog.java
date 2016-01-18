package GUI;

import level.Level;
import level.LevelType;

import javax.swing.*;

/**
 * Created by Omer on 1/17/2016.
 */
public class NewLevelDialog extends JPanel {
    private Level newLevel;

    private static final int X = 300;
    private static final int Y = 300;

    private static final int WIDTH = 300;
    private static final int HEIGTH = 300;

    private String levelName;
    private LevelType levelType;
    private int levelWidth;
    private int levelHeight;
    private int tileSize;

    private JTextField nameField = new JTextField(10);
    private String[] types = LevelType.toStringArray();
    private JComboBox<String> typeField = new JComboBox<String>(types);
    private JComboBox<Integer> widthField = new JComboBox<Integer>();
    private JComboBox<Integer> heightField = new JComboBox<Integer>();
    private JComboBox<Integer> tileSizeField = new JComboBox<Integer>();


    public NewLevelDialog() {
        super();
        for (int i = 100; i <= 4000; i += 100) {
            widthField.addItem(i);
            heightField.addItem(i);
        }

        tileSizeField.addItem(10);
        tileSizeField.addItem(20);
        tileSizeField.addItem(40);


        setBounds(X, Y, WIDTH, HEIGTH);
        add(new JLabel("Level Name:"));
        add(nameField);
        add(new JLabel("Level Type:"));
        add(typeField);
        add(Box.createHorizontalStrut(10));
        add(new JLabel("Width"));
        add(widthField);
        add(new JLabel("Height"));
        add(heightField);
        add(new JLabel("Tile Size:"));
        add(tileSizeField);
    }


    public void initialize(){
        int result = JOptionPane.showConfirmDialog(null, this, "Creating new level...", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            levelName = nameField.getText();  levelName = levelName.trim();  levelName = levelName.replace("\\s",""); levelName = levelName.replaceAll("[^a-zA-Z0-9]+","");
            levelType = LevelType.valueOf((String)typeField.getSelectedItem());
            levelWidth = (Integer)widthField.getSelectedItem();
            levelHeight = (Integer)heightField.getSelectedItem();
            tileSize = (Integer)tileSizeField.getSelectedItem();

            System.out.println(levelName);
            System.out.println(levelType);
            System.out.println(levelWidth);
            System.out.println(levelHeight);
            System.out.println(tileSize);

        }


    }
}