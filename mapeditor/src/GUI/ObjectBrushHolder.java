package GUI;

import IOUtils.ObjectIO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Omer on 1/31/2016.
 */
public class ObjectBrushHolder extends JInternalFrame implements ListSelectionListener,ActionListener {
    private JLabel selectedBrush = new JLabel();
    private JButton directory = new JButton("Directory");

    private JScrollPane listHolder = new JScrollPane();
    private JList<String> list = new JList<String>();
    private DefaultListModel<String> listModel = new DefaultListModel<String>();

    File[] objects;

    private LevelHolder levelHolder;
    private ObjectHolder objectHolder;

    public ObjectBrushHolder(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
        super(title, resizable, closable, maximizable, iconifiable);
        setLayout(null);
        setSize(100,200);
        selectedBrush.setBounds(0,0,100,20);
        selectedBrush.setText("------------------");

        directory.setBounds(0,20,100,20);
        directory.addActionListener(this);
        add(directory);

        listHolder.setViewportView(list);
        listHolder.setBounds(0,40,100,180);

        list.addListSelectionListener(this);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setModel(listModel);

        add(selectedBrush);
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
            File file = objects[list.getSelectedIndex()];
            selectedBrush.setText(file.getName());
            objectHolder.setObjectFile(file);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String path;
        JFileChooser loader = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "EnJine2D Files","enjo");
        loader.setFileFilter(filter);
        int lVal = loader.showOpenDialog(this);
        if(lVal ==JFileChooser.APPROVE_OPTION){
            path = loader.getCurrentDirectory().toString();
           objects = (new File(path)).listFiles();
            for(int i = 0; i < objects.length; i++){
                listModel.addElement(objects[i].getName());
            }
            return;
        }
    }
}
