package GUI;

import level.objects.MapObject;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 2/2/2016.
 */
public class TileHolder extends JInternalFrame implements ListSelectionListener{

    private LevelHolder levelHolder;

    private JScrollPane listHolder = new JScrollPane();
    private JList<String> objectNamesList = new JList<String>();
    private DefaultListModel<String> objectNames = new DefaultListModel<String>();
    private List<MapObject> objects = new ArrayList<MapObject>();


    public TileHolder(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
        super(title, resizable, closable, maximizable, iconifiable);
        setLayout(null);
        setSize(120,180);
        listHolder.setBounds(0,0,120,180);
        objectNamesList.setModel(objectNames);
        listHolder.setViewportView(objectNamesList);
        objectNamesList.addListSelectionListener(this);
        add(listHolder);

    }

    public void addObjects(List<MapObject> objects){
        clear();
        this.objects.addAll(objects);
        for(MapObject object: objects){
            objectNames.addElement(object.objectFile.getName().substring(0,object.objectFile.getName().length()-5));
        }
    }

    public void setLevelHolder(LevelHolder levelHolder){
        this.levelHolder = levelHolder;
    }

    public void clear(){
        objectNames.clear();
        objects.clear();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList)e.getSource();
        if(e.getValueIsAdjusting()){
                levelHolder.setObjectBrush(objects.get(list.getSelectedIndex()));
        }
    }
}
