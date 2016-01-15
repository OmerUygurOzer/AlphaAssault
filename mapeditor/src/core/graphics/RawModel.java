package core.graphics;

/**
 * Created by Omer on 1/14/2016.
 */
public class RawModel {
    private int vaoID;
    private int vertexCount;

    public RawModel(int vaoID,int vertexCount){
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;

    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getVaoID() {
        return vaoID;
    }
}
