import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import core.enjineutils.EditPanel;
import core.enjineutils.LevelPanel;



import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;


/**
 * Created by Omer on 12/28/2015.
 */
public class MapEditorLauncher extends JFrame implements ActionListener{
    private static final String TITLE = "EnJine2D Level Editor";

    private static final int APP_SIZE_WIDTH = 1224;
    private static final int APP_SIZE_HEIGHT = 828;

    private LevelPanel levelPanel;
    private EditPanel editPanel;

    JMenuItem newLevel;
    JMenuItem loadLevel;
    JMenuItem saveLevel;
    JMenuItem exit;

    JMenuItem undo;
    JMenuItem redo;

    JMenuItem showTileGrids;

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

        editPanel = new EditPanel();
        levelPanel = new LevelPanel(editPanel);



        setLayout(null);
        setSize(APP_SIZE_WIDTH,APP_SIZE_HEIGHT);

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu map  = new JMenu("Map");

        newLevel  = new JMenuItem("New Level");
        loadLevel = new JMenuItem("Load Level");
        saveLevel = new JMenuItem("Save Level");
        exit     = new JMenuItem("Exit");

        undo = new JMenuItem("Undo");
        redo = new JMenuItem("Redo");

        showTileGrids  = new JMenuItem("Show tile grids");

        newLevel.addActionListener(this);file.add(newLevel);
        loadLevel.addActionListener(this);file.add(loadLevel);
        saveLevel.addActionListener(this);file.add(saveLevel);
        exit.addActionListener(this);file.add(exit);


        undo.addActionListener(this);edit.add(undo);
        redo.addActionListener(this);edit.add(redo);

        showTileGrids.addActionListener(this);map.add(showTileGrids);

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(map);

        setJMenuBar(menuBar);

        add(levelPanel);
        add(editPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem selection = (JMenuItem)e.getSource();
        if(selection.equals(newLevel)){
            System.out.println("ASD");
            return;
        }
    }
}

