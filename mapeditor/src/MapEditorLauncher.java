import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import core.enjineutils.EditPanel;
import core.enjineutils.MapPanel;


import javax.swing.*;


/**
 * Created by Omer on 12/28/2015.
 */
public class MapEditorLauncher extends JFrame{
    private static final String TITLE = "EnJine2D Level Editor";

    private static final int APP_SIZE_WIDTH = 1224;
    private static final int APP_SIZE_HEIGHT = 828;

    private MapPanel mapPanel;
    private EditPanel editPanel;

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
        setResizable(false);

        mapPanel = new MapPanel();
        editPanel = new EditPanel();


        setLayout(null);
        setSize(APP_SIZE_WIDTH,APP_SIZE_HEIGHT);

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu map  = new JMenu("Map");

        JMenuItem loadFile = new JMenuItem("Load Level");
        JMenuItem saveFile = new JMenuItem("Save Level");
        JMenuItem exit     = new JMenuItem("Exit");

        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem redo = new JMenuItem("Redo");

        JMenuItem setSize        = new JMenuItem("Set Size:");
        JMenuItem generateRandom = new JMenuItem("Generate random map");
        JMenuItem showTileGrids  = new JMenuItem("Show tile grids");

        file.add(loadFile);
        file.add(saveFile);
        file.add(exit);

        edit.add(undo);
        edit.add(redo);

        map.add(setSize);
        map.add(generateRandom);
        map.add(showTileGrids);

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(map);


        setJMenuBar(menuBar);

        add(mapPanel);
        add(editPanel);
    }


}

