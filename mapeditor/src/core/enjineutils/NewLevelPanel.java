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
        private JComboBox<Integer> widthField = new JComboBox<Integer>();
        private JComboBox<Integer> heightField = new JComboBox<Integer>();
        private JComboBox<Integer> tileSizeField = new JComboBox<Integer>();


        public NewLevelPanel(){
                super();
                for(int i = 100; i<=4000 ; i+=100){
                        widthField.addItem(i);
                        heightField.addItem(i);
                }
                tileSizeField.addItem(5);
                tileSizeField.addItem(10);
                tileSizeField.addItem(20);
                tileSizeField.addItem(40);


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
