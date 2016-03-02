package ingame.physics;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 2/8/2016.
 */
public class BodyComponent {
    private Vector2 attachmentPoint;
    private Vector2 position;
    private BodyComponent parent;
    private List<BodyComponent> attachments = new ArrayList<BodyComponent>();

    private float[] rawVertices;
    private float[] transformedVertices;
    private Vector2[] vertexArray;
    public int rotation = 0;
    public int scale = 1;
    public Vector2 translation = new Vector2();

    private boolean altered = true;
    private Polygon bounds;



    public BodyComponent(float[] rawVertices){
        this.attachmentPoint = new Vector2();
        this.position = new Vector2();
        this.rawVertices = rawVertices;
        this.vertexArray = createVertexArray(rawVertices);
    }

    public BodyComponent(float[] rawVertices,Vector2 position){
        this(rawVertices);
        translate(position.x,position.y);
        this.position = position;
    }

    public void attach(BodyComponent bodyComponent,Vector2 attachmentPoint){
        bodyComponent.translate(attachmentPoint.x,attachmentPoint.y);
        attachments.add(bodyComponent);
        bodyComponent.parent = this;
        bodyComponent.attachmentPoint = attachmentPoint;
    }

    public void rotate(float deg){
        rotation +=deg;
        float [] rotationVal = new float[3*3];
        rotationVal[0] =(float)  Math.cos(Math.toRadians(deg));
        rotationVal[1] =(float)  Math.sin(Math.toRadians(deg));
        rotationVal[2] = 0f;
        rotationVal[3] =(float) -Math.sin(Math.toRadians(deg));
        rotationVal[4] =(float)  Math.cos(Math.toRadians(deg));
        rotationVal[5] = 0f;
        rotationVal[6] = 0f;
        rotationVal[7] = 0f;
        rotationVal[8] = 0f;

        Matrix3 rotation = new Matrix3(rotationVal);

        for(int i = 0 ; i < vertexArray.length ; i++){
            vertexArray[i].sub(attachmentPoint.x,attachmentPoint.y);
            vertexArray[i] = vertexArray[i].mul(rotation);
            vertexArray[i].add(attachmentPoint.x,attachmentPoint.y);
        }

        for(BodyComponent bodyComponent  :  attachments){
            bodyComponent.rotate(deg,attachmentPoint);
        }
        altered = true;
    }

    public void rotate(float deg, Vector2 about){
        rotation +=deg;
        float [] rotationVal = new float[3*3];
        rotationVal[0] =(float)  Math.cos(Math.toRadians(deg));
        rotationVal[1] =(float)  Math.sin(Math.toRadians(deg));
        rotationVal[2] = 0f;
        rotationVal[3] =(float) -Math.sin(Math.toRadians(deg));
        rotationVal[4] =(float)  Math.cos(Math.toRadians(deg));
        rotationVal[5] = 0f;
        rotationVal[6] = 0f;
        rotationVal[7] = 0f;
        rotationVal[8] = 0f;
        Matrix3 rotation = new Matrix3(rotationVal);

        for(int i = 0 ; i < vertexArray.length ; i++){
            vertexArray[i].sub(about.x,about.y);
            vertexArray[i] = vertexArray[i].mul(rotation);
            vertexArray[i].add(about.x,about.y);
        }


        for(BodyComponent bodyComponent  :  attachments){
            bodyComponent.rotate(deg,about);
        }
        altered = true;
    }

    public void translate(float x,float y){
        translation.add(x,y);
        position.add(x,y);
        attachmentPoint.add(x,y);

        for(int i = 0 ; i < vertexArray.length ; i++){
           vertexArray[i].add(x,y);
        }

        for(BodyComponent bodyComponent:attachments){
            bodyComponent.translate(x,y);
        }
        altered = true;
    }

    public void translate(Vector2 vector2){
        this.translate(vector2.x,vector2.y);
    }

    public void scale(int scale){
        this.scale = scale;
        altered = true;
    }


    public Polygon getBounds(){
        if(!altered){return bounds;}

        transformedVertices =  createVertices(vertexArray);
        bounds = new Polygon(transformedVertices);
        altered = false;
        return  bounds;
    }

    public static Vector2[] createVertexArray(float[] rawVertices){
        Vector2[] vectors = new Vector2[rawVertices.length/2];
        for(int i = 0 ; i < rawVertices.length; i+=2){
            vectors[i/2] = new Vector2(rawVertices[i],rawVertices[i+1]);
        }
        return  vectors;
    }

    public static float[] createVertices(Vector2[] bounds){
        float[] newVertices = new float[bounds.length*2];
        for(int i = 0 ; i < newVertices.length ; i++){
            newVertices[i] = i%2==0 ? bounds[i/2].x : bounds[i/2].y;
        }
        return newVertices;
    }
}
