package core.graphics.GUI;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Omer on 12/28/2015.
 */
public abstract class Component {
    public Vector2 center;


    public int width;
    public int height;

    protected Viewport viewport;
    protected OrthographicCamera camera;

    protected Component(float _x,float _y,int _width,int _height){
        center = new Vector2(_x,_y);

        width = _width;
        height = _height;
    }

    public void setView(Viewport _viewPort){
        viewport = _viewPort;
    }
    public void setCamera(OrthographicCamera _camera){camera = _camera;}

    public Viewport getViewport(){
        return  viewport;
    }


    public abstract void draw(SpriteBatch _spriteBatch);
    public abstract void receiveInput();

}
