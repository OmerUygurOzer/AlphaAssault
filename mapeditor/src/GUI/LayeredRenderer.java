package GUI;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import level.objects.MapObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 2/1/2016.
 */
public class LayeredRenderer {

    private static final int ALL_LAYERS = 0;
    private List<Integer> renderedLayers = new ArrayList<Integer>();

    private SpriteBatch spriteBatch;
    private List<List<MapObject>> layers;

    private OrthographicCamera orthographicCamera;
    private Viewport viewport;

    private Object lock = new Object();

    public LayeredRenderer(){
        spriteBatch = new SpriteBatch();
        layers = new ArrayList<List<MapObject>>();
    }

    public void addCamera(OrthographicCamera orthographicCamera){
        this.orthographicCamera = orthographicCamera;
    }

    public void addViewport(Viewport viewport){
        this.viewport = viewport;
    }

    public void addObject(int layer, MapObject object){
        synchronized (lock) {
            if (layer < layers.size()) {
                layers.get(layer).add(object);
            } else {
                layers.add(new ArrayList<MapObject>());
                layers.get(layers.size() - 1).add(object);
            }
        }
    }

    public void removeObject(int layer,MapObject object){
        synchronized (lock) {
            layers.get(layer).remove(object);
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
        synchronized (lock) {
            spriteBatch.begin();
            spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
            viewport.apply();
            for (int index = 0; index < layers.size(); index++) {
                if (renderedLayers.contains(index)) {
                    for (MapObject object : layers.get(index)) {
                        object.getCurrentFrame().draw(spriteBatch);
                    }
                }
            }
            spriteBatch.end();
        }
    }


    public void clear(){
        layers.clear();
        //renderedLayers.clear();
    }


    public void dispose(){
        spriteBatch.dispose();
    }

}
