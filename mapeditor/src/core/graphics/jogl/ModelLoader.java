package core.graphics.jogl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLContext;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Omer on 1/15/2016.
 */
public class ModelLoader {
    private  GL2 gl;
    private List<int[]> vaos = new ArrayList<int[]>();
    private List<int[]> vbos = new ArrayList<int[]>();

    public ModelLoader(GL2 gl){
        this.gl = gl;
    }

    public JoglModel loadToVao(float[] positions){
        int vaoID = createVAO();
        storeDataInAttributeList(0,positions);
        unbind();


        return new JoglModel(vaoID,positions.length/3);
    }

    private int createVAO(){
        int[] vaoID = new int[1];
        gl.glGenVertexArrays(vaoID.length, vaoID, 0);
        vaos.add(vaoID);
        gl.glBindVertexArray(vaoID[0]);
        return vaoID[0];
    }

    private void storeDataInAttributeList(int attributeNumber,float[] data){
        int[] vboID = new int[1];
        gl.glGenBuffers(vboID.length,vboID,0);
        vbos.add(vboID);
        gl.glBindBuffer(gl.GL_ARRAY_BUFFER,vboID[0]);
        FloatBuffer floatBuffer = createFloatBuffer(data);
        gl.glBufferData(gl.GL_ARRAY_BUFFER,floatBuffer.remaining(),floatBuffer,gl.GL_STATIC_DRAW);
        gl.glVertexAttribPointer(attributeNumber,3,gl.GL_FLOAT,false,0,0);
        gl.glBindBuffer(gl.GL_ARRAY_BUFFER,0);

    }

    private FloatBuffer createFloatBuffer(float[] data){
        FloatBuffer floatBuffer = FloatBuffer.allocate(data.length);
        floatBuffer.put(data);
        floatBuffer.flip();
        return floatBuffer;
    }

    private void unbind(){}
    public void clear(){
        for(int[] vao : vaos){
            gl.glDeleteVertexArrays(vao.length,vao,0);
        }

        for(int[] vbo: vbos){
            gl.glDeleteBuffers(vbo.length,vbo,0);
        }
        vaos.clear();
        vbos.clear();
    }
}
