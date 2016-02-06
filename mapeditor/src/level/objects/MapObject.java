package level.objects;

import utilities.ByteIO;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import graphics.SerializableImage;
import ingame.objects.RawObject;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 2/1/2016.
 */
public class MapObject {
    public File objectFile;
    public RawObject rawObject;

    private Sprite currentFrame;
    public Vector2 position;
    public int layer;
    private int currentFrameIndex;


    private List<Sprite> frameSprites;

    private Object lock = new Object();

    public MapObject(File objectFile,RawObject rawObject){
            this.rawObject = rawObject;
            this.objectFile = objectFile;
            this.layer = 0;
            this.position = new Vector2(0f,0f);
            frameSprites = new ArrayList<Sprite>();
            createFrames();
    }


    public Sprite getCurrentFrame() {
        return new Sprite(currentFrame);
    }

    public File getObjectFile(){return objectFile;}

    public void setRelativePosition(Vector2 position) {
            this.position = position;
    }

    public void setImagePosition(float x,float y){
        synchronized (lock){
            currentFrame.setCenter(x,y);
            for (Sprite sprite : frameSprites) {
                sprite.setCenter(position.x,position.y);
            }
        }
    }

    public void setSize(float width, float heigth){
        synchronized (lock) {
            for (Sprite sprite : frameSprites) {
                sprite.setSize(width, heigth);
            }
        }
    }

    public void setLayer(int layer){
        synchronized (lock){
            this.layer = layer;
        }
    }

    public void draw(SpriteBatch spriteBatch){
        synchronized (lock) {
            currentFrame.draw(spriteBatch);
        }
    }

    public void changeCurrentFrame(int i){
        synchronized (lock) {
            currentFrame = frameSprites.get(i);
            this.currentFrameIndex = i;
        }
    }


    private void createFrames(){
            for (SerializableImage image : rawObject.frames) {
                byte[] bytes = ByteIO.convertToByteArray(image.image);
                Pixmap pixmap = new Pixmap(bytes,0,bytes.length);
                frameSprites.add(new Sprite(new Texture(pixmap)));
                this.currentFrame = frameSprites.get(0);

            }
    }

    public MapObject clone(){
        MapObject object = new MapObject(objectFile, rawObject);
        object.setSize(currentFrame.getWidth(),currentFrame.getHeight());
        object.setRelativePosition(position);
        object.changeCurrentFrame(currentFrameIndex);
        object.setLayer(layer);
        return object;
    }

    public RawObject getRawObject() {
        return rawObject;
    }
}
