package GUI;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;

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
    private ObjectBrushHolder objectBrushHolder;
    private ObjectHolder objectHolder;

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

        addWindowListener(this);

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
        final JInternalFrame jInternalFrame = new JInternalFrame("EnJine2D Map",false,false,false,false);
        jInternalFrame.addComponentListener(this);
        jInternalFrame.setSize(WIDTH,HEIGHT);
        jInternalFrame.setVisible(true);


        objectHolder = new ObjectHolder("Object Holder",true,false,true,false);
        objectHolder.addComponentListener(this);
        objectHolder.setLevelHolder(levelHolder);
        objectHolder.setVisible(true);
        getContentPane().add(objectHolder);

        objectBrushHolder = new ObjectBrushHolder("Object Brush Holder",true,false,true,false);
        objectBrushHolder.addComponentListener(this);
        objectBrushHolder.setLevelHolder(levelHolder);
        objectBrushHolder.setObjectHolder(objectHolder);

        levelHolder.setObjectBrushHolder(objectBrushHolder);


        objectBrushHolder.setVisible(true);
        getContentPane().add(objectBrushHolder);

        getContentPane().add(jInternalFrame);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               jInternalFrame.getContentPane().add(lwjglCanvas.getCanvas());
               setVisible(true);
            }
        });
    }

    @Override
    public void windowOpened(WindowEvent e) {
        JMenuBar jMenuBar = new JMenuBar();



        JMenu file = new JMenu("File");
        newLevel = new JMenuItem("New Level...");         newLevel.addActionListener(this);
        loadLevel = new JMenuItem("Load Level...");       loadLevel.addActionListener(this);
        saveLevel = new JMenuItem("Save Level...");       saveLevel.addActionListener(this);
        exit = new JMenuItem("Exit");                     exit.addActionListener(this);

        JMenu tools = new JMenu("Tools");
        JMenu popToolWindow = new JMenu("Pop Tool Window");
        basePalette = new JMenuItem("Base Palette");      basePalette.addActionListener(this);
        featurePalette = new JMenuItem("Feature Palette");featurePalette.addActionListener(this);
        objectPalette = new JMenuItem("Object Palette");  objectPalette.addActionListener(this);

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

            localSettings.store();
    }
}
