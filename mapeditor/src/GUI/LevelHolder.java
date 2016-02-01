package GUI;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import objects.ObjectBase;


/**
 * Created by Omer on 1/17/2016.
 */
public class LevelHolder implements ApplicationListener{

    OrthographicCamera orthographicCamera;
    Viewport viewport;

    SpriteBatch spriteBatch;
    ShapeRenderer shapeRenderer;

    int width = 800;
    int height = 800;

    int tileSize = 20;


    boolean a_prev = false;
    boolean s_prev = false;
    boolean d_prev = false;
    boolean w_prev = false;

    private Object editLock = new Object();

    private ObjectBase objectBase;

    private Texture texture;
    private Sprite sprite;

    @Override
    public void create() {

        orthographicCamera = new OrthographicCamera();
        viewport = new FitViewport(width,height,orthographicCamera);
        orthographicCamera.translate(width/2,height/2);
        orthographicCamera.update();

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        texture = new Texture("C:\\Users\\Omer\\Desktop\\Game Projects\\GameArt\\bricks.png");
        sprite = new Sprite(texture);
        sprite.setPosition(200,200);
        sprite.setSize(100,100);

    }

    public void setTileSize(int tileSize){
        synchronized (editLock) {
            this.tileSize = tileSize;
        }
    }

    public void setSize(int width,int heigth){
        synchronized (editLock) {
            this.width = width;
            this.height = heigth;
        }
    }

    private void drawGrids(){
        synchronized (editLock) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
            viewport.apply();

            int borderLeft   = (int) orthographicCamera.position.x - (width / 2);
            int borderRigth  = (int) orthographicCamera.position.x + (width / 2);
            int borderTop    = (int) orthographicCamera.position.y + (height / 2);
            int borderBottom = (int) orthographicCamera.position.y - (height / 2);

            for (int v = borderLeft; v < borderRigth; v += tileSize) {
                shapeRenderer.line(v, borderBottom, v, borderTop, Color.BLACK, Color.BLACK);
            }
            for (int h = borderBottom; h < borderTop; h += tileSize) {
                shapeRenderer.line(borderLeft, h, borderRigth, h, Color.BLACK, Color.BLACK);
            }

            shapeRenderer.end();
        }
    }

    private void setObject(ObjectBase objectBase){
        synchronized (editLock) {
            this.objectBase = objectBase;
        }
    }

    public void handleInput(){
        boolean a_pressed = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean s_pressed = Gdx.input.isKeyPressed(Input.Keys.S);
        boolean d_pressed = Gdx.input.isKeyPressed(Input.Keys.D);
        boolean w_pressed = Gdx.input.isKeyPressed(Input.Keys.W);

        if(a_pressed && !a_prev){orthographicCamera.position.x -= tileSize;}
        if(d_pressed && !d_prev){orthographicCamera.position.x += tileSize;}
        if(w_pressed && !w_prev){orthographicCamera.position.y += tileSize;}
        if(s_pressed && !s_prev){orthographicCamera.position.y -= tileSize;}

        a_prev = a_pressed;
        d_prev = d_pressed;
        w_prev = w_pressed;
        s_prev = s_pressed;



        }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void render() {
        synchronized (editLock) {
            Gdx.gl.glClearColor(0, 1, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            drawGrids();

            handleInput();

            spriteBatch.begin();
            spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
            viewport.apply();
            //DRAW STUFF
            sprite.draw(spriteBatch);
            spriteBatch.end();



        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }
}
