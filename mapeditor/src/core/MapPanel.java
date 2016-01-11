package core;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglGraphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.awt.GLJPanel;

/**
 * Created by Omer on 1/7/2016.
 */
public class MapPanel extends GLJPanel{


    private static final int X = 0;
    private static final int Y = 0;

    private static final int WIDTH = 824;
    private static final int HEIGHT = 768;


    public MapPanel() throws GLException {
            setBounds(X,Y,WIDTH,HEIGHT);
            setLayout(null);
      }

    public void draw(){

    }


}
