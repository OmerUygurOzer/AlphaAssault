package core.enjineutils;

import core.level.Level;
import core.level.LevelType;

import javax.swing.*;
import java.text.NumberFormat;

/**
 * Created by Omer on 1/13/2016.
 */
public class NewLevelPanel extends JPanel {
        private Level newLevel;

        private static final int X = 300;
        private static final int Y = 300;

        private static final int WIDTH  = 300;
        private static final int HEIGTH = 300;

        private String levelName;
        private LevelType levelType;
        private int levelWidth;
        private int levelHeight;
        private int tileSize;

        private JTextField nameField = new JTextField(10);
        private String[] types = LevelType.toStringArray();private JComboBox<String> typeField = new JComboBox<String>(types);
        private JTextField widthField = new JFormattedTextField(NumberFormat.getNumberInstance());
        private JTextField heightField = new JFormattedTextField(NumberFormat.getNumberInstance());
        private JTextField tileSizeField = new JFormattedTextField(NumberFormat.getNumberInstance());

        public NewLevelPanel(){
                super();
                widthField.setColumns(4);
                heightField.setColumns(4);
                tileSizeField.setColumns(3);
                setBounds(X,Y,WIDTH,HEIGTH);
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
                boolean GTG = false;
                int result = JOptionPane.showConfirmDialog(null, this, "Creating new level...", JOptionPane.OK_CANCEL_OPTION);
                if(result == JOptionPane.OK_OPTION){
                        levelName = nameField.getText();
                        levelType = LevelType.valueOf((String)typeField.getSelectedItem());
                        levelWidth = Integer.parseInt(widthField.getText());
                        levelHeight = Integer.parseInt(heightField.getText());
                        tileSize = Integer.parseInt(tileSizeField.getText());

                        System.out.println(levelName);
                        System.out.println(levelType);
                        System.out.println(levelWidth);
                        System.out.println(levelHeight);
                        System.out.println(tileSize);
                        GTG = true; //DEBUG
                }

                if(GTG){
                        newLevel = new Level(levelName,levelType);
                        newLevel.setSize(levelWidth,levelHeight);
                        newLevel.setTileSize(tileSize);
                        newLevel.generate();
                }
        }

        public Level getLevel(){return newLevel.isGenerated() ? newLevel:null;}

}
