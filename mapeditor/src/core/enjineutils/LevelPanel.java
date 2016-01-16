package core.enjineutils;


import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;
import core.graphics.jogl.JoglModel;
import core.graphics.jogl.JoglRenderer;
import core.graphics.jogl.ModelLoader;
import core.level.Level;

import java.awt.*;

/**
 * Created by Omer on 1/7/2016.
 */
public class LevelPanel extends GLJPanel{
    private Level level;

    private EditPanel editPanel;

    private static final int X = 0; //CORNER SIZE
    private static final int Y = 0;

    private static final int WIDTH = 824;
    private static final int HEIGHT = 768;

    private static final int SCROLL_BAR_THICKNESS = 20;
    private static final int CORNER_SIZE = 20;


    private Scrollbar verticalBar;
    private Scrollbar horizontalBar;



    public LevelPanel(EditPanel editPanel) throws GLException {
            this.editPanel = editPanel;
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
                GL2 gl;
                ModelLoader modelLoader;
                JoglRenderer renderer;
                JoglModel model;

                @Override
                public void init(GLAutoDrawable glAutoDrawable) {
                    System.out.println("Initialized:");
                    gl = glAutoDrawable.getGL().getGL2();

                    modelLoader = new ModelLoader(gl);
                    renderer = new JoglRenderer(gl);


                    float[] vertices = {
                            -0.5f, 0.5f, 0f,
                            -0.5f, -0.5f, 0f,
                            0.5f, -0.5f, 0f,
                            0.5f, -0.5f, 0f,
                            0.5f, 0.5f, 0f,
                            -0.5f, 0.5f, 0f
                    };
                    model = modelLoader.loadToVao(vertices);

                }

                @Override
                public void dispose(GLAutoDrawable glAutoDrawable) {
                    System.out.println("Disposed:");
                    modelLoader.clear();
                }

                @Override
                public void display(GLAutoDrawable glAutoDrawable) {
                    System.out.println("Displayed.");

                    render(gl);
                    renderer.begin();
                    renderer.render(model);
                    renderer.checkError();
                }

                @Override
                public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
                    System.out.println("Reshaped");
                }
            });

            FPSAnimator animator = new FPSAnimator(this,1);
            animator.start();



    }



    private void render(GL2 gl){

        if(level!=null && level.isGenerated()){
            level.render();
        }


    }


    public void setLevel(Level level){
        this.level = level;
    }

}
