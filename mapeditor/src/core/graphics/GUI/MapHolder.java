package core.graphics.GUI;

import com.badlogic.gdx.graphics.OrthographicCamera;
import core.graphics.views.ComponentView;

/**
 * Created by Omer on 12/28/2015.
 */
public class MapHolder {
    private int x;
    private int y;
    private float width;
    private float height;

    private OrthographicCamera camera;
    private ComponentView view;

    private int scrollerWidth;
    private int scrollerHeight;

    private int scrollerX;
    private int scrollerY;

  //  private Map map;

    public MapHolder(int _x,int _y,int _width,int _height){
        camera = new OrthographicCamera();
        view = new ComponentView(_x,_y,_width,_height,camera);
        view.apply();
        camera.update();

    }

    public void resize(int _screenWidth,int _screenHeight){
        view.update(_screenWidth,_screenHeight);
        width = view.getViewWidth();
        height = view.getViewHeight();
        x = view.getX();
        y = view.getY();
        camera.update();

    }


}
