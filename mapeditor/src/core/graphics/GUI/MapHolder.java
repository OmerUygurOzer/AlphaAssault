package core.graphics.GUI;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import core.Resources;
import core.graphics.views.ComponentView;
import core.inputs.Inputs;
import core.map.Entity;
import core.map.EntityParser;
import core.map.Map;


/**
 * Created by Omer on 12/28/2015.
 */
public class MapHolder extends Component{


    private Sprite test;

    private Map map;

    private Entity selection;


    public MapHolder(int _x,int _y,int _width,int _height,Map _map){
        super(_x,_y,_width,_height);
        camera = new OrthographicCamera();
        viewport = new ComponentView(_x,_y,_width,_height,camera);
        viewport.apply();
        //camera.translate(_width/2,_height/2);
        camera.update();

        test = new Sprite(Resources.getTextureRegions(Resources.BACKGROUND)[0][1]);
        test.setSize(_width,_height);
        test.setCenter(_width/2,_height/2);
        map = _map;
    }

    public void setSelection(int _id){
        if(selection !=null){
            if(selection.id!=_id){
                selection = EntityParser.parse(_id);
                System.out.println(_id);
            }
            return;
        }
        selection = EntityParser.parse(_id);
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
            _spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
            viewport.apply();
            _spriteBatch.begin();
            map.draw(_spriteBatch);
            if(selection!=null){
                selection.image.draw(_spriteBatch);
            }
            _spriteBatch.end();
    }

    @Override
    public void receiveInput() {

        if(selection!=null){
            float x = Inputs.getHoverLocation().x;
            float y = Inputs.getHoverLocation().y;
            boolean fitsX = x > viewport.getScreenX() && x < viewport.getScreenX() + viewport.getScreenWidth();
            boolean fitsY = y > viewport.getScreenY() && y < viewport.getScreenY() + viewport.getScreenHeight();
            if(fitsX && fitsY){
                selection.center.x = x - (viewport.getScreenX()-camera.position.x+viewport.getScreenWidth()/2);
                selection.center.y = y - (viewport.getScreenY()-camera.position.y+viewport.getScreenHeight()/2);
                selection.image.setCenter(selection.center.x,selection.center.y);

            }
            boolean clicked = false;
            for(Vector2 click : Inputs.getInputTouch().values()){
                x = click.x;
                y = click.y;
                fitsX = x > viewport.getScreenX() && x < viewport.getScreenX() + viewport.getScreenWidth();
                fitsY = y > viewport.getScreenY() && y < viewport.getScreenY() + viewport.getScreenHeight();
                if(fitsX && fitsY){
                   // System.out.println(viewport.getScreenX());
                   clicked = true;
                }
            }

            if(clicked){
                map.addEntity(selection);
            }

        }



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
