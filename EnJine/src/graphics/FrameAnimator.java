package graphics;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 2/9/2016.
 */
public class FrameAnimator {
    public Map<String,Sprite[]> frames;
    private float spf;
    private String currentSheet;
    private int currentFrame;
    private long time;

    public FrameAnimator(float spf){
        this.frames = new HashMap<String, Sprite[]>();
        this.spf = spf;
        this.currentFrame = 0;
    }



    public void addFrameSheet(String key,Sprite[] sheet){
        if(frames.isEmpty()){
            currentSheet = key;}
        frames.put(key,sheet);
    }

    public void changeFrameSheet(String key){
        currentSheet = key;
    }

    public void draw(SpriteBatch spriteBatch){
        Sprite[] sheet = frames.get(currentSheet);
           if(System.currentTimeMillis() - time > spf) {
               currentFrame++;
               currentFrame = currentFrame == sheet.length ? 0 : currentFrame;
           }
            time = System.currentTimeMillis();
            sheet[currentFrame].draw(spriteBatch);
    }
}
