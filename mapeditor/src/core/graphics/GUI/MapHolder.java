package core.graphics.GUI;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import core.Resources;
import core.graphics.views.ComponentView;

/**
 * Created by Omer on 12/28/2015.
 */
public class MapHolder extends Component{

    private static final int SCROLLER_WIDTH = 50;
    private static final int SCROLLER_HEIGHT = 10;

    private OrthographicCamera camera;


    private int horizontalScrollerLocation;
    private int verticalScrollerLocation;

    private int horizontalScrollTab;
    private int verticalScrollTab;

    private Sprite test;

    //private Map map;

    public MapHolder(int _x,int _y,int _width,int _height){
        super(_x,_y,_width,_height);
        camera = new OrthographicCamera();
        viewport = new ComponentView(_x,_y,_width,_height,camera);
        ((ComponentView)viewport).apply();
        camera.update();


        horizontalScrollerLocation = 0;
        verticalScrollerLocation = 0;

        test = new Sprite(Resources.getTextureRegions(Resources.BACKGROUND)[0][1]);
        test.setSize(_width,_height);
        test.setCenter(_width/2,_height/2);

    }



    public void resize(int _screenWidth,int _screenHeight){
        viewport.update(_screenWidth,_screenHeight);

    }




    @Override
    public void draw(SpriteBatch _spriteBatch)
    {
            test.draw(_spriteBatch);
    }

    @Override
    public void receiveInput() {

    }
}
