package graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import maths.geometry.Grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 3/8/2016.
 */
public class SpriteSheet {


    public Map<String,Sprite[]> frames;
    public Texture texture;


    private SpriteSheet(){
        frames = new HashMap<String, Sprite[]>();
    }

    public static SpriteSheet generateSheet(TextureRegion region, Grid grid,List<String> keys){
        SpriteSheet spriteSheet = new SpriteSheet();
        spriteSheet.texture = region.getTexture();

        TextureRegion[][] regions = region.split(grid.tileWidth,grid.tileHeight);
        for(int i = 0 ; i< regions.length;i++){
            Sprite[] sprites = new Sprite[regions[i].length];
            for(int j = 0 ; j < regions[0].length ; j++){
                sprites[j] = new Sprite(regions[i][j]);
            }
            spriteSheet.frames.put(keys.get(i),sprites);
        }
        return spriteSheet;
    }


}
