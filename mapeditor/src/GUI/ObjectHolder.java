package GUI;

import IOUtils.ObjectIO;
import objects.ObjectBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Omer on 2/1/2016.
 */
public class ObjectHolder extends JInternalFrame implements ActionListener {
    private JScrollPane attributePane;
    private JList<String> attributeList;
    private DefaultListModel<String> attributeModel;
    private JComboBox<Integer> frames;
    private JLabel imageHolder;

    private File objectFile;
    private ObjectBase objectBase;

    private LevelHolder levelHolder;



    public ObjectHolder(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
        super(title, resizable, closable, maximizable, iconifiable);
        setLayout(null);
        setSize(240,240);
        attributePane = new JScrollPane();
        attributeList = new JList<String>();
        attributeModel = new DefaultListModel<String>();
        attributePane.setBounds(0,0,120,240);
        attributeList.setModel(attributeModel);
        attributePane.setViewportView(attributeList);

        frames = new JComboBox<Integer>();
        frames.setBounds(120,0,40,20);
        frames.addActionListener(this);


        imageHolder = new JLabel();
        imageHolder.setBounds(120,20,100,100);

        add(frames);
        add(attributePane);
        add(imageHolder);


    }

    public void setLevelHolder(LevelHolder levelHolder) {
        this.levelHolder = levelHolder;
    }

    public void setObjectFile(File file){
        this.objectFile = file;
        reset();
        loadObject(objectFile);
    }

    public void reset(){
        frames.removeAllItems();
        attributeModel.removeAllElements();
    }

    private void loadObject(File file){
        String absPath = file.getAbsolutePath();
        objectBase = ObjectIO.readObject(absPath);
        for(String key: objectBase.attributes.keySet()){
            String attribute = "";
            attribute+= key;
            attribute+= " ";
            attribute+= objectBase.attributes.get(key).toString();
            attributeModel.addElement(attribute);
        }

        for(int i = 0; i<objectBase.frames.size();i++){
            frames.addItem(i);
        }
        imageHolder.setIcon(new ImageIcon(objectBase.frames.get(0).image));
        repaint();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox jComboBox = (JComboBox) e.getSource();
        Object item = jComboBox.getSelectedItem();
        if(item!=null) {
            int selection = (Integer) jComboBox.getSelectedItem();
            imageHolder.setIcon(new ImageIcon(objectBase.frames.get(selection).image));
            add(imageHolder);
            if(levelHolder.getObjectBrush()!=null) {
                levelHolder.changeBrushFrame(selection);
            }
        }
        repaint();
    }

    public void clearObject(){
        frames.removeAllItems();
        attributeModel.removeAllElements();
        imageHolder.setIcon(null);
        remove(imageHolder);
        repaint();
    }
}
