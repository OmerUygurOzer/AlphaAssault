package core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglGraphics;
import com.badlogic.gdx.math.Vector2;

import javax.swing.*;

/**
 * Created by Omer on 1/7/2016.
 */
public class EditPanel extends JPanel {
    private static final int X = 824;
    private static final int Y = 0;

    private static final int WIDTH = 200;
    private static final int HEIGHT = 768;




    public EditPanel(){
        setBounds(X,Y,WIDTH,HEIGHT);
        setLayout(null);


    }


}
