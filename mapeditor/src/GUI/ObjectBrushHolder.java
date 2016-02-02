package GUI;

import IOUtils.ObjectIO;
import objects.ObjectBase;
import org.lwjgl.Sys;

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
 * Created by Omer on 1/31/2016.
 */
public class ObjectBrushHolder extends JInternalFrame implements ListSelectionListener,ActionListener {
    private JLabel selectedBrush = new JLabel();
    private JButton directory = new JButton("Directory");
    private JLabel tilePosition = new JLabel("Tile:"+"0,0");

    private JScrollPane listHolder = new JScrollPane();
    private JList<String> list = new JList<String>();
    private DefaultListModel<String> listModel = new DefaultListModel<String>();

    private JLabel widthLabel = new JLabel("W:");
    private JLabel heigthLabel = new JLabel("H:");
    private JButton widthUp = new JButton("+");
    private JButton widthDown = new JButton("-");
    private JButton heigthUp = new JButton("+");
    private JButton heigthDown = new JButton("-");
    private JComboBox<Integer> depthSelector = new JComboBox<Integer>();
    private JComboBox<String> pointTypeSelecetor = new JComboBox<String>();

    private File[] objectFiles;

    private LevelHolder levelHolder;
    private ObjectHolder objectHolder;

    public ObjectBrushHolder(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
        super(title, resizable, closable, maximizable, iconifiable);
        setLayout(null);
        setSize(240,200);


        selectedBrush.setBounds(0,0,100,20);
        selectedBrush.setText("------------------");

        tilePosition.setBounds(0,20,100,20);

        directory.setBounds(0,40,100,20);
        directory.addActionListener(this);


        listHolder.setViewportView(list);
        listHolder.setBounds(0,60,100,180);

        list.addListSelectionListener(this);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setModel(listModel);

        heigthDown.setBounds(100,0,45,20);
        heigthLabel.setBounds(145,0,50,20);
        heigthUp.setBounds(195,0,45,20);

        widthDown.setBounds(100,20,45,20);
        widthLabel.setBounds(145,20,50,20);
        widthUp.setBounds(195,20,45,20);

        depthSelector.setBounds(100,50,40,20);

        heigthUp.addActionListener(this);
        heigthDown.addActionListener(this);

        widthUp.addActionListener(this);
        widthDown.addActionListener(this);

        for(int i = 0;i < 20; i++){
            depthSelector.addItem(i);
        }
        depthSelector.addActionListener(this);

        pointTypeSelecetor.setBounds(100,80,140,20);
        pointTypeSelecetor.addItem("To Tile");
        pointTypeSelecetor.addItem("To Absolute Point");
        pointTypeSelecetor.addActionListener(this);


        add(heigthDown);
        add(heigthLabel);
        add(heigthUp);

        add(widthDown);
        add(widthLabel);
        add(widthUp);

        add(depthSelector);
        add(pointTypeSelecetor);

        add(directory);
        add(selectedBrush);
        add(tilePosition);
        add(listHolder);


    }

    public void setLevelHolder(LevelHolder levelHolder){
        this.levelHolder = levelHolder;
    }
    public void setObjectHolder(ObjectHolder objectHolder){this.objectHolder = objectHolder;}


    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList)e.getSource();
        if(!e.getValueIsAdjusting()){
            File file = objectFiles[list.getSelectedIndex()];
            selectedBrush.setText(file.getName());
            objectHolder.setObjectFile(file);
            levelHolder.setObjectBrush(file);
            levelHolder.setActiveLayer(depthSelector.getSelectedIndex());
            levelHolder.setPointType(pointTypeSelecetor.getSelectedIndex());
            System.out.println(depthSelector.getSelectedIndex());
            System.out.println(pointTypeSelecetor.getSelectedIndex());
        }

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
    }


    public void loadObjects(String absolutePath){
        objectFiles = (new File(absolutePath)).listFiles();
        for(int i = 0; i < objectFiles.length; i++){
            listModel.addElement(objectFiles[i].getName());
        }
    }

    public void setTilePositionText(String tile){
        String text = "Tile:" + tile;
        tilePosition.setText(text);
    }
}
