import com.badlogic.gdx.math.Vector2;
import core.MapPanel;

import javax.swing.*;


/**
 * Created by Omer on 12/28/2015.
 */
public class MapEditorLauncher extends JFrame {
    private static final String TITLE = "EnJine2D Level Editor";
    private static final Vector2 APP_SIZE = new Vector2(1600,800);

    private MapPanel mapPanel;

    public static void main(String[] args) {
        final MapEditorLauncher app = new MapEditorLauncher();
        //
        SwingUtilities.invokeLater (
                new Runnable() {
                    public void run() {
                        app.setVisible(true);
                    }
                }
        );
    }
    public MapEditorLauncher() {
        super(TITLE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mapPanel = new MapPanel();

        mapPanel.setLayout(null);
        setLayout(null);

        setBounds(0,0,(int)APP_SIZE.x,(int)APP_SIZE.y);


    }

}

