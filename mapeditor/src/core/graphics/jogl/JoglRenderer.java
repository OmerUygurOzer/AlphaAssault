package core.graphics.jogl;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLContext;

/**
 * Created by Omer on 1/15/2016.
 */
public class JoglRenderer {
   private GL2 gl;

    public JoglRenderer(GL2 gl){
        this.gl = gl;
    }

    public void begin(){
       gl.glClearColor(1f,0f,0f,1f);
       gl.glClear(gl.GL_CLEAR_BUFFER);


    }

    public void render(JoglModel joglModel){

        gl.glBindVertexArray(joglModel.getVaoID());
        gl.glEnableVertexAttribArray(0);
        gl.glDrawArrays(gl.GL_TRIANGLES,0,joglModel.getVertexCount());
        gl.glDisableVertexAttribArray(0);
        gl.glBindVertexArray(0);

/*
        gl.glBegin(gl.GL_TRIANGLES);
        gl.glColor3f(1, 0, 0);
        gl.glVertex2f(-1, -1);
        gl.glColor3f(0, 1, 0);
        gl.glVertex2f(0, 1);
        gl.glColor3f(0, 0, 1);
        gl.glVertex2f(1, -1);
       gl.glEnd();
        */
    }

    public void checkError() {
        String errorString = "";
        int error = gl.glGetError();
        if (error != GL.GL_NO_ERROR) {

            switch (error) {
                case GL.GL_INVALID_ENUM:
                    errorString = "GL_INVALID_ENUM";
                    break;
                case GL.GL_INVALID_VALUE:
                    errorString = "GL_INVALID_VALUE";
                    break;
                case GL.GL_INVALID_OPERATION:
                    errorString = "GL_INVALID_OPERATION";
                    break;
                case GL.GL_INVALID_FRAMEBUFFER_OPERATION:
                    errorString = "GL_INVALID_FRAMEBUFFER_OPERATION";
                    break;
                case GL.GL_OUT_OF_MEMORY:
                    errorString = "GL_OUT_OF_MEMORY";
                    break;
                default:
                    errorString = "UNKNOWN";
                    break;
            }

        }
        System.out.println(errorString);
    }
}
