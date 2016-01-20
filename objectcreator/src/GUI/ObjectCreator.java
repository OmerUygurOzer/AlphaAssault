package GUI;

import GUI.utils.ImageHolder;


import javax.swing.*;

/**
 * Created by Omer on 1/17/2016.
 */
public class ObjectCreator extends JFrame {
        private static final String TITLE = "EnJine2D Object Creator";
        private static final int WIDTH = 1200;
        private static final int HEIGHT = 800;


        private JTabbedPane typePane;


        public ObjectCreator(){
            setLayout(null);
            setSize(WIDTH,HEIGHT);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);

            typePane = new JTabbedPane();
            typePane.setBounds(0,0,WIDTH,HEIGHT);
            typePane.addTab("Base Editor",new BaseEditor());

            add(typePane);



            setVisible(true);
        }


}
