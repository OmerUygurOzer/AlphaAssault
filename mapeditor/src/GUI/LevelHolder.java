package GUI;



import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ingame.physics.BodyComponent;
import handlers.TextureManager;
import utilities.ObjectIO;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import level.LoadableLevel;
import level.objects.MapObject;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Omer on 1/17/2016.
 */
public class LevelHolder implements ApplicationListener{
    private Sprite cursor;

    private LoadableLevel loadableLevel;

    public static final int POINTING_TO_TILE = 0;
    public static final int POINTING_TO_ABSOLUTE = 1;

    private Palette palette;

    private OrthographicCamera orthographicCamera;
    private  Viewport viewport;

    private LayeredRenderer layeredRenderer;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch localSpriteBatch;

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGTH = 800;

    private int width;
    private int height;


    private int tileSize = 20;


    private boolean a_prev = false;
    private boolean s_prev = false;
    private boolean d_prev = false;
    private boolean w_prev = false;

    private boolean left_prev = false;
    private boolean right_prev = false;

    private Object editLock = new Object();

    private MapObject objectBrush;
    private int activeLayer;
    private int pointType;

    private int pickedTileIndex = 0;
    private boolean pickedTileActive = false;
    private Vector2 pickedTilePosition = new Vector2();

    private List<MapObject> objects = new ArrayList<MapObject>();

    private boolean drawGrids = false;
    private boolean drawLights = false;
    private boolean drawFog = false;


    TextureManager textureManager = new TextureManager();
    private TextureAtlas atlas;

    BodyComponent bodyComponent1;
    BodyComponent bodyComponent2;
    BodyComponent bodyComponent3;

    @Override
    public void create() {
        loadableLevel = new LoadableLevel();

        textureManager.initialize();
        atlas = textureManager.getAtlas();
        //Debug
        float [] polygon1 = {
                            0f,0f,
                            100f,0f,
                            100f,50f,
                            0f,50f
                            };

        float [] polygon2 = {
                            0f,0f,
                            50f,0f,
                            50f,50f,
                            0f,50f
                            };

        float [] polygon3 = {
                            0f,0f,
                            50f,0f,
                            50f,50f,
                            0f,50f
                            };

        bodyComponent1 = new BodyComponent(polygon1,new Vector2(0f,0f));
        bodyComponent2 = new BodyComponent(polygon2);
        bodyComponent3 = new BodyComponent(polygon3);
        bodyComponent1.attach(bodyComponent2,new Vector2(100f,50f));
        bodyComponent2.attach(bodyComponent3,new Vector2(150f,100f));
        bodyComponent1.translate(50,50);
        bodyComponent1.rotate(0);
        bodyComponent1.rotate(10);
        bodyComponent2.rotate(10);
        bodyComponent3.rotate(10);
        ///////////////////////////////



        width = SCREEN_WIDTH;
        height = SCREEN_HEIGTH;

        localSpriteBatch = new SpriteBatch();

        cursor = atlas.createSprite("cursor");
        cursor.setSize(20,20);

        orthographicCamera = new OrthographicCamera();
        viewport = new FitViewport(SCREEN_WIDTH,SCREEN_HEIGTH,orthographicCamera);
        orthographicCamera.position.set(SCREEN_WIDTH/2,SCREEN_HEIGTH/2,0);
        orthographicCamera.update();

        layeredRenderer = new LayeredRenderer();
        layeredRenderer.addViewport(viewport);
        layeredRenderer.addCamera(orthographicCamera);

        shapeRenderer = new ShapeRenderer();

        layeredRenderer.setRenderedLayers(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19);

    }

    public LoadableLevel getLoadableLevel() {
        return loadableLevel;
    }

    public void createNewLevel(String name,int tileSize,int width,int heigth){
        loadableLevel.setName(name);
        loadableLevel.setTileSize(tileSize);
        loadableLevel.setSize(width,heigth);
        loadableLevel.generateLevel();
        synchronized (editLock){
            this.tileSize = tileSize;
            this.width = width;
            this.height = heigth;


           if(width<=heigth){
               viewport.setWorldSize(width,width);
               orthographicCamera.position.set(width/2,width/2,0);
               orthographicCamera.update();}
            else{
               viewport.setWorldSize(heigth,heigth);
               orthographicCamera.position.set(heigth/2,heigth/2,0);
               orthographicCamera.update();}

            viewport.apply();

        }

        layeredRenderer.clear();
        loadableLevel.clear();


    }



