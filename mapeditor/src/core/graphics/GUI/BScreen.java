package core.graphics.GUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 12/28/2015.
 */
public class BScreen {
    private List<Component> componentsList;

    public BScreen(){
        componentsList = new ArrayList<Component>();
    }

    public void addComponent(Component _component){
        componentsList.add(_component);
    }

    public void draw(SpriteBatch _spriteBatch){
        for(Component component: componentsList){
            component.draw(_spriteBatch);
        }
    }

}
