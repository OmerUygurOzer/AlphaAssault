package GUI;

import utilities.ObjectIO;
import ingame.objects.RawObject;
import level.LoadableLevel;
import level.objects.MapObject;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 2/5/2016.
 */
public class Palette extends JInternalFrame implements ActionListener,ListSelectionListener {
    private LevelHolder levelHolder;

    //Object Brush
    private JLabel selectedBrush = new JLabel();
    private JButton directory = new JButton("Directory");
    private JLabel tilePosition = new JLabel("Tile:"+"0,0");

    private JScrollPane listHolderBrush = new JScrollPane();
    private JList<String> objectsList = new JList<String>();
    private DefaultListModel<String> listModel = new DefaultListModel<String>();

    private JLabel widthLabel = new JLabel("W:");
    private JLabel heigthLabel = new JLabel("H:");
    private JButton widthUp = new JButton("+");
    private JButton widthDown = new JButton("-");
    private JButton heigthUp = new JButton("+");
    private JButton heigthDown = new JButton("-");
    private JComboBox<Integer> depthSelector = new JComboBox<Integer>();
    private JButton fitTile = new JButton("Fit Tile");
    private JComboBox<String> pointTypeSelecetor = new JComboBox<String>();

    private List<File> objectFilesFiltered;


    private String directoryPath = "";


    //Object Holder
    private JScrollPane attributePane;
    private JList<String> attributeList;
    private DefaultListModel<String> attributeModel;
    private JComboBox<Integer> frames;
    private JLabel imageHolder;

    private File objectFile;
    private RawObject rawObject;

    //Tile Holder
    private JScrollPane listHolderTiles = new JScrollPane();
    private JList<String> objectNamesList = new JList<String>();
    private DefaultListModel<String> objectNames = new DefaultListModel<String>();
    private List<MapObject> objects = new ArrayList<MapObject>();


    //Tools
    private JButton zoomIn = new JButton("+");
    private JLabel zoom = new JLabel("Z:");
    private JButton zoomOut = new JButton("-");

    private JButton toggleGrids = new JButton("Toggle Grids");
    private JButton toggleLights = new JButton("Toggle Lights");
    private JButton toggleFog = new JButton("Toggle Fogs");

    private LoadableLevel loadableLevel;


