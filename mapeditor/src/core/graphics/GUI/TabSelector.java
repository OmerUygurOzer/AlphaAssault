package core.graphics.GUI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import core.Resources;
import core.System;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 1/3/2016.
 */
public class TabSelector extends Component {

    private static final int TAB_SIZE = 40;

    private static final int ITEM_SIZE = 60;
    private static final int ITEM_PADDING = 10;

    private static final int WIDTH = 160;
    private static final int HEIGHT = 600;

    private Vector2 ITEM_START_POINT;


    private class Tab{
        public int tabNumber;
        public Sprite icon;
        public List<Item> itemList;
        public Tab(Sprite _icon,int _tabNumber){
                        icon = _icon;
                        tabNumber = _tabNumber;
                        itemList = new ArrayList<Item>();
        }
    }

    private class Item{
        public Vector2 center;
        public Sprite icon;
    }

    private int tabCount;
    private int activeTab;

    private Map<Integer,Tab> tabMap;



    public TabSelector(float _x, float _y) {
        super(_x, _y, WIDTH, HEIGHT);

        ITEM_START_POINT = new Vector2(_x+95,_y+100);

        tabCount = 0;
        activeTab = 0;

        tabMap = new HashMap<Integer, Tab>();

        Sprite mapfeatureIcon = new Sprite(Resources.getTextureRegions(Resources.CRATES)[0][0]);
        addTab(mapfeatureIcon);

    }

    private void addTab(Sprite _icon){
        _icon.setSize(TAB_SIZE,TAB_SIZE);
        _icon.setCenter(center.x, System.VIRTUAL_HEIGHT - center.y);
        Tab tab = new Tab(_icon,tabCount);
        tabMap.put(tabCount,tab);
        tabCount++;
    }

    private void addItem(int _tab,Sprite _icon){
        int itemCount = tabMap.get(_tab).itemList.size();

        Item item = new Item();

        item.center = new Vector2(ITEM_START_POINT.x,ITEM_START_POINT.y + itemCount * ITEM_SIZE);
        item.icon = _icon;

        tabMap.get(_tab).itemList.add(item);
    }

    @Override
    public void draw(SpriteBatch _spriteBatch) {
        _spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        _spriteBatch.begin();
       for(Tab tab:tabMap.values()){
           tab.icon.draw(_spriteBatch);
       }
        for(Item item: tabMap.get(activeTab).itemList){
            item.icon.draw(_spriteBatch);
        }

        _spriteBatch.end();
    }

    @Override
    public void receiveInput() {

    }
}
