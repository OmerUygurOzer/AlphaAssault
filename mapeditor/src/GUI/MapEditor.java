package GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import handlers.TextureManager;
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
    private Palette palette;

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


        palette = new Palette("Palette",true, false,true,true);
        palette.setVisible(true);
        palette.setLevelHolder(levelHolder);
        levelHolder.setPalette(palette);

        getContentPane().add(palette);
        getContentPane().add(levelHolderFrame);


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                levelHolderFrame.getContentPane().add(lwjglCanvas.getCanvas());
                setVisible(true);
            }
        });

        loadSettings();

        levelHolderFrame.addComponentListener(this);
        palette.addComponentListener(this);

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
            localSettings.objectDirectory = levelHolder.getPalette().getDirectoryPath();

            localSettings.mainWidth = this.getWidth();
            localSettings.mainHeight = this.getHeight();

            localSettings.paletteX = palette.getX();
            localSettings.paletteY = palette.getY();
            localSettings.paletteWidth = palette.getWidth();
            localSettings.paletteHeight = palette.getHeight();

            localSettings.levelHolderX = levelHolderFrame.getX();
            localSettings.levelHolderY = levelHolderFrame.getY();
            localSettings.levelHolderWidth = levelHolderFrame.getWidth();
            localSettings.levelHolderHeight = levelHolderFrame.getHeight();


            localSettings.store();
        }else {
            localSettings = new LocalSettings();
        }
    }

    private void loadSettings(){
        if (localSettings!=null){
            levelHolderFrame.setBounds(localSettings.levelHolderX,localSettings.levelHolderY,localSettings.levelHolderWidth,localSettings.levelHolderHeight);
            palette.setBounds(localSettings.paletteX,localSettings.paletteY,localSettings.paletteWidth,localSettings.paletteHeight);
            setSize(localSettings.mainWidth,localSettings.mainHeight);
            if(!localSettings.objectDirectory.equals(""))palette.loadObjects(localSettings.objectDirectory);
        }
    }

}
