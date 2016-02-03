package GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import org.lwjgl.Sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Created by Omer on 1/17/2016.
 */
public class MapEditor extends JFrame implements WindowListener,ActionListener,ComponentListener{
    private static final String TITLE = "EnJine2D LevelEditor";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private JDesktopPane mainpane;
    private JInternalFrame levelHolderFrame;
    private ObjectBrushHolder objectBrushHolder;
    private ObjectHolder objectHolder;
    private ToolsHolder toolsHolder;
    private TileHolder tileHolder;

    private LwjglCanvas canvas;
    private LevelHolder levelHolder;


    private JMenuItem newLevel;
    private JMenuItem loadLevel;
    private JMenuItem saveLevel;
    private JMenuItem exit;

    private JMenuItem basePalette;
    private JMenuItem featurePalette;
    private JMenuItem objectPalette;

    private LocalSettings localSettings;

    public MapEditor(LocalSettings localSettings) throws HeadlessException {
        setTitle(TITLE);
        setSize(WIDTH,HEIGHT);


        this.localSettings = localSettings;


        mainpane = new JDesktopPane();
        mainpane.setSize(WIDTH,HEIGHT);
        mainpane.setBackground(Color.GRAY);
        setContentPane(mainpane);

        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.width  = WIDTH;
        configuration.height = HEIGHT;

        levelHolder = new LevelHolder();
        final LwjglCanvas lwjglCanvas = new LwjglCanvas(levelHolder,configuration);
        canvas = lwjglCanvas;
        levelHolderFrame = new JInternalFrame("EnJine2D Map",false,false,false,true);
        levelHolderFrame.setSize(WIDTH,HEIGHT);
        levelHolderFrame.setVisible(true);


        objectHolder = new ObjectHolder("Object",true,false,true,true);
        objectHolder.setVisible(true);


        objectBrushHolder = new ObjectBrushHolder("Object Brush",true,false,true,true);
        objectBrushHolder.setVisible(true);



        toolsHolder = new ToolsHolder("Tools",true,false,true,true);
        toolsHolder.setLevelHolder(levelHolder);
        toolsHolder.setVisible(true);

        tileHolder = new TileHolder("Tile Content",true,false,true,true);
        tileHolder.setVisible(true);



        objectBrushHolder.setLevelHolder(levelHolder);
        objectBrushHolder.setObjectHolder(objectHolder);
        levelHolder.setObjectBrushHolder(objectBrushHolder);
        levelHolder.setTileContentHolder(tileHolder);
        objectHolder.setLevelHolder(levelHolder);
        tileHolder.setLevelHolder(levelHolder);

        getContentPane().add(objectHolder);
        getContentPane().add(toolsHolder);
        getContentPane().add(objectBrushHolder);
        getContentPane().add(levelHolderFrame);
        getContentPane().add(tileHolder);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                levelHolderFrame.getContentPane().add(lwjglCanvas.getCanvas());
                setVisible(true);
            }
        });

        loadSettings();

        levelHolderFrame.addComponentListener(this);
        objectHolder.addComponentListener(this);
        objectBrushHolder.addComponentListener(this);
        toolsHolder.addComponentListener(this);
        tileHolder.addComponentListener(this);

        addWindowListener(this);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        JMenuBar jMenuBar = new JMenuBar();



        JMenu file = new JMenu("File");
        newLevel = new JMenuItem("New LoadableLevel...");         newLevel.addActionListener(this);
        loadLevel = new JMenuItem("Load LoadableLevel...");       loadLevel.addActionListener(this);
        saveLevel = new JMenuItem("Save LoadableLevel...");       saveLevel.addActionListener(this);
        exit = new JMenuItem("Exit");                     exit.addActionListener(this);

        JMenu tools = new JMenu("Tools");
        JMenu popToolWindow = new JMenu("Pop Tool Window");
        basePalette = new JMenuItem("Base Palette");      basePalette.addActionListener(this);
        featurePalette = new JMenuItem("Feature Palette");featurePalette.addActionListener(this);
        objectPalette = new JMenuItem("Object Palette");  objectPalette.addActionListener(this);

        JMenu help = new JMenu("Help");

        popToolWindow.add(basePalette);
        popToolWindow.add(featurePalette);
        popToolWindow.add(objectPalette);
        tools.add(popToolWindow);



        file.add(newLevel);
        file.add(loadLevel);
        file.add(saveLevel);
        file.add(exit);

        jMenuBar.add(file);
        jMenuBar.add(tools);
        setJMenuBar(jMenuBar);


        revalidate();


    }

    @Override
    public void windowClosing(WindowEvent e) {
        canvas.stop();
        e.getWindow().dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = toItem(e);
        if(item == newLevel){
            NewLevelDialog newLevelDialog = new NewLevelDialog(levelHolder);
            newLevelDialog.initialize();
            return;
        }


    }

    private static JMenuItem toItem(ActionEvent e){
        return (JMenuItem)e.getSource();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        saveSettings();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        saveSettings();
    }

    @Override
    public void componentShown(ComponentEvent e) {
        saveSettings();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        saveSettings();
    }

    private void saveSettings(){
        if(localSettings!=null) {
            localSettings.objectDirectory = levelHolder.getObjectBrushHolder().getDirectoryPath();

            localSettings.mainWidth = this.getWidth();
            localSettings.mainHeight = this.getHeight();

            localSettings.lhX = levelHolderFrame.getX();
            localSettings.lhY = levelHolderFrame.getY();
            localSettings.lhWidth = levelHolderFrame.getWidth();
            localSettings.lhHeigth = levelHolderFrame.getHeight();

            localSettings.obhX = objectBrushHolder.getX();
            localSettings.obhY = objectBrushHolder.getY();
            localSettings.obhWidth = objectBrushHolder.getWidth();
            localSettings.obhHeigth = objectBrushHolder.getHeight();

            localSettings.ohX = objectHolder.getX();
            localSettings.ohY = objectHolder.getY();
            localSettings.ohWidth = objectHolder.getWidth();
            localSettings.ohHeigth = objectHolder.getHeight();

            localSettings.tX = toolsHolder.getX();
            localSettings.tY = toolsHolder.getY();
            localSettings.tWidth = toolsHolder.getWidth();
            localSettings.tHeight = toolsHolder.getHeight();

            localSettings.tiX = tileHolder.getX();
            localSettings.tiY = tileHolder.getY();
            localSettings.tiWidth = tileHolder.getWidth();
            localSettings.tiHeigth = tileHolder.getHeight();


            localSettings.store();
        }else {
            localSettings = new LocalSettings();
        }
    }

    private void loadSettings(){
        if (localSettings!=null){
            levelHolderFrame.setBounds(localSettings.lhX,localSettings.lhY,localSettings.lhWidth,localSettings.lhHeigth);
            objectBrushHolder.setBounds(localSettings.obhX,localSettings.obhY,localSettings.obhWidth,localSettings.obhHeigth);
            objectHolder.setBounds(localSettings.ohX,localSettings.ohY,localSettings.ohWidth,localSettings.ohHeigth);
            toolsHolder.setBounds(localSettings.tX,localSettings.tY,localSettings.tWidth,localSettings.tHeight);
            tileHolder.setBounds(localSettings.tiX,localSettings.tiY,localSettings.tiWidth,localSettings.tiHeigth);
            setSize(localSettings.mainWidth,localSettings.mainHeight);
            if(!localSettings.objectDirectory.equals(""))objectBrushHolder.loadObjects(localSettings.objectDirectory);
        }
    }

}
