package GUI;

import GUI.utils.ImageHolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

/**
 * Created by Omer on 1/17/2016.
 */
public class ObjectCreator extends JFrame {
        private static final String TITLE = "EnJine2D Object Creator";
        private static final int WIDTH = 1200;
        private static final int HEIGHT = 800;

        private static final int IMAGE_PANE_SIZE = 800;
        private ImageHolder objectTexture;

        private JTabbedPane typePane;
        private static final int TYPE_PANE_WIDTH = 400;

        public ObjectCreator(){
            setLayout(null);
            setSize(WIDTH,HEIGHT);


            typePane = new JTabbedPane();
            typePane.setBounds(WIDTH-TYPE_PANE_WIDTH,0,TYPE_PANE_WIDTH,HEIGHT);
            typePane.addTab("Base Editor",new BaseEditor());
            add(typePane);






            File test = new File("C:\\Users\\Omer\\Desktop\\Game Projects\\AlphaAssault\\android\\assets\\character.png");
            try {

                BufferedImage testImage = ImageIO.read(test);
                objectTexture = new ImageHolder(testImage,0,0,IMAGE_PANE_SIZE,IMAGE_PANE_SIZE);
                getContentPane().add(objectTexture);

            } catch (IOException e) {
                e.printStackTrace();
            }

            setVisible(true);
        }


}
