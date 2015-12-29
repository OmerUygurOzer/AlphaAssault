import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import core.System;
import core.main.MapEditor;

/**
 * Created by Omer on 12/28/2015.
 */
public class MapEditorLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = System.VIRTUAL_WIDTH;
        config.height = System.VIRTUAL_HEIGHT;
        new LwjglApplication(new MapEditor(), config);
    }
}
