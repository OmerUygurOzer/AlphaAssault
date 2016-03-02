package graphics;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Omer on 2/9/2016.
 */
public class FrameAnimator {
    public Sprite[] frames;
    private float spf;
    private int currentSprite;
    private long time;

    public FrameAnimator(Sprite[] frames,float spf){
        this.frames = frames;
        this.spf = spf;
        this.currentSprite = 0;
    }

    public void draw(SpriteBatch spriteBatch){
           if(System.currentTimeMillis() - time > spf) {
               currentSprite++;
               currentSprite = currentSprite == frames.length ? 0 : currentSprite;
           }
            time = System.currentTimeMillis();
            frames[currentSprite].draw(spriteBatch);
    }
}