    private void drawGrids(){
        synchronized (editLock) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
                viewport.apply();

                int borderLeft = (int) orthographicCamera.position.x - (SCREEN_WIDTH / 2);
                int borderRigth = (int) orthographicCamera.position.x + (SCREEN_WIDTH / 2);
                int borderTop = (int) orthographicCamera.position.y + (SCREEN_HEIGTH / 2);
                int borderBottom = (int) orthographicCamera.position.y - (SCREEN_HEIGTH / 2);

                for (int v = borderLeft; v <= borderRigth; v += tileSize) {
                    shapeRenderer.line(v, borderBottom, v, borderTop, Color.WHITE, Color.WHITE);
                }
                for (int h = borderBottom; h <= borderTop; h += tileSize) {
                    shapeRenderer.line(borderLeft, h, borderRigth, h, Color.WHITE, Color.WHITE);
                }

                int worldLeft = 0;
                int worldRigth = width;
                int worldTop = height;
                int worldBottom = 0;

                shapeRenderer.line(worldLeft, worldBottom, worldRigth, worldBottom, Color.MAGENTA, Color.MAGENTA);
                shapeRenderer.line(worldLeft, worldBottom, worldLeft, worldTop, Color.MAGENTA, Color.MAGENTA);
                shapeRenderer.line(worldLeft, worldTop, worldRigth, worldTop, Color.MAGENTA, Color.MAGENTA);
                shapeRenderer.line(worldRigth, worldTop, worldRigth, worldBottom, Color.MAGENTA, Color.MAGENTA);

                shapeRenderer.end();
        }
    }

    private void drawLights(){}
    private void drawFog(){}

    public void toggleGrids(){synchronized (editLock){drawGrids = !drawGrids;}}
    public void toggleLights(){synchronized (editLock){drawLights = !drawLights;}}
    public void toggleFog(){synchronized (editLock){drawFog = !drawFog;}}

    public void setPalette(Palette palette){this.palette = palette;}
    public Palette getPalette() {
        return palette;
    }

    public void setObjectBrush(File file){
        synchronized (editLock) {
            if(file==null){this.objectBrush = null; return;}
            this.objectBrush = new MapObject(file, ObjectIO.readObject(file.getAbsolutePath()));
        }
    }

    public void setObjectBrush(MapObject objectBrush){
        synchronized (editLock){
            this.objectBrush = objectBrush;
            layeredRenderer.removeObject(objectBrush.layer,objectBrush);
            loadableLevel.removeObject(objectBrush);
        }
    }

    public void clearObjectBrush(){
        synchronized (editLock){
            this.objectBrush = null;
        }
    }

    public void changeBrushFrame(int frameIndex){objectBrush.changeCurrentFrame(frameIndex);}
    public MapObject getObjectBrush(){
        return objectBrush;
    }

    public void zoomIn(){
        synchronized (editLock) {
            orthographicCamera.zoom -= 0.02;
        }
    }

    public void zoomOut(){
        synchronized (editLock) {
            orthographicCamera.zoom += 0.02;
        }
    }

    public OrthographicCamera getOrthographicCamera() {
        return orthographicCamera;
    }

    public void setActiveLayer(int activeLayer){this.activeLayer = activeLayer;}
    public void setPointType(int pointType){this.pointType = pointType;}

    public void handleInput(){
        boolean a_pressed = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean s_pressed = Gdx.input.isKeyPressed(Input.Keys.S);
        boolean d_pressed = Gdx.input.isKeyPressed(Input.Keys.D);
        boolean w_pressed = Gdx.input.isKeyPressed(Input.Keys.W);

        float pos_x = orthographicCamera.position.x;
        float pos_y = orthographicCamera.position.y;

        if((a_pressed && !a_prev)||(a_pressed && a_prev)){pos_x -= tileSize;}
        if((d_pressed && !d_prev)||(d_pressed && d_prev)){pos_x += tileSize;}
        if((w_pressed && !w_prev)||(w_pressed && w_prev)){pos_y  += tileSize;}
        if((s_pressed && !s_prev)||(s_pressed && s_prev)){pos_y  -= tileSize;}

        pos_x = pos_x>width ? width : pos_x;
        pos_x = pos_x<0 ? 0 : pos_x;

        pos_y = pos_y>height? height : pos_y;
        pos_y = pos_y<0 ? 0 : pos_y;

        orthographicCamera.position.x = pos_x;
        orthographicCamera.position.y = pos_y;

        a_prev = a_pressed;
        d_prev = d_pressed;
        w_prev = w_pressed;
        s_prev = s_pressed;


        int mouseX = Gdx.input.getX();
        int mouseY = SCREEN_HEIGTH - Gdx.input.getY();


        float screenBoundsLeft = pos_x - (orthographicCamera.zoom*orthographicCamera.viewportWidth/2);
        float screenBoundsBot  = pos_y - (orthographicCamera.zoom*orthographicCamera.viewportHeight/2);
        float screenWidth      = orthographicCamera.viewportWidth * orthographicCamera.zoom;
        float screenHeigth     = orthographicCamera.viewportHeight * orthographicCamera.zoom;


        float relativePosX = screenBoundsLeft + (((float)mouseX / (float)SCREEN_WIDTH) * screenWidth);
        float relativePosY = screenBoundsBot + (((float)mouseY / (float)SCREEN_HEIGTH) * screenHeigth);
        cursor.setCenter(relativePosX,relativePosY);

        palette.setTilePositionText(getTile(relativePosX) + "," + getTile(relativePosY));
        boolean left_click = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        boolean right_click = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);


        if(objectBrush!=null) {
            objectBrush.setImagePosition(relativePosX,relativePosY);
            objectBrush.setRelativePosition(new Vector2(relativePosX,relativePosY));
            if(left_click && !left_prev){
               insertObject(objectBrush.clone(),pointType,(int)relativePosX,(int)relativePosY,activeLayer);
            }
            if(right_click && !right_prev){
                palette.clearBrush();
                pickedTileActive = false;
                palette.clearTile();
            }

        }else{
            if(left_click && !left_prev){
              pickTile(relativePosX,relativePosY);
            }

            if(right_click && !right_prev){
                pickedTileActive = false;
                palette.clearTile();
            }
        }


        left_prev = left_click;
        right_prev = right_click;



        }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void render() {
        synchronized (editLock) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


            handleInput();


            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
            viewport.apply();
            shapeRenderer.polygon(bodyComponent1.getBounds().getTransformedVertices());
            shapeRenderer.polygon(bodyComponent2.getBounds().getTransformedVertices());
            shapeRenderer.polygon(bodyComponent3.getBounds().getTransformedVertices());
            shapeRenderer.end();

            layeredRenderer.draw();

            if(drawGrids){drawGrids();}
            if(drawLights){drawLights();}
            if(drawFog){drawFog();}

            localSpriteBatch.begin();
            localSpriteBatch.setProjectionMatrix(viewport.getCamera().combined);
            viewport.apply();
            if(objectBrush!=null) {
                objectBrush.draw(localSpriteBatch);
            }
            localSpriteBatch.end();

            if(pickedTileActive){renderTilePicker();}
            localSpriteBatch.begin();
            localSpriteBatch.setProjectionMatrix(viewport.getCamera().combined);
            viewport.apply();
            cursor.draw(localSpriteBatch);
            localSpriteBatch.end();



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
        layeredRenderer.dispose();
        shapeRenderer.dispose();
    }



    private int getTile(float tile){
        return Math.round(tile/tileSize);
    }

    private void pickTile(float x, float y){
        int tileX = Math.round((x - (x%tileSize))/tileSize);
        int tileY = Math.round((y - (y%tileSize))/tileSize);
        if(!loadableLevel.fits(tileX,tileY)){return;}
        pickedTileIndex = tileX + tileY*(width/tileSize);

        int centerX = tileX * tileSize;
        int centerY = tileY * tileSize;
        pickedTilePosition.x = centerX;
        pickedTilePosition.y = centerY;

        pickedTileActive = true;
        palette.addObjects(loadableLevel.getTile(tileX,tileY));
    }
    private void renderTilePicker(){
        synchronized (editLock){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
            viewport.apply();
            shapeRenderer.rect(pickedTilePosition.x,pickedTilePosition.y,tileSize,tileSize,Color.RED,Color.RED,Color.RED,Color.RED);
            shapeRenderer.end();
        }
    }


    public int getTileSize() {return tileSize;}

    private void insertObject(MapObject object, int pointing, int x, int y, int layer){
        int offsetX = (x - (x%tileSize))+(tileSize/2);
        int offsetY = (y - (y%tileSize))+(tileSize/2);
        int tileX = (x - (x%tileSize))/tileSize;
        int tileY = (y - (y%tileSize))/tileSize;
        if(!loadableLevel.fits(tileX,tileY)){return;}

        switch (pointing){
            case POINTING_TO_TILE:
                object.setSize(tileSize,tileSize);
                object.setRelativePosition(new Vector2(offsetX,offsetY));
                object.setImagePosition(offsetX,offsetY);
                object.layer = layer;
                layeredRenderer.addObject(layer,object);
                objects.add(object);
                loadableLevel.addObject(object,tileX,tileY);

                break;
            case POINTING_TO_ABSOLUTE:
                object.setRelativePosition(new Vector2(x,y));
                object.setImagePosition(x,y);
                object.layer = layer;
                layeredRenderer.addObject(layer,object);
                objects.add(object);
                loadableLevel.addObject(object,tileX,tileY);
                break;
            default:
                //Do noting
                break;
        }



    }


}
