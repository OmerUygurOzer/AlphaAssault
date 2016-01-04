package core.graphics.GUI;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import core.Resources;
import core.graphics.views.ComponentView;
import core.inputs.Inputs;

/**
 * Created by Omer on 12/28/2015.
 */
public class MapHolder extends Component{



    private OrthographicCamera camera;




    private Sprite test;

    //private Map map;

    public MapHolder(int _x,int _y,int _width,int _height){
        super(_x,_y,_width,_height);
        camera = new OrthographicCamera();
        viewport = new ComponentView(_x,_y,_width,_height,camera);
        ((ComponentView)viewport).apply();
        camera.update();

        test = new Sprite(Resources.getTextureRegions(Resources.BACKGROUND)[0][1]);
        test.setSize(_width,_height);
        test.setCenter(_width/2,_height/2);

    }



    public void resize(int _screenWidth,int _screenHeight){
        viewport.update(_screenWidth,_screenHeight);

    }

    public void moveCamera(int _x,int _y){
        camera.position.x += _x;
        camera.position.y -= _y;
        camera.update();
    }




    public void resetCamera(){
        camera.position.x = 0;
        camera.position.y = 0;
    }


    @Override
    public void draw(SpriteBatch _spriteBatch)
    {
            test.draw(_spriteBatch);
    }

    @Override
    public void receiveInput() {
        int xMovement = 0;
        int yMovement = 0;

        if(Inputs.getInputKeys().get(Input.Keys.A)!= null){
            if(Inputs.getInputKeys().get(Input.Keys.A)){
                xMovement -=1;
            }

        }
        if(Inputs.getInputKeys().get(Input.Keys.D)!= null){
            if(Inputs.getInputKeys().get(Input.Keys.D)){
                xMovement +=1;
            }
        }

        if(Inputs.getInputKeys().get(Input.Keys.W)!= null){
            if(Inputs.getInputKeys().get(Input.Keys.W)){
                yMovement -=1;
            }
        }

        if(Inputs.getInputKeys().get(Input.Keys.S)!= null){
            if(Inputs.getInputKeys().get(Input.Keys.S)){
                yMovement +=1;
            }
        }

        if(Inputs.getInputKeys().get(Input.Keys.R)!= null){
            if(Inputs.getInputKeys().get(Input.Keys.R)){
                resetCamera();
            }
        }
        moveCamera(xMovement,yMovement);
    }
}
