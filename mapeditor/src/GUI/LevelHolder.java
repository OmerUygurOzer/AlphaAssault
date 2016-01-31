package GUI;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Omer on 1/17/2016.
 */
public class LevelHolder implements ApplicationListener{

    SpriteBatch spriteBatch;
    Texture texture ;

    int x,y;

    @Override
    public void create() {
        x = y = 0;
        spriteBatch = new SpriteBatch();
        texture  = new Texture("C:\\Users\\Omer\\Desktop\\Game Projects\\GameArt\\grass.png");

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
            Gdx.gl.glClearColor(1, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            spriteBatch.begin();
            spriteBatch.draw(texture,0,0);
            spriteBatch.end();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
