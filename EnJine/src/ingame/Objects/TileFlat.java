package ingame.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import graphics.FrameAnimator;

/**
 * Created by Omer on 2/9/2016.
 */
public class TileFlat extends Tile {

    public TileFlat(Vector2 position,int layer,int width,int height,TextureRegion[] frames){
        this.position = position;
        this.layer = layer;
        this.width = width;
        this.height = height;
        Sprite[] sprites = new Sprite[frames.length];
        for(int i = 0; i<frames.length ; i ++){
            sprites[i] = new Sprite(frames[i]);
            sprites[i].setSize(width,height);
            sprites[i].setCenter(position.x,position.y);
        }

        this.frameAnimator = new FrameAnimator(sprites,0.1f);
        createBounds();
    }

    @Override
    protected void createBounds() {
        float [] vertices = new float[8];
        vertices[0] = position.x - width/2;
        vertices[1] = position.y - height/2;
        vertices[2] = position.x - width/2;
        vertices[3] = position.y + height/2;
        vertices[4] = position.x + width/2;
        vertices[5] = position.y + height/2;
        vertices[6] = position.x + width/2;
        vertices[7] = position.y - height/2;
        bounds = new Polygon(vertices);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        frameAnimator.draw(spriteBatch);
    }

    @Override
    public void move(float x, float y) {
        position.x = x;
        position.y = y;
        for(Sprite f : frameAnimator.frames){
            f.setCenter(x,y);
        }
    }

    @Override
    public GameObject copy() {
        Sprite[] copiedFrames = new Sprite[frameAnimator.frames.length];
        for(int i = 0 ; i<frameAnimator.frames.length ; i ++){
            copiedFrames[i] = new Sprite(frameAnimator.frames[i]);
        }
        TileFlat copied = new TileFlat(new Vector2(position),layer,width,height,copiedFrames);
        copied.move(position.x,position.y);
        return copied;
    }
}