    public Palette(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
        super(title, resizable, closable, maximizable, iconifiable);
        setLayout(null);
        setSize(200,800);

        //Object Brush
        objectFilesFiltered = new ArrayList<File>();

        selectedBrush.setBounds(0,0,100,20);
        selectedBrush.setText("------------------");

        tilePosition.setBounds(0,20,100,20);

        directory.setBounds(0,40,100,20);
        directory.addActionListener(this);


        listHolderBrush.setViewportView(objectsList);
        listHolderBrush.setBounds(0,60,100,180);

        objectsList.addListSelectionListener(this);
        objectsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        objectsList.setModel(listModel);

        heigthDown.setBounds(100,0,45,20);
        heigthLabel.setBounds(145,0,50,20);
        heigthUp.setBounds(195,0,45,20);

        widthDown.setBounds(100,20,45,20);
        widthLabel.setBounds(145,20,50,20);
        widthUp.setBounds(195,20,45,20);

        depthSelector.setBounds(100,50,40,20);
        fitTile.setBounds(140,50,100,20);

        heigthUp.addActionListener(this);
        heigthDown.addActionListener(this);

        widthUp.addActionListener(this);
        widthDown.addActionListener(this);

        for(int i = 0;i < 20; i++){
            depthSelector.addItem(i);
        }
        depthSelector.addActionListener(this);
        fitTile.addActionListener(this);

        pointTypeSelecetor.setBounds(100,80,140,20);
        pointTypeSelecetor.addItem("To Tile");
        pointTypeSelecetor.addItem("To Absolute Point");
        pointTypeSelecetor.addActionListener(this);
        /////////////////////////////////////////////////////////////

        //Object Holder

        attributePane = new JScrollPane();
        attributeList = new JList<String>();
        attributeModel = new DefaultListModel<String>();
        attributePane.setBounds(0,250,120,240);
        attributeList.setModel(attributeModel);
        attributePane.setViewportView(attributeList);

        frames = new JComboBox<Integer>();
        frames.setBounds(120,250,40,20);
        frames.addActionListener(this);

        imageHolder = new JLabel();
        imageHolder.setBounds(120,270,100,100);
        ////////////////////////////////////////////////////////////////////

        //Tile Holder
        listHolderTiles.setBounds(0,360,120,180);
        objectNamesList.setModel(objectNames);
        listHolderTiles.setViewportView(objectNamesList);
        objectNamesList.addListSelectionListener(this);
        ////////////////////////////////////////////////////////////////////

        //Tool Holder

        zoomOut.setBounds(40,550,45,20);
        zoom.setBounds(95,550,60,20);
        zoomIn.setBounds(145,550,45,20);

        toggleGrids.setBounds(40,570,150,20);
        toggleLights.setBounds(40,590,150,20);
        toggleFog.setBounds(40,610,150,20);



        zoomOut.addActionListener(this);
        zoomIn.addActionListener(this);
        toggleGrids.addActionListener(this);
        toggleLights.addActionListener(this);
        toggleFog.addActionListener(this);

        add(zoomOut);
        add(zoom);
        add(zoomIn);
        add(toggleGrids);
        add(toggleLights);
        add(toggleFog);


        add(listHolderTiles);

        add(frames);
        add(attributePane);
        add(imageHolder);

        add(heigthDown);
        add(heigthLabel);
        add(heigthUp);

        add(widthDown);
        add(widthLabel);
        add(widthUp);

        add(depthSelector);
        add(fitTile);
        add(pointTypeSelecetor);

        add(directory);
        add(selectedBrush);
        add(tilePosition);
        add(listHolderBrush);




    }

