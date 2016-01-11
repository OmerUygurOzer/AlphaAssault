package core;


import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.*;

/**
 * Created by Omer on 1/7/2016.
 */
public class MapPanel extends GLJPanel{


    private static final int X = 0; //CORNER SIZE
    private static final int Y = 0;

    private static final int WIDTH = 824;
    private static final int HEIGHT = 768;

    private static final int SCROLL_BAR_THICKNESS = 20;
    private static final int CORNER_SIZE = 20;


    private Scrollbar verticalBar;
    private Scrollbar horizontalBar;


    public MapPanel() throws GLException {
            setBounds(X,Y,WIDTH,HEIGHT);
            setLayout(null);



            verticalBar = new Scrollbar();
            horizontalBar = new Scrollbar();

            verticalBar.setOrientation(Scrollbar.VERTICAL);
            horizontalBar.setOrientation(Scrollbar.HORIZONTAL);

            verticalBar.setBounds(0,0,SCROLL_BAR_THICKNESS,HEIGHT-CORNER_SIZE);
            horizontalBar.setBounds(CORNER_SIZE,HEIGHT-CORNER_SIZE,WIDTH-(CORNER_SIZE),CORNER_SIZE);


            add(verticalBar);
            add(horizontalBar);


            addGLEventListener(new GLEventListener() {
                @Override
                public void init(GLAutoDrawable glAutoDrawable) {
                    System.out.println("Initialized:");
                }

                @Override
                public void dispose(GLAutoDrawable glAutoDrawable) {
                    System.out.println("Disposed:");
                }

                @Override
                public void display(GLAutoDrawable glAutoDrawable) {
                    System.out.println("Displayed.");
                }

                @Override
                public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
                    System.out.println("Reshaped");
                }
            });

            FPSAnimator animator = new FPSAnimator(this,1);
            animator.start();

    }



    private void render(){

    }




}
