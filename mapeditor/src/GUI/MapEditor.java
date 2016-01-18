package GUI;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


/**
 * Created by Omer on 1/17/2016.
 */
public class MapEditor extends JFrame implements WindowListener,ActionListener{
    private static final String TITLE = "EnJine2D LevelEditor";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private LwjglCanvas canvas;


    private JMenuItem newLevel;
    private JMenuItem loadLevel;
    private JMenuItem saveLevel;
    private JMenuItem exit;

    private JMenuItem basePalette;
    private JMenuItem featurePalette;
    private JMenuItem objectPalette;



    public MapEditor() throws HeadlessException {
        setTitle(TITLE);
        setSize(WIDTH,HEIGHT);
        addWindowListener(this);




        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.width  = WIDTH;
        configuration.height = HEIGHT;
        final LwjglCanvas lwjglCanvas = new LwjglCanvas(new LevelHolder(),configuration);
        canvas = lwjglCanvas;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               getContentPane().add(lwjglCanvas.getCanvas());
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
            NewLevelDialog newLevelDialog = new NewLevelDialog();
            newLevelDialog.initialize();
            return;
        }


    }

    private JMenuItem toItem(ActionEvent e){
        return (JMenuItem)e.getSource();
    }
}
