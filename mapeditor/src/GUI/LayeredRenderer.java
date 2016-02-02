package GUI;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 2/1/2016.
 */
public class LayeredRenderer {

    private static final int ALL_LAYERS = 0;
    private List<Integer> renderedLayers = new ArrayList<Integer>();

    private SpriteBatch spriteBatch;
    private List<List<Sprite>> layers;

    private OrthographicCamera orthographicCamera;
    private Viewport viewport;

    public LayeredRenderer(){
        spriteBatch = new SpriteBatch();
        layers = new ArrayList<List<Sprite>>();
    }

    public void addCamera(OrthographicCamera orthographicCamera){
        this.orthographicCamera = orthographicCamera;
    }

    public void addViewport(Viewport viewport){
        this.viewport = viewport;
    }

    public void addSprite(int layer, Sprite sprite){
        if(layer < layers.size()){
            layers.get(layer).add(sprite);
        }else{
            layers.add(new ArrayList<Sprite>());
            layers.get(layers.size()-1).add(sprite);
        }
    }

    public void setRenderedLayers(int... layers){
        renderedLayers.clear();
        for(int i = 0 ; i < layers.length ; i++){
            renderedLayers.add(layers[i]);
        }
    }

    public void setRenderedLayers(boolean drawAll){
        renderedLayers.clear();
        if(drawAll){
            for(int i = 0 ; i < layers.size();i++){
                renderedLayers.add(i);
            }
        }
    }

    public void draw(){
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        viewport.apply();
        for(int index = 0;index < layers.size();index++) {
            if (renderedLayers.contains(index)){
                for (Sprite sprite : layers.get(index)) {
                    sprite.draw(spriteBatch);
                }
             }
        }
        spriteBatch.end();
    }




    public void dispose(){
        spriteBatch.dispose();
    }

}