    public void setLevelHolder(LevelHolder levelHolder){
        this.levelHolder = levelHolder;
        this.loadableLevel = levelHolder.getLoadableLevel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(directory)) {
            String path;
            JFileChooser loader = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "EnJine2D Files", "enjo");
            loader.setFileFilter(filter);
            int lVal = loader.showOpenDialog(this);
            if (lVal == JFileChooser.APPROVE_OPTION) {
                path = loader.getCurrentDirectory().toString();
                directoryPath = path;
                loadObjects(path);
                return;
            }
        }
        if(levelHolder.getObjectBrush()!=null) {
            if (e.getSource().equals(widthDown)) {
                levelHolder.getObjectBrush().setSize(levelHolder.getObjectBrush().getCurrentFrame().getWidth() - 2f, levelHolder.getObjectBrush().getCurrentFrame().getHeight());
            }
            if (e.getSource().equals(widthUp)) {
                levelHolder.getObjectBrush().setSize(levelHolder.getObjectBrush().getCurrentFrame().getWidth() + 2f, levelHolder.getObjectBrush().getCurrentFrame().getHeight());
            }
            if (e.getSource().equals(heigthDown)) {
                levelHolder.getObjectBrush().setSize(levelHolder.getObjectBrush().getCurrentFrame().getWidth(), levelHolder.getObjectBrush().getCurrentFrame().getHeight() - 2f);
            }
            if (e.getSource().equals(heigthUp)) {
                levelHolder.getObjectBrush().setSize(levelHolder.getObjectBrush().getCurrentFrame().getWidth(), levelHolder.getObjectBrush().getCurrentFrame().getHeight() + 2f);
            }
            widthLabel.setText("W:"+levelHolder.getObjectBrush().getCurrentFrame().getWidth());
            heigthLabel.setText("H:"+levelHolder.getObjectBrush().getCurrentFrame().getHeight());
            repaint();
        }

        if(e.getSource().equals(depthSelector)){
            levelHolder.setActiveLayer(depthSelector.getSelectedIndex());
        }

        if(e.getSource().equals(fitTile)){
            int tileSize = levelHolder.getTileSize();
            levelHolder.getObjectBrush().setSize(tileSize,tileSize);
            widthLabel.setText("W:"+levelHolder.getObjectBrush().getCurrentFrame().getWidth());
            heigthLabel.setText("H:"+levelHolder.getObjectBrush().getCurrentFrame().getHeight());
            repaint();
        }


        if(e.getSource().equals(pointTypeSelecetor)){
            levelHolder.setPointType(pointTypeSelecetor.getSelectedIndex());
        }

        if(e.getSource().equals(frames)) {
            JComboBox jComboBox = (JComboBox) e.getSource();
            Object item = jComboBox.getSelectedItem();
            if (item != null) {
                int selection = (Integer) jComboBox.getSelectedItem();
                imageHolder.setIcon(new ImageIcon(rawObject.frames.get(selection).image));
                add(imageHolder);
                if (levelHolder.getObjectBrush() != null) {
                    levelHolder.changeBrushFrame(selection);
                }
            }
            repaint();
        }

        if(e.getSource().equals(zoomIn)){
            levelHolder.zoomIn();
            zoom.setText("Z:"+levelHolder.getOrthographicCamera().zoom);
        }
        if(e.getSource().equals(zoomOut)){
            levelHolder.zoomOut();
            zoom.setText("Z:"+levelHolder.getOrthographicCamera().zoom);
        }

        if(e.getSource().equals(toggleGrids)){
            levelHolder.toggleGrids();
        }

        if(e.getSource().equals(toggleLights)){
            levelHolder.toggleLights();
        }

        if(e.getSource().equals(toggleFog)){
            levelHolder.toggleFog();
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource().equals(objectsList)) {
            if (e.getValueIsAdjusting()) {
                File file = objectFilesFiltered.get(objectsList.getSelectedIndex());
                selectedBrush.setText(file.getName());
                setObjectFile(file);
                levelHolder.setObjectBrush(file);
                levelHolder.setPointType(pointTypeSelecetor.getSelectedIndex());
            }
        }

        if (e.getSource().equals(objectNamesList)) {
            if (e.getValueIsAdjusting()) {
                levelHolder.setObjectBrush(objects.get(objectNamesList.getSelectedIndex()));
            }
        }
    }

    private void setObjectFile(File file){
        this.objectFile = file;
        resetFrames();
        loadObject(objectFile);
    }

    private void resetFrames(){
        frames.removeAllItems();
        attributeModel.removeAllElements();
    }

    private void loadObject(File file){
        String absPath = file.getAbsolutePath();
        rawObject = ObjectIO.readObject(absPath);
        for(String key: rawObject.attributes.keySet()){
            String attribute = "";
            attribute+= key;
            attribute+= " ";
            attribute+= rawObject.attributes.get(key).toString();
            attributeModel.addElement(attribute);
        }

        for(int i = 0; i< rawObject.frames.size(); i++){
            frames.addItem(i);
        }
        imageHolder.setIcon(new ImageIcon(rawObject.frames.get(0).image));
        repaint();

    }


    public void loadObjects(String absolutePath){
        directoryPath = absolutePath;
        File[] objectFiles = (new File(absolutePath)).listFiles();
        for(int i = 0; i < objectFiles.length; i++){
            if(objectFiles[i].getName().endsWith(".enjo")) {
                listModel.addElement(objectFiles[i].getName());
                objectFilesFiltered.add(objectFiles[i]);
            }
        }
    }

    public void setTilePositionText(String tile){
        String text = "Tile:" + tile;
        tilePosition.setText(text);
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void clearBrush(){
        levelHolder.clearObjectBrush();
        clearObject();
    }

    private void clearObject(){
        frames.removeAllItems();
        objectsList.clearSelection();
        attributeModel.removeAllElements();
        imageHolder.setIcon(null);
        remove(imageHolder);
        repaint();
    }

    public void addObjects(List<MapObject> objects){
        clearTile();
        this.objects.addAll(objects);
        for(MapObject object: objects){
            objectNames.addElement(object.objectFile.getName().substring(0,object.objectFile.getName().length()-5));
        }
    }

    public void clearTile(){
        objectNames.clear();
        objects.clear();
    }


}
