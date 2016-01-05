package core.graphics.GUI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import core.Resources;
import core.inputs.Inputs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 1/3/2016.
 */
public class TabSelector extends Component {

    private static final int MAX_TABS = 4;

    private static final int TAB_SIZE = 40;

    private static final int ITEM_SIZE = 60;
    private static final int PADDING = 10;

    private static final int WIDTH = 200;
    private static final int HEIGHT = 600;

    private Vector2 ITEM_START_POINT;
    private Vector2 TAB_START_POINT;

    public MapHolder mapHolder;

    private class Tab{
        public int tabNumber;
        public Vector2 center;
        public Sprite icon;
        public Sprite selectorActive;
        public Sprite selectorInactive;
        public List<Item> itemList;
        public boolean isActive;
        public Tab(Sprite _icon,int _tabNumber){
                        icon = _icon;
                        tabNumber = _tabNumber;
                        itemList = new ArrayList<Item>();
                        isActive = false;
                        selectorActive = new Sprite(Resources.getTextureRegions(Resources.YELLOW_SELECTOR)[0][0]);
                        selectorActive.setSize(TAB_SIZE,TAB_SIZE);
                        selectorInactive = new Sprite(Resources.getTextureRegions(Resources.RED_SELECTOR)[0][0]);
                        selectorInactive.setSize(TAB_SIZE,TAB_SIZE);

        }
        public void draw(SpriteBatch _spriteBatch){

            icon.draw(_spriteBatch);
            if(isActive){
                selectorActive.setCenter(center.x,center.y);
                selectorActive.draw(_spriteBatch);
                for(Item item : itemList){
                    item.icon.draw(_spriteBatch);
                }
            }else{
                selectorInactive.setCenter(center.x,center.y);
                selectorInactive.draw(_spriteBatch);
            }


        }

    }

    private class Item{
        public int entitiyID;
        public Vector2 center;
        public Sprite icon;
    }

    private int tabCount;
    private int activeTab;
    private int activeItem;

    private Map<Integer,Tab> tabMap;

    private Sprite panel;

    public boolean[] tabsToggled;

    public TabSelector(float _x, float _y) {
        super(_x, _y, WIDTH, HEIGHT);
        tabsToggled = new boolean[]{false,false,false,false,false};

        TAB_START_POINT = new Vector2(_x +PADDING + TAB_SIZE/2, _y - PADDING - TAB_SIZE/2);
        ITEM_START_POINT = new Vector2(_x + WIDTH/2,_y - (TAB_SIZE + ITEM_SIZE));

        tabCount = 0;
        activeTab = 0;

        tabMap = new HashMap<Integer, Tab>();

        panel = new Sprite(Resources.getTextureRegions(Resources.TAB_PANEL)[0][0]);
        panel.setSize(WIDTH,HEIGHT);
        panel.setCenter(_x + WIDTH/2 ,_y - HEIGHT/2);

        Sprite mapfeatureIcon = new Sprite(Resources.getTextureRegions(Resources.MAP_FEATURES_ICON)[0][0]);
        Sprite unitsIcon = new Sprite(Resources.getTextureRegions(Resources.UNITS_ICON)[0][0]);
        Sprite spawnersIcon = new Sprite(Resources.getTextureRegions(Resources.SPAWNERS_ICON)[0][0]);

        addTab(mapfeatureIcon);
        addTab(unitsIcon);
        addTab(spawnersIcon);

        Sprite bush = new Sprite(Resources.getTextureRegions(Resources.BUSHES)[0][1]);
        Sprite crate = new Sprite(Resources.getTextureRegions(Resources.CRATES)[0][1]);
        Sprite river = new Sprite(Resources.getTextureRegions(Resources.WATER)[0][1]);
        Sprite rocks = new Sprite(Resources.getTextureRegions(Resources.ROCKS)[0][0]);
        Sprite trees = new Sprite(Resources.getTextureRegions(Resources.TREES)[0][0]);

        addItem(0,bush,24);
        addItem(0,crate,23);
        addItem(0,river,26);
        addItem(0,rocks,25);
        addItem(0,trees,22);


    }

    public void setMapHolder(MapHolder _mapHolder){mapHolder = _mapHolder;}

    private void addTab(Sprite _icon){
        _icon.setSize(TAB_SIZE,TAB_SIZE);
        _icon.setCenter(TAB_START_POINT.x + PADDING  + (tabCount*(TAB_SIZE+PADDING)),TAB_START_POINT.y);
        Tab tab = new Tab(_icon,tabCount);
        tab.center = new Vector2(TAB_START_POINT.x + PADDING  + (tabCount*(TAB_SIZE+PADDING)),TAB_START_POINT.y);
        tabMap.put(tabCount,tab);
        tabCount++;
    }

    private void addItem(int _tab,Sprite _icon,int _entityID){
        int itemCount = tabMap.get(_tab).itemList.size();

        Item item = new Item();
        item.entitiyID = _entityID;
        item.center = new Vector2(ITEM_START_POINT.x,ITEM_START_POINT.y - (itemCount * ITEM_SIZE));
        item.icon = _icon;
        item.icon.setCenter(item.center.x,item.center.y);
        item.icon.setSize(ITEM_SIZE,ITEM_SIZE);
        tabMap.get(_tab).itemList.add(item);
    }

    @Override
    public void draw(SpriteBatch _spriteBatch) {
        _spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        viewport.apply();
        _spriteBatch.begin();
        panel.draw(_spriteBatch);

       for(Tab tab:tabMap.values()){
         tab.draw(_spriteBatch);
       }


        _spriteBatch.end();
    }

    @Override
    public void receiveInput() {
            for(Vector2 click: Inputs.getInputTouch().values()){
                for(Tab tab : tabMap.values()){
                    boolean fitsX = click.x < tab.center.x + TAB_SIZE/2  && click.x > tab.center.x - TAB_SIZE/2;
                    boolean fitsY = click.y < tab.center.y + TAB_SIZE/2  && click.y > tab.center.y - TAB_SIZE/2;
                    if(fitsX && fitsY){
                       tabsToggled[tab.tabNumber] = true;
                        activeTab = tab.tabNumber;

                    }
                }
            }

        boolean itemWasSelected = false;
        for(Vector2 click: Inputs.getInputTouch().values()){
            for(Item item : tabMap.get(activeTab).itemList){
                boolean fitsX = click.x < item.center.x + ITEM_SIZE/2  && click.x > item.center.x -ITEM_SIZE/2;
                boolean fitsY = click.y < item.center.y + ITEM_SIZE/2   && click.y > item.center.y - ITEM_SIZE/2;
                if(fitsX && fitsY){
                    activeItem = item.entitiyID;
                    itemWasSelected = true;
                }
            }
        }

        if(itemWasSelected) {
            mapHolder.setSelection(activeItem);
        }

        for(Tab tab : tabMap.values()){
            if (tab.tabNumber !=activeTab){
                tab.isActive = false;
            }else{
                tab.isActive = true;
            }
        }

        for(int i = 0; i < MAX_TABS ; i++){
           tabsToggled[i] =false;
        }
    }
}
