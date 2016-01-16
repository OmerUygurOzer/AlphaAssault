package core.graphics.jogl;

/**
 * Created by Omer on 1/15/2016.
 */
public class JoglModel {
    private int vaoID;
    private int vertexCount;

    public JoglModel(int vertexCount, int vaoID) {
        this.vertexCount = vertexCount;
        this.vaoID = vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getVaoID() {
        return vaoID;
    }
}
